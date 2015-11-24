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
import com.thoughtworks.lhli.lovelive_rock.model.EventModel;

import org.modelmapper.ModelMapper;

import java.util.List;

public class DatabaseManager {

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
            return null;
        } else {
            Event event = modelMapper.map(cardModel.getEventModel(), Event.class);
            return eventDao.insert(event);
        }
    }

    private Long insertIdol(CardModel cardModel, Long characterVoiceId) {
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        idolDao = daoSession.getIdolDao();

        if (cardModel.getIdolModel() == null) {
            return null;
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
            return null;
        } else {
            CharacterVoice characterVoice =
                    modelMapper.map(cardModel.getIdolModel().getCvModel(), CharacterVoice.class);
            return characterVoiceDao.insert(characterVoice);
        }
    }

    private Event queryEventByName(CardModel cardModel) {
        DaoMaster daoMaster = new DaoMaster(helper.getReadableDatabase());
        daoSession = daoMaster.newSession();
        eventDao = daoSession.getEventDao();

        List<Event> eventList = eventDao.queryBuilder()
                .where(EventDao.Properties.JapaneseName.eq(cardModel.getEventModel().getJapaneseName()))
                .list();
        if (eventList.size() != 0) {
            return eventList.get(0);
        } else {
            return null;
        }
    }

    private Idol queryIdolByName(CardModel cardModel) {
        DaoMaster daoMaster = new DaoMaster(helper.getReadableDatabase());
        daoSession = daoMaster.newSession();
        idolDao = daoSession.getIdolDao();

        List<Idol> idolList = idolDao.queryBuilder()
                .where(IdolDao.Properties.JapaneseName.eq(cardModel.getIdolModel().getJapaneseName()))
                .list();
        if (idolList.size() != 0) {
            return idolList.get(0);
        } else {
            return null;
        }
    }

    private CharacterVoice queryCharacterVoiceByName(CardModel cardModel) {
        DaoMaster daoMaster = new DaoMaster(helper.getReadableDatabase());
        daoSession = daoMaster.newSession();
        characterVoiceDao = daoSession.getCharacterVoiceDao();

        List<CharacterVoice> characterVoiceList = characterVoiceDao.queryBuilder()
                .where(CharacterVoiceDao.Properties.Name.eq(cardModel.getIdolModel().getCvModel().getName()))
                .list();
        if (characterVoiceList.size() != 0) {
            return characterVoiceList.get(0);
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
            EventModel eventModel = modelMapper.map(eventList.get(0), EventModel.class);
            if (eventModel.getJapaneseName() != null) {
                return eventModel;
            }
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
            CardModel cardModel = modelMapper.map(cardList.get(0), CardModel.class);
            if (cardModel.getCardId() != null) {
                return cardModel;
            }
        }
        return null;
    }
}
