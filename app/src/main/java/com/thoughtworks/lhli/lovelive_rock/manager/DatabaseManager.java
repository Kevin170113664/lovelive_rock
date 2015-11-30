package com.thoughtworks.lhli.lovelive_rock.manager;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

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
        Long characterVoiceId = getCharacterVoiceId(cardModel);
        Long idolId = getIdolId(cardModel, characterVoiceId);
        Long eventId = getEventId(cardModel);
        insertCard(cardModel, idolId, eventId);
    }

    private Long getEventId(CardModel cardModel) {
        Long eventId = NULL_FIELD_FOR_FOREIGN_KEY;
        if (cardModel.getEventModel() != null) {
            eventId = queryEventByName(cardModel);
            if (eventId.equals(NULL_FIELD_FOR_FOREIGN_KEY)) {
                eventId = insertEvent(cardModel.getEventModel());
            }
        }
        return eventId;
    }

    private Long getIdolId(CardModel cardModel, Long characterVoiceId) {
        Long idolId = NULL_FIELD_FOR_FOREIGN_KEY;
        if (cardModel.getIdolModel() != null) {
            idolId = queryIdolByName(cardModel);
            if (idolId.equals(NULL_FIELD_FOR_FOREIGN_KEY)) {
                idolId = insertIdol(cardModel.getIdolModel(), characterVoiceId);
            }
        }
        return idolId;
    }

    private Long getCharacterVoiceId(CardModel cardModel) {
        Long characterVoiceId = NULL_FIELD_FOR_FOREIGN_KEY;
        if (cardModel.getIdolModel() != null && cardModel.getIdolModel().getCvModel() != null) {
            characterVoiceId = queryCharacterVoiceByName(cardModel);
            if (characterVoiceId.equals(NULL_FIELD_FOR_FOREIGN_KEY)) {
                characterVoiceId = insertCv(cardModel.getIdolModel().getCvModel());
            }
        }
        return characterVoiceId;
    }

    private void insertCard(CardModel cardModel, Long idolId, Long eventId) {
        getCardDao(helper.getWritableDatabase());

        cardModel.setEventModel(null);
        cardModel.setIdolModel(null);
        Card card = com.thoughtworks.lhli.lovelive_rock.ModelMapper.mapCard(cardModel);
        card.setIdolId(idolId);
        card.setEventId(eventId);
        cardDao.insert(card);
    }

    private Long insertEvent(EventModel eventModel) {
        getEventDao(helper.getWritableDatabase());

        return eventDao.insert(modelMapper.map(eventModel, Event.class));
    }

    private Long insertIdol(IdolModel idolModel, Long characterVoiceId) {
        getIdolDao(helper.getWritableDatabase());

        idolModel.setCvModel(null);
        Idol idol = modelMapper.map(idolModel, Idol.class);
        idol.setCharacterVoiceId(characterVoiceId);
        return idolDao.insert(idol);
    }

    private Long insertCv(CvModel cvModel) {
        getCvDao(helper.getWritableDatabase());

        return characterVoiceDao.insert(modelMapper.map(cvModel, CharacterVoice.class));
    }

    public CardModel queryCardById(String cardId) {
        getCardDao(helper.getReadableDatabase());

        List<Card> cardList
                = cardDao.queryBuilder()
                .where(CardDao.Properties.CardId.eq(cardId))
                .list();
        if (cardList.size() != 0) {
            return com.thoughtworks.lhli.lovelive_rock.ModelMapper.mapCard(cardList.get(0));
        } else {
            return null;
        }
    }

    public Long queryEventByName(CardModel cardModel) {
        getEventDao(helper.getReadableDatabase());

        List<Event> eventList = eventDao.queryBuilder()
                .where(EventDao.Properties.JapaneseName.eq(cardModel.getEventModel().getJapaneseName()))
                .list();
        if (eventList.size() != 0) {
            return eventList.get(0).getId();
        } else {
            return NULL_FIELD_FOR_FOREIGN_KEY;
        }
    }

    public EventModel queryEventById(String id) {
        getEventDao(helper.getReadableDatabase());

        List<Event> eventList = eventDao.queryBuilder()
                .where(EventDao.Properties.Id.eq(id))
                .list();
        if (eventList.size() != 0) {
            return modelMapper.map(eventList.get(0), EventModel.class);
        } else {
            return null;
        }
    }

    public Long queryIdolByName(CardModel cardModel) {
        getIdolDao(helper.getReadableDatabase());

        List<Idol> idolList = idolDao.queryBuilder()
                .where(IdolDao.Properties.JapaneseName.eq(cardModel.getIdolModel().getJapaneseName()))
                .list();
        if (idolList.size() != 0) {
            return idolList.get(0).getId();
        } else {
            return NULL_FIELD_FOR_FOREIGN_KEY;
        }
    }

    public IdolModel queryIdolById(String id) {
        getIdolDao(helper.getReadableDatabase());

        List<Idol> idolList = idolDao.queryBuilder()
                .where(IdolDao.Properties.Id.eq(id))
                .list();
        if (idolList.size() != 0) {
            return generateIdolModel(idolList);
        } else {
            return null;
        }
    }

    public Long queryCharacterVoiceByName(CardModel cardModel) {
        getCvDao(helper.getReadableDatabase());

        List<CharacterVoice> characterVoiceList = characterVoiceDao.queryBuilder()
                .where(CharacterVoiceDao.Properties.Name.eq(cardModel.getIdolModel().getCvModel().getName()))
                .list();
        if (characterVoiceList.size() != 0) {
            return characterVoiceList.get(0).getId();
        } else {
            return NULL_FIELD_FOR_FOREIGN_KEY;
        }
    }

    public CvModel queryCharacterVoiceById(String id) {
        getCvDao(helper.getReadableDatabase());

        List<CharacterVoice> characterVoiceList = characterVoiceDao.queryBuilder()
                .where(CharacterVoiceDao.Properties.Id.eq(id))
                .list();
        if (characterVoiceList.size() != 0) {
            return modelMapper.map(characterVoiceList.get(0), CvModel.class);
        } else {
            return null;
        }
    }

    public EventModel queryLatestEvent(String japaneseName) {
        getEventDao(helper.getReadableDatabase());

        List<Event> eventList = eventDao.queryBuilder()
                .where(EventDao.Properties.JapaneseName.eq(japaneseName))
                .list();
        if (eventList.size() != 0) {
            return modelMapper.map(eventList.get(0), EventModel.class);
        }
        return null;
    }

    public List<CardModel> queryCardByPage(String page) {
        getCardDao(helper.getReadableDatabase());

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
        getCardDao(helper.getReadableDatabase());

        List<Card> cardList
                = cardDao.queryBuilder()
                .where(CardDao.Properties.CardId.isNotNull())
                .list();
        if (cardList.size() != 0) {
            List<CardModel> cardModelList = new ArrayList<>();
            for (Card card : cardList) {
                cardModelList.add(generateCardModel(card));
            }
            return cardModelList;
        } else {
            return null;
        }
    }

    @NonNull
    private IdolModel generateIdolModel(List<Idol> idolList) {
        IdolModel idolModel = modelMapper.map(idolList.get(0), IdolModel.class);
        idolModel.setCvModel(queryCharacterVoiceById(String.format("%s", idolList.get(0).getCharacterVoiceId())));
        return idolModel;
    }

    @NonNull
    private CardModel generateCardModel(Card card) {
        IdolModel idolModel = queryIdolById(String.format("%s", card.getIdolId()));
        EventModel eventModel = queryEventById(String.format("%s", card.getEventId()));
        CardModel cardModel = com.thoughtworks.lhli.lovelive_rock.ModelMapper.mapCard(card);
        cardModel.setIdolModel(idolModel);
        cardModel.setEventModel(eventModel);
        return cardModel;
    }

    private void getCardDao(SQLiteDatabase database) {
        openSession(database);
        cardDao = daoSession.getCardDao();
    }

    private void getEventDao(SQLiteDatabase database) {
        openSession(database);
        eventDao = daoSession.getEventDao();
    }

    private void getIdolDao(SQLiteDatabase database) {
        openSession(database);
        idolDao = daoSession.getIdolDao();
    }

    private void getCvDao(SQLiteDatabase database) {
        openSession(database);
        characterVoiceDao = daoSession.getCharacterVoiceDao();
    }

    private void openSession(SQLiteDatabase database) {
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }
}
