package com.huitu.alyssachia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.huitu.alyssachia.R;
import com.huitu.alyssachia.base.BaseActivity;

/**
 * Created by l on 2017/7/27.
 */

public class SplashActivity extends BaseActivity implements View.OnClickListener {

    private ImageView sp_bg;
    private Button sp_jump_btn;
    private Context mContext;

    @Override
    public int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mContext = this;
        sp_jump_btn = (Button) findViewById(R.id.sp_jump_btn);
        sp_bg = (ImageView) findViewById(R.id.sp_bg);
        sp_jump_btn.setOnClickListener(this);
        sp_bg.setOnClickListener(this);
        startClock();
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void setData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null)
            countDownTimer.cancel();
    }

    private CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            sp_jump_btn.setText("(" + millisUntilFinished / 1000 + "s)");
        }

        @Override
        public void onFinish() {
            sp_jump_btn.setText("(" + 0 + "s)");
            startActivity(new Intent(mContext,MainActivity.class));
            finish();
        }
    };

    //开起倒计时
    private void startClock() {
        sp_jump_btn.setVisibility(View.VISIBLE);
        countDownTimer.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sp_bg:
//                ToastUtils.toast(mContext,R.string.gotoMainActivty);
                startActivity(new Intent(mContext,WelcomeActivity.class));
                finish();
                break;
            case R.id.sp_jump_btn:
                startActivity(new Intent(mContext,MainActivity.class));
                finish();
                break;
        }
    }
}
