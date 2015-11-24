package com.thoughtworks.lhli.lovelive_rock.manager;

import android.content.Context;

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

    public void cacheEvent(List<Event> eventList) {
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
            CharacterVoice dataCharacterVoice =
                    modelMapper.map(c.getIdol().getCharacterVoice(), CharacterVoice.class);
            Long characterVoiceId = characterVoiceDao.insert(dataCharacterVoice);

            c.getIdol().setCharacterVoice(null);
            Idol dataIdol = modelMapper.map(c.getIdol(), Idol.class);
            dataIdol.setCharacterVoiceId(characterVoiceId);
            Long idolId = idolDao.insert(dataIdol);

            com.thoughtworks.lhli.lovelive_rock.data.Event dataEvent =
                    modelMapper.map(c.getEvent(),
                            com.thoughtworks.lhli.lovelive_rock.data.Event.class);
            Long eventId = eventDao.insert(dataEvent);

            c.setEvent(null);
            c.setIdol(null);
            com.thoughtworks.lhli.lovelive_rock.data.Card dataCard =
                    modelMapper.map(c, com.thoughtworks.lhli.lovelive_rock.data.Card.class);
            dataCard.setIdolId(idolId);
            dataCard.setEventId(eventId);
            cardDao.insert(dataCard);
        }
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
