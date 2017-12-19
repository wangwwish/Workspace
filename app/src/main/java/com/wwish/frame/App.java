package com.wwish.frame;

import android.content.Context;

import com.wwish.commonlibrary.BaseApplication;

/**
 * Created by wwish on 2017/12/13.
 */

public class App extends BaseApplication {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
