package com.huitu.alyssachia.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * author: l
 * date 2017/2/22 下午4:52
 **/
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getContentView());
//        StatusBarUtils.initStatusBar(this);
        initView(savedInstanceState);
        setData();
        setListeners();
    }

    /**
     * 返回content layout id
     * @return
     */
    public abstract int getContentView();

    /**
     * 初始化view布局
     * @param savedInstanceState
     */
    public abstract void initView(Bundle savedInstanceState);

    /**
     * 设置监听listener
     */
    public abstract void setListeners();

    /**
     * 获取数据、加载数据
     */
    public abstract void setData();


//    private boolean isQuit = false;
//    @Override
//    public void onBackPressed() {
//        if (!isQuit) {
//            ToastUtils.toast(BaseActivity.this, R.string.gotoexit);
//            isQuit = true;
//            //在两秒钟之后isQuit会变成false
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } finally {
//                        isQuit = false;
//                    }
//                }
//            }).start();
//        } else {
//            System.exit(0);
//        }
//    }
}
