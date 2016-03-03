package com.cmbb.smartkids.topic;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.alibaba.sdk.android.media.upload.UploadListener;
import com.alibaba.sdk.android.media.upload.UploadTask;
import com.alibaba.sdk.android.media.utils.FailReason;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.framework.api.TopicDetailModel;
import com.cmbb.smartkids.framework.api.TopicReplayModel;
import com.cmbb.smartkids.framework.api.okhttp.ImageUpload;
import com.cmbb.smartkids.framework.api.okhttp.OkHttpClientManager;
import com.cmbb.smartkids.framework.base.BaseActivity;
import com.cmbb.smartkids.framework.base.BaseApplication;
import com.cmbb.smartkids.framework.base.Constants;
import com.cmbb.smartkids.framework.utils.CustomListener;
import com.cmbb.smartkids.framework.utils.JsonParser;
import com.cmbb.smartkids.framework.utils.ShareUtils;
import com.cmbb.smartkids.framework.utils.TDevice;
import com.cmbb.smartkids.framework.utils.XunFeiUtils;
import com.cmbb.smartkids.photopicker.PhotoPickerActivity;
import com.cmbb.smartkids.photopicker.utils.PhotoPickerIntent;
import com.cmbb.smartkids.topic.fragment.TopicBaseFragment;
import com.cmbb.smartkids.topic.fragment.TopicFirstFragment;
import com.cmbb.smartkids.topic.fragment.TopicListOneFragment;
import com.cmbb.smartkids.topic.fragment.TopicListTwoFragment;
import com.cmbb.smartkids.topic.popWindows.CommentPop;
import com.facebook.drawee.view.SimpleDraweeView;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.javon.library.PullToRefreshBase;
import com.squareup.okhttp.Request;
import com.umeng.socialize.sso.UMSsoHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class TopicDetailActivity extends BaseActivity implements CustomListener.FragmentInterface {

    private final String TAG = TopicDetailActivity.class.getSimpleName();

    private FrameLayout flContainer;
    private ImageView ivSkip;
    private EditText etRever;
    private LinearLayout llBottom;
    private RelativeLayout rlBottomRever;
    private RelativeLayout root;

    private SimpleDraweeView ivImage;
    private ImageView tvSpeak;
    private TextView tvSend;

    private LinearLayout llSkip;
    private TextView tvSkip, tvSb;
    private SeekBar sbSkip;
    private ImageView ivCollect, ivMore;
    private CommentPop popTitle;

    private int progress;
    private int topicId;
    public int total;
    private String from;

    //分页
    private int pager = 0;
    private int pageSize = 10;

    private TopicBaseFragment baseFragment;
    private final int PIC_REQUEST_CODE = 1001;

    private TopicReplayModel cacheReplay;
    private TopicDetailModel cacheDetail;

    private ProgressDialog progressDialog;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_topic_detail;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ((TextView) findViewById(R.id.tv_title)).setText("话题详情");
        initView();
        initData();
        addListener();
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
        sbSkip.setMax(total - 1);
        sbSkip.setProgress(pager);
    }

    private void initView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在加载中...");
        root = (RelativeLayout) findViewById(R.id.rl_root);
        flContainer = (FrameLayout) findViewById(R.id.container);
        ivSkip = (ImageView) findViewById(R.id.iv_community_skip);
        etRever = (EditText) findViewById(R.id.et_community_rever);
        llBottom = (LinearLayout) findViewById(R.id.ll_community_bottom);
        rlBottomRever = (RelativeLayout) findViewById(R.id.rl_community_comment_rever);
        llSkip = (LinearLayout) findViewById(R.id.ll_community_detail_skip);
        tvSkip = (TextView) findViewById(R.id.tv_community_detail_skip);
        tvSb = (TextView) findViewById(R.id.tv_sb_community_detail);
        sbSkip = (SeekBar) findViewById(R.id.sb_community_detail_skip);
        ivCollect = (ImageView) findViewById(R.id.iv_community_collect_right);
        ivMore = (ImageView) findViewById(R.id.iv_community_more_right);
        ivImage = (SimpleDraweeView) findViewById(R.id.iv_community_comment_rever_upload);
        tvSend = (TextView) findViewById(R.id.tv_community_comment_rever_publish);
        tvSpeak = (ImageView) findViewById(R.id.tv_speak);
    }

    private void initData() {
        topicId = getIntent().getIntExtra("id", -1);
        from = getIntent().getStringExtra("from");
        ShareUtils.instanceOf(this);
        ShareUtils.configPlatforms();
        Log.e("TopicId", "Topic ID = " + topicId);
        showWaitDialog();
        handleInitData(null);
    }

    private void showWaitDialog() {
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    private void hideWaitDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }


    private void handleInitData(final PullToRefreshBase<RecyclerView> refreshView) {

        TopicDetailModel.getTopicDetailRequest(topicId, BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicDetailModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(TopicDetailModel response) {
                if (response != null) {
                    cacheDetail = (TopicDetailModel) response;
                    if (cacheReplay != null && cacheDetail != null) {
                        hideWaitDialog();
                        if (cacheDetail.getData().getTopicImgList().size() != 0) {
                            ShareUtils.setShareContent(cacheDetail.getData().getTitle(), cacheDetail.getData().getContents(), Constants.Share.getTopicShareUrl(cacheDetail.getData().getId()), cacheDetail.getData().getTopicImgList().get(0).getImg());
                        } else {
                            ShareUtils.setShareContent(cacheDetail.getData().getTitle(), cacheDetail.getData().getContents(), Constants.Share.getTopicShareUrl(cacheDetail.getData().getId()), R.mipmap.ic_launcher);
                        }
                        if (cacheDetail.getData().getUserBasicInfo().getIsLoginUser() == 1) {
                            ivCollect.setVisibility(View.GONE);
                        } else {
                            ivCollect.setVisibility(View.VISIBLE);
                        }

                        setPopTitle(cacheDetail.getData().getUserBasicInfo().getIsLoginUser());
                        baseFragment = TopicFirstFragment.newInstance(cacheDetail, cacheReplay);
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, baseFragment);
                        transaction.commitAllowingStateLoss();
                        if (refreshView != null) {
                            refreshView.onRefreshComplete();
                        }
                    }
                }
            }
        });


        TopicReplayModel.getReplayListRequest(topicId, 1, pager, pageSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicReplayModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(TopicReplayModel response) {
                if (response != null) {
                    cacheReplay = (TopicReplayModel) response;
                    if (cacheReplay != null && cacheDetail != null) {
                        hideWaitDialog();
                        if (cacheDetail.getData().getTopicImgList().size() != 0) {
                            ShareUtils.setShareContent(cacheDetail.getData().getTitle(), cacheDetail.getData().getContents(), Constants.Share.getTopicShareUrl(cacheDetail.getData().getId()), cacheDetail.getData().getTopicImgList().get(0).getImg());
                        } else {
                            ShareUtils.setShareContent(cacheDetail.getData().getTitle(), cacheDetail.getData().getContents(), Constants.Share.getTopicShareUrl(cacheDetail.getData().getId()), R.mipmap.ic_launcher);
                        }
                        if (cacheDetail.getData().getUserBasicInfo().getIsLoginUser() == 1) {
                            ivCollect.setVisibility(View.GONE);
                        } else {
                            ivCollect.setVisibility(View.VISIBLE);
                        }
                        setPopTitle(cacheDetail.getData().getUserBasicInfo().getIsLoginUser());
                        baseFragment = TopicFirstFragment.newInstance(cacheDetail, cacheReplay);

                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, baseFragment);
                        transaction.commitAllowingStateLoss();
                        if (refreshView != null) {
                            refreshView.onRefreshComplete();
                        }
                    }
                }
            }
        });
    }


    private void addListener() {
        tvSpeak.setOnClickListener(this);
        ivSkip.setOnClickListener(this);
        ivMore.setOnClickListener(this);
        ivCollect.setOnClickListener(this);
        tvSkip.setOnClickListener(this);
        sbSkip.setOnSeekBarChangeListener(sbListener);

        tvSend.setOnClickListener(this);
        ivImage.setOnClickListener(this);
        etRever.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    etRever.setMinHeight(TDevice.dip2px(100, TopicDetailActivity.this));
                    rlBottomRever.setVisibility(View.VISIBLE);
                    ivSkip.setVisibility(View.GONE);
                    llSkip.setVisibility(View.GONE);
                } else {
                    etRever.setMinHeight(TDevice.dip2px(36, TopicDetailActivity.this));
                    rlBottomRever.setVisibility(View.GONE);
                    ivSkip.setVisibility(View.VISIBLE);
                    llSkip.setVisibility(View.GONE);
                }
            }
        });
    }


    private CustomListener.ItemClickListener onPopItemDeleteListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            popTitle.dismiss();
            switch (position) {
                case 0://分享
                    ShareUtils.showShareView();
                    break;
                case 1://删除

                    TopicReplayModel.handleDeleteRequest(topicId, BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicReplayModel>() {
                        @Override
                        public void onError(Request request, Exception e) {
                            showShortToast(e.toString());
                        }

                        @Override
                        public void onResponse(TopicReplayModel response) {
                            if (response != null) {
                                showShortToast(response.getMsg());
                                setResult(RESULT_OK);
                                finish();
                            }
                        }
                    });
                    break;
            }

        }
    };
    private CustomListener.ItemClickListener onPopItemReportListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            popTitle.dismiss();
            switch (position) {
                case 0://分享
                    ShareUtils.showShareView();
                    break;
                case 1://举报
                    TopicReplayModel.handleReportRequest(topicId, BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicReplayModel>() {
                        @Override
                        public void onError(Request request, Exception e) {
                            showShortToast(e.toString());
                        }

                        @Override
                        public void onResponse(TopicReplayModel response) {
                            showShortToast(response.getMsg());
                        }
                    });
                    break;
            }

        }
    };

    private SeekBar.OnSeekBarChangeListener sbListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            TopicDetailActivity.this.progress = progress;
            int showProgress = progress + 1;
            tvSb.setText(showProgress + "/" + total);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    @Override
    protected void onDestroy() {
        cancelRequest();
        baseFragment.topicDetailModel = null;
        baseFragment.topicReplayModel = null;
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_speak:
                XunFeiUtils.getInstance(this).startVoice(new RecognizerDialogListener() {
                    @Override
                    public void onResult(RecognizerResult recognizerResult, boolean b) {
                        printResult(recognizerResult, etRever);
                    }

                    @Override
                    public void onError(SpeechError speechError) {

                    }
                });
                break;
            case R.id.iv_community_more_right:
                if (null != popTitle) {
                    if (popTitle.isShowing()) {
                        popTitle.dismiss();
                    } else {
                        popTitle.showPop(ivMore, -popTitle.getWidth() - TDevice.dip2px(18, TopicDetailActivity.this), TDevice.dip2px(18, TopicDetailActivity.this));
                    }
                }
                break;
            case R.id.iv_community_collect_right:

                TopicReplayModel.handleCollectionRequest(topicId, baseFragment.topicDetailModel.getData().getIsCollect(), BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicReplayModel>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        showShortToast(e.toString());
                        switch (baseFragment.topicDetailModel.getData().getIsCollect()) {
                            case 0:
                                baseFragment.topicDetailModel.getData().setIsCollect(0);
                                break;
                            case 1:
                                baseFragment.topicDetailModel.getData().setIsCollect(1);
                                break;
                        }
                    }

                    @Override
                    public void onResponse(TopicReplayModel response) {
                        if (response != null) {
                            showShortToast(response.getMsg());
                            switch (baseFragment.topicDetailModel.getData().getIsCollect()) {
                                case 0:
                                    baseFragment.topicDetailModel.getData().setIsCollect(1);
                                    setCollectionView(1);
                                    break;
                                case 1:
                                    baseFragment.topicDetailModel.getData().setIsCollect(0);
                                    setCollectionView(0);
                                    break;
                            }
                        }
                    }
                });

                break;
            case R.id.iv_community_skip:
                if (llSkip.getVisibility() == View.VISIBLE) {
                    llSkip.setVisibility(View.GONE);
                } else {
                    llSkip.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_community_detail_skip:
                if (pager > progress) {
                    upPager(progress, null);
                } else if (pager < progress) {
                    dowmPager(progress, null);
                }
                pager = progress;
                break;

            case R.id.iv_community_comment_rever_upload://选取图片
                PhotoPickerIntent intent = new PhotoPickerIntent(this);
                intent.setPhotoCount(1);
                startActivityForResult(intent, PIC_REQUEST_CODE);
                break;
            case R.id.tv_community_comment_rever_publish://发送
                if (TextUtils.isEmpty(etRever.getText().toString()) && (tempImages == null || tempImages.size() == 0)) {
                    showShortToast("请输入回复内容");
                } else if (tempImages.size() > 0) {
                    showWaitDialog();
                    ImageUpload.getInstance().uploadImages(this, tempImages, new UploadListener() {
                        @Override
                        public void onUploading(UploadTask uploadTask) {

                        }

                        @Override
                        public void onUploadFailed(UploadTask uploadTask, FailReason failReason) {
                            hideWaitDialog();
                            showShortToast(failReason.getMessage());
                        }

                        @Override
                        public void onUploadComplete(UploadTask uploadTask) {
                            try {
                                JSONObject object = new JSONObject(uploadTask.getResult().message);
                                String url = object.optString("url");
                                String width = object.optString("returnBody").split("_")[0];
                                String height = object.optString("returnBody").split("_")[1];
                                String content = etRever.getText().toString();
                                handleReplayTopRequest(topicId, 0, content, url, width, height);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onUploadCancelled(UploadTask uploadTask) {

                        }
                    });
                } else {
                    handleReplayTopRequest(topicId, 0, etRever.getText().toString(), null, null, null);
                }
                break;
        }
    }

    @Override
    public void onDownPager(PullToRefreshBase<RecyclerView> refreshView) {
        if (pager >= total - 1) {
            showShortToast("最后一页啦！");
            if (refreshView != null)
                refreshView.onRefreshComplete();
            return;
        }
        pager++;
        dowmPager(pager, refreshView);
        progress = pager;
        sbSkip.setProgress(progress);
        int showProgress = progress + 1;
        tvSb.setText(showProgress + "/" + total);
    }

    @Override
    public void onUpPager(PullToRefreshBase<RecyclerView> refreshView) {
        pager--;
        if (pager < 0) {
            pager = 0;
            progress = pager;
            sbSkip.setProgress(progress);
            int showProgress = progress + 1;
            tvSb.setText(showProgress + "/" + total);
            // 刷新
            handleInitData(refreshView);
        } else {
            upPager(pager, refreshView);
            progress = pager;
            sbSkip.setProgress(progress);
            int showProgress = progress + 1;
            tvSb.setText(showProgress + "/" + total);
        }
    }

    @Override
    public void showKeyboard() {
        etRever.setFocusable(true);
    }

    /**
     * 设置POP
     *
     * @param currentUser int
     */
    private void setPopTitle(int currentUser) {
        switch (currentUser) {
            case 1:
                popTitle = new CommentPop(TopicDetailActivity.this, new int[]{R.mipmap.btn_share_bg, R.mipmap.btn_community_comment_del_bg}, new String[]{"分享", "删除"});
                popTitle.setOnItemListener(onPopItemDeleteListener);
                break;
            case 0:
                popTitle = new CommentPop(TopicDetailActivity.this, new int[]{R.mipmap.btn_share_bg, R.mipmap.btn_report_bg}, new String[]{"分享", "举报"});
                popTitle.setOnItemListener(onPopItemReportListener);
                break;
        }
    }


    /**
     * 向下翻页
     *
     * @param pager
     */
    private void upPager(final int pager, final PullToRefreshBase<RecyclerView> refreshView) {
        if (pager == 0) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            baseFragment = TopicFirstFragment.newInstance(cacheDetail, cacheReplay);
            transaction.setCustomAnimations(R.anim.slide_in_from_top, R.anim.slide_out_to_bottom);
            transaction.replace(R.id.container, baseFragment);
            transaction.commitAllowingStateLoss();
        } else {
            TopicReplayModel.getReplayListRequest(topicId, 1, pager, pageSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicReplayModel>() {
                @Override
                public void onError(Request request, Exception e) {
                    showShortToast(e.toString());
                    if (refreshView != null)
                        refreshView.onRefreshComplete();
                }

                @Override
                public void onResponse(TopicReplayModel response) {
                    if (response != null) {
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        baseFragment = TopicListTwoFragment.newInstance(response);
                        transaction.setCustomAnimations(R.anim.slide_in_from_top, R.anim.slide_out_to_bottom);
                        transaction.replace(R.id.container, baseFragment);
                        transaction.commitAllowingStateLoss();
                        if (refreshView != null)
                            refreshView.onRefreshComplete();
                    }
                }
            });
        }
    }

    /**
     * 想上翻页
     */
    private void dowmPager(int pager, final PullToRefreshBase<RecyclerView> refreshView) {
        TopicReplayModel.getReplayListRequest(topicId, 1, pager, pageSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicReplayModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
                if (refreshView != null)
                    refreshView.onRefreshComplete();
            }

            @Override
            public void onResponse(TopicReplayModel response) {
                if (response != null) {
                    if (response.getData().getRows().size() > 0) {
                        setTotal(response.getData().getTotal());
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        baseFragment = TopicListOneFragment.newInstance(response);
                        transaction.setCustomAnimations(R.anim.slide_in_from_bottom, R.anim.slide_out_to_top);
                        transaction.replace(R.id.container, baseFragment);
                        transaction.commitAllowingStateLoss();
                    } else {
                        showShortToast("最后一页啦");
                    }

                    if (refreshView != null) refreshView.onRefreshComplete();
                }
            }
        });
    }


    /**
     * 设置收藏界面
     *
     * @param isCollect int
     */
    public void setCollectionView(int isCollect) {
        switch (isCollect) {
            case 0:
                ivCollect.setBackgroundResource(R.mipmap.btn_community_collect_normal);
                break;
            case 1:
                ivCollect.setBackgroundResource(R.mipmap.btn_community_collect_pressed);
                break;
        }
    }


    /**
     * 回复楼主
     *
     * @param topicId   话题ID
     * @param replyType 回复类型
     * @param contents  回复内容
     * @param img       图片地址
     * @param imgWidth  图片宽度
     * @param imgHeight 图片高度
     */
    private void handleReplayTopRequest(int topicId, int replyType, String contents, String img, String imgWidth, String imgHeight) {
        showWaitDialog();
        TopicReplayModel.handleReplayRequest(topicId, replyType, contents, img, imgWidth, imgHeight, BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicReplayModel>() {
            @Override
            public void onError(Request request, Exception e) {
                hideWaitDialog();
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(TopicReplayModel response) {
                if (response != null) {
                    handleRefreshCurrentPage();
                    hideWaitDialog();
                    showShortToast(response.getMsg());
                    etRever.clearFocus();
                    // 置空
                    etRever.setText("");
                    ivImage.setImageURI(null);
                }
            }
        });
    }

    /**
     * 刷新当前页面
     */
    private void handleRefreshCurrentPage() {
        TopicReplayModel.getReplayListRequest(topicId, 1, pager, pageSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicReplayModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(TopicReplayModel response) {
                setTotal(response.getData().getTotal());
                baseFragment.updateCurrentFragment(response);
            }
        });
    }


    /**
     * 删除评论
     *
     * @param replyId 删除ID
     */
    public void handleDeleteReplayRequest(int replyId) {

        TopicReplayModel.handleDeleteRequest(replyId, BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicReplayModel>() {
            @Override
            public void onError(Request request, Exception e) {
                hideWaitDialog();
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(TopicReplayModel response) {
                if (response != null) {
                    handleRefreshCurrentPage();
                    hideWaitDialog();
                    showShortToast(response.getMsg());
                }
            }
        });
    }

    /**
     * 举报
     *
     * @param replyId 举报ID
     */
    public void handleReportReplayRequest(int replyId) {

        TopicReplayModel.handleReportRequest(replyId, BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicReplayModel>() {
            @Override
            public void onError(Request request, Exception e) {
                hideWaitDialog();
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(TopicReplayModel response) {
                if (response != null) {
                    handleRefreshCurrentPage();
                    hideWaitDialog();
                    showShortToast(response.getMsg());
                }
            }
        });
    }

    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();

    /**
     * 语音输出
     *
     * @param results     RecognizerResult
     * @param mResultText EditText对象
     */
    private void printResult(RecognizerResult results, TextView mResultText) {
        String text = JsonParser.parseIatResult(results.getResultString());
        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mIatResults.put(sn, text);
        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }
        mResultText.setText(resultBuffer.toString());
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onBackPressed() {
        if (!TextUtils.isEmpty(from)) {
            setResult(RESULT_OK);
            finish();
        } else {
            super.onBackPressed();
        }
    }

    private ArrayList<String> tempImages = new ArrayList<>();

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        UMSsoHandler ssoHandler = ShareUtils.mController.getConfig().getSsoHandler(requestCode);
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
        if (resultCode == -1 && requestCode == PIC_REQUEST_CODE) {
            if (data != null) {
                tempImages = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                ivImage.setImageURI(Uri.parse("file://" + tempImages.get(0)));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            etRever.clearFocus();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void clearFocus() {
        etRever.clearFocus();
    }

    /**
     * 启动
     * @param context
     * @param id
     */
    public static void newInstance(Context context, int id) {
        Intent intent = new Intent(context, TopicDetailActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    /**
     * @param context Context ForResutl
     * @param id      话题ID
     * @param from    收藏位置启动
     */
    public static void newInstance(AppCompatActivity context, int id, String from) {
        /*Intent intent = new Intent(context, TopicDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("from", from);
        context.startActivityForResult(intent, COMMUNITY_DETAIL_REQUEST);*/
    }

}
