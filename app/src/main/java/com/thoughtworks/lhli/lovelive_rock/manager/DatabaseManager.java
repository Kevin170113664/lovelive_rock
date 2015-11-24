package com.thoughtworks.lhli.lovelive_rock.manager;

import android.content.Context;

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

    public DatabaseManager(Context context) {
        this.helper = new DaoMaster.DevOpenHelper(context, "lovelive-db", null);
    }

    public void cacheLatestEvent(List<EventModel> eventModelList) {
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        eventDao = daoSession.getEventDao();

        for (EventModel e : eventModelList) {
            Event event = modelMapper.map(e, Event.class);
            eventDao.insert(event);
        }
    }

    public void cacheCardsByPage(List<CardModel> cardModelList) {
        for (CardModel cardModel : cardModelList) {
            cacheCard(cardModel);
        }
    }

    public void cacheCard(CardModel cardModel) {
        Long characterVoiceId = insertCv(cardModel);
        Long idolId = insertIdol(cardModel, characterVoiceId);
        Long eventId = insertEvent(cardModel);
        insertCard(cardModel, idolId, eventId);
    }

    private void insertCard(CardModel cardModel, Long idolId, Long eventId) {
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        cardDao = daoSession.getCardDao();

        cardModel.setEventModel(null);
        cardModel.setIdolModel(null);
        Card card = modelMapper.map(cardModel, Card.class);
        card.setIdolId(idolId);
        card.setEventId(eventId);
        cardDao.insert(card);
    }

    private Long insertEvent(CardModel cardModel) {
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        eventDao = daoSession.getEventDao();

        if (cardModel.getEventModel() == null) {
            return NULL_FIELD_FOR_FOREIGN_KEY;
        } else {
            return eventDao.insert(modelMapper.map(cardModel.getEventModel(), Event.class));
        }
    }

    private Long insertIdol(CardModel cardModel, Long characterVoiceId) {
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        idolDao = daoSession.getIdolDao();

        if (cardModel.getIdolModel() == null) {
            return NULL_FIELD_FOR_FOREIGN_KEY;
        } else {
            cardModel.getIdolModel().setCvModel(null);
            Idol idol = modelMapper.map(cardModel.getIdolModel(), Idol.class);
            idol.setCharacterVoiceId(characterVoiceId);
            return idolDao.insert(idol);
        }
    }

    private Long insertCv(CardModel cardModel) {
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        characterVoiceDao = daoSession.getCharacterVoiceDao();

        if (cardModel.getIdolModel() == null || cardModel.getIdolModel().getCvModel() == null) {
            return NULL_FIELD_FOR_FOREIGN_KEY;
        } else {
            return characterVoiceDao.insert(
                    modelMapper.map(cardModel.getIdolModel().getCvModel(), CharacterVoice.class));
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
            return modelMapper.map(cardList.get(0), CardModel.class);
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
                cardModelList.add(modelMapper.map(card, CardModel.class));
            }
            return cardModelList;
        } else {
            return null;
        }
    }
}
