package com.huitu.alyssachia.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.yanzhenjie.album.Album;
import com.huitu.alyssachia.R;
import com.huitu.alyssachia.base.BaseActivity;
import com.huitu.alyssachia.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by l on 2017/7/26.
 */

public class UploadHeadActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_head,iv_head_;
    private static final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    private static final int ACTIVITY_REQUEST_PREVIEW_PHOTO = 101;
    private ArrayList<String> mImageList = new ArrayList<>();
    private List<LocalMedia> selectList = new ArrayList<>();
    @Override
    public int getContentView() {
        return R.layout.activity_uploadhead;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        iv_head = (ImageView) findViewById(R.id.iv_head);
        iv_head.setOnClickListener(this);
        iv_head_ = (ImageView) findViewById(R.id.iv_head_);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void setData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_head:
//                showSelectDialog();
// 进入相册 以下是例子：用不到的api可以不写
                PictureSelector.create(UploadHeadActivity.this)
                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                        .theme(R.style.picture_default_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .minSelectNum(1)// 最小选择数量 int
                        .imageSpanCount(4)// 每行显示个数 int
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .previewImage(true)// 是否可预览图片 true or false
                        .previewVideo(false)// 是否可预览视频 true or false
                        .enablePreviewAudio(false) // 是否可播放音频 true or false
                        .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.THIRD_GEAR、Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                        .isCamera(true)// 是否显示拍照按钮 true or false
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                        .enableCrop(true)// 是否裁剪 true or false
                        .compress(false)// 是否压缩 true or false
//                        .compressMode()//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                        .glideOverride(160, 160)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                        .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示 true or false
                        .isGif(false)// 是否显示gif图片 true or false
                        .freeStyleCropEnabled(false)// 裁剪框是否可拖拽 true or false
                        .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                        .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                        .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                        .openClickSound(false)// 是否开启点击声音 true or false
                        .selectionMedia(selectList)// 是否传入已选图片 List<LocalMedia> list
//                        .previewEggs()// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
//                        .cropCompressQuality()// 裁剪压缩质量 默认90 int
//                        .compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效 int
//                        .compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效  int
//                        .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
//                        .rotateEnabled() // 裁剪是否可旋转图片 true or false
//                        .scaleEnabled()// 裁剪是否可放大缩小图片 true or false
//                        .videoQuality()// 视频录制质量 0 or 1 int
//                        .videoSecond()// 显示多少秒以内的视频or音频也可适用 int
//                        .recordVideoSecond()//视频秒数录制 默认60s int
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

                break;
            case R.id.takePhoto:
                if (bottomDialog != null && bottomDialog.isShowing()) {
                    bottomDialog.dismiss();
                    selectImage();//调用相机
                }
                break;
            case R.id.fromAlbum:
                if (bottomDialog != null && bottomDialog.isShowing()) {
                    bottomDialog.dismiss();
                    selectImage();//调用相机
                }
                break;
            case R.id.cancel:
                if (bottomDialog != null && bottomDialog.isShowing()) {
                    bottomDialog.dismiss();
                }
                break;
        }
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
                .selectCount(1) // select count.
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
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    Glide.with(UploadHeadActivity.this)
                            .load(selectList.get(0).getCutPath())
                            .into(iv_head);
//                    DebugUtil.i("onActivityResult", "onActivityResult:" + selectList.size());
//                    BitmapFactory.Options options2 = new BitmapFactory.Options();
//                    options2.inPreferredConfig = Bitmap.Config.RGB_565;
//                    Bitmap bitmap= BitmapFactory.decodeFile(selectList.get(0).getCutPath(),options2);
//                    iv_head_.setImageBitmap(bitmap);
//                    Log.i("onActivityResult",selectList.size()+"----"+selectList.get(0).getCutPath()+"---"+bitmap.getByteCount() / 1024 / 1024);
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限
        PictureFileUtils.deleteCacheDirFile(UploadHeadActivity.this);
    }

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case ACTIVITY_REQUEST_SELECT_PHOTO: {
//                if (resultCode == RESULT_OK) { // Successfully.
//                    mImageList = Album.parseResult(data); // Parse select result.
//                    if (mImageList.size() > 0) {
//                        Album.getAlbumConfig().getImageLoader().loadImage(iv_head, mImageList.get(0), iv_head.getMeasuredWidth(), iv_head.getMeasuredWidth());
//                    }
//                } else if (resultCode == RESULT_CANCELED) { // User canceled.
//                    AlertDialog.build(this)
//                            .setTitle(R.string.title_dialog_hint)
//                            .setMessage(R.string.cancel_select_photo_hint)
//                            .setPositiveButton(R.string.confirm, null)
//                            .show();
//                }
//                break;
//            }
//            case ACTIVITY_REQUEST_PREVIEW_PHOTO: {
//                if (resultCode == RESULT_OK) { // Successfully.
//                    mImageList = Album.parseResult(data); // Parse select result.
//                    if (mImageList.size() > 0) {
//                        Album.getAlbumConfig().getImageLoader().loadImage(iv_head, mImageList.get(0), iv_head.getMeasuredWidth(), iv_head.getMeasuredWidth());
//                    }
//                }
//                break;
//            }
//        }
//    }

    Dialog bottomDialog;

    public void showSelectDialog() {
        bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_content_circle, null);
        contentView.findViewById(R.id.takePhoto).setOnClickListener(this);
        contentView.findViewById(R.id.fromAlbum).setOnClickListener(this);
        contentView.findViewById(R.id.cancel).setOnClickListener(this);
        bottomDialog.setContentView(contentView);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) contentView.getLayoutParams();
        params.width = getResources().getDisplayMetrics().widthPixels - DensityUtil.dp2px(this, 16f);
        params.bottomMargin = DensityUtil.dp2px(this, 8f);
        contentView.setLayoutParams(params);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();
    }
}
