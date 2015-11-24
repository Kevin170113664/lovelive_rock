package com.thoughtworks.lhli.lovelive_rock.manager;

import android.content.Context;
import android.support.annotation.NonNull;

import com.thoughtworks.lhli.lovelive_rock.data.CardDao;
import com.thoughtworks.lhli.lovelive_rock.data.CharacterVoice;
import com.thoughtworks.lhli.lovelive_rock.data.CharacterVoiceDao;
import com.thoughtworks.lhli.lovelive_rock.data.DaoMaster;
import com.thoughtworks.lhli.lovelive_rock.data.DaoSession;
import com.thoughtworks.lhli.lovelive_rock.data.EventDao;
import com.thoughtworks.lhli.lovelive_rock.data.Idol;
import com.thoughtworks.lhli.lovelive_rock.data.IdolDao;
import com.thoughtworks.lhli.lovelive_rock.model.Card;
import com.thoughtworks.lhli.lovelive_rock.model.Event;

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

    public void cacheLatestEvent(List<Event> eventList) {
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        eventDao = daoSession.getEventDao();

        for (Event e : eventList) {
            com.thoughtworks.lhli.lovelive_rock.data.Event dataEvent =
                    modelMapper.map(e, com.thoughtworks.lhli.lovelive_rock.data.Event.class);
            eventDao.insert(dataEvent);
        }
    }

    public void cacheCards(List<Card> cardList) {
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        cardDao = daoSession.getCardDao();
        eventDao = daoSession.getEventDao();
        idolDao = daoSession.getIdolDao();
        characterVoiceDao = daoSession.getCharacterVoiceDao();

        for (Card c : cardList) {
            Long characterVoiceId = cacheCharacterVoice(c);
            Long idolId = cacheIdol(c, characterVoiceId);
            Long eventId = cacheEvent(c);
            cacheCard(c, idolId, eventId);
        }
    }

    private void cacheCard(Card card, Long idolId, Long eventId) {
        card.setEvent(null);
        card.setIdol(null);
        com.thoughtworks.lhli.lovelive_rock.data.Card dataCard =
                modelMapper.map(card, com.thoughtworks.lhli.lovelive_rock.data.Card.class);
        dataCard.setIdolId(idolId);
        dataCard.setEventId(eventId);
        cardDao.insert(dataCard);
    }

    @NonNull
    private Long cacheEvent(Card card) {
        com.thoughtworks.lhli.lovelive_rock.data.Event dataEvent =
                modelMapper.map(card.getEvent(),
                        com.thoughtworks.lhli.lovelive_rock.data.Event.class);
        return eventDao.insert(dataEvent);
    }

    @NonNull
    private Long cacheIdol(Card card, Long characterVoiceId) {
        card.getIdol().setCharacterVoice(null);
        Idol dataIdol = modelMapper.map(card.getIdol(), Idol.class);
        dataIdol.setCharacterVoiceId(characterVoiceId);
        return idolDao.insert(dataIdol);
    }

    @NonNull
    private Long cacheCharacterVoice(Card c) {
        CharacterVoice dataCharacterVoice =
                modelMapper.map(c.getIdol().getCharacterVoice(), CharacterVoice.class);
        return characterVoiceDao.insert(dataCharacterVoice);
    }

    public Event getLatestEventFromCache(String japaneseName) {
        DaoMaster daoMaster = new DaoMaster(helper.getReadableDatabase());
        daoSession = daoMaster.newSession();
        eventDao = daoSession.getEventDao();

        List dataEvent
                = eventDao.queryBuilder()
                .where(EventDao.Properties.JapaneseName.eq(japaneseName))
                .list();
        if (dataEvent.size() != 0) {
            Event event = modelMapper.map(dataEvent.get(0), Event.class);
            if (event.getJapaneseName() != null) {
                return event;
            }
        }
        return null;
    }

    public Card getCardByIdFromCache(String cardId) {
        DaoMaster daoMaster = new DaoMaster(helper.getReadableDatabase());
        daoSession = daoMaster.newSession();
        cardDao = daoSession.getCardDao();

        List dataCard
                = cardDao.queryBuilder()
                .where(CardDao.Properties.CardId.eq(cardId))
                .list();
        if (dataCard.size() != 0) {
            Card card = modelMapper.map(dataCard.get(0), Card.class);
            if (card.getCardId() != null) {
                return card;
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
