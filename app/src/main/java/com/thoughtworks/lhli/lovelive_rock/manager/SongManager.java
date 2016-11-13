package com.thoughtworks.lhli.lovelive_rock.manager;

import com.thoughtworks.lhli.lovelive_rock.LoveLiveApp;
import com.thoughtworks.lhli.lovelive_rock.Retrofit;
import com.thoughtworks.lhli.lovelive_rock.bus.FetchProcessEvent;
import com.thoughtworks.lhli.lovelive_rock.bus.SongEvent;
import com.thoughtworks.lhli.lovelive_rock.model.MultipleSongs;
import com.thoughtworks.lhli.lovelive_rock.model.SongModel;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Response;

public class SongManager {

    private Integer maxSongNumber = 101;
    private List<SongModel> songModelList;
    private DatabaseManager databaseManager;

    public SongManager(List<SongModel> songModelList) {
        this.songModelList = songModelList;
        this.databaseManager = new DatabaseManager();
    }

    public void getAllSongs() throws IOException {
        setMaxSongNumber();
    }

    private void getSongByPages() throws IOException {
        for (int page = 1; page <= LoveLiveApp.calculateMaxPage(maxSongNumber); page++) {
            if (LoveLiveApp.getInstance().isNetworkAvailable()) {
                Call<MultipleSongs> call = Retrofit.getInstance().getSongService().getSongList(page, true);
                Response<MultipleSongs> songsResponse = call.execute();
                saveSongsAndSendEvents(songsResponse);
            } else {
                System.out.print("Get songs from " + page + " pages failed.");
            }
        }
    }

    private void setMaxSongNumber() throws IOException {
        if (LoveLiveApp.getInstance().isNetworkAvailable()) {
            Call<MultipleSongs> call = Retrofit.getInstance().getSongService().getSongByPageSize(1, 1);
            Response<MultipleSongs> songsResponse = call.execute();
            maxSongNumber = songsResponse.body().getCount();
        } else {
            System.out.print("Get max song number failed.");
        }
        getAllSongsByMaxSongNumber();
    }

    private void getAllSongsByMaxSongNumber() throws IOException {
        List<SongModel> songModelListFromDB = databaseManager.queryAllSongs();

        if (songModelListFromDB.size() >= maxSongNumber) {
            EventBus.getDefault().post(new SongEvent(songModelListFromDB));
            EventBus.getDefault().post(new FetchProcessEvent("100"));
        } else {
            getSongByPages();
        }
    }

    private void saveSongsAndSendEvents(Response<MultipleSongs> response) {
        if (response.isSuccess()) {
            songModelList.addAll(Arrays.asList(response.body().getResults()));
            cacheOnePageSongs();
            sendFetchProcessEvent(songModelList.size());
            sendSongEvent();
        } else {
            System.out.print("Get one page of songs failed.");
        }
    }

    private void sendFetchProcessEvent(int gottenSongNumber) {
        Double percentage = 100 * gottenSongNumber / Double.parseDouble(maxSongNumber.toString());
        String percentageMsg = new DecimalFormat("0").format((Double) (percentage > 100.0 ? 100.0 : percentage));
        EventBus.getDefault().post(new FetchProcessEvent(percentageMsg));
    }

    private void cacheOnePageSongs() {
        List<SongModel> cloneSongModelList = new ArrayList<>();
        cloneSongModelList.addAll(songModelList);
        for (SongModel songModel : cloneSongModelList) {
            SongModel queriedSongModel = databaseManager.querySongByName(songModel.getName());
            if (queriedSongModel == null) {
                databaseManager.cacheSong(songModel);
            }
        }
    }

    private void sendSongEvent() {
        if (songModelList.size() >= maxSongNumber) {
            EventBus.getDefault().post(new SongEvent(songModelList));
        }
    }
}