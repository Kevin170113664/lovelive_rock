package com.thoughtworks.lhli.lovelive_rock.bus;

import com.thoughtworks.lhli.lovelive_rock.model.CardModel;

import java.util.List;

public class CardDetailSmallCardEvent {

    public List<CardModel> cardModelList;

    public CardDetailSmallCardEvent(List<CardModel> cardModelList) {
        this.cardModelList = cardModelList;
    }

    public List<CardModel> getCardModelList() {
        return cardModelList;
    }
}
