package com.huitu.alyssachia.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    boolean isToastShow = false;
    static Toast toast;

    public static void toast(Context context, CharSequence text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, int resource) {
        Toast.makeText(context, resource,
                Toast.LENGTH_LONG).show();
    }

    public static void toastInt(Context context, int position) {
        // TODO Auto-generated method stub
        Toast.makeText(context, Integer.toString(position), Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context,
                                 String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
