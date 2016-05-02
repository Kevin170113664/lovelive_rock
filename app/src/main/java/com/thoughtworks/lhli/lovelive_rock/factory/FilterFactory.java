package com.thoughtworks.lhli.lovelive_rock.factory;

import android.support.annotation.NonNull;

import com.thoughtworks.lhli.lovelive_rock.LoveLiveApp;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FilterFactory {

    private String[] rarityArray = LoveLiveApp.getInstance().getResources().getStringArray(R.array.rarity_array);
    private String[] gradeArray = LoveLiveApp.getInstance().getResources().getStringArray(R.array.grade_array);
    private String[] idolArray = LoveLiveApp.getInstance().getResources().getStringArray(R.array.idol_array);
    private String[] subTeamArray = LoveLiveApp.getInstance().getResources().getStringArray(R.array.sub_team_array);
    private String[] attributeArray = LoveLiveApp.getInstance().getResources().getStringArray(R.array.attribute_array);
    private String[] skillTypeArray = LoveLiveApp.getInstance().getResources().getStringArray(R.array.skill_type_array);
    private String[] isEventCardOrNot = LoveLiveApp.getInstance().getResources().getStringArray(R.array.is_event_card_or_not);
    private String[] isPromoOrNot = LoveLiveApp.getInstance().getResources().getStringArray(R.array.is_promo_or_not);
    private String[] collectionArray = LoveLiveApp.getInstance().getResources().getStringArray(R.array.collection_array);

    private String[] firstGradeMember = LoveLiveApp.getInstance().getResources().getStringArray(R.array.first_grade_member);
    private String[] secondGradeMember = LoveLiveApp.getInstance().getResources().getStringArray(R.array.second_grade_member);
    private String[] thirdGradeMember = LoveLiveApp.getInstance().getResources().getStringArray(R.array.third_grade_member);
    private String[] printempsMember = LoveLiveApp.getInstance().getResources().getStringArray(R.array.printemps_member);
    private String[] bibiMember = LoveLiveApp.getInstance().getResources().getStringArray(R.array.bibi_member);
    private String[] lilyWhiteMember = LoveLiveApp.getInstance().getResources().getStringArray(R.array.lily_white_member);
    private String[] scoreUpSkill = LoveLiveApp.getInstance().getResources().getStringArray(R.array.score_up_skill);
    private String[] perfectLockSkill = LoveLiveApp.getInstance().getResources().getStringArray(R.array.perfect_lock_skill);
    private String[] healerSkill = LoveLiveApp.getInstance().getResources().getStringArray(R.array.healer_skill);

    public List<CardModel> filterCards(List<CardModel> cardModelList, HashMap<Integer, String> filterMap) {
        List<CardModel> visibleCardModelList;
        visibleCardModelList = filterByRarity(cardModelList, filterMap);
        visibleCardModelList = filterByIdol(visibleCardModelList, filterMap);
        visibleCardModelList = filterByAttribute(visibleCardModelList, filterMap);
        visibleCardModelList = filterByGrade(visibleCardModelList, filterMap);
        visibleCardModelList = filterBySubTeam(visibleCardModelList, filterMap);
        visibleCardModelList = filterBySkillType(visibleCardModelList, filterMap);
        visibleCardModelList = filterByIsEventCardOrNot(visibleCardModelList, filterMap);
        visibleCardModelList = filterByIsPromoOrNot(visibleCardModelList, filterMap);
        visibleCardModelList = filterByCollection(visibleCardModelList, filterMap);
        return visibleCardModelList;
    }

    private List<CardModel> filterByRarity(List<CardModel> cardModelList, HashMap<Integer, String> filterMap) {
        if (!filterMap.get(R.id.rarity_spinner).equals(rarityArray[0])) {
            List<CardModel> visibleCardModelList = new ArrayList<>();
            for (CardModel c : cardModelList) {
                if (c.getRarity() != null && c.getRarity().equals(filterMap.get(R.id.rarity_spinner))) {
                    visibleCardModelList.add(c);
                }
            }
            return visibleCardModelList;
        }
        return cardModelList;
    }

    private List<CardModel> filterByIdol(List<CardModel> cardModelList, HashMap<Integer, String> filterMap) {
        if (!filterMap.get(R.id.idol_spinner).equals(idolArray[0])) {
            List<CardModel> visibleCardModelList = new ArrayList<>();
            for (CardModel c : cardModelList) {
                if (c.getJapaneseName() != null &&
                        c.getJapaneseName().replaceAll("\\s", "").equals(filterMap.get(R.id.idol_spinner))) {
                    visibleCardModelList.add(c);
                }
            }
            return visibleCardModelList;
        }
        return cardModelList;
    }

    private List<CardModel> filterByAttribute(List<CardModel> cardModelList, HashMap<Integer, String> filterMap) {
        if (!filterMap.get(R.id.attribute_spinner).equals(attributeArray[0])) {
            List<CardModel> visibleCardModelList = new ArrayList<>();
            for (CardModel c : cardModelList) {
                if (c.getAttribute() != null && c.getAttribute().equals(filterMap.get(R.id.attribute_spinner))) {
                    visibleCardModelList.add(c);
                }
            }
            return visibleCardModelList;
        }
        return cardModelList;
    }

    private List<CardModel> filterByGrade(List<CardModel> cardModelList, HashMap<Integer, String> filterMap) {
        if (!filterMap.get(R.id.grade_spinner).equals(gradeArray[0])) {
            List<CardModel> visibleCardModelList = new ArrayList<>();
            HashMap<String, String[]> gradeMap = getGradeMap();
            List<String> gradeMember = Arrays.asList(gradeMap.get(filterMap.get(R.id.grade_spinner)));
            for (CardModel c : cardModelList) {
                if (c.getJapaneseName() != null && gradeMember.contains(c.getJapaneseName().replaceAll("\\s", ""))) {
                    visibleCardModelList.add(c);
                }
            }
            return visibleCardModelList;
        }
        return cardModelList;
    }

    private List<CardModel> filterBySubTeam(List<CardModel> cardModelList, HashMap<Integer, String> filterMap) {
        if (!filterMap.get(R.id.sub_team_spinner).equals(subTeamArray[0])) {
            List<CardModel> visibleCardModelList = new ArrayList<>();
            HashMap<String, String[]> subTeamMap = getSubTeamMap();
            List<String> subTeamMember = Arrays.asList(subTeamMap.get(filterMap.get(R.id.sub_team_spinner)));
            for (CardModel c : cardModelList) {
                if (c.getJapaneseName() != null && subTeamMember.contains(c.getJapaneseName().replaceAll("\\s", ""))) {
                    visibleCardModelList.add(c);
                }
            }
            return visibleCardModelList;
        }
        return cardModelList;
    }

    private List<CardModel> filterBySkillType(List<CardModel> cardModelList, HashMap<Integer, String> filterMap) {
        if (!filterMap.get(R.id.skill_type_spinner).equals(skillTypeArray[0])) {
            List<CardModel> visibleCardModelList = new ArrayList<>();
            HashMap<String, String[]> skillTypeMap = getSkillMap();
            List<String> skills = Arrays.asList(skillTypeMap.get(filterMap.get(R.id.skill_type_spinner)));
            for (CardModel c : cardModelList) {
                if (c.getSkill() != null && skills.contains(c.getSkill())) {
                    visibleCardModelList.add(c);
                }
            }
            return visibleCardModelList;
        }
        return cardModelList;
    }

    private List<CardModel> filterByIsEventCardOrNot(List<CardModel> cardModelList, HashMap<Integer, String> filterMap) {
        if (!filterMap.get(R.id.event_spinner).equals(isEventCardOrNot[0])) {
            List<CardModel> visibleCardModelList = new ArrayList<>();
            HashMap<String, Boolean> eventCardMap = getEventCardMap();
            for (CardModel c : cardModelList) {
                if (c.getEventModel() != null == eventCardMap.get(filterMap.get(R.id.event_spinner))) {
                    visibleCardModelList.add(c);
                }
            }
            return visibleCardModelList;
        }
        return cardModelList;
    }

    private List<CardModel> filterByIsPromoOrNot(List<CardModel> cardModelList, HashMap<Integer, String> filterMap) {
        if (!filterMap.get(R.id.promo_spinner).equals(isPromoOrNot[0])) {
            List<CardModel> visibleCardModelList = new ArrayList<>();
            HashMap<String, Boolean> promoMap = getPromoMap();
            for (CardModel c : cardModelList) {
                if (c.isPromo() == promoMap.get(filterMap.get(R.id.promo_spinner))) {
                    visibleCardModelList.add(c);
                }
            }
            return visibleCardModelList;
        }
        return cardModelList;
    }

    private List<CardModel> filterByCollection(List<CardModel> cardModelList, HashMap<Integer, String> filterMap) {
        if (!filterMap.get(R.id.collection_spinner).equals(collectionArray[0])) {
            List<CardModel> visibleCardModelList = new ArrayList<>();
            for (CardModel c : cardModelList) {
                if (c.getJapaneseCollection() != null &&
                        c.getJapaneseCollection().equals(filterMap.get(R.id.collection_spinner))) {
                    visibleCardModelList.add(c);
                }
            }
            return visibleCardModelList;
        }
        return cardModelList;
    }

    private HashMap<String, Boolean> getEventCardMap() {
        HashMap<String, Boolean> eventCardMap = new HashMap<>();
        eventCardMap.put(isEventCardOrNot[1], true);
        eventCardMap.put(isEventCardOrNot[2], false);
        return eventCardMap;
    }

    private HashMap<String, Boolean> getPromoMap() {
        HashMap<String, Boolean> promoMap = new HashMap<>();
        promoMap.put(isPromoOrNot[1], true);
        promoMap.put(isPromoOrNot[2], false);
        return promoMap;
    }

    private HashMap<String, String[]> getSkillMap() {
        HashMap<String, String[]> skillMap = new HashMap<>();
        skillMap.put(skillTypeArray[1], scoreUpSkill);
        skillMap.put(skillTypeArray[2], perfectLockSkill);
        skillMap.put(skillTypeArray[3], healerSkill);
        return skillMap;
    }

    @NonNull
    private HashMap<String, String[]> getGradeMap() {
        HashMap<String, String[]> gradeMap = new HashMap<>();
        gradeMap.put(gradeArray[1], firstGradeMember);
        gradeMap.put(gradeArray[2], secondGradeMember);
        gradeMap.put(gradeArray[3], thirdGradeMember);
        return gradeMap;
    }

    @NonNull
    private HashMap<String, String[]> getSubTeamMap() {
        HashMap<String, String[]> subTeamMap = new HashMap<>();
        subTeamMap.put(subTeamArray[1], printempsMember);
        subTeamMap.put(subTeamArray[2], bibiMember);
        subTeamMap.put(subTeamArray[3], lilyWhiteMember);
        return subTeamMap;
    }
}