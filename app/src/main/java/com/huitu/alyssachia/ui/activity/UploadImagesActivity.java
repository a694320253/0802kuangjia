package com.huitu.alyssachia.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.util.DisplayUtils;
import com.yanzhenjie.album.widget.recyclerview.AlbumVerticalGirdDecoration;
import com.huitu.alyssachia.R;
import com.huitu.alyssachia.album.AlertDialog;
import com.huitu.alyssachia.album.GridAdapter;
import com.huitu.alyssachia.album.OnCompatItemClickListener;
import com.huitu.alyssachia.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by l on 2017/7/25.
 */

public class UploadImagesActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private TextView tv_title;
    private GridAdapter mGridAdapter;
    private ArrayList<String> mImageList = new ArrayList<>();
    private Drawable mDrawable;

    private static final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    private static final int ACTIVITY_REQUEST_PREVIEW_PHOTO = 101;

    @Override
    public int getContentView() {
        DisplayUtils.initScreen(this);
        return R.layout.activity_uploadimages;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(R.string.menu_check_image);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.decoration_white, null);
        recyclerView.addItemDecoration(new AlbumVerticalGirdDecoration(drawable));

        assert drawable != null;
        int itemSize = (DisplayUtils.screenWidth - (drawable.getIntrinsicWidth() * 4)) / 4;
        mGridAdapter = new GridAdapter(this, new OnCompatItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (mImageList == null){
                    selectImage();
                } else if (mImageList.size() == 9){
                    previewImage(position);
                } else if (mImageList.size() == position){
                    selectImage();
                } else {
                    previewImage(position);
                }
            }
        }, itemSize);
        recyclerView.setAdapter(mGridAdapter);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void setData() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Select image.
     */
    private void selectImage() {
        Album.album(this)
                .requestCode(ACTIVITY_REQUEST_SELECT_PHOTO)
                .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary)) // Toolbar color.
                .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)) // StatusBar color.
                .navigationBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryDark)) // NavigationBar color.
                .selectCount(9) // select count.
                .columnCount(3) // span count.
                .camera(true) // has camera function.
                .checkedList(mImageList) // The picture has been selected for anti-election.
                .start();
    }

    /**
     * Preview image.
     *
     * @param position current position.
     */
    private void previewImage(int position) {
        Album.gallery(this)
                .requestCode(ACTIVITY_REQUEST_PREVIEW_PHOTO)
                .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary)) // Toolbar color.
                .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)) // StatusBar color.
                .navigationBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryDark)) // NavigationBar color.
                .checkedList(mImageList) // Image list.
                .currentPosition(position) // Preview first to show the first few.
                .checkFunction(true) // Does the user have an anti-selection when previewing.
                .start();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ACTIVITY_REQUEST_SELECT_PHOTO: {
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList = Album.parseResult(data); // Parse select result.
                    handleSelectImage(mImageList);
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                    AlertDialog.build(this)
                            .setTitle(R.string.title_dialog_hint)
                            .setMessage(R.string.cancel_select_photo_hint)
                            .setPositiveButton(R.string.confirm,null)
                            .show();
                }
                break;
            }
            case ACTIVITY_REQUEST_PREVIEW_PHOTO: {
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList = Album.parseResult(data); // Parse select result.
                    handleSelectImage(mImageList);
                }
                break;
            }
        }
    }

    /**
     * Process selection results.
     */
    private void handleSelectImage(List<String> pathList) {
        mGridAdapter.notifyDataSetChanged(pathList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
