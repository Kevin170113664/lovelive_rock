package com.thoughtworks.lhli.lovelive_rock.manager;

import com.thoughtworks.lhli.lovelive_rock.LoveLiveApp;
import com.thoughtworks.lhli.lovelive_rock.Retrofit;
import com.thoughtworks.lhli.lovelive_rock.bus.FetchProcessEvent;
import com.thoughtworks.lhli.lovelive_rock.bus.SongEvent;
import com.thoughtworks.lhli.lovelive_rock.model.MultipleSongs;
import com.thoughtworks.lhli.lovelive_rock.model.SongModel;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Response;

public class SongManager {

    private Integer maxSongNumber = 100;
    private List<SongModel> songModelList;
    DatabaseManager databaseManager;

    public SongManager(List<SongModel> songModelList) {
        this.songModelList = songModelList;
        this.databaseManager = new DatabaseManager();
    }

    private void getSongByPages() throws IOException {
        for (int page = 1; page <= 10; page++) {
            if (LoveLiveApp.getInstance().isNetworkAvailable()) {
                Call<MultipleSongs> call = Retrofit.getInstance().getSongService().getSongList(page);
                Response<MultipleSongs> songsResponse = call.execute();
                saveSongsAndSendEvents(songsResponse);
            } else {
                System.out.print("Get songs from " + page + " pages failed.");
            }
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
        for (SongModel songModel : songModelList) {
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
