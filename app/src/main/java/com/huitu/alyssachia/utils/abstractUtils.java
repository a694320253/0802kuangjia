package com.huitu.alyssachia.utils;

import android.os.CountDownTimer;

/**
 * Created by l on 2017/7/27.
 */

public class abstractUtils {

    //倒计时  定时器
    private CountDownTimer timer = new CountDownTimer(10000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
//            TextView.setText((millisUntilFinished / 1000) + "秒后可重发");
        }

        @Override
        public void onFinish() {
//            TextView .setEnabled(true);
//            TextView .setText("获取验证码");
        }
    };

}
