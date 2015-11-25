package com.thoughtworks.lhli.lovelive_rock.bus;

import com.thoughtworks.lhli.lovelive_rock.model.CardModel;

import java.util.List;

public class SmallCardEvent {

    public List<CardModel> cardModelList;

    public SmallCardEvent(List<CardModel> cardModelList) {
        this.cardModelList = cardModelList;
    }

    public List<CardModel> getCardModelList() {
        return cardModelList;
    }
}
