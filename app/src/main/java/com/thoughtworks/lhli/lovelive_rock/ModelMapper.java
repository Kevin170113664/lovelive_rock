package com.thoughtworks.lhli.lovelive_rock;

import com.thoughtworks.lhli.lovelive_rock.data.Card;
import com.thoughtworks.lhli.lovelive_rock.data.Event;
import com.thoughtworks.lhli.lovelive_rock.data.Idol;
import com.thoughtworks.lhli.lovelive_rock.data.Song;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;
import com.thoughtworks.lhli.lovelive_rock.model.SongModel;

public class ModelMapper {

    private static Long NULL_FIELD_ID = -1L;

    public static Card mapCard(CardModel cardModel) {
        Card card = new Card();
        Idol idol = new Idol();
        idol.setId(NULL_FIELD_ID);
        Event event = new Event();
        event.setId(NULL_FIELD_ID);

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
        card.setCleanUr(cardModel.getCleanUr());
        card.setCleanUrIdolized(cardModel.getCleanUrIdolized());
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
        cardModel.setIsPromo(card.getIsPromo() == null ? false : card.getIsPromo());
        cardModel.setPromoItem(card.getPromoItem());
        cardModel.setReleaseDate(card.getReleaseDate());
        cardModel.setJapanOnly(card.getJapanOnly() == null ? false : card.getJapanOnly());
        cardModel.setEventModel(null);
        cardModel.setIsSpecial(card.getIsSpecial() == null ? false : card.getIsSpecial());
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
        cardModel.setCleanUr(card.getCleanUr());
        cardModel.setCleanUrIdolized(card.getCleanUrIdolized());
        return cardModel;
    }

    public static Song mapSong(SongModel songModel) {
        Song song = new Song();
        Event event = new Event();
        event.setId(NULL_FIELD_ID);

        song.setName(songModel.getName());
        song.setRomajiName(songModel.getRomajiName());
        song.setTranslatedName(songModel.getTranslatedName());
        song.setAttribute(songModel.getAttribute());
        song.setBPM(songModel.getBPM());
        song.setTime(songModel.getTime());
        song.setEvent(event);
        song.setRank(songModel.getRank());
        song.setDailyRotation(songModel.getDailyRotation());
        song.setDailyRotationPosition(songModel.getDailyRotationPosition());
        song.setImage(songModel.getImage());
        song.setEasyDifficulty(songModel.getEasyDifficulty());
        song.setEasyNotes(songModel.getEasyNotes());
        song.setNormalDifficulty(songModel.getNormalDifficulty());
        song.setNormalNotes(songModel.getNormalNotes());
        song.setHardDifficulty(songModel.getHardDifficulty());
        song.setHardNotes(songModel.getHardNotes());
        song.setExpertDifficulty(songModel.getExpertDifficulty());
        song.setExpertRandomDifficulty(songModel.getExpertRandomDifficulty());
        song.setExpertNotes(songModel.getExpertNotes());
        song.setMasterDifficulty(songModel.getMasterDifficulty());
        song.setMasterNotes(songModel.getMasterNotes());
        song.setAvailable(songModel.getAvailable());
        song.setItunesId(songModel.getItunesId());

        return song;
    }

    public static SongModel mapSong(Song song) {
        SongModel songModel = new SongModel();

        songModel.setName(song.getName());
        songModel.setRomajiName(song.getRomajiName());
        songModel.setTranslatedName(song.getTranslatedName());
        songModel.setAttribute(song.getAttribute());
        songModel.setBPM(song.getBPM());
        songModel.setTime(song.getTime());
        songModel.setEventModel(null);
        songModel.setRank(song.getRank());
        songModel.setDailyRotation(song.getDailyRotation());
        songModel.setDailyRotationPosition(song.getDailyRotationPosition());
        songModel.setImage(song.getImage());
        songModel.setEasyDifficulty(song.getEasyDifficulty());
        songModel.setEasyNotes(song.getEasyNotes());
        songModel.setNormalDifficulty(song.getNormalDifficulty());
        songModel.setNormalNotes(song.getNormalNotes());
        songModel.setHardDifficulty(song.getHardDifficulty());
        songModel.setHardNotes(song.getHardNotes());
        songModel.setExpertDifficulty(song.getExpertDifficulty());
        songModel.setExpertRandomDifficulty(song.getExpertRandomDifficulty());
        songModel.setExpertNotes(song.getExpertNotes());
        songModel.setMasterDifficulty(song.getMasterDifficulty());
        songModel.setMasterNotes(song.getMasterNotes());
        songModel.setAvailable(song.getAvailable());
        songModel.setItunesId(song.getItunesId());

        return songModel;
    }
}
