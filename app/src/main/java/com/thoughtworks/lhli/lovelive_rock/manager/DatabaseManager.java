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
import com.thoughtworks.lhli.lovelive_rock.data.Song;
import com.thoughtworks.lhli.lovelive_rock.data.SongDao;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;
import com.thoughtworks.lhli.lovelive_rock.model.CvModel;
import com.thoughtworks.lhli.lovelive_rock.model.EventModel;
import com.thoughtworks.lhli.lovelive_rock.model.IdolModel;
import com.thoughtworks.lhli.lovelive_rock.model.SongModel;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private final Long NULL_FIELD_ID = -1L;
    private DaoMaster.OpenHelper helper;
    private DaoSession daoSession;
    private CardDao cardDao;
    private EventDao eventDao;
    private IdolDao idolDao;
    private CharacterVoiceDao characterVoiceDao;
    private SongDao songDao;
    private ModelMapper modelMapper = new ModelMapper();

    public DatabaseManager() {
        this.helper = new DaoMaster.UpgradeHelper(LoveLiveApp.getInstance(), "lovelive-db", null);
    }

    public void deleteCard(String cardId) {
        Long primaryKey = queryCardPrimaryKeyById(cardId);
        getCardDao(helper.getWritableDatabase());
        cardDao.deleteByKey(primaryKey);
    }

    public void updateEventRankAndPoints(EventModel eventModel) {
        getEventDao(helper.getWritableDatabase());
        eventDao.getSession().getDatabase().execSQL(updateJapaneseT1Rank(eventModel));
        eventDao.getSession().getDatabase().execSQL(updateJapaneseT1Points(eventModel));
        eventDao.getSession().getDatabase().execSQL(updateJapaneseT2Rank(eventModel));
        eventDao.getSession().getDatabase().execSQL(updateJapaneseT2Points(eventModel));
    }

    protected String updateJapaneseT1Rank(EventModel eventModel) {
        return "UPDATE " + eventDao.getTablename() + " SET " + EventDao.Properties.JapaneseT1Rank.columnName
                + " = " + eventModel.getJapaneseT1Rank() + " WHERE " + EventDao.Properties.End.columnName + " = '"
                + eventModel.getEnd() + "';";
    }

    protected String updateJapaneseT1Points(EventModel eventModel) {
        return "UPDATE " + eventDao.getTablename() + " SET " + EventDao.Properties.JapaneseT1Points.columnName
                + " = " + eventModel.getJapaneseT1Points() + " WHERE " + EventDao.Properties.End.columnName + " = '"
                + eventModel.getEnd() + "';";
    }

    protected String updateJapaneseT2Rank(EventModel eventModel) {
        return "UPDATE " + eventDao.getTablename() + " SET " + EventDao.Properties.JapaneseT2Rank.columnName
                + " = " + eventModel.getJapaneseT2Rank() + " WHERE " + EventDao.Properties.End.columnName + " = '"
                + eventModel.getEnd() + "';";
    }

    protected String updateJapaneseT2Points(EventModel eventModel) {
        return "UPDATE " + eventDao.getTablename() + " SET " + EventDao.Properties.JapaneseT2Points.columnName
                + " = " + eventModel.getJapaneseT2Points() + " WHERE " + EventDao.Properties.End.columnName + " = '"
                + eventModel.getEnd() + "';";
    }

    public void cacheSong(SongModel songModel) {
        Long eventId = getEventId(songModel);
        insertSong(songModel, eventId);
    }

    public void cacheCard(CardModel cardModel) {
        Long characterVoiceId = getCharacterVoiceId(cardModel);
        Long idolId = getIdolId(cardModel, characterVoiceId);
        Long eventId = getEventId(cardModel);
        insertCard(cardModel, idolId, eventId);
    }

    private Long getEventId(CardModel cardModel) {
        Long eventId = NULL_FIELD_ID;
        if (cardModel.getEventModel() != null) {
            eventId = queryEventByName(cardModel.getEventModel());
            if (eventId.equals(NULL_FIELD_ID)) {
                eventId = insertEvent(cardModel.getEventModel());
            }
        }
        return eventId;
    }

    private Long getEventId(SongModel songModel) {
        Long eventId = NULL_FIELD_ID;
        if (songModel.getEventModel() != null) {
            eventId = queryEventByName(songModel.getEventModel());
            if (eventId.equals(NULL_FIELD_ID)) {
                eventId = insertEvent(songModel.getEventModel());
            }
        }
        return eventId;
    }

    private Long getIdolId(CardModel cardModel, Long characterVoiceId) {
        Long idolId = NULL_FIELD_ID;
        if (cardModel.getIdolModel() != null) {
            idolId = queryIdolByName(cardModel);
            if (idolId.equals(NULL_FIELD_ID)) {
                idolId = insertIdol(cardModel.getIdolModel(), characterVoiceId);
            }
        }
        return idolId;
    }

    private Long getCharacterVoiceId(CardModel cardModel) {
        Long characterVoiceId = NULL_FIELD_ID;
        if (cardModel.getIdolModel() != null && cardModel.getIdolModel().getCvModel() != null) {
            characterVoiceId = queryCharacterVoiceByName(cardModel);
            if (characterVoiceId.equals(NULL_FIELD_ID)) {
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

    private void insertSong(SongModel songModel, Long eventId) {
        getSongDao(helper.getWritableDatabase());

        songModel.setEventModel(null);
        Song song = com.thoughtworks.lhli.lovelive_rock.ModelMapper.mapSong(songModel);
        song.setEventId(eventId);
        songDao.insert(song);
    }

    public Long insertEvent(EventModel eventModel) {
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

    public Long queryCardPrimaryKeyById(String cardId) {
        getCardDao(helper.getReadableDatabase());

        List<Card> cardList
                = cardDao.queryBuilder()
                .where(CardDao.Properties.CardId.eq(cardId))
                .list();

        return cardList.size() == 0 ? -1 : cardList.get(0).getId();
    }

    public Long queryEventByName(EventModel eventModel) {
        getEventDao(helper.getReadableDatabase());

        List<Event> eventList = eventDao.queryBuilder()
                .where(EventDao.Properties.JapaneseName.eq(eventModel.getJapaneseName()))
                .list();
        if (eventList.size() != 0) {
            return eventList.get(0).getId();
        } else {
            return NULL_FIELD_ID;
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
            return NULL_FIELD_ID;
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
            return NULL_FIELD_ID;
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

    public EventModel queryLatestEvent() {
        getEventDao(helper.getReadableDatabase());

        List<Event> eventList = eventDao.queryBuilder()
                .where(EventDao.Properties.JapanCurrent.eq(true))
                .list();
        if (eventList.size() != 0) {
            return modelMapper.map(eventList.get(0), EventModel.class);
        }
        return null;
    }

    public List<CardModel> queryAllCards() {
        getCardDao(helper.getReadableDatabase());

        List<Card> cardList = cardDao.queryBuilder()
                .where(CardDao.Properties.CardId.isNotNull())
                .list();
        if (cardList.size() != 0) {
            List<CardModel> cardModelList = new ArrayList<>();
            for (Card card : cardList) {
                cardModelList.add(generateCardModel(card));
            }
            return cardModelList;
        } else {
            return new ArrayList<>();
        }
    }

    public List<CardModel> queryCardsBySkill(String skill) {
        getCardDao(helper.getReadableDatabase());

        List<Card> cardList
                = cardDao.queryBuilder()
                .where(cardDao.queryBuilder().and(CardDao.Properties.Skill.eq(skill),
                        CardDao.Properties.Rarity.eq("R"), CardDao.Properties.IsPromo.eq(false)))
                .list();
        if (cardList.size() != 0) {
            List<CardModel> cardModelList = new ArrayList<>();
            for (Card card : cardList) {
                cardModelList.add(generateCardModel(card));
            }
            return cardModelList;
        } else {
            return new ArrayList<>();
        }
    }

    public List<CardModel> queryEventCards() {
        getCardDao(helper.getReadableDatabase());

        List<Card> cardList
                = cardDao.queryBuilder()
                .where(CardDao.Properties.EventId.notEq(NULL_FIELD_ID))
                .list();
        if (cardList.size() != 0) {
            List<CardModel> cardModelList = new ArrayList<>();
            for (Card card : cardList) {
                cardModelList.add(generateCardModel(card));
            }
            return cardModelList;
        } else {
            return new ArrayList<>();
        }
    }

    public List<EventModel> queryAllEvents() {
        getEventDao(helper.getReadableDatabase());

        List<Event> eventList = eventDao.queryBuilder()
                .where(EventDao.Properties.JapaneseName.isNotNull())
                .list();
        if (eventList.size() != 0) {
            List<EventModel> eventModelList = new ArrayList<>();
            for (Event event : eventList) {
                eventModelList.add(modelMapper.map(event, EventModel.class));
            }
            return eventModelList;
        } else {
            return new ArrayList<>();
        }
    }

    public SongModel querySongByName(String name) {
        getSongDao(helper.getReadableDatabase());

        List<Song> songList = songDao.queryBuilder()
                .where(SongDao.Properties.Name.eq(name))
                .list();
        if (songList.size() != 0) {
            return modelMapper.map(songList.get(0), SongModel.class);
        }
        return null;
    }

    public List<SongModel> queryAllSongs() {
        getSongDao(helper.getReadableDatabase());

        List<Song> songList = songDao.queryBuilder()
                .where(SongDao.Properties.Name.isNotNull())
                .list();
        if (songList.size() != 0) {
            List<SongModel> songModelList = new ArrayList<>();
            for (Song song : songList) {
                songModelList.add(com.thoughtworks.lhli.lovelive_rock.ModelMapper.mapSong(song));
            }
            return songModelList;
        } else {
            return new ArrayList<>();
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

    private void getSongDao(SQLiteDatabase database) {
        openSession(database);
        songDao = daoSession.getSongDao();
    }

    private void openSession(SQLiteDatabase database) {
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }
}
