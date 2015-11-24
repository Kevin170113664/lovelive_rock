package com.thoughtworks.lhli.lovelive_rock.bus;

import com.thoughtworks.lhli.lovelive_rock.model.EventModel;

import java.util.List;

public class EventEvent {

    public List<EventModel> eventModelList;

    public EventEvent(List<EventModel> eventModelList) {
        this.eventModelList = eventModelList;
    }

    public List<EventModel> getEventModelList() {
        return eventModelList;
    }
}
