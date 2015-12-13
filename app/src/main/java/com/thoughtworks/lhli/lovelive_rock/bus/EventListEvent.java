package com.thoughtworks.lhli.lovelive_rock.bus;

import com.thoughtworks.lhli.lovelive_rock.model.CardModel;
import com.thoughtworks.lhli.lovelive_rock.model.EventModel;

import java.util.List;

public class EventListEvent {
    public List<EventModel> eventModelList;
    public List<CardModel> eventCardModelList;

    public EventListEvent(List<EventModel> eventModelList, List<CardModel> eventCardModelList) {
        this.eventModelList = eventModelList;
        this.eventCardModelList = eventCardModelList;
    }

    public List<EventModel> getEventModelList() {
        return eventModelList;
    }

    public List<CardModel> getEventCardModelList() {
        return eventCardModelList;
    }
}
