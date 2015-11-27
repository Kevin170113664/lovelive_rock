package com.thoughtworks.lhli.lovelive_rock;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class LoveLiveApp extends Application {
    private static LoveLiveApp instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static LoveLiveApp getInstance() {
        return instance;
    }

    public Boolean isNetworkAvailable() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
