package com.huitu.alyssachia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.huitu.alyssachia.Beans.Item;
import com.huitu.alyssachia.R;
import com.huitu.alyssachia.base.BaseActivity;
import com.huitu.alyssachia.utils.GlideApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;


/**
 * Created by l on 2017/7/26.
 */

public class News2Activity extends BaseActivity {
    private RecyclerView recycler_view;
    private List<Item> list;
    private int mCurrentCounter = 0;
    private PullToRefreshAdapter homeAdapter;
    private boolean isErr;
    private static final int PAGE_SIZE = 16;
    private RefreshLayout refreshLayout;
    private String[] impa = {"http://img.zcool.cn/community/01b72057a7e0790000018c1bf4fce0.png", "http://img.zcool.cn/community/01fca557a7f5f90000012e7e9feea8.jpg",
            "http://img.zcool.cn/community/01996b57a7f6020000018c1bedef97.jpg", "http://img.zcool.cn/community/01700557a7f42f0000018c1bd6eb23.jpg"};
    private List<String> stringList;
    private Banner banner;
    private List<String> images;

    @Override
    public int getContentView() {
        return R.layout.activity_news;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        //添加Header
        View header = LayoutInflater.from(this).inflate(R.layout.item_banner,null);
        banner = (Banner) header.findViewById(R.id.banner);
        images = new ArrayList<>();
        images.add("http://img.zcool.cn/community/01b72057a7e0790000018c1bf4fce0.png");
        images.add("http://img.zcool.cn/community/01fca557a7f5f90000012e7e9feea8.jpg");
        images.add("http://img.zcool.cn/community/01996b57a7f6020000018c1bedef97.jpg");
        images.add("http://img.zcool.cn/community/01700557a7f42f0000018c1bd6eb23.jpg");

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(final Context context, Object path, ImageView imageView) {
                RequestOptions options = new RequestOptions().error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher);
                GlideApp.with(context).load(path.toString()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imageView);
            }
        });

        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(News2Activity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        stringList = Arrays.asList(impa);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Toast.makeText(News2Activity.this, "下拉刷新", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        homeAdapter.setNewData(addiList());
                        refreshLayout.finishRefresh();
                    }
                }, 2000);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                Toast.makeText(News2Activity.this, "上拉加载更多", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        homeAdapter.addData(addiList_());
                        refreshLayout.finishLoadmore();
                    }
                }, 2000);
            }
        });
//        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableHeaderTranslationContent(false);
        refreshLayout.setDisableContentWhenRefresh(true);
        refreshLayout.setDisableContentWhenLoading(true);
        refreshLayout.setEnableAutoLoadmore(false);//是否监听列表滚动到底部自动加载；
        //设置 Header 为 Material风格
        refreshLayout.setRefreshHeader(new MaterialHeader(this));
        //设置 Footer 为 球脉冲
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        intiList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(linearLayoutManager);
        recycler_view.addItemDecoration(new DividerItemDecoration(this,VERTICAL));
        homeAdapter = new PullToRefreshAdapter(R.layout.test_item, list);
        homeAdapter.isFirstOnly(false);
//        homeAdapter.setNotDoAnimationCount(6);
        homeAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        homeAdapter.addHeaderView(header);
        recycler_view.setAdapter(homeAdapter);
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(News2Activity.this, "onItemClick" + position, Toast.LENGTH_SHORT).show();
            }
        });


    }

    public class PullToRefreshAdapter extends BaseQuickAdapter<Item, BaseViewHolder> {
        public PullToRefreshAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Item item) {
            helper.setText(R.id.tese_tv, item.getSt());
//            Log.i("TSGTSG", "-=---" + item.getPa());
//            RequestOptions options = new RequestOptions().error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher);
            GlideApp.with(News2Activity.this).load(item.getPa()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into((ImageView) helper.getView(R.id.image_view));

        }

    }

    @Override
    public void setListeners() {
    }

    @Override
    public void setData() {
        getData();
    }

    public void getData() {

    }


    private void intiList() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Item item = new Item();
            item.setSt("测试数据" + i);
            Log.i("TSGTSG", "-=---" + "测试数据" + i);
            item.setPa(stringList.get(i % 4));
            list.add(item);
        }

    }

    private List<Item> addiList() {
        list.clear();
        for (int i = 0; i < 20; i++) {
            Item item = new Item();
            item.setSt("测试数据" + i);
            Log.i("TSGTSG", "-=---" + "测试数据" + i);
            item.setPa(stringList.get(i % 4));
            list.add(item);
        }
        return list;
    }

    private List<Item> addiList_() {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Item item = new Item();
            item.setSt("测试数据" + (homeAdapter.getData().size() + i));
            Log.i("TSGTSGsssssssss", "-=---" + homeAdapter.getData().size() + "测试数据" + (homeAdapter.getData().size() + i));
            item.setPa(stringList.get(i % 4));
            items.add(item);
        }
        return items;
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }
}
