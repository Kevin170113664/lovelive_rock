package com.thoughtworks.lhli.lovelive_rock.bus;

import com.thoughtworks.lhli.lovelive_rock.model.SongModel;

import java.util.List;

public class SongEvent {

    private List<SongModel> songModelList;

    public SongEvent(List<SongModel> songModelList) {
        this.songModelList = songModelList;
    }

    public List<SongModel> getSongModelList() {
        return songModelList;
    }
}
