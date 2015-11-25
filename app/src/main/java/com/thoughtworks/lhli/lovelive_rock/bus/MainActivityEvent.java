package com.thoughtworks.lhli.lovelive_rock.bus;

import java.util.HashMap;

public class MainActivityEvent {
    public HashMap<String, String> hashMap;

    public MainActivityEvent(HashMap<String, String> hashMap) {
        this.hashMap = hashMap;
    }

    public HashMap<String, String> getHashMap() {
        return hashMap;
    }
}
