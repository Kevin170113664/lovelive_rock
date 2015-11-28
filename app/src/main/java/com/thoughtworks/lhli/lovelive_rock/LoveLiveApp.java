package com.thoughtworks.lhli.lovelive_rock;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class LoveLiveApp extends Application {

    private static LoveLiveApp instance;
    private String maxCardNumber = "738";
    private String latestEventName = "Score Match Round 22";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static LoveLiveApp getInstance() {
        return instance;
    }

    public void setMaxCardNumber(String maxCardNumber) {
        this.maxCardNumber = maxCardNumber;
    }

    public String getMaxCardNumber() {
        return maxCardNumber;
    }

    public void setLatestEventName(String latestEventName) {
        this.latestEventName = latestEventName;
    }

    public String getLatestEventName() {
        return latestEventName;
    }

    public Boolean isNetworkAvailable() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
