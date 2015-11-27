package com.thoughtworks.lhli.lovelive_rock;

import com.thoughtworks.lhli.lovelive_rock.model.CardModel;

import java.util.HashMap;
import java.util.List;

public class FilterFactory {

    private HashMap<Integer, String> filterMap;
    private List<CardModel> cardModelList;

    public FilterFactory(HashMap<Integer, String> filterMap, List<CardModel> cardModelList) {
        this.filterMap = filterMap;
        this.cardModelList = cardModelList;
    }

    public List<CardModel> filterCardModelList() {
        return null;
    }

//    private void filterCardByRarity(String rarity) {
//        visibleCardModelList.clear();
//        for (CardModel cardModel : cardModelList) {
//            if (cardModel.getRarity().equals(rarity)) {
//                visibleCardModelList.add(cardModel);
//            }
//        }
//    }
}
