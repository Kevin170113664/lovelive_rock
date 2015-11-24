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
            Event dataEvent =
                    modelMapper.map(e, Event.class);
            eventDao.insert(dataEvent);
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
        List<Card> dataCardList
                = cardDao.queryBuilder()
                .where(CardDao.Properties.CardId.eq(cardModel.getCardId()))
                .list();
        if (dataCardList.size() != 0) {
//            return dataCardList.get(0).getId();
        } else {
            cardModel.setEventModel(null);
            cardModel.setIdolModel(null);
            Card dataCard =
                    modelMapper.map(cardModel, Card.class);
            dataCard.setIdolId(idolId);
            dataCard.setEventId(eventId);
            cardDao.insert(dataCard);
        }
    }

    @NonNull
    private Long insertEvent(CardModel cardModel) {
        List<Event> dataEventList
                = eventDao.queryBuilder()
                .where(EventDao.Properties.JapaneseName.eq(cardModel.getEventModel().getJapaneseName()))
                .list();
        if (dataEventList.size() != 0) {
            return dataEventList.get(0).getId();
        } else {
            Event dataEvent =
                    modelMapper.map(cardModel.getEventModel(),
                            Event.class);
            return eventDao.insert(dataEvent);
        }
    }

    @NonNull
    private Long insertIdol(CardModel cardModel, Long characterVoiceId) {
        List<Idol> dataIdolList
                = idolDao.queryBuilder()
                .where(IdolDao.Properties.JapaneseName.eq(cardModel.getIdolModel().getJapaneseName()))
                .list();
        if (dataIdolList.size() != 0) {
            return dataIdolList.get(0).getId();
        } else {
            cardModel.getIdolModel().setCvModel(null);
            Idol dataIdol = modelMapper.map(cardModel.getIdolModel(), Idol.class);
            dataIdol.setCharacterVoiceId(characterVoiceId);
            return idolDao.insert(dataIdol);
        }
    }

    @NonNull
    private Long insertCv(CardModel cardModel) {
        List<CharacterVoice> dataCharacterVoiceList
                = characterVoiceDao.queryBuilder()
                .where(CharacterVoiceDao.Properties.Name.eq(cardModel.getIdolModel().getCvModel().getName()))
                .list();
        if (dataCharacterVoiceList.size() != 0) {
            return dataCharacterVoiceList.get(0).getId();
        } else {
            CharacterVoice dataCharacterVoice =
                    modelMapper.map(cardModel.getIdolModel().getCvModel(), CharacterVoice.class);
            return characterVoiceDao.insert(dataCharacterVoice);
        }
    }

    public EventModel getLatestEventFromCache(String japaneseName) {
        DaoMaster daoMaster = new DaoMaster(helper.getReadableDatabase());
        daoSession = daoMaster.newSession();
        eventDao = daoSession.getEventDao();

        List<Event> dataEvent
                = eventDao.queryBuilder()
                .where(EventDao.Properties.JapaneseName.eq(japaneseName))
                .list();
        if (dataEvent.size() != 0) {
            EventModel eventModel = modelMapper.map(dataEvent.get(0), EventModel.class);
            if (eventModel.getJapaneseName() != null) {
                return eventModel;
            }
        }
        return null;
    }

    public CardModel getCardByIdFromCache(String cardId) {
        DaoMaster daoMaster = new DaoMaster(helper.getReadableDatabase());
        daoSession = daoMaster.newSession();
        cardDao = daoSession.getCardDao();

        List<Card> dataCard
                = cardDao.queryBuilder()
                .where(CardDao.Properties.CardId.eq(cardId))
                .list();
        if (dataCard.size() != 0) {
            CardModel cardModel = modelMapper.map(dataCard.get(0), CardModel.class);
            if (cardModel.getCardId() != null) {
                return cardModel;
            }
        }
        return null;
    }

    public Integer getTotalCardsAmountFromCache() {
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        cardDao = daoSession.getCardDao();

//        List dataCard
//                = cardDao.queryBuilder()
//                .buildCount().list();
        return 0;
    }
}
