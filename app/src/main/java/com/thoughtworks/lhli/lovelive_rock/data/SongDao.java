package com.thoughtworks.lhli.lovelive_rock.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.internal.SqlUtils;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SONG".
*/
public class SongDao extends AbstractDao<Song, Long> {

    public static final String TABLENAME = "SONG";

    /**
     * Properties of entity Song.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property RomajiName = new Property(2, String.class, "romajiName", false, "ROMAJI_NAME");
        public final static Property TranslatedName = new Property(3, String.class, "translatedName", false, "TRANSLATED_NAME");
        public final static Property Attribute = new Property(4, String.class, "attribute", false, "ATTRIBUTE");
        public final static Property BPM = new Property(5, Short.class, "BPM", false, "BPM");
        public final static Property Time = new Property(6, Short.class, "time", false, "TIME");
        public final static Property EventId = new Property(7, long.class, "eventId", false, "EVENT_ID");
        public final static Property Rank = new Property(8, Short.class, "rank", false, "RANK");
        public final static Property DailyRotation = new Property(9, String.class, "dailyRotation", false, "DAILY_ROTATION");
        public final static Property DailyRotationPosition = new Property(10, Short.class, "dailyRotationPosition", false, "DAILY_ROTATION_POSITION");
        public final static Property Image = new Property(11, String.class, "image", false, "IMAGE");
        public final static Property EasyDifficulty = new Property(12, Short.class, "easyDifficulty", false, "EASY_DIFFICULTY");
        public final static Property EasyNotes = new Property(13, Short.class, "easyNotes", false, "EASY_NOTES");
        public final static Property NormalDifficulty = new Property(14, Short.class, "normalDifficulty", false, "NORMAL_DIFFICULTY");
        public final static Property NormalNotes = new Property(15, Short.class, "normalNotes", false, "NORMAL_NOTES");
        public final static Property HardDifficulty = new Property(16, Short.class, "hardDifficulty", false, "HARD_DIFFICULTY");
        public final static Property HardNotes = new Property(17, Short.class, "hardNotes", false, "HARD_NOTES");
        public final static Property ExpertDifficulty = new Property(18, Short.class, "expertDifficulty", false, "EXPERT_DIFFICULTY");
        public final static Property ExpertRandomDifficulty = new Property(19, Short.class, "expertRandomDifficulty", false, "EXPERT_RANDOM_DIFFICULTY");
        public final static Property ExpertNotes = new Property(20, Short.class, "expertNotes", false, "EXPERT_NOTES");
        public final static Property MasterDifficulty = new Property(21, Short.class, "masterDifficulty", false, "MASTER_DIFFICULTY");
        public final static Property MasterNotes = new Property(22, Short.class, "masterNotes", false, "MASTER_NOTES");
        public final static Property Available = new Property(23, Boolean.class, "available", false, "AVAILABLE");
        public final static Property ItunesId = new Property(24, Long.class, "itunesId", false, "ITUNES_ID");
    };

    private DaoSession daoSession;


    public SongDao(DaoConfig config) {
        super(config);
    }
    
    public SongDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SONG\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"ROMAJI_NAME\" TEXT," + // 2: romajiName
                "\"TRANSLATED_NAME\" TEXT," + // 3: translatedName
                "\"ATTRIBUTE\" TEXT," + // 4: attribute
                "\"BPM\" INTEGER," + // 5: BPM
                "\"TIME\" INTEGER," + // 6: time
                "\"EVENT_ID\" INTEGER NOT NULL ," + // 7: eventId
                "\"RANK\" INTEGER," + // 8: rank
                "\"DAILY_ROTATION\" TEXT," + // 9: dailyRotation
                "\"DAILY_ROTATION_POSITION\" INTEGER," + // 10: dailyRotationPosition
                "\"IMAGE\" TEXT," + // 11: image
                "\"EASY_DIFFICULTY\" INTEGER," + // 12: easyDifficulty
                "\"EASY_NOTES\" INTEGER," + // 13: easyNotes
                "\"NORMAL_DIFFICULTY\" INTEGER," + // 14: normalDifficulty
                "\"NORMAL_NOTES\" INTEGER," + // 15: normalNotes
                "\"HARD_DIFFICULTY\" INTEGER," + // 16: hardDifficulty
                "\"HARD_NOTES\" INTEGER," + // 17: hardNotes
                "\"EXPERT_DIFFICULTY\" INTEGER," + // 18: expertDifficulty
                "\"EXPERT_RANDOM_DIFFICULTY\" INTEGER," + // 19: expertRandomDifficulty
                "\"EXPERT_NOTES\" INTEGER," + // 20: expertNotes
                "\"MASTER_DIFFICULTY\" INTEGER," + // 21: masterDifficulty
                "\"MASTER_NOTES\" INTEGER," + // 22: masterNotes
                "\"AVAILABLE\" INTEGER," + // 23: available
                "\"ITUNES_ID\" INTEGER);"); // 24: itunesId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SONG\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Song entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String romajiName = entity.getRomajiName();
        if (romajiName != null) {
            stmt.bindString(3, romajiName);
        }
 
        String translatedName = entity.getTranslatedName();
        if (translatedName != null) {
            stmt.bindString(4, translatedName);
        }
 
        String attribute = entity.getAttribute();
        if (attribute != null) {
            stmt.bindString(5, attribute);
        }
 
        Short BPM = entity.getBPM();
        if (BPM != null) {
            stmt.bindLong(6, BPM);
        }
 
        Short time = entity.getTime();
        if (time != null) {
            stmt.bindLong(7, time);
        }
        stmt.bindLong(8, entity.getEventId());
 
        Short rank = entity.getRank();
        if (rank != null) {
            stmt.bindLong(9, rank);
        }
 
        String dailyRotation = entity.getDailyRotation();
        if (dailyRotation != null) {
            stmt.bindString(10, dailyRotation);
        }
 
        Short dailyRotationPosition = entity.getDailyRotationPosition();
        if (dailyRotationPosition != null) {
            stmt.bindLong(11, dailyRotationPosition);
        }
 
        String image = entity.getImage();
        if (image != null) {
            stmt.bindString(12, image);
        }
 
        Short easyDifficulty = entity.getEasyDifficulty();
        if (easyDifficulty != null) {
            stmt.bindLong(13, easyDifficulty);
        }
 
        Short easyNotes = entity.getEasyNotes();
        if (easyNotes != null) {
            stmt.bindLong(14, easyNotes);
        }
 
        Short normalDifficulty = entity.getNormalDifficulty();
        if (normalDifficulty != null) {
            stmt.bindLong(15, normalDifficulty);
        }
 
        Short normalNotes = entity.getNormalNotes();
        if (normalNotes != null) {
            stmt.bindLong(16, normalNotes);
        }
 
        Short hardDifficulty = entity.getHardDifficulty();
        if (hardDifficulty != null) {
            stmt.bindLong(17, hardDifficulty);
        }
 
        Short hardNotes = entity.getHardNotes();
        if (hardNotes != null) {
            stmt.bindLong(18, hardNotes);
        }
 
        Short expertDifficulty = entity.getExpertDifficulty();
        if (expertDifficulty != null) {
            stmt.bindLong(19, expertDifficulty);
        }
 
        Short expertRandomDifficulty = entity.getExpertRandomDifficulty();
        if (expertRandomDifficulty != null) {
            stmt.bindLong(20, expertRandomDifficulty);
        }
 
        Short expertNotes = entity.getExpertNotes();
        if (expertNotes != null) {
            stmt.bindLong(21, expertNotes);
        }
 
        Short masterDifficulty = entity.getMasterDifficulty();
        if (masterDifficulty != null) {
            stmt.bindLong(22, masterDifficulty);
        }
 
        Short masterNotes = entity.getMasterNotes();
        if (masterNotes != null) {
            stmt.bindLong(23, masterNotes);
        }
 
        Boolean available = entity.getAvailable();
        if (available != null) {
            stmt.bindLong(24, available ? 1L: 0L);
        }
 
        Long itunesId = entity.getItunesId();
        if (itunesId != null) {
            stmt.bindLong(25, itunesId);
        }
    }

    @Override
    protected void attachEntity(Song entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Song readEntity(Cursor cursor, int offset) {
        Song entity = new Song( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // romajiName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // translatedName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // attribute
            cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5), // BPM
            cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6), // time
            cursor.getLong(offset + 7), // eventId
            cursor.isNull(offset + 8) ? null : cursor.getShort(offset + 8), // rank
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // dailyRotation
            cursor.isNull(offset + 10) ? null : cursor.getShort(offset + 10), // dailyRotationPosition
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // image
            cursor.isNull(offset + 12) ? null : cursor.getShort(offset + 12), // easyDifficulty
            cursor.isNull(offset + 13) ? null : cursor.getShort(offset + 13), // easyNotes
            cursor.isNull(offset + 14) ? null : cursor.getShort(offset + 14), // normalDifficulty
            cursor.isNull(offset + 15) ? null : cursor.getShort(offset + 15), // normalNotes
            cursor.isNull(offset + 16) ? null : cursor.getShort(offset + 16), // hardDifficulty
            cursor.isNull(offset + 17) ? null : cursor.getShort(offset + 17), // hardNotes
            cursor.isNull(offset + 18) ? null : cursor.getShort(offset + 18), // expertDifficulty
            cursor.isNull(offset + 19) ? null : cursor.getShort(offset + 19), // expertRandomDifficulty
            cursor.isNull(offset + 20) ? null : cursor.getShort(offset + 20), // expertNotes
            cursor.isNull(offset + 21) ? null : cursor.getShort(offset + 21), // masterDifficulty
            cursor.isNull(offset + 22) ? null : cursor.getShort(offset + 22), // masterNotes
            cursor.isNull(offset + 23) ? null : cursor.getShort(offset + 23) != 0, // available
            cursor.isNull(offset + 24) ? null : cursor.getLong(offset + 24) // itunesId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Song entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setRomajiName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTranslatedName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAttribute(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setBPM(cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5));
        entity.setTime(cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6));
        entity.setEventId(cursor.getLong(offset + 7));
        entity.setRank(cursor.isNull(offset + 8) ? null : cursor.getShort(offset + 8));
        entity.setDailyRotation(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setDailyRotationPosition(cursor.isNull(offset + 10) ? null : cursor.getShort(offset + 10));
        entity.setImage(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setEasyDifficulty(cursor.isNull(offset + 12) ? null : cursor.getShort(offset + 12));
        entity.setEasyNotes(cursor.isNull(offset + 13) ? null : cursor.getShort(offset + 13));
        entity.setNormalDifficulty(cursor.isNull(offset + 14) ? null : cursor.getShort(offset + 14));
        entity.setNormalNotes(cursor.isNull(offset + 15) ? null : cursor.getShort(offset + 15));
        entity.setHardDifficulty(cursor.isNull(offset + 16) ? null : cursor.getShort(offset + 16));
        entity.setHardNotes(cursor.isNull(offset + 17) ? null : cursor.getShort(offset + 17));
        entity.setExpertDifficulty(cursor.isNull(offset + 18) ? null : cursor.getShort(offset + 18));
        entity.setExpertRandomDifficulty(cursor.isNull(offset + 19) ? null : cursor.getShort(offset + 19));
        entity.setExpertNotes(cursor.isNull(offset + 20) ? null : cursor.getShort(offset + 20));
        entity.setMasterDifficulty(cursor.isNull(offset + 21) ? null : cursor.getShort(offset + 21));
        entity.setMasterNotes(cursor.isNull(offset + 22) ? null : cursor.getShort(offset + 22));
        entity.setAvailable(cursor.isNull(offset + 23) ? null : cursor.getShort(offset + 23) != 0);
        entity.setItunesId(cursor.isNull(offset + 24) ? null : cursor.getLong(offset + 24));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Song entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Song entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getEventDao().getAllColumns());
            builder.append(" FROM SONG T");
            builder.append(" LEFT JOIN EVENT T0 ON T.\"EVENT_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Song loadCurrentDeep(Cursor cursor, boolean lock) {
        Song entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Event event = loadCurrentOther(daoSession.getEventDao(), cursor, offset);
         if(event != null) {
            entity.setEvent(event);
        }

        return entity;    
    }

    public Song loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Song> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Song> list = new ArrayList<Song>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Song> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Song> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
