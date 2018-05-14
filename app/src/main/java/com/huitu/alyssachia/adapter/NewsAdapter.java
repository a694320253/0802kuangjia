package com.huitu.alyssachia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huitu.alyssachia.R;

/**
 * Created by l on 2017/7/27.
 */

public abstract class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_news,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_author_name;
        public TextView tv_date;
        public TextView tv_title;
        public ImageView iv_thumbnail;
        public ViewHolder(View view){
            super(view);
            tv_author_name = (TextView) view.findViewById(R.id.tv_author_name);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            iv_thumbnail = (ImageView) view.findViewById(R.id.iv_thumbnail);
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(View view,int position);
        void onItemLongClick(View view ,int position);
    }

    public void setOnItemClickLitsener(OnItemClickListener mOnItemClickLitsener)
    {
        mOnItemClickListener = mOnItemClickLitsener;
    }

}
