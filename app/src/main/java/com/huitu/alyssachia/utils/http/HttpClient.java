package com.huitu.alyssachia.utils.http;


import okhttp3.OkHttpClient;

/**
 * author: shell
 * date 2017/2/24 下午3:13
 **/
public enum HttpClient {

    INSTANCE;
    private OkHttpClient client;
    private PersistentCookieStore persistentCookieStore;

    HttpClient() {

    }

    public OkHttpClient getOkHttpClient() {
        return client;
    }

    public void clearCookie() {
        persistentCookieStore.removeAll();
    }
}
