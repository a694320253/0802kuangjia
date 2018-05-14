package com.huitu.alyssachia.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huitu.alyssachia.R;
import com.huitu.alyssachia.base.BaseFragment;


/**
 * Created by l on 2017/7/21.
 */

public class TopicListFragment extends BaseFragment {

    private static final String TAG = TopicListFragment.class.getName();

    SwipeRefreshLayout swipeRefreshLayout;

    private String mTab;

    public static TopicListFragment newInstance(String tab) {
        TopicListFragment fragment = new TopicListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tab", tab);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);//Activity 重绘时，我们的 Fragment 不会被销毁 重绘
        if (getArguments() != null) {
            mTab = getArguments().getString("tab");
        }
    }

    @Override
    public int setContentView() {
        return R.layout.fragment_topic_list;
    }

    @Override
    public void lazyLoad() {

    }

    @Override
    public void initView(View root) {

    }

    @Override
    public void setListeners() {

    }

    @Override
    public void bind() {

    }

    @Override
    public void unBind() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
