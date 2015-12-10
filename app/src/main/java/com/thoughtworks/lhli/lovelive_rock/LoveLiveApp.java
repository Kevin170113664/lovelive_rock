package com.thoughtworks.lhli.lovelive_rock;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class LoveLiveApp extends Application {

    private static LoveLiveApp instance;
    private String maxCardNumber;
    private String latestEventName;
    private String latestEventBeginning;
    private String latestEventEnd;

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

    public String getLatestEventEnd() {
        return latestEventEnd;
    }

    public void setLatestEventEnd(String latestEventEnd) {
        this.latestEventEnd = latestEventEnd;
    }

    public String getLatestEventBeginning() {
        return latestEventBeginning;
    }

    public void setLatestEventBeginning(String latestEventBeginning) {
        this.latestEventBeginning = latestEventBeginning;
    }

    public Boolean isNetworkAvailable() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
