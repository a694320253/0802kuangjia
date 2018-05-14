package com.huitu.alyssachia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import com.huitu.alyssachia.R;
import com.huitu.alyssachia.adapter.NewsAdapter;
import com.huitu.alyssachia.base.BaseActivity;
import com.huitu.alyssachia.okhttp.CallServer;
import com.huitu.alyssachia.okhttp.HttpListener;

/**
 * Created by l on 2017/7/26.
 */

public class NewsActivity extends BaseActivity implements HttpListener<String>,  NewsAdapter.OnItemClickListener{

    private TextView tv_title;
    private NewsAdapter adapter;
    int page = 1;
    private Context mContext;

    @Override
    public int getContentView() {
        return R.layout.activity_news;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mContext = this;
        tv_title = (TextView) findViewById(R.id.tv_title);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能

//        recycler_news.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);

        setData();

    }

    @Override
    public void setListeners() {
    }

    @Override
    public void setData() {
        getData();
    }

    public void getData(){
        Request<String> request = NoHttp.createStringRequest("http://v.juhe.cn/toutiao/index?", RequestMethod.POST);
        request.add("type","top");
        request.add("key","b151d0c513d2dc11c0c1001edb12faf0");
        CallServer callServerInstance = CallServer.getRequestInstance();
        callServerInstance.add(this, 0, request, this, true, true);
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        switch (what){
            case 0:
                break;
            case 1:

                break;
            default:

                break;
        }
    }

    @Override
    public void onFailed(int what, Response<String> response) {

    }


    //RecyclerView条目点击事件
    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

}
