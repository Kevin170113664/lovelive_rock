package com.thoughtworks.lhli.lovelive_rock;

import android.app.Activity;

import com.thoughtworks.lhli.lovelive_rock.model.CardModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FilterFactory extends Activity {

    private String[] rarityArray = LoveLiveApp.getInstance().getResources().getStringArray(R.array.rarity_array);
    private String[] gradeArray = LoveLiveApp.getInstance().getResources().getStringArray(R.array.grade_array);
    private String[] idolArray = LoveLiveApp.getInstance().getResources().getStringArray(R.array.idol_array);
    private String[] subTeamArray = LoveLiveApp.getInstance().getResources().getStringArray(R.array.sub_team_array);
    private String[] attributeArray = LoveLiveApp.getInstance().getResources().getStringArray(R.array.attribute_array);
    private String[] skillTypeArray = LoveLiveApp.getInstance().getResources().getStringArray(R.array.skill_type_array);
    private String[] firstGradeMember = LoveLiveApp.getInstance().getResources().getStringArray(R.array.first_grade_member);
    private String[] secondGradeMember = LoveLiveApp.getInstance().getResources().getStringArray(R.array.second_grade_member);
    private String[] thirdGradeMember = LoveLiveApp.getInstance().getResources().getStringArray(R.array.third_grade_member);
    private String[] printempsMember = LoveLiveApp.getInstance().getResources().getStringArray(R.array.printemps_member);
    private String[] bibiMember = LoveLiveApp.getInstance().getResources().getStringArray(R.array.bibi_member);
    private String[] lilyWhiteMember = LoveLiveApp.getInstance().getResources().getStringArray(R.array.lily_white_member);

    public List<CardModel> filterByRarity(List<CardModel> cardModelList, HashMap<Integer, String> filterMap) {
        List<CardModel> visibleCardModelList = new ArrayList<>();
        if (!filterMap.get(R.id.rarity_spinner).equals(rarityArray[0])) {
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
        if (!filterMap.get(R.id.idol_spinner).equals(idolArray[0])) {
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
        if (!filterMap.get(R.id.attribute_spinner).equals(attributeArray[0])) {
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
        if (!filterMap.get(R.id.grade_spinner).equals(gradeArray[0])) {
            for (CardModel c : cardModelList) {
                if (filterMap.get(R.id.grade_spinner).equals(gradeArray[1])
                        && Arrays.asList(firstGradeMember).contains(c.getJapaneseName())) {
                    visibleCardModelList.add(c);
                }
                if (filterMap.get(R.id.grade_spinner).equals(gradeArray[2])
                        && Arrays.asList(secondGradeMember).contains(c.getJapaneseName())) {
                    visibleCardModelList.add(c);
                }
                if (filterMap.get(R.id.grade_spinner).equals(gradeArray[3])
                        && Arrays.asList(thirdGradeMember).contains(c.getJapaneseName())) {
                    visibleCardModelList.add(c);
                }
            }
            return visibleCardModelList;
        }

        return cardModelList;
    }

    public List<CardModel> filterBySubTeam(List<CardModel> cardModelList, HashMap<Integer, String> filterMap) {
        List<CardModel> visibleCardModelList = new ArrayList<>();
        if (!filterMap.get(R.id.sub_team_spinner).equals(subTeamArray[0])) {
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