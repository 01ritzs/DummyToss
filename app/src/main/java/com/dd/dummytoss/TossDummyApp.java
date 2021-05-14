package com.dd.dummytoss;

import android.app.Application;

public class TossDummyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AdsUtils.getInstance().initMobileAds(this);
    }
}
