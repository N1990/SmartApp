package com.cmbb.smartkids.topic.fragment;

import android.support.v4.app.Fragment;

import com.cmbb.smartkids.framework.api.TopicDetailModel;
import com.cmbb.smartkids.framework.api.TopicReplayModel;
import com.cmbb.smartkids.topic.adapter.TopicDetailAdapter;
import com.cmbb.smartkids.topic.adapter.TopicReplayAdapter;

import java.lang.reflect.Field;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/13 下午5:34
 */
public class TopicBaseFragment extends BaseFragment {

    public static TopicDetailModel topicDetailModel;
    public static TopicReplayModel topicReplayModel;
    public TopicDetailAdapter adapterFirst;
    public TopicReplayAdapter adapterNext;


    public void updateCurrentFragment(TopicReplayModel topicReplayModel) {
        if (adapterFirst != null) {
            adapterFirst.updateData(topicReplayModel);
        }
        if (adapterNext != null) {
            adapterNext.updateData(topicReplayModel);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
