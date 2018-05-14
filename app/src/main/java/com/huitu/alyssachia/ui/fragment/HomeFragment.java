package com.huitu.alyssachia.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huitu.alyssachia.R;
import com.huitu.alyssachia.base.BaseFragment;


/**
 * Created by l on 2017/7/21.
 */

public class HomeFragment extends BaseFragment {

    private TextView tvTitle;

    public static HomeFragment newInstance() {
        Bundle bundle = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setContentView() {
        return R.layout.fragment_home;
    }


    @Override
    public void lazyLoad() {
    }


    @Override
    public void initView(View root) {
        tvTitle = (TextView) root.findViewById(R.id.tv_title);
        tvTitle.setText("HOME");
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
