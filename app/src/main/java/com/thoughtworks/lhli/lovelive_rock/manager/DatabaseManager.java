package com.thoughtworks.lhli.lovelive_rock.manager;

import android.content.Context;
import android.support.annotation.NonNull;

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
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        cardDao = daoSession.getCardDao();
        eventDao = daoSession.getEventDao();
        idolDao = daoSession.getIdolDao();
        characterVoiceDao = daoSession.getCharacterVoiceDao();

        Long characterVoiceId = insertCv(cardModel);
        Long idolId = insertIdol(cardModel, characterVoiceId);
        Long eventId = insertEvent(cardModel);
        insertCard(cardModel, idolId, eventId);
    }

    private void insertCard(CardModel cardModel, Long idolId, Long eventId) {
        List<Card> cardList = cardDao.queryBuilder()
                .where(CardDao.Properties.CardId.eq(cardModel.getCardId()))
                .list();
        if (cardList.size() != 0) {

        } else {
            cardModel.setEventModel(null);
            cardModel.setIdolModel(null);
            Card card = modelMapper.map(cardModel, Card.class);
            card.setIdolId(idolId);
            card.setEventId(eventId);
            cardDao.insert(card);
        }
    }

    @NonNull
    private Long insertEvent(CardModel cardModel) {
        List<Event> eventList = eventDao.queryBuilder()
                .where(EventDao.Properties.JapaneseName.eq(cardModel.getEventModel().getJapaneseName()))
                .list();
        if (eventList.size() != 0) {
            return eventList.get(0).getId();
        } else {
            Event event = modelMapper.map(cardModel.getEventModel(), Event.class);
            return eventDao.insert(event);
        }
    }

    @NonNull
    private Long insertIdol(CardModel cardModel, Long characterVoiceId) {
        List<Idol> idolList = idolDao.queryBuilder()
                .where(IdolDao.Properties.JapaneseName.eq(cardModel.getIdolModel().getJapaneseName()))
                .list();
        if (idolList.size() != 0) {
            return idolList.get(0).getId();
        } else {
            cardModel.getIdolModel().setCvModel(null);
            Idol idol = modelMapper.map(cardModel.getIdolModel(), Idol.class);
            idol.setCharacterVoiceId(characterVoiceId);
            return idolDao.insert(idol);
        }
    }

    @NonNull
    private Long insertCv(CardModel cardModel) {
        List<CharacterVoice> characterVoiceList = characterVoiceDao.queryBuilder()
                .where(CharacterVoiceDao.Properties.Name.eq(cardModel.getIdolModel().getCvModel().getName()))
                .list();
        if (characterVoiceList.size() != 0) {
            return characterVoiceList.get(0).getId();
        } else {
            CharacterVoice characterVoice =
                    modelMapper.map(cardModel.getIdolModel().getCvModel(), CharacterVoice.class);
            return characterVoiceDao.insert(characterVoice);
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
