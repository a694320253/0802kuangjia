package com.huitu.alyssachia.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.view.menu.MenuItemImpl;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.huitu.alyssachia.R;
import com.huitu.alyssachia.base.BaseActivity;
import com.huitu.alyssachia.base.BaseFragment;
import com.huitu.alyssachia.ui.fragment.CategoryFragment;
import com.huitu.alyssachia.ui.fragment.HomeFragment;
import com.huitu.alyssachia.ui.fragment.MineFragment;
import com.huitu.alyssachia.ui.fragment.NoticationFragment;
import com.huitu.alyssachia.utils.BottomNavigationViewHelper;
import com.huitu.alyssachia.utils.FragmentUtils;


public class MainActivity extends BaseActivity {

    FrameLayout content;
    BottomNavigationView bottomNavigationView;
    LinearLayout activityMain;

    private BaseFragment[] mFragments = new BaseFragment[4];
    private int mCurrPosition;

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        content = (FrameLayout) findViewById(R.id.content);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        activityMain = (LinearLayout) findViewById(R.id.activity_main);

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);//取消BottomNavigationView放大动画
        if (savedInstanceState == null) {
            mFragments = new BaseFragment[4];
            mFragments[0] = HomeFragment.newInstance();
            mFragments[1] = CategoryFragment.newInstance();
            mFragments[2] = MineFragment.newInstance();
            mFragments[3] = NoticationFragment.newInstance();
            FragmentUtils.addMultiple(getSupportFragmentManager(), R.id.content, mCurrPosition, mFragments);
        } else {
            mCurrPosition = savedInstanceState.getInt("currPosition");
            mFragments[0] = findFragment(HomeFragment.class);
            mFragments[1] = findFragment(CategoryFragment.class);
            mFragments[2] = findFragment(MineFragment.class);
            mFragments[3] = findFragment(NoticationFragment.class);
            if (mCurrPosition != 0) {
                updateNavigationBarState(mCurrPosition);
            }
        }
    }

    @Override
    public void setListeners() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mCurrPosition = item.getItemId();
                switch (item.getItemId()) {
                    case R.id.item_home:
                        showHideFragment(0);
                        break;
                    case R.id.item_category:
                        showHideFragment(1);
                        break;
                    case R.id.item_mine:
                        showHideFragment(2);
                        break;
                    case R.id.item_news:
                        showHideFragment(3);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currPosition", mCurrPosition);
    }

    @Override
    public void setData() {

    }

    private void showHideFragment(int position) {
        mFragments[position].setUserVisibleHint(true);
        FragmentUtils.showHideFragment(getSupportFragmentManager(), mFragments[position], null,false,false);
    }

    private <T extends BaseFragment> T findFragment(Class<T> tClass) {
        return FragmentUtils.findFragment(getSupportFragmentManager(), tClass);
    }

    private void updateNavigationBarState(int actionId) {
        Menu menu = bottomNavigationView.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem menuItem = menu.getItem(i);
            ((MenuItemImpl) menuItem).setExclusiveCheckable(false);
            menuItem.setChecked(menuItem.getItemId() == actionId);
            ((MenuItemImpl) menuItem).setExclusiveCheckable(true);
        }
    }
}
