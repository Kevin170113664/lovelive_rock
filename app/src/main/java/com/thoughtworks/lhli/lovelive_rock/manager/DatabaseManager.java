package com.thoughtworks.lhli.lovelive_rock.manager;

import com.thoughtworks.lhli.lovelive_rock.LoveLiveApp;
import com.thoughtworks.lhli.lovelive_rock.data.Card;
import com.thoughtworks.lhli.lovelive_rock.data.CardDao;
import com.thoughtworks.lhli.lovelive_rock.data.CharacterVoice;
import com.thoughtworks.lhli.lovelive_rock.data.CharacterVoiceDao;
import com.thoughtworks.lhli.lovelive_rock.data.DaoMaster;
import com.thoughtworks.lhli.lovelive_rock.data.DaoSession;
import com.thoughtworks.lhli.lovelive_rock.data.Event;
import com.thoughtworks.lhli.lovelive_rock.data.EventDao;
import com.thoughtworks.lhli.lovelive_rock.data.Idol;
import com.thoughtworks.lhli.lovelive_rock.data.IdolDao;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;
import com.thoughtworks.lhli.lovelive_rock.model.CvModel;
import com.thoughtworks.lhli.lovelive_rock.model.EventModel;
import com.thoughtworks.lhli.lovelive_rock.model.IdolModel;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private final Integer MAX_CARDS_AMOUNT_IN_ONE_PAGE = 10;
    private final Long NULL_FIELD_FOR_FOREIGN_KEY = -1L;
    private DaoMaster.OpenHelper helper;
    private DaoSession daoSession;
    private CardDao cardDao;
    private EventDao eventDao;
    private IdolDao idolDao;
    private CharacterVoiceDao characterVoiceDao;
    private ModelMapper modelMapper = new ModelMapper();

    public DatabaseManager() {
        this.helper = new DaoMaster.DevOpenHelper(LoveLiveApp.getInstance(), "lovelive-db", null);
    }

    public void cacheCard(CardModel cardModel) {
        Long characterVoiceId = NULL_FIELD_FOR_FOREIGN_KEY;
        Long idolId = NULL_FIELD_FOR_FOREIGN_KEY;
        Long eventId = NULL_FIELD_FOR_FOREIGN_KEY;
        if (cardModel.getIdolModel() != null && queryCharacterVoiceByName(cardModel) == null) {
            characterVoiceId = insertCv(cardModel.getIdolModel().getCvModel());
        }
        if (queryIdolByName(cardModel) == null) {
            idolId = insertIdol(cardModel.getIdolModel(), characterVoiceId);
        }
        if (queryEventByName(cardModel) == null) {
            eventId = insertEvent(cardModel.getEventModel());
        }
        insertCard(cardModel, idolId, eventId);
    }

    private void insertCard(CardModel cardModel, Long idolId, Long eventId) {
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        cardDao = daoSession.getCardDao();

        cardModel.setEventModel(null);
        cardModel.setIdolModel(null);
        Card card = com.thoughtworks.lhli.lovelive_rock.ModelMapper.mapCard(cardModel);
        card.setIdolId(idolId);
        card.setEventId(eventId);
        cardDao.insert(card);
    }

    private Long insertEvent(EventModel eventModel) {
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        eventDao = daoSession.getEventDao();

        if (eventModel != null) {
            return eventDao.insert(modelMapper.map(eventModel, Event.class));
        } else {
            return NULL_FIELD_FOR_FOREIGN_KEY;
        }
    }

    private Long insertIdol(IdolModel idolModel, Long characterVoiceId) {
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        idolDao = daoSession.getIdolDao();

        if (idolModel == null) {
            return NULL_FIELD_FOR_FOREIGN_KEY;
        } else {
            idolModel.setCvModel(null);
            Idol idol = modelMapper.map(idolModel, Idol.class);
            idol.setCharacterVoiceId(characterVoiceId);
            return idolDao.insert(idol);
        }
    }

    private Long insertCv(CvModel cvModel) {
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        characterVoiceDao = daoSession.getCharacterVoiceDao();

        if (cvModel == null) {
            return NULL_FIELD_FOR_FOREIGN_KEY;
        } else {
            return characterVoiceDao.insert(modelMapper.map(cvModel, CharacterVoice.class));
        }
    }

    public EventModel queryEventByName(CardModel cardModel) {
        DaoMaster daoMaster = new DaoMaster(helper.getReadableDatabase());
        daoSession = daoMaster.newSession();
        eventDao = daoSession.getEventDao();

        List<Event> eventList = eventDao.queryBuilder()
                .where(EventDao.Properties.JapaneseName.eq(cardModel.getEventModel().getJapaneseName()))
                .list();
        if (eventList.size() != 0) {
            return modelMapper.map(eventList.get(0), EventModel.class);
        } else {
            return null;
        }
    }

    public IdolModel queryIdolByName(CardModel cardModel) {
        DaoMaster daoMaster = new DaoMaster(helper.getReadableDatabase());
        daoSession = daoMaster.newSession();
        idolDao = daoSession.getIdolDao();

        List<Idol> idolList = idolDao.queryBuilder()
                .where(IdolDao.Properties.JapaneseName.eq(cardModel.getIdolModel().getJapaneseName()))
                .list();
        if (idolList.size() != 0) {
            return modelMapper.map(idolList.get(0), IdolModel.class);
        } else {
            return null;
        }
    }

    public CvModel queryCharacterVoiceByName(CardModel cardModel) {
        DaoMaster daoMaster = new DaoMaster(helper.getReadableDatabase());
        daoSession = daoMaster.newSession();
        characterVoiceDao = daoSession.getCharacterVoiceDao();

        List<CharacterVoice> characterVoiceList = characterVoiceDao.queryBuilder()
                .where(CharacterVoiceDao.Properties.Name.eq(cardModel.getIdolModel().getCvModel().getName()))
                .list();
        if (characterVoiceList.size() != 0) {
            return modelMapper.map(characterVoiceList.get(0), CvModel.class);
        } else {
            return null;
        }
    }

    public EventModel queryLatestEvent(String japaneseName) {
        DaoMaster daoMaster = new DaoMaster(helper.getReadableDatabase());
        daoSession = daoMaster.newSession();
        eventDao = daoSession.getEventDao();

        List<Event> eventList = eventDao.queryBuilder()
                .where(EventDao.Properties.JapaneseName.eq(japaneseName))
                .list();
        if (eventList.size() != 0) {
            return modelMapper.map(eventList.get(0), EventModel.class);
        }
        return null;
    }

    public CardModel queryCardById(String cardId) {
        DaoMaster daoMaster = new DaoMaster(helper.getReadableDatabase());
        daoSession = daoMaster.newSession();
        cardDao = daoSession.getCardDao();

        List<Card> cardList
                = cardDao.queryBuilder()
                .where(CardDao.Properties.CardId.eq(cardId))
                .list();
        if (cardList.size() != 0) {
            return com.thoughtworks.lhli.lovelive_rock.ModelMapper.mapCard(cardList.get(0));
        }
        return null;
    }

    public List<CardModel> queryCardByPage(String page) {
        DaoMaster daoMaster = new DaoMaster(helper.getReadableDatabase());
        daoSession = daoMaster.newSession();
        cardDao = daoSession.getCardDao();

        Integer startId = Integer.parseInt(page) * MAX_CARDS_AMOUNT_IN_ONE_PAGE
                - (MAX_CARDS_AMOUNT_IN_ONE_PAGE - 1);
        Integer ednId = Integer.parseInt(page) * MAX_CARDS_AMOUNT_IN_ONE_PAGE;

        List<Card> cardList
                = cardDao.queryBuilder()
                .where(CardDao.Properties.CardId.between(startId, ednId))
                .list();
        if (cardList.size() != 0) {
            List<CardModel> cardModelList = new ArrayList<>();
            for (Card card : cardList) {
                cardModelList.add(com.thoughtworks.lhli.lovelive_rock.ModelMapper.mapCard(card));
            }
            return cardModelList;
        } else {
            return null;
        }
    }

    public List<CardModel> queryAllCards() {
        DaoMaster daoMaster = new DaoMaster(helper.getReadableDatabase());
        daoSession = daoMaster.newSession();
        cardDao = daoSession.getCardDao();

        List<Card> cardList
                = cardDao.queryBuilder()
                .where(CardDao.Properties.CardId.isNotNull())
                .list();
        if (cardList.size() != 0) {
            List<CardModel> cardModelList = new ArrayList<>();
            for (Card card : cardList) {
                cardModelList.add(com.thoughtworks.lhli.lovelive_rock.ModelMapper.mapCard(card));
            }
            return cardModelList;
        } else {
            return null;
        }
    }
}
