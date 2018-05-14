package com.huitu.alyssachia.album;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yanzhenjie.album.Album;
import com.huitu.alyssachia.R;

import java.util.List;

/**
 * Created by l on 2017/7/24.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ImageViewHolder> {

    private LayoutInflater mInflater;
    private int itemSize;
    private List<String> mImagePathList;
    private OnCompatItemClickListener mItemClickListener;
    private static int count;
    private Context context;

    public GridAdapter(Context context, OnCompatItemClickListener itemClickListener, int itemSize) {
        this.mInflater = LayoutInflater.from(context);
        this.mItemClickListener = itemClickListener;
        this.itemSize = itemSize;
        this.context = context;
    }

    public void notifyDataSetChanged(List<String> imagePathList) {
        this.mImagePathList = imagePathList;
        super.notifyDataSetChanged();
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageViewHolder viewHolder = new ImageViewHolder(itemSize, mInflater.inflate(R.layout.item_main_image, parent, false));
        viewHolder.mItemClickListener = mItemClickListener;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        if (isShowAddItem(position))
        {
            Drawable mDrawable = context.getResources().getDrawable(R.drawable.addimage);
            holder.loadImageDrawable(mDrawable);
//            holder.loadImage(mImagePathList.get(holder.getAdapterPosition()));
        } else {
            holder.loadImage(mImagePathList.get(holder.getAdapterPosition()));
        }
    }

    @Override
    public int getItemCount() {
//        return mImagePathList == null ? 0 : mImagePathList.size();
        if (mImagePathList == null) {
            count =1;
            return 1;
        } else if (mImagePathList.size() == 0){
            count = 1;
            return 1;
        } else if (mImagePathList.size() !=9) {
            count = mImagePathList.size() + 1;
            return mImagePathList.size() + 1;
        } else {
            count = mImagePathList.size();
            return mImagePathList.size();
        }
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnCompatItemClickListener mItemClickListener;
        private int itemSize;
        ImageView mIvIcon;

        public ImageViewHolder(int itemSize, View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.itemSize = itemSize;
            itemView.getLayoutParams().height = itemSize;
            itemView.requestLayout();
            mIvIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
        }

        public void loadImage(String imagePath) {
            Album.getAlbumConfig().getImageLoader().loadImage(mIvIcon, imagePath, itemSize, itemSize);
        }

        public void loadImageDrawable(Drawable drawable) {
            mIvIcon.setImageDrawable(drawable);
//            Album.getAlbumConfig().getImageLoader().loadImage(mIvIcon, imagePath, itemSize, itemSize);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }


    private boolean isShowAddItem(int position)
    {
        int size = mImagePathList == null ? 0 : mImagePathList.size();
        return position == size;
    }

}
