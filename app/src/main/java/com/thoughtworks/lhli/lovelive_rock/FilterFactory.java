package com.thoughtworks.lhli.lovelive_rock;

import android.app.Activity;

import com.thoughtworks.lhli.lovelive_rock.model.CardModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilterFactory extends Activity{

    public List<CardModel> filterByRarity(List<CardModel> cardModelList, HashMap<Integer, String> filterMap) {
        List<CardModel> visibleCardModelList = new ArrayList<>();
        if (!filterMap.get(R.id.rarity_spinner).equals("稀有度")) {
            for (CardModel c : cardModelList) {
                if (c.getRarity().equals(filterMap.get(R.id.rarity_spinner))) {
                    visibleCardModelList.add(c);
                }
            }
            return visibleCardModelList;
        }
        return cardModelList;
    }

    public List<CardModel> filterByIdol(List<CardModel> cardModelList, HashMap<Integer, String> filterMap) {
        List<CardModel> visibleCardModelList = new ArrayList<>();
        if (!filterMap.get(R.id.idol_spinner).equals("角色")) {
            for (CardModel c : cardModelList) {
                if (c.getJapaneseName().equals(filterMap.get(R.id.idol_spinner))) {
                    visibleCardModelList.add(c);
                }
            }
            return visibleCardModelList;
        }
        return cardModelList;
    }

    public List<CardModel> filterByAttribute(List<CardModel> cardModelList, HashMap<Integer, String> filterMap) {
        List<CardModel> visibleCardModelList = new ArrayList<>();
        if (!filterMap.get(R.id.attribute_spinner).equals("属性")) {
            for (CardModel c : cardModelList) {
                if (c.getAttribute().equals(filterMap.get(R.id.attribute_spinner))) {
                    visibleCardModelList.add(c);
                }
            }
            return visibleCardModelList;
        }
        return cardModelList;
    }

    public List<CardModel> filterByGrade(List<CardModel> cardModelList, HashMap<Integer, String> filterMap) {
        List<CardModel> visibleCardModelList = new ArrayList<>();
        if (!filterMap.get(R.id.grade_spinner).equals("年级")) {
            for (CardModel c : cardModelList) {
                if (filterMap.get(R.id.grade_spinner).equals("一年级")) {
                    if (c.getJapaneseName().equals("小泉花陽")
                            || c.getJapaneseName().equals("西木野真姫")
                            || c.getJapaneseName().equals("星空凛")) {
                        visibleCardModelList.add(c);
                    }
                }
                if (filterMap.get(R.id.grade_spinner).equals("二年级")) {
                    if (c.getJapaneseName().equals("南ことり")
                            || c.getJapaneseName().equals("高坂穂乃果")
                            || c.getJapaneseName().equals("園田海未")) {
                        visibleCardModelList.add(c);
                    }
                }
                if (filterMap.get(R.id.grade_spinner).equals("三年级")) {
                    if (c.getJapaneseName().equals("東條希")
                            || c.getJapaneseName().equals("矢澤にこ")
                            || c.getJapaneseName().equals("絢瀬絵里")) {
                        visibleCardModelList.add(c);
                    }
                }
            }
            return visibleCardModelList;
        }
        return cardModelList;
    }

    public List<CardModel> filterBySubTeam(List<CardModel> cardModelList, HashMap<Integer, String> filterMap) {
        List<CardModel> visibleCardModelList = new ArrayList<>();
        if (!filterMap.get(R.id.sub_team_spinner).equals("小组")) {
            for (CardModel c : cardModelList) {
                if (filterMap.get(R.id.sub_team_spinner).equals("Printemps")) {
                    if (c.getJapaneseName().equals("小泉花陽")
                            || c.getJapaneseName().equals("南ことり")
                            || c.getJapaneseName().equals("高坂穂乃果")) {
                        visibleCardModelList.add(c);
                    }
                }
                if (filterMap.get(R.id.sub_team_spinner).equals("BiBi")) {
                    if (c.getJapaneseName().equals("西木野真姫")
                            || c.getJapaneseName().equals("矢澤にこ")
                            || c.getJapaneseName().equals("絢瀬絵里")) {
                        visibleCardModelList.add(c);
                    }
                }
                if (filterMap.get(R.id.sub_team_spinner).equals("Lily White")) {
                    if (c.getJapaneseName().equals("東條希")
                            || c.getJapaneseName().equals("星空凛")
                            || c.getJapaneseName().equals("園田海未")) {
                        visibleCardModelList.add(c);
                    }
                }
            }
            return visibleCardModelList;
        }
        return cardModelList;
    }
}