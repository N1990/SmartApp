package com.cmbb.smartkids.topic;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.sdk.android.media.upload.UploadListener;
import com.alibaba.sdk.android.media.upload.UploadTask;
import com.alibaba.sdk.android.media.utils.FailReason;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.framework.api.TopicReplayDetailListModel;
import com.cmbb.smartkids.framework.api.TopicReplayDetailModel;
import com.cmbb.smartkids.framework.api.okhttp.ImageUpload;
import com.cmbb.smartkids.framework.api.okhttp.OkHttpClientManager;
import com.cmbb.smartkids.framework.base.BaseApplication;
import com.cmbb.smartkids.framework.base.SmartActivity;
import com.cmbb.smartkids.framework.utils.CustomListener;
import com.cmbb.smartkids.framework.utils.Fresco;
import com.cmbb.smartkids.framework.utils.TDevice;
import com.cmbb.smartkids.framework.utils.date.JTimeTransform;
import com.cmbb.smartkids.framework.utils.date.RecentDateFormat;
import com.cmbb.smartkids.framework.utils.log.Log;
import com.cmbb.smartkids.photopicker.PhotoPickerActivity;
import com.cmbb.smartkids.photopicker.PhotoViewActivity;
import com.cmbb.smartkids.photopicker.utils.PhotoPickerIntent;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.cmbb.smartkids.topic.adapter.TopicReplayDetailListAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/3 上午10:46
 */
public class TopicReplayInnerActivity extends SmartActivity {

    private static final String TAG = TopicReplayInnerActivity.class.getSimpleName();

    //headView
    private SimpleDraweeView header;
    private SimpleDraweeView ivContent;
    private ImageView ivComment, ivMore;
    private TextView tvName, tvTime, tvIdentify, tvContent;

    private EditText etRever;
    private TextView tvSend;
    private SimpleDraweeView ivImage;
    private RelativeLayout rlBottomRever;

    private ProgressDialog progressDialog;

    private int pageNo;
    private int pageSize = 20;

    @Override
    protected boolean initAdapter() {
        adapter = new TopicReplayDetailListAdapter(this);
        ((TopicReplayDetailListAdapter) adapter).setOnReverPreListener(onReverPreListener);
        ((TopicReplayDetailListAdapter) adapter).setOnReverDeleteListener(onReverDeleteListener);
        return true;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ((TextView) findViewById(R.id.tv_title)).setText("评论回复");
        initView();
        initRecyclerView();
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                RelativeLayout header = (RelativeLayout) LayoutInflater.from(TopicReplayInnerActivity.this).inflate(R.layout.activity_replay_detail_head, null);
                header.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                return header;
            }

            @Override
            public void onBindView(View itemView) {
                header = (SimpleDraweeView) itemView.findViewById(R.id.iv_community_comment_item);
                ivContent = (SimpleDraweeView) itemView.findViewById(R.id.iv_community_comment_content_item);
                ivComment = (ImageView) itemView.findViewById(R.id.iv_community_comment_perssion_item);
                ivMore = (ImageView) itemView.findViewById(R.id.iv_community_comment_more_item);
                tvName = (TextView) itemView.findViewById(R.id.tv_community_comment_nickname_item);
                tvTime = (TextView) itemView.findViewById(R.id.tv_community_comment_time_item);
                tvIdentify = (TextView) itemView.findViewById(R.id.tv_community_comment_tag_item);
                tvContent = (TextView) itemView.findViewById(R.id.tv_community_comment_content_item);
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                etRever.clearFocus();
            }
        });
        initHead();
        onRefresh();
    }

    private void initView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在提交...");
        etRever = (EditText) findViewById(R.id.et_comment_rever);
        ivImage = (SimpleDraweeView) findViewById(R.id.iv_community_comment_rever_upload);
        tvSend = (TextView) findViewById(R.id.tv_community_comment_rever_publish);
        rlBottomRever = (RelativeLayout) findViewById(R.id.rl_community_comment_rever);

        etRever.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    etRever.setMinHeight(TDevice.dip2px(100, TopicReplayInnerActivity.this));
                    rlBottomRever.setVisibility(View.VISIBLE);
                } else {
                    etRever.setMinHeight(TDevice.dip2px(36, TopicReplayInnerActivity.this));
                    rlBottomRever.setVisibility(View.GONE);
                }
            }
        });
        tvSend.setOnClickListener(this);
        ivImage.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_community_comment_rever_upload://选取图片
                PhotoPickerIntent intent = new PhotoPickerIntent(this);
                intent.setPhotoCount(1);
                startActivityForResult(intent, PIC_REQUEST_CODE);
                break;
            case R.id.tv_community_comment_rever_publish:
                // 发送评论
                if (TextUtils.isEmpty(etRever.getText().toString()) && (tempImages == null || tempImages.size() == 0)) {
                    showShortToast("请输入回复内容");
                } else if (tempImages.size() > 0) {
                    progressDialog.show();
                    ImageUpload.getInstance().uploadImages(this, tempImages, new UploadListener() {
                        @Override
                        public void onUploading(UploadTask uploadTask) {

                        }

                        @Override
                        public void onUploadFailed(UploadTask uploadTask, FailReason failReason) {
                            progressDialog.dismiss();
                            showShortToast(failReason.getMessage());
                        }

                        @Override
                        public void onUploadComplete(UploadTask uploadTask) {
                            try {
                                JSONObject object = new JSONObject(uploadTask.getResult().message);
                                String url = object.optString("url");
                                String width = object.optString("returnBody").split("_")[0];
                                String height = object.optString("returnBody").split("_")[1];

                                TopicReplayDetailModel.handReplayRequest(getIntent().getIntExtra("replayId", -1), etRever.getText().toString(), url, width, height, BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicReplayDetailModel>() {
                                    @Override
                                    public void onError(Request request, Exception e) {
                                        progressDialog.dismiss();
                                        showShortToast(e.toString());
                                    }

                                    @Override
                                    public void onResponse(TopicReplayDetailModel response) {
                                        progressDialog.dismiss();
                                        showShortToast(response.getMsg());
                                        etRever.clearFocus();
                                        etRever.setText("");
                                        ivImage.setImageURI(null);
                                        pageNo = 0;
                                        onRefresh();
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onUploadCancelled(UploadTask uploadTask) {

                        }
                    });
                } else {
                    progressDialog.show();
                    TopicReplayDetailModel.handReplayRequest(getIntent().getIntExtra("replayId", -1), etRever.getText().toString(), "", "", "", BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicReplayDetailModel>() {
                        @Override
                        public void onError(Request request, Exception e) {
                            progressDialog.dismiss();
                            showShortToast(e.toString());
                        }

                        @Override
                        public void onResponse(TopicReplayDetailModel response) {
                            progressDialog.dismiss();
                            showShortToast(response.getMsg());
                            etRever.clearFocus();
                            etRever.setText("");
                            ivImage.setImageURI(null);
                            pageNo = 0;
                            onRefresh();
                        }
                    });
                }
                break;
        }
    }

    private void initHead() {
        TopicReplayDetailModel.getReplayDetailRequest(getIntent().getIntExtra("replayId", -1), BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicReplayDetailModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(TopicReplayDetailModel data) {
                if (data != null) {
                    // 设置数据
                    Fresco.loadImage(header, data.getData().getUserBasicInfo().getUserSmallImg(), data.getData().getUserBasicInfo().getUserSmallWidth() + "", data.getData().getUserBasicInfo().getUserSmallHeight() + "");
                    if (TextUtils.isEmpty(data.getData().getImg())) {
                        ivContent.setVisibility(View.GONE);
                    } else {
                        ivContent.setVisibility(View.VISIBLE);
                        Fresco.loadImage(ivContent, data.getData().getImg(), TDevice.dip2px(200, ivComment.getContext()) + "");
                    }
                    tvName.setText(data.getData().getUserBasicInfo().getUserNike());
                    tvContent.setText(data.getData().getContents());
                    tvIdentify.setText(data.getData().getUserBasicInfo().getUserRole().get(0).getEredarName());
                    tvTime.setText("第" + data.getData().getReplysFloor() + "楼  " + new JTimeTransform(data.getData().getCreateDate()).toString(new RecentDateFormat()));

                    header.setTag(data.getData().getUserBasicInfo().getUserId());
                    header.setOnClickListener(headClick);
                    tvName.setTag(data.getData().getUserBasicInfo().getUserId());
                    tvName.setOnClickListener(headClick);
//                    ivContent.setTag(data.getData().getImg());
//                    ivContent.setOnClickListener(onEveryListener);
                    ivComment.setVisibility(View.GONE);
//                    ivComment.setOnClickListener(onEveryListener);
                    ivMore.setTag(data);
                    ivMore.setOnClickListener(detailMore);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_topic_replay_inner_layout;
    }

    @Override
    public void onLoadMore() {
        pageNo++;
        TopicReplayDetailListModel.handleDetailListReplayRequest(getIntent().getIntExtra("replayId", -1), pageNo, pageSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicReplayDetailListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(TopicReplayDetailListModel response) {
                if (response != null) {
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        pageNo = 0;
        TopicReplayDetailListModel.handleDetailListReplayRequest(getIntent().getIntExtra("replayId", -1), pageNo, pageSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicReplayDetailListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(TopicReplayDetailListModel response) {
                if (response != null) {
                    if (response.getData().getRows().size() > 0) {
                        adapter.clear();
                    }
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }

    // 头像点击
    private View.OnClickListener headClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };


    /**
     * 删除或者举报
     */
    private View.OnClickListener detailMore = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final TopicReplayDetailModel data = (TopicReplayDetailModel) v.getTag();
            switch (data.getData().getUserBasicInfo().getIsLoginUser()) {
                case 1:// delete
                    new AlertDialog.Builder(TopicReplayInnerActivity.this)
                            .setTitle("提示")
                            .setMessage("您确认要删除您的回复吗？")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    TopicReplayDetailModel.getReplayDeleteRequest(data.getData().getId(), BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicReplayDetailModel>() {
                                        @Override
                                        public void onError(Request request, Exception e) {
                                            showShortToast(e.toString());
                                        }

                                        @Override
                                        public void onResponse(TopicReplayDetailModel response) {
                                            if (response != null) {
                                                showShortToast(response.getMsg());
                                                finish();
                                            }
                                        }
                                    });

                                }
                            })
                            .create().show();
                    break;
                case 0://
                    // report
                    new AlertDialog.Builder(TopicReplayInnerActivity.this)
                            .setTitle("提示")
                            .setMessage("您确认要举报此回复吗？")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    TopicReplayDetailModel.getReplayReportRequest(data.getData().getId(), BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicReplayDetailModel>() {
                                        @Override
                                        public void onError(Request request, Exception e) {
                                            showShortToast(e.toString());
                                        }

                                        @Override
                                        public void onResponse(TopicReplayDetailModel response) {
                                            if (response != null) {
                                                showShortToast(response.getMsg());
                                            }
                                        }
                                    });
                                }
                            })
                            .create().show();
                    break;
            }
        }
    };


    private CustomListener.ItemClickListener onReverPreListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            String image = (String) v.getTag();
            PhotoViewActivity.IntentPhotoView(v.getContext(), image);

        }
    };

    /**
     * 删除回复内容
     */
    private CustomListener.ItemClickListener onReverDeleteListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            final int replyId = (int) v.getTag();

            new AlertDialog.Builder(TopicReplayInnerActivity.this)
                    .setTitle("提示")
                    .setMessage("您确认要删除您的回复吗？")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            TopicReplayDetailModel.getReplayDeleteRequest(replyId, BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicReplayDetailModel>() {
                                @Override
                                public void onError(Request request, Exception e) {
                                    showShortToast(e.toString());
                                }

                                @Override
                                public void onResponse(TopicReplayDetailModel response) {
                                    if (response != null) {
                                        showShortToast(response.getMsg());
                                        onRefresh();
                                    }
                                }
                            });

                        }
                    })
                    .create().show();
        }
    };


    private ArrayList<String> tempImages = new ArrayList<>();
    private final int PIC_REQUEST_CODE = 1001;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1 && requestCode == PIC_REQUEST_CODE) {
            if (data != null) {
                tempImages = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                Log.e("ImagePath", "ImagePaTH = " + tempImages.get(0));
                ivImage.setImageURI(Uri.parse("file://" + tempImages.get(0)));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    public static void newInstance(Context context, int replayId) {
        Intent intent = new Intent(context, TopicReplayInnerActivity.class);
        intent.putExtra("replayId", replayId);
        context.startActivity(intent);
    }
}
