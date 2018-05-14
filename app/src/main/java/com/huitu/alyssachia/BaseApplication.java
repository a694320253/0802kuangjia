package com.huitu.alyssachia;

import android.app.Application;
import android.content.Context;

import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.huitu.alyssachia.utils.GlideImageLoader;
import com.huitu.alyssachia.utils.SharedPreferenceUtils;

/**
 * Created by l on 2017/7/21.
 */

public class BaseApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        SharedPreferenceUtils.init(this);
        Album.initialize(
                new AlbumConfig.Build()
                        .setImageLoader(new GlideImageLoader()) // Use glide loader.
                        .build()
        );
        NoHttp.initialize(this, new NoHttp.Config()
                // 设置全局连接超时时间，单位毫秒
                .setConnectTimeout(10 * 1000)
                // 设置全局服务器响应超时时间，单位毫秒
                .setReadTimeout(30 * 1000)
        );
        Logger.setDebug(true);// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。
        Logger.setTag("NoHttpSample");// 设置NoHttp打印Log的tag。
    }

    public static Context getContext() {return  mContext;}
}
