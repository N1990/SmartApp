package com.cmbb.smartkids.topic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.framework.api.SpotModel;
import com.cmbb.smartkids.framework.api.TopicDetailModel;
import com.cmbb.smartkids.framework.api.TopicReplayModel;
import com.cmbb.smartkids.framework.api.okhttp.OkHttpClientManager;
import com.cmbb.smartkids.framework.base.BaseApplication;
import com.cmbb.smartkids.framework.utils.CustomListener;
import com.cmbb.smartkids.framework.widget.wheelview.CustomDialogBuilder;
import com.cmbb.smartkids.photopicker.PhotoViewActivity;
import com.cmbb.smartkids.topic.TopicDetailActivity;
import com.cmbb.smartkids.topic.TopicReplayInnerActivity;
import com.cmbb.smartkids.topic.adapter.TopicDetailAdapter;
import com.cmbb.smartkids.topic.model.ImageTagModel;
import com.javon.library.PtrRecyclerView;
import com.javon.library.PullToRefreshBase;
import com.squareup.okhttp.Request;

import java.util.ArrayList;


public class TopicFirstFragment extends TopicBaseFragment {
    private final String TAG = TopicFirstFragment.class.getSimpleName();
    private PtrRecyclerView prt;
    private CustomListener.FragmentInterface mListener;

    public TopicFirstFragment() {
        // Required empty public constructor
    }


    public static TopicFirstFragment newInstance(TopicDetailModel resultDetail, TopicReplayModel resultReplay) {
        TopicFirstFragment fragment = new TopicFirstFragment();
        Bundle args = new Bundle();
        args.putParcelable("resultDetail", resultDetail);
        args.putParcelable("resultReplay", resultReplay);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topic_first, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prt = (PtrRecyclerView) getView().findViewById(R.id.pull_refresh_recycler_comment_first);
        adapterFirst = new TopicDetailAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        prt.setLayoutManager(manager);
        prt.setMode(PullToRefreshBase.Mode.BOTH);
        addListener();
        if (getArguments() != null) {
            topicDetailModel = getArguments().getParcelable("resultDetail");
            topicReplayModel = getArguments().getParcelable("resultReplay");
            ((TopicDetailActivity) getActivity()).setCollectionView(topicDetailModel.getData().getIsCollect());
            ((TopicDetailActivity) getActivity()).setTotal(topicReplayModel.getData().getTotal());
            adapterFirst.setDate(topicDetailModel, topicReplayModel);
            prt.setAdapter(adapterFirst);
        }
    }


    private void addListener() {
        prt.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(final PullToRefreshBase<RecyclerView> refreshView) {
                if (mListener != null)
                    mListener.onUpPager(refreshView);
            }

            @Override
            public void onPullUpToRefresh(final PullToRefreshBase<RecyclerView> refreshView) {
                if (mListener != null)
                    mListener.onDownPager(refreshView);
            }
        });


        adapterFirst.setOnHeaderListener(onHeaderListener);
        adapterFirst.setOnTeletextListener(onTeletextListener);
        adapterFirst.setOnPraiseListener(onPraiseListener);
        adapterFirst.setOnPreUserListener(onUsersPreListener);
        adapterFirst.setOnCommentHeaderListener(onCommentHeaderListener);
        adapterFirst.setOnReverListener(onReverListener);
        adapterFirst.setOnMoreListener(onMoreListener);
        adapterFirst.setOnPreCommentListener(onCommentPreListener);
        adapterFirst.setOnReverDeepListener(onDeepReverListener);
        adapterFirst.setOnReverDelListener(onReverDelListener);
        adapterFirst.setOnPreReverListener(onReverPreListener);
        adapterFirst.setOnMoreReverListener(moreReverListener);
        adapterFirst.setOnReverNickListener(onCommentNickListener);
        adapterFirst.setOnEveryListener(new CustomListener.ItemClickListener() {
            @Override
            public void onItemClick(View v, int position, Object object) {
                ((TopicDetailActivity) getActivity()).clearFocus();
            }
        });
    }


    /**
     * 创建话题人头像
     */
    private View.OnClickListener onHeaderListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int userId = (int) v.getTag();
            /*Intent intent = new Intent(getActivity(), UserCenterActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);*/
        }
    };

    /**
     * 话题图文
     */
    private CustomListener.ItemClickListener onTeletextListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            ImageTagModel imageTagModel = (ImageTagModel) v.getTag();
            PhotoViewActivity.IntentPhotoView(v.getContext(), imageTagModel.getImages(), imageTagModel.getPosition());
        }
    };

    /**
     * 话题点赞
     */
    private View.OnClickListener onPraiseListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final TopicDetailModel data = (TopicDetailModel) v.getTag();
            SpotModel.handlerSpotRequest(data.getData().getId(), data.getData().getIsSpot(), BaseApplication.token, new OkHttpClientManager.ResultCallback<SpotModel>() {
                @Override
                public void onError(Request request, Exception e) {
                    showShortToast(e.toString());
                    switch (data.getData().getIsSpot()) {
                        case 0:
                            topicDetailModel.getData().setIsSpot(0);
                            break;
                        case 1:
                            topicDetailModel.getData().setIsSpot(1);
                            break;
                    }
                    adapterFirst.notifyDataSetChanged();
                }

                @Override
                public void onResponse(SpotModel response) {
                    if (response != null) {
                        showShortToast(response.getMsg());
                        switch (data.getData().getIsSpot()) {
                            case 0:
                                topicDetailModel.getData().setIsSpot(1);
                                topicDetailModel.getData().setSpots(topicDetailModel.getData().getSpots() + 1);
                                //添加
                                if (topicDetailModel.getData().getSpotList().size() == 5) {
                                    topicDetailModel.getData().getSpotList().remove(4);
                                    topicDetailModel.getData().getSpotList().add(0, response.getData());
                                } else {
                                    topicDetailModel.getData().getSpotList().add(0, response.getData());
                                }
                                break;
                            case 1:
                                topicDetailModel.getData().setIsSpot(0);
                                topicDetailModel.getData().setSpots(topicDetailModel.getData().getSpots() - 1);
                                // 删除
                                for (int i = 0; i < topicDetailModel.getData().getSpotList().size(); i++) {
                                    if (topicDetailModel.getData().getSpotList().get(i).getId() == response.getData().getId()) {
                                        topicDetailModel.getData().getSpotList().remove(i);
                                    }
                                }
                                break;
                        }
                        adapterFirst.notifyDataSetChanged();
                    }
                }
            });
        }
    };

    /**
     * 话题用户列表
     */
    private CustomListener.ItemClickListener onUsersPreListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            int userId = (int) v.getTag();
            /*Intent intent = new Intent(getActivity(), UserCenterActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);*/
        }
    };

    /**
     * 评论用户头像
     */
    private CustomListener.ItemClickListener onCommentHeaderListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            int userId = (int) v.getTag();
            /*Intent intent = new Intent(getActivity(), UserCenterActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);*/
        }
    };

    /**
     * 评论用户昵称
     */
    private CustomListener.ItemClickListener onCommentNickListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            TopicReplayModel.DataEntity.RowsEntity.ChildReplyEntity data = (TopicReplayModel.DataEntity.RowsEntity.ChildReplyEntity) v.getTag();
            /*Intent intent = new Intent(getActivity(), UserCenterActivity.class);
            intent.putExtra("userId", data.getUserBasicInfo().getUserId());
            startActivity(intent);*/
        }
    };


    /**
     * 评论回复
     */
    private CustomListener.ItemClickListener onReverListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            // 回复楼中楼
            /*Intent intent = new Intent(getActivity(), ReverCommentActivity.class);
            int replayId = (int) v.getTag();
            intent.putExtra("replayId", replayId);
            startActivity(intent);*/
            TopicReplayInnerActivity.newInstance(getActivity(), (int) v.getTag());

        }
    };


    /**
     * 评论更多
     */
    CustomDialogBuilder builder;
    private CustomListener.ItemClickListener onMoreListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            final ArrayList<Integer> more = (ArrayList<Integer>) object;
            switch (more.get(1)) {
                case 1:// delete
                    if (builder != null)
                        builder.setDialogDismiss();
                    builder = CustomDialogBuilder.getInstance(v.getContext()).withTitle("操作")
                            .withMessage("您确认要删除您的回复吗？")
                            .withComfirmText("确认", new CustomListener.DialogListener() {
                                @Override
                                public void onClick(View v) {
                                    if (builder != null)
                                        builder.setDialogDismiss();
                                    ((TopicDetailActivity) getActivity()).handleDeleteReplayRequest(more.get(0));
                                }
                            })
                            .withCancelText("取消", new CustomListener.DialogListener() {
                                @Override
                                public void onClick(View v) {
                                    if (builder != null)
                                        builder.setDialogDismiss();
                                }
                            });
                    builder.show();

                    break;
                case 0:// report
                    if (builder != null)
                        builder.setDialogDismiss();
                    builder = CustomDialogBuilder.getInstance(v.getContext()).withTitle("操作")
                            .withMessage("您确认要举报此回复吗？")
                            .withComfirmText("确认", new CustomListener.DialogListener() {
                                @Override
                                public void onClick(View v) {
                                    if (builder != null)
                                        builder.setDialogDismiss();
                                    ((TopicDetailActivity) getActivity()).handleReportReplayRequest(more.get(0));
                                }
                            }).withCancelText("取消", new CustomListener.DialogListener() {
                                @Override
                                public void onClick(View v) {
                                    if (builder != null)
                                        builder.setDialogDismiss();
                                }
                            });
                    builder.show();
                    break;
            }
        }
    };


    /**
     * 评论图片预览
     */
    private CustomListener.ItemClickListener onCommentPreListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            String image = (String) v.getTag();
            PhotoViewActivity.IntentPhotoView(v.getContext(), image);

        }
    };

    /**
     * 回复的回复
     */
    private CustomListener.ItemClickListener onDeepReverListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            showShortToast("回复的回复~");
        }
    };

    /**
     * 删除回复内容
     */
    private CustomListener.ItemClickListener onReverDelListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            int replyId = (int) v.getTag();
            ((TopicDetailActivity) getActivity()).handleDeleteReplayRequest(replyId);
        }
    };


    private CustomListener.ItemClickListener onReverPreListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {

            TopicReplayModel.DataEntity.RowsEntity.ChildReplyEntity data = (TopicReplayModel.DataEntity.RowsEntity.ChildReplyEntity) v.getTag();
            String image = data.getImg();
            PhotoViewActivity.IntentPhotoView(v.getContext(), image);
        }
    };


    /**
     * 更多回复内容
     */
    private CustomListener.ItemClickListener moreReverListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            /*Intent intent = new Intent(getActivity(), ReverCommentActivity.class);
            int replayId = (int) v.getTag();
            intent.putExtra("replayId", replayId);
            startActivity(intent);*/
            TopicReplayInnerActivity.newInstance(getActivity(), (int) v.getTag());
        }
    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CustomListener.FragmentInterface) {
            mListener = (CustomListener.FragmentInterface) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        if (builder != null)
            builder.setDialogDismiss();
//        super.onDestroy();
    }


}
