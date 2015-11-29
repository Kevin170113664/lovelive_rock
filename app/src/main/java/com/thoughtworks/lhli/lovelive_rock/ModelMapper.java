package com.thoughtworks.lhli.lovelive_rock;

import com.thoughtworks.lhli.lovelive_rock.data.Card;
import com.thoughtworks.lhli.lovelive_rock.data.Event;
import com.thoughtworks.lhli.lovelive_rock.data.Idol;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;

public class ModelMapper {

    public static Card mapCard(CardModel cardModel) {
        Card card = new Card();
        Idol idol = new Idol();
        idol.setId(-1L);
        Event event = new Event();
        event.setId(-1L);

        card.setCardId(cardModel.getCardId());
        card.setName(cardModel.getName());
        card.setJapaneseName(cardModel.getJapaneseName());
        card.setIdol(idol);
        card.setJapaneseCollection(cardModel.getJapaneseCollection());
        card.setRarity(cardModel.getRarity());
        card.setAttribute(cardModel.getAttribute());
        card.setJapaneseAttribute(cardModel.getJapaneseAttribute());
        card.setIsPromo(cardModel.isPromo());
        card.setPromoItem(cardModel.getPromoItem());
        card.setReleaseDate(cardModel.getReleaseDate());
        card.setJapanOnly(cardModel.isJapanOnly());
        card.setEvent(event);
        card.setIsSpecial(cardModel.isSpecial());
        card.setHp(cardModel.getHp());
        card.setMinimumStatisticsSmile(cardModel.getMinimumStatisticsSmile());
        card.setMinimumStatisticsPure(cardModel.getMinimumStatisticsPure());
        card.setMinimumStatisticsCool(cardModel.getMinimumStatisticsCool());
        card.setNonIdolizedMaximumStatisticsSmile(cardModel.getNonIdolizedMaximumStatisticsSmile());
        card.setNonIdolizedMaximumStatisticsPure(cardModel.getNonIdolizedMaximumStatisticsPure());
        card.setNonIdolizedMaximumStatisticsCool(cardModel.getNonIdolizedMaximumStatisticsCool());
        card.setIdolizedMaximumStatisticsSmile(cardModel.getIdolizedMaximumStatisticsSmile());
        card.setIdolizedMaximumStatisticsPure(cardModel.getIdolizedMaximumStatisticsPure());
        card.setIdolizedMaximumStatisticsCool(cardModel.getIdolizedMaximumStatisticsCool());
        card.setSkill(cardModel.getSkill());
        card.setJapaneseSkill(cardModel.getJapaneseSkill());
        card.setSkillDetails(cardModel.getSkillDetails());
        card.setJapaneseSkillDetails(cardModel.getJapaneseSkillDetails());
        card.setCenterSkill(cardModel.getCenterSkill());
        card.setJapaneseCenterSkill(cardModel.getJapaneseCenterSkill());
        card.setJapaneseCenterSkillDetails(cardModel.getJapaneseCenterSkillDetails());
        card.setCardImage(cardModel.getCardImage());
        card.setCardIdolizedImage(cardModel.getCardIdolizedImage());
        card.setRoundCardImage(cardModel.getRoundCardImage());
        card.setRoundCardIdolizedImage(cardModel.getRoundCardIdolizedImage());
        card.setVideoStory(cardModel.getVideoStory());
        card.setJapaneseVideoStory(cardModel.getJapaneseVideoStory());
        card.setWebsiteUrl(cardModel.getWebsiteUrl());
        card.setNonIdolizedMaxLevel(cardModel.getNonIdolizedMaxLevel());
        card.setIdolizedMaxLevel(cardModel.getIdolizedMaxLevel());
        card.setOwnedCards(cardModel.getOwnedCards());
        card.setTransparentImage(cardModel.getTransparentImage());
        card.setTransparentIdolizedImage(cardModel.getTransparentIdolizedImage());
        card.setTransparentUrPair(cardModel.getTransparentUrPair());
        card.setTransparentIdolizedUrPair(cardModel.getTransparentIdolizedUrPair());
        return card;
    }

    public static CardModel mapCard(Card card) {
        CardModel cardModel = new CardModel();
        cardModel.setCardId(card.getCardId());
        cardModel.setName(card.getName());
        cardModel.setJapaneseName(card.getJapaneseName());
        cardModel.setIdolModel(null);
        cardModel.setJapaneseCollection(card.getJapaneseCollection());
        cardModel.setRarity(card.getRarity());
        cardModel.setAttribute(card.getAttribute());
        cardModel.setJapaneseAttribute(card.getJapaneseAttribute());
        cardModel.setIsPromo(card.getIsPromo());
        cardModel.setPromoItem(card.getPromoItem());
        cardModel.setReleaseDate(card.getReleaseDate());
        cardModel.setJapanOnly(card.getJapanOnly());
        cardModel.setEventModel(null);
        cardModel.setIsSpecial(card.getIsSpecial());
        cardModel.setHp(card.getHp());
        cardModel.setMinimumStatisticsSmile(card.getMinimumStatisticsSmile());
        cardModel.setMinimumStatisticsPure(card.getMinimumStatisticsPure());
        cardModel.setMinimumStatisticsCool(card.getMinimumStatisticsCool());
        cardModel.setNonIdolizedMaximumStatisticsSmile(card.getNonIdolizedMaximumStatisticsSmile());
        cardModel.setNonIdolizedMaximumStatisticsPure(card.getNonIdolizedMaximumStatisticsPure());
        cardModel.setNonIdolizedMaximumStatisticsCool(card.getNonIdolizedMaximumStatisticsCool());
        cardModel.setIdolizedMaximumStatisticsSmile(card.getIdolizedMaximumStatisticsSmile());
        cardModel.setIdolizedMaximumStatisticsPure(card.getIdolizedMaximumStatisticsPure());
        cardModel.setIdolizedMaximumStatisticsCool(card.getIdolizedMaximumStatisticsCool());
        cardModel.setSkill(card.getSkill());
        cardModel.setJapaneseSkill(card.getJapaneseSkill());
        cardModel.setSkillDetails(card.getSkillDetails());
        cardModel.setJapaneseSkillDetails(card.getJapaneseSkillDetails());
        cardModel.setCenterSkill(card.getCenterSkill());
        cardModel.setJapaneseCenterSkill(card.getJapaneseCenterSkill());
        cardModel.setJapaneseCenterSkillDetails(card.getJapaneseCenterSkillDetails());
        cardModel.setCardImage(card.getCardImage());
        cardModel.setCardIdolizedImage(card.getCardIdolizedImage());
        cardModel.setRoundCardImage(card.getRoundCardImage());
        cardModel.setRoundCardIdolizedImage(card.getRoundCardIdolizedImage());
        cardModel.setVideoStory(card.getVideoStory());
        cardModel.setJapaneseVideoStory(card.getJapaneseVideoStory());
        cardModel.setWebsiteUrl(card.getWebsiteUrl());
        cardModel.setNonIdolizedMaxLevel(card.getNonIdolizedMaxLevel());
        cardModel.setIdolizedMaxLevel(card.getIdolizedMaxLevel());
        cardModel.setOwnedCards(card.getOwnedCards());
        cardModel.setTransparentImage(card.getTransparentImage());
        cardModel.setTransparentIdolizedImage(card.getTransparentIdolizedImage());
        cardModel.setTransparentUrPair(card.getTransparentUrPair());
        cardModel.setTransparentIdolizedUrPair(card.getTransparentIdolizedUrPair());
        return cardModel;
    }
}
