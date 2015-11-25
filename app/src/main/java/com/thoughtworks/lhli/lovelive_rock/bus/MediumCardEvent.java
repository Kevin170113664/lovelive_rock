package com.thoughtworks.lhli.lovelive_rock.bus;

import com.thoughtworks.lhli.lovelive_rock.model.CardModel;

import java.util.List;

public class MediumCardEvent {

    public List<CardModel> cardModelList;

    public MediumCardEvent(List<CardModel> cardModelList) {
        this.cardModelList = cardModelList;
    }

    public List<CardModel> getCardModelList() {
        return cardModelList;
    }
}
