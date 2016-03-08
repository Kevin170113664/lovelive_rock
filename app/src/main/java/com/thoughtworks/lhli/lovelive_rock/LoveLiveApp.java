package com.thoughtworks.lhli.lovelive_rock;

import android.app.Application;
import android.app.DownloadManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

public class LoveLiveApp extends Application {

    private static LoveLiveApp instance;
    private String maxCardNumber = "0";
    private String latestEventName = "";
    private String latestEventBeginning = "";
    private String latestEventEnd = "";

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

    public static void file_download(String imageUrl, Context context) {
        String fileName = generateFileName(imageUrl);
        DownloadManager mgr = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(imageUrl);
        DownloadManager.Request request = new DownloadManager.Request(downloadUri);
        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle("LoveLive!")
                .setDescription("LoveLive card image")
                .setDestinationInExternalPublicDir("/lovelive+", fileName);
        mgr.enqueue(request);

        Toast.makeText(context.getApplicationContext(), R.string.save_card_success_msg, Toast.LENGTH_SHORT).show();
    }

    public static String generateFileName(String imageUrl) {
        File direct = new File(Environment.getExternalStorageDirectory()
                + "/lovelive+");
        String[] urlArray = imageUrl.split("/");
        String fileName = urlArray[urlArray.length - 1];

        if (!direct.exists()) {
            direct.mkdirs();
        }

        return fileName;
    }

    public static Short getValidShort(Short value) {
        if (value == null) {
            return 0;
        } else {
            return value;
        }
    }
}
