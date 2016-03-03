package com.cmbb.smartkids.topic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.framework.api.TopicReplayModel;
import com.cmbb.smartkids.framework.utils.CustomListener;
import com.cmbb.smartkids.framework.widget.wheelview.CustomDialogBuilder;
import com.cmbb.smartkids.photopicker.PhotoViewActivity;
import com.cmbb.smartkids.topic.TopicDetailActivity;
import com.cmbb.smartkids.topic.TopicReplayInnerActivity;
import com.cmbb.smartkids.topic.adapter.TopicReplayAdapter;
import com.javon.library.PtrRecyclerView;
import com.javon.library.PullToRefreshBase;

import java.util.ArrayList;


/**
 */
public class TopicListTwoFragment extends TopicBaseFragment {
    private static final String TOPIC_ID = "topic_id";
    private static final String PAGER = "pager";
    private static final String PAGER_SIZE = "pager_size";


    private PtrRecyclerView prt;


    private CustomListener.FragmentInterface mListener;

    public TopicListTwoFragment() {
        // Required empty public constructor
    }

    public static TopicListTwoFragment newInstance(Parcelable data) {
        TopicListTwoFragment fragment = new TopicListTwoFragment();
        Bundle args = new Bundle();
        args.putParcelable("data", data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topic_list_one, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        addListener();
    }

    private void initData() {
        if (getArguments() != null) {
            topicReplayModel = getArguments().getParcelable("data");
            adapterNext.setData(topicReplayModel);
            prt.setAdapter(adapterNext);
        }
    }

    private void initView() {
        prt = (PtrRecyclerView) getView().findViewById(R.id.pull_refresh_recycler_comment_one);
        adapterNext = new TopicReplayAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        prt.setLayoutManager(manager);
        prt.setMode(PullToRefreshBase.Mode.BOTH);
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

        adapterNext.setOnCommentHeaderListener(onCommentHeaderListener);
        adapterNext.setOnReverListener(onReverListener);
        adapterNext.setOnMoreListener(onMoreListener);
        adapterNext.setOnPreCommentListener(onCommentPreListener);
        adapterNext.setOnReverDeepListener(onDeepReverListener);
        adapterNext.setOnReverDelListener(onReverDelListener);
        adapterNext.setOnPreReverListener(onReverPreListener);
        adapterNext.setOnMoreReverListener(moreReverListener);
        adapterNext.setOnReverNickListener(onCommentNickListener);
        adapterNext.setOnEveryListener(new CustomListener.ItemClickListener() {
            @Override
            public void onItemClick(View v, int position, Object object) {
                ((TopicDetailActivity) getActivity()).clearFocus();
            }
        });
    }


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


    CustomDialogBuilder builder;
    /**
     * 评论更多
     */
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
                                    if (builder != null) builder.setDialogDismiss();
                                    ((TopicDetailActivity) getActivity()).handleDeleteReplayRequest(more.get(0));
                                }
                            })
                            .withCancelText("取消", new CustomListener.DialogListener() {
                                @Override
                                public void onClick(View v) {
                                    if (builder != null) builder.setDialogDismiss();
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
            int id = (int) v.getTag();
            showShortToast("回复你的评论~" + id);
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
           /* Intent intent = new Intent(getActivity(), ReverCommentActivity.class);
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
