package com.thoughtworks.lhli.lovelive_rock;

import com.thoughtworks.lhli.lovelive_rock.model.Card;

import java.util.List;

public class CardEvent {

    public List<Card> cardList;

    public CardEvent(List<Card> cardList) {
        this.cardList = cardList;
    }

    public List<Card> getCardList() {
        return cardList;
    }
}
