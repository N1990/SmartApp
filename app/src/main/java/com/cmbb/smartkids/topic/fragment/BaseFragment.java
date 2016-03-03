package com.cmbb.smartkids.topic.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.cmbb.smartkids.framework.base.BaseActivity;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/11 10:08
 */
public class BaseFragment extends Fragment implements View.OnClickListener {
    private final String TAG = BaseFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    protected void showToast(String tip) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showToast(tip);
        }
    }

    protected void showShortToast(String tip) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showShortToast(tip);
        }
    }

    protected void cancelRequest() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).cancelRequest();
        }
    }


    @Override
    public void onClick(View v) {

    }
}
