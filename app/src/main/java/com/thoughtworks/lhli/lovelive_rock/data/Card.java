package com.thoughtworks.lhli.lovelive_rock.data;

import com.thoughtworks.lhli.lovelive_rock.data.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "CARD".
 */
public class Card implements java.io.Serializable {

    private Long id;
    private String cardId;
    private String name;
    private String japaneseName;
    private long idolId;
    private String japaneseCollection;
    private String rarity;
    private String attribute;
    private String japaneseAttribute;
    private Boolean isPromo;
    private String promoItem;
    private String releaseDate;
    private Boolean japanOnly;
    private long eventId;
    private Boolean isSpecial;
    private String hp;
    private String minimumStatisticsSmile;
    private String minimumStatisticsPure;
    private String minimumStatisticsCool;
    private String nonIdolizedMaximumStatisticsSmile;
    private String nonIdolizedMaximumStatisticsPure;
    private String nonIdolizedMaximumStatisticsCool;
    private String idolizedMaximumStatisticsSmile;
    private String idolizedMaximumStatisticsPure;
    private String idolizedMaximumStatisticsCool;
    private String skill;
    private String japaneseSkill;
    private String skillDetails;
    private String japaneseSkillDetails;
    private String centerSkill;
    private String japaneseCenterSkill;
    private String japaneseCenterSkillDetails;
    private String cardImage;
    private String cardIdolizedImage;
    private String roundCardImage;
    private String videoStory;
    private String japaneseVideoStory;
    private String websiteUrl;
    private String nonIdolizedMaxLevel;
    private String idolizedMaxLevel;
    private String ownedCards;
    private String transparentImage;
    private String transparentIdolizedImage;
    private String transparentUrPair;
    private String transparentIdolizedUrPair;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient CardDao myDao;

    private Idol idol;
    private Long idol__resolvedKey;

    private Event event;
    private Long event__resolvedKey;


    public Card() {
    }

    public Card(Long id) {
        this.id = id;
    }

    public Card(Long id, String cardId, String name, String japaneseName, long idolId, String japaneseCollection, String rarity, String attribute, String japaneseAttribute, Boolean isPromo, String promoItem, String releaseDate, Boolean japanOnly, long eventId, Boolean isSpecial, String hp, String minimumStatisticsSmile, String minimumStatisticsPure, String minimumStatisticsCool, String nonIdolizedMaximumStatisticsSmile, String nonIdolizedMaximumStatisticsPure, String nonIdolizedMaximumStatisticsCool, String idolizedMaximumStatisticsSmile, String idolizedMaximumStatisticsPure, String idolizedMaximumStatisticsCool, String skill, String japaneseSkill, String skillDetails, String japaneseSkillDetails, String centerSkill, String japaneseCenterSkill, String japaneseCenterSkillDetails, String cardImage, String cardIdolizedImage, String roundCardImage, String videoStory, String japaneseVideoStory, String websiteUrl, String nonIdolizedMaxLevel, String idolizedMaxLevel, String ownedCards, String transparentImage, String transparentIdolizedImage, String transparentUrPair, String transparentIdolizedUrPair) {
        this.id = id;
        this.cardId = cardId;
        this.name = name;
        this.japaneseName = japaneseName;
        this.idolId = idolId;
        this.japaneseCollection = japaneseCollection;
        this.rarity = rarity;
        this.attribute = attribute;
        this.japaneseAttribute = japaneseAttribute;
        this.isPromo = isPromo;
        this.promoItem = promoItem;
        this.releaseDate = releaseDate;
        this.japanOnly = japanOnly;
        this.eventId = eventId;
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
        this.transparentUrPair = transparentUrPair;
        this.transparentIdolizedUrPair = transparentIdolizedUrPair;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCardDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJapaneseName() {
        return japaneseName;
    }

    public void setJapaneseName(String japaneseName) {
        this.japaneseName = japaneseName;
    }

    public long getIdolId() {
        return idolId;
    }

    public void setIdolId(long idolId) {
        this.idolId = idolId;
    }

    public String getJapaneseCollection() {
        return japaneseCollection;
    }

    public void setJapaneseCollection(String japaneseCollection) {
        this.japaneseCollection = japaneseCollection;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getJapaneseAttribute() {
        return japaneseAttribute;
    }

    public void setJapaneseAttribute(String japaneseAttribute) {
        this.japaneseAttribute = japaneseAttribute;
    }

    public Boolean getIsPromo() {
        return isPromo;
    }

    public void setIsPromo(Boolean isPromo) {
        this.isPromo = isPromo;
    }

    public String getPromoItem() {
        return promoItem;
    }

    public void setPromoItem(String promoItem) {
        this.promoItem = promoItem;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Boolean getJapanOnly() {
        return japanOnly;
    }

    public void setJapanOnly(Boolean japanOnly) {
        this.japanOnly = japanOnly;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public Boolean getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(Boolean isSpecial) {
        this.isSpecial = isSpecial;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getMinimumStatisticsSmile() {
        return minimumStatisticsSmile;
    }

    public void setMinimumStatisticsSmile(String minimumStatisticsSmile) {
        this.minimumStatisticsSmile = minimumStatisticsSmile;
    }

    public String getMinimumStatisticsPure() {
        return minimumStatisticsPure;
    }

    public void setMinimumStatisticsPure(String minimumStatisticsPure) {
        this.minimumStatisticsPure = minimumStatisticsPure;
    }

    public String getMinimumStatisticsCool() {
        return minimumStatisticsCool;
    }

    public void setMinimumStatisticsCool(String minimumStatisticsCool) {
        this.minimumStatisticsCool = minimumStatisticsCool;
    }

    public String getNonIdolizedMaximumStatisticsSmile() {
        return nonIdolizedMaximumStatisticsSmile;
    }

    public void setNonIdolizedMaximumStatisticsSmile(String nonIdolizedMaximumStatisticsSmile) {
        this.nonIdolizedMaximumStatisticsSmile = nonIdolizedMaximumStatisticsSmile;
    }

    public String getNonIdolizedMaximumStatisticsPure() {
        return nonIdolizedMaximumStatisticsPure;
    }

    public void setNonIdolizedMaximumStatisticsPure(String nonIdolizedMaximumStatisticsPure) {
        this.nonIdolizedMaximumStatisticsPure = nonIdolizedMaximumStatisticsPure;
    }

    public String getNonIdolizedMaximumStatisticsCool() {
        return nonIdolizedMaximumStatisticsCool;
    }

    public void setNonIdolizedMaximumStatisticsCool(String nonIdolizedMaximumStatisticsCool) {
        this.nonIdolizedMaximumStatisticsCool = nonIdolizedMaximumStatisticsCool;
    }

    public String getIdolizedMaximumStatisticsSmile() {
        return idolizedMaximumStatisticsSmile;
    }

    public void setIdolizedMaximumStatisticsSmile(String idolizedMaximumStatisticsSmile) {
        this.idolizedMaximumStatisticsSmile = idolizedMaximumStatisticsSmile;
    }

    public String getIdolizedMaximumStatisticsPure() {
        return idolizedMaximumStatisticsPure;
    }

    public void setIdolizedMaximumStatisticsPure(String idolizedMaximumStatisticsPure) {
        this.idolizedMaximumStatisticsPure = idolizedMaximumStatisticsPure;
    }

    public String getIdolizedMaximumStatisticsCool() {
        return idolizedMaximumStatisticsCool;
    }

    public void setIdolizedMaximumStatisticsCool(String idolizedMaximumStatisticsCool) {
        this.idolizedMaximumStatisticsCool = idolizedMaximumStatisticsCool;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getJapaneseSkill() {
        return japaneseSkill;
    }

    public void setJapaneseSkill(String japaneseSkill) {
        this.japaneseSkill = japaneseSkill;
    }

    public String getSkillDetails() {
        return skillDetails;
    }

    public void setSkillDetails(String skillDetails) {
        this.skillDetails = skillDetails;
    }

    public String getJapaneseSkillDetails() {
        return japaneseSkillDetails;
    }

    public void setJapaneseSkillDetails(String japaneseSkillDetails) {
        this.japaneseSkillDetails = japaneseSkillDetails;
    }

    public String getCenterSkill() {
        return centerSkill;
    }

    public void setCenterSkill(String centerSkill) {
        this.centerSkill = centerSkill;
    }

    public String getJapaneseCenterSkill() {
        return japaneseCenterSkill;
    }

    public void setJapaneseCenterSkill(String japaneseCenterSkill) {
        this.japaneseCenterSkill = japaneseCenterSkill;
    }

    public String getJapaneseCenterSkillDetails() {
        return japaneseCenterSkillDetails;
    }

    public void setJapaneseCenterSkillDetails(String japaneseCenterSkillDetails) {
        this.japaneseCenterSkillDetails = japaneseCenterSkillDetails;
    }

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    public String getCardIdolizedImage() {
        return cardIdolizedImage;
    }

    public void setCardIdolizedImage(String cardIdolizedImage) {
        this.cardIdolizedImage = cardIdolizedImage;
    }

    public String getRoundCardImage() {
        return roundCardImage;
    }

    public void setRoundCardImage(String roundCardImage) {
        this.roundCardImage = roundCardImage;
    }

    public String getVideoStory() {
        return videoStory;
    }

    public void setVideoStory(String videoStory) {
        this.videoStory = videoStory;
    }

    public String getJapaneseVideoStory() {
        return japaneseVideoStory;
    }

    public void setJapaneseVideoStory(String japaneseVideoStory) {
        this.japaneseVideoStory = japaneseVideoStory;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getNonIdolizedMaxLevel() {
        return nonIdolizedMaxLevel;
    }

    public void setNonIdolizedMaxLevel(String nonIdolizedMaxLevel) {
        this.nonIdolizedMaxLevel = nonIdolizedMaxLevel;
    }

    public String getIdolizedMaxLevel() {
        return idolizedMaxLevel;
    }

    public void setIdolizedMaxLevel(String idolizedMaxLevel) {
        this.idolizedMaxLevel = idolizedMaxLevel;
    }

    public String getOwnedCards() {
        return ownedCards;
    }

    public void setOwnedCards(String ownedCards) {
        this.ownedCards = ownedCards;
    }

    public String getTransparentImage() {
        return transparentImage;
    }

    public void setTransparentImage(String transparentImage) {
        this.transparentImage = transparentImage;
    }

    public String getTransparentIdolizedImage() {
        return transparentIdolizedImage;
    }

    public void setTransparentIdolizedImage(String transparentIdolizedImage) {
        this.transparentIdolizedImage = transparentIdolizedImage;
    }

    public String getTransparentUrPair() {
        return transparentUrPair;
    }

    public void setTransparentUrPair(String transparentUrPair) {
        this.transparentUrPair = transparentUrPair;
    }

    public String getTransparentIdolizedUrPair() {
        return transparentIdolizedUrPair;
    }

    public void setTransparentIdolizedUrPair(String transparentIdolizedUrPair) {
        this.transparentIdolizedUrPair = transparentIdolizedUrPair;
    }

    /** To-one relationship, resolved on first access. */
    public Idol getIdol() {
        long __key = this.idolId;
        if (idol__resolvedKey == null || !idol__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            IdolDao targetDao = daoSession.getIdolDao();
            Idol idolNew = targetDao.load(__key);
            synchronized (this) {
                idol = idolNew;
            	idol__resolvedKey = __key;
            }
        }
        return idol;
    }

    public void setIdol(Idol idol) {
        if (idol == null) {
            throw new DaoException("To-one property 'idolId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.idol = idol;
            idolId = idol.getId();
            idol__resolvedKey = idolId;
        }
    }

    /** To-one relationship, resolved on first access. */
    public Event getEvent() {
        long __key = this.eventId;
        if (event__resolvedKey == null || !event__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EventDao targetDao = daoSession.getEventDao();
            Event eventNew = targetDao.load(__key);
            synchronized (this) {
                event = eventNew;
            	event__resolvedKey = __key;
            }
        }
        return event;
    }

    public void setEvent(Event event) {
        if (event == null) {
            throw new DaoException("To-one property 'eventId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.event = event;
            eventId = event.getId();
            event__resolvedKey = eventId;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
