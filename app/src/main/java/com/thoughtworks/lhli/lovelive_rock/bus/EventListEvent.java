package com.thoughtworks.lhli.lovelive_rock.bus;

import com.thoughtworks.lhli.lovelive_rock.model.EventModel;

import java.util.List;

public class EventListEvent {
    public List<EventModel> eventModelList;

    public EventListEvent(List<EventModel> eventModelList) {
        this.eventModelList = eventModelList;
    }

    public List<EventModel> getEventModelList() {
        return eventModelList;
    }
}
