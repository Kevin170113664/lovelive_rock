package com.thoughtworks.lhli.lovelive_rock.manager;

import android.content.Context;

import com.thoughtworks.lhli.lovelive_rock.data.CardDao;
import com.thoughtworks.lhli.lovelive_rock.data.DaoMaster;
import com.thoughtworks.lhli.lovelive_rock.data.DaoSession;
import com.thoughtworks.lhli.lovelive_rock.data.EventDao;
import com.thoughtworks.lhli.lovelive_rock.model.Card;
import com.thoughtworks.lhli.lovelive_rock.model.Event;

import org.modelmapper.ModelMapper;

import java.util.List;

public class DatabaseManager {

    private Context context;
    private DaoSession daoSession;
    private CardDao cardDao;
    private EventDao eventDao;
    private ModelMapper modelMapper = new ModelMapper();

    public DatabaseManager(Context context) {
        this.context = context;
    }

    public void cacheEvent(List<Event> eventList) {
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, "lovelive-db", null);
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
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, "lovelive-db", null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        cardDao = daoSession.getCardDao();

        for (Card c : cardList) {
            com.thoughtworks.lhli.lovelive_rock.data.Card dataCard =
                    modelMapper.map(c, com.thoughtworks.lhli.lovelive_rock.data.Card.class);
            cardDao.insert(dataCard);
        }
    }

    public Event getLatestEventFromDatabase(String japaneseName) {
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, "lovelive-db", null);
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

    public Card getCardByIdFromDatabase(String cardId) {
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, "lovelive-db", null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
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
}
