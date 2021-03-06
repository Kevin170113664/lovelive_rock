package com.thoughtworks.lhli.lovelive_rock.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CardModel implements Serializable {

    @SerializedName("id")
    private String cardId;

    @SerializedName("name")
    private String name;

    @SerializedName("japanese_name")
    private String japaneseName;

    @SerializedName("idol")
    private IdolModel idolModel;

    @SerializedName("japanese_collection")
    private String japaneseCollection;

    @SerializedName("rarity")
    private String rarity;

    @SerializedName("attribute")
    private String attribute;

    @SerializedName("japanese_attribute")
    private String japaneseAttribute;

    @SerializedName("is_promo")
    private boolean isPromo;

    @SerializedName("promo_item")
    private String promoItem;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("japan_only")
    private boolean japanOnly;

    @SerializedName("event")
    private EventModel eventModel;

    @SerializedName("is_special")
    private boolean isSpecial;

    @SerializedName("hp")
    private String hp;

    @SerializedName("minimum_statistics_smile")
    private String minimumStatisticsSmile;

    @SerializedName("minimum_statistics_pure")
    private String minimumStatisticsPure;

    @SerializedName("minimum_statistics_cool")
    private String minimumStatisticsCool;

    @SerializedName("non_idolized_maximum_statistics_smile")
    private String nonIdolizedMaximumStatisticsSmile;

    @SerializedName("non_idolized_maximum_statistics_pure")
    private String nonIdolizedMaximumStatisticsPure;

    @SerializedName("non_idolized_maximum_statistics_cool")
    private String nonIdolizedMaximumStatisticsCool;

    @SerializedName("idolized_maximum_statistics_smile")
    private String idolizedMaximumStatisticsSmile;

    @SerializedName("idolized_maximum_statistics_pure")
    private String idolizedMaximumStatisticsPure;

    @SerializedName("idolized_maximum_statistics_cool")
    private String idolizedMaximumStatisticsCool;

    @SerializedName("skill")
    private String skill;

    @SerializedName("japanese_skill")
    private String japaneseSkill;

    @SerializedName("skill_details")
    private String skillDetails;

    @SerializedName("japanese_skill_details")
    private String japaneseSkillDetails;

    @SerializedName("center_skill")
    private String centerSkill;

    @SerializedName("japanese_center_skill")
    private String japaneseCenterSkill;

    @SerializedName("japanese_center_skill_details")
    private String japaneseCenterSkillDetails;

    @SerializedName("card_image")
    private String cardImage;

    @SerializedName("card_idolized_image")
    private String cardIdolizedImage;

    @SerializedName("round_card_image")
    private String roundCardImage;

    @SerializedName("round_card_idolized_image")
    private String roundCardIdolizedImage;

    @SerializedName("video_story")
    private String videoStory;

    @SerializedName("japanese_video_story")
    private String japaneseVideoStory;

    @SerializedName("website_url")
    private String websiteUrl;

    @SerializedName("non_idolized_max_level")
    private String nonIdolizedMaxLevel;

    @SerializedName("idolized_max_level")
    private String idolizedMaxLevel;

    @SerializedName("owned_cards")
    private String ownedCards;

    @SerializedName("transparent_image")
    private String transparentImage;

    @SerializedName("transparent_idolized_image")
    private String transparentIdolizedImage;

    @SerializedName("clean_ur")
    private String cleanUr;

    @SerializedName("clean_ur_idolized")
    private String cleanUrIdolized;

    public CardModel(String cardId, String name, String japaneseName, IdolModel idolModel, String japaneseCollection,
                     String rarity, String attribute, String japaneseAttribute, boolean isPromo,
                     String promoItem, String releaseDate, boolean japanOnly, EventModel eventModel,
                     boolean isSpecial, String hp, String minimumStatisticsSmile,
                     String minimumStatisticsPure, String minimumStatisticsCool,
                     String nonIdolizedMaximumStatisticsSmile, String nonIdolizedMaximumStatisticsPure,
                     String nonIdolizedMaximumStatisticsCool, String idolizedMaximumStatisticsSmile,
                     String idolizedMaximumStatisticsPure, String idolizedMaximumStatisticsCool,
                     String skill, String japaneseSkill, String skillDetails,
                     String japaneseSkillDetails, String centerSkill, String japaneseCenterSkill,
                     String japaneseCenterSkillDetails, String cardImage, String cardIdolizedImage,
                     String roundCardImage, String videoStory, String japaneseVideoStory,
                     String websiteUrl, String nonIdolizedMaxLevel, String idolizedMaxLevel,
                     String ownedCards, String transparentImage, String transparentIdolizedImage,
                     String cleanUr, String cleanUrIdolized) {
        this.cardId = cardId;
        this.name = name;
        this.japaneseName = japaneseName;
        this.idolModel = idolModel;
        this.japaneseCollection = japaneseCollection;
        this.rarity = rarity;
        this.attribute = attribute;
        this.japaneseAttribute = japaneseAttribute;
        this.isPromo = isPromo;
        this.promoItem = promoItem;
        this.releaseDate = releaseDate;
        this.japanOnly = japanOnly;
        this.eventModel = eventModel;
        this.isSpecial = isSpecial;
        this.hp = hp;
        this.minimumStatisticsSmile = minimumStatisticsSmile;
        this.minimumStatisticsPure = minimumStatisticsPure;
        this.minimumStatisticsCool = minimumStatisticsCool;
        this.nonIdolizedMaximumStatisticsSmile = nonIdolizedMaximumStatisticsSmile;
        this.nonIdolizedMaximumStatisticsPure = nonIdolizedMaximumStatisticsPure;
        this.nonIdolizedMaximumStatisticsCool = nonIdolizedMaximumStatisticsCool;
        this.idolizedMaximumStatisticsSmile = idolizedMaximumStatisticsSmile;
        this.idolizedMaximumStatisticsPure = idolizedMaximumStatisticsPure;
        this.idolizedMaximumStatisticsCool = idolizedMaximumStatisticsCool;
        this.skill = skill;
        this.japaneseSkill = japaneseSkill;
        this.skillDetails = skillDetails;
        this.japaneseSkillDetails = japaneseSkillDetails;
        this.centerSkill = centerSkill;
        this.japaneseCenterSkill = japaneseCenterSkill;
        this.japaneseCenterSkillDetails = japaneseCenterSkillDetails;
        this.cardImage = cardImage;
        this.cardIdolizedImage = cardIdolizedImage;
        this.roundCardImage = roundCardImage;
        this.videoStory = videoStory;
        this.japaneseVideoStory = japaneseVideoStory;
        this.websiteUrl = websiteUrl;
        this.nonIdolizedMaxLevel = nonIdolizedMaxLevel;
        this.idolizedMaxLevel = idolizedMaxLevel;
        this.ownedCards = ownedCards;
        this.transparentImage = transparentImage;
        this.transparentIdolizedImage = transparentIdolizedImage;
        this.cleanUr = cleanUr;
        this.cleanUrIdolized = cleanUrIdolized;
    }

    public CardModel() {
    }

    public String getCardId() {
        return cardId;
    }

    public String getName() {
        return name;
    }

    public String getJapaneseName() {
        return japaneseName;
    }

    public IdolModel getIdolModel() {
        return idolModel;
    }

    public String getJapaneseCollection() {
        return japaneseCollection;
    }

    public String getRarity() {
        return rarity;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getJapaneseAttribute() {
        return japaneseAttribute;
    }

    public boolean isPromo() {
        return isPromo;
    }

    public String getPromoItem() {
        return promoItem;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public boolean isJapanOnly() {
        return japanOnly;
    }

    public EventModel getEventModel() {
        return eventModel;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public String getHp() {
        return hp;
    }

    public String getMinimumStatisticsSmile() {
        return minimumStatisticsSmile;
    }

    public String getMinimumStatisticsPure() {
        return minimumStatisticsPure;
    }

    public String getMinimumStatisticsCool() {
        return minimumStatisticsCool;
    }

    public String getNonIdolizedMaximumStatisticsSmile() {
        return nonIdolizedMaximumStatisticsSmile;
    }

    public String getNonIdolizedMaximumStatisticsPure() {
        return nonIdolizedMaximumStatisticsPure;
    }

    public String getNonIdolizedMaximumStatisticsCool() {
        return nonIdolizedMaximumStatisticsCool;
    }

    public String getIdolizedMaximumStatisticsSmile() {
        return idolizedMaximumStatisticsSmile;
    }

    public String getIdolizedMaximumStatisticsPure() {
        return idolizedMaximumStatisticsPure;
    }

    public String getIdolizedMaximumStatisticsCool() {
        return idolizedMaximumStatisticsCool;
    }

    public String getSkill() {
        return skill;
    }

    public String getJapaneseSkill() {
        return japaneseSkill;
    }

    public String getSkillDetails() {
        return skillDetails;
    }

    public String getJapaneseSkillDetails() {
        return japaneseSkillDetails;
    }

    public String getCenterSkill() {
        return centerSkill;
    }

    public String getJapaneseCenterSkill() {
        return japaneseCenterSkill;
    }

    public String getJapaneseCenterSkillDetails() {
        return japaneseCenterSkillDetails;
    }

    public String getCardImage() {
        return cardImage;
    }

    public String getCardIdolizedImage() {
        return cardIdolizedImage;
    }

    public String getRoundCardImage() {
        return roundCardImage;
    }

    public String getVideoStory() {
        return videoStory;
    }

    public String getJapaneseVideoStory() {
        return japaneseVideoStory;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public String getNonIdolizedMaxLevel() {
        return nonIdolizedMaxLevel;
    }

    public String getIdolizedMaxLevel() {
        return idolizedMaxLevel;
    }

    public String getOwnedCards() {
        return ownedCards;
    }

    public String getTransparentImage() {
        return transparentImage;
    }

    public String getTransparentIdolizedImage() {
        return transparentIdolizedImage;
    }

    public String getCleanUr() {
        return cleanUr;
    }

    public String getCleanUrIdolized() {
        return cleanUrIdolized;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJapaneseName(String japaneseName) {
        this.japaneseName = japaneseName;
    }

    public void setIdolModel(IdolModel idolModel) {
        this.idolModel = idolModel;
    }

    public void setJapaneseCollection(String japaneseCollection) {
        this.japaneseCollection = japaneseCollection;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setJapaneseAttribute(String japaneseAttribute) {
        this.japaneseAttribute = japaneseAttribute;
    }

    public void setIsPromo(boolean isPromo) {
        this.isPromo = isPromo;
    }

    public void setPromoItem(String promoItem) {
        this.promoItem = promoItem;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setJapanOnly(boolean japanOnly) {
        this.japanOnly = japanOnly;
    }

    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    public void setIsSpecial(boolean isSpecial) {
        this.isSpecial = isSpecial;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public void setMinimumStatisticsSmile(String minimumStatisticsSmile) {
        this.minimumStatisticsSmile = minimumStatisticsSmile;
    }

    public void setMinimumStatisticsPure(String minimumStatisticsPure) {
        this.minimumStatisticsPure = minimumStatisticsPure;
    }

    public void setMinimumStatisticsCool(String minimumStatisticsCool) {
        this.minimumStatisticsCool = minimumStatisticsCool;
    }

    public void setNonIdolizedMaximumStatisticsSmile(String nonIdolizedMaximumStatisticsSmile) {
        this.nonIdolizedMaximumStatisticsSmile = nonIdolizedMaximumStatisticsSmile;
    }

    public void setNonIdolizedMaximumStatisticsPure(String nonIdolizedMaximumStatisticsPure) {
        this.nonIdolizedMaximumStatisticsPure = nonIdolizedMaximumStatisticsPure;
    }

    public void setNonIdolizedMaximumStatisticsCool(String nonIdolizedMaximumStatisticsCool) {
        this.nonIdolizedMaximumStatisticsCool = nonIdolizedMaximumStatisticsCool;
    }

    public void setIdolizedMaximumStatisticsSmile(String idolizedMaximumStatisticsSmile) {
        this.idolizedMaximumStatisticsSmile = idolizedMaximumStatisticsSmile;
    }

    public void setIdolizedMaximumStatisticsPure(String idolizedMaximumStatisticsPure) {
        this.idolizedMaximumStatisticsPure = idolizedMaximumStatisticsPure;
    }

    public void setIdolizedMaximumStatisticsCool(String idolizedMaximumStatisticsCool) {
        this.idolizedMaximumStatisticsCool = idolizedMaximumStatisticsCool;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setJapaneseSkill(String japaneseSkill) {
        this.japaneseSkill = japaneseSkill;
    }

    public void setSkillDetails(String skillDetails) {
        this.skillDetails = skillDetails;
    }

    public void setJapaneseSkillDetails(String japaneseSkillDetails) {
        this.japaneseSkillDetails = japaneseSkillDetails;
    }

    public void setCenterSkill(String centerSkill) {
        this.centerSkill = centerSkill;
    }

    public void setJapaneseCenterSkill(String japaneseCenterSkill) {
        this.japaneseCenterSkill = japaneseCenterSkill;
    }

    public void setJapaneseCenterSkillDetails(String japaneseCenterSkillDetails) {
        this.japaneseCenterSkillDetails = japaneseCenterSkillDetails;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    public void setCardIdolizedImage(String cardIdolizedImage) {
        this.cardIdolizedImage = cardIdolizedImage;
    }

    public void setRoundCardImage(String roundCardImage) {
        this.roundCardImage = roundCardImage;
    }

    public void setVideoStory(String videoStory) {
        this.videoStory = videoStory;
    }

    public void setJapaneseVideoStory(String japaneseVideoStory) {
        this.japaneseVideoStory = japaneseVideoStory;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public void setNonIdolizedMaxLevel(String nonIdolizedMaxLevel) {
        this.nonIdolizedMaxLevel = nonIdolizedMaxLevel;
    }

    public void setIdolizedMaxLevel(String idolizedMaxLevel) {
        this.idolizedMaxLevel = idolizedMaxLevel;
    }

    public void setOwnedCards(String ownedCards) {
        this.ownedCards = ownedCards;
    }

    public void setTransparentImage(String transparentImage) {
        this.transparentImage = transparentImage;
    }

    public void setTransparentIdolizedImage(String transparentIdolizedImage) {
        this.transparentIdolizedImage = transparentIdolizedImage;
    }

    public void setCleanUr(String cleanUr) {
        this.cleanUr = cleanUr;
    }

    public void setCleanUrIdolized(String cleanUrIdolized) {
        this.cleanUrIdolized = cleanUrIdolized;
    }

    public String getRoundCardIdolizedImage() {
        return roundCardIdolizedImage;
    }

    public void setRoundCardIdolizedImage(String roundCardIdolizedImage) {
        this.roundCardIdolizedImage = roundCardIdolizedImage;
    }
}