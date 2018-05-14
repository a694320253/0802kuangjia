package com.huitu.alyssachia.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.huitu.alyssachia.R;
import com.huitu.alyssachia.base.BaseFragment;

/**
 * Created by l on 2017/7/21.
 */

public class MineFragment extends BaseFragment{

    public static MineFragment newInstance() {
        Bundle bundle = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setContentView() {
        return R.layout.fragment_mine;
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
}
