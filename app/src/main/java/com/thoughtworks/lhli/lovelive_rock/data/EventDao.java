package com.thoughtworks.lhli.lovelive_rock.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "EVENT".
*/
public class EventDao extends AbstractDao<Event, Long> {

    public static final String TABLENAME = "EVENT";

    /**
     * Properties of entity Event.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property JapaneseName = new Property(1, String.class, "japaneseName", false, "JAPANESE_NAME");
        public final static Property RomajiName = new Property(2, String.class, "romajiName", false, "ROMAJI_NAME");
        public final static Property EnglishName = new Property(3, String.class, "englishName", false, "ENGLISH_NAME");
        public final static Property Image = new Property(4, String.class, "image", false, "IMAGE");
        public final static Property EnglishImage = new Property(5, String.class, "englishImage", false, "ENGLISH_IMAGE");
        public final static Property Beginning = new Property(6, String.class, "beginning", false, "BEGINNING");
        public final static Property End = new Property(7, String.class, "end", false, "END");
        public final static Property EnglishBeginning = new Property(8, String.class, "englishBeginning", false, "ENGLISH_BEGINNING");
        public final static Property EnglishEnd = new Property(9, String.class, "englishEnd", false, "ENGLISH_END");
        public final static Property JapanCurrent = new Property(10, Boolean.class, "japanCurrent", false, "JAPAN_CURRENT");
        public final static Property WorldCurrent = new Property(11, Boolean.class, "worldCurrent", false, "WORLD_CURRENT");
        public final static Property Song = new Property(12, String.class, "song", false, "SONG");
        public final static Property JapaneseT1Points = new Property(13, Long.class, "japaneseT1Points", false, "JAPANESE_T1_POINTS");
        public final static Property JapaneseT1Rank = new Property(14, Long.class, "japaneseT1Rank", false, "JAPANESE_T1_RANK");
        public final static Property JapaneseT2Points = new Property(15, Long.class, "japaneseT2Points", false, "JAPANESE_T2_POINTS");
        public final static Property JapaneseT2Rank = new Property(16, Long.class, "japaneseT2Rank", false, "JAPANESE_T2_RANK");
        public final static Property EnglishT1Points = new Property(17, Long.class, "englishT1Points", false, "ENGLISH_T1_POINTS");
        public final static Property EnglishT1Rank = new Property(18, Long.class, "englishT1Rank", false, "ENGLISH_T1_RANK");
        public final static Property EnglishT2Points = new Property(19, Long.class, "englishT2Points", false, "ENGLISH_T2_POINTS");
        public final static Property EnglishT2Rank = new Property(20, Long.class, "englishT2Rank", false, "ENGLISH_T2_RANK");
        public final static Property Note = new Property(21, String.class, "note", false, "NOTE");
    };


    public EventDao(DaoConfig config) {
        super(config);
    }
    
    public EventDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"EVENT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"JAPANESE_NAME\" TEXT," + // 1: japaneseName
                "\"ROMAJI_NAME\" TEXT," + // 2: romajiName
                "\"ENGLISH_NAME\" TEXT," + // 3: englishName
                "\"IMAGE\" TEXT," + // 4: image
                "\"ENGLISH_IMAGE\" TEXT," + // 5: englishImage
                "\"BEGINNING\" TEXT," + // 6: beginning
                "\"END\" TEXT," + // 7: end
                "\"ENGLISH_BEGINNING\" TEXT," + // 8: englishBeginning
                "\"ENGLISH_END\" TEXT," + // 9: englishEnd
                "\"JAPAN_CURRENT\" INTEGER," + // 10: japanCurrent
                "\"WORLD_CURRENT\" INTEGER," + // 11: worldCurrent
                "\"SONG\" TEXT," + // 12: song
                "\"JAPANESE_T1_POINTS\" INTEGER," + // 13: japaneseT1Points
                "\"JAPANESE_T1_RANK\" INTEGER," + // 14: japaneseT1Rank
                "\"JAPANESE_T2_POINTS\" INTEGER," + // 15: japaneseT2Points
                "\"JAPANESE_T2_RANK\" INTEGER," + // 16: japaneseT2Rank
                "\"ENGLISH_T1_POINTS\" INTEGER," + // 17: englishT1Points
                "\"ENGLISH_T1_RANK\" INTEGER," + // 18: englishT1Rank
                "\"ENGLISH_T2_POINTS\" INTEGER," + // 19: englishT2Points
                "\"ENGLISH_T2_RANK\" INTEGER," + // 20: englishT2Rank
                "\"NOTE\" TEXT);"); // 21: note
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"EVENT\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Event entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String japaneseName = entity.getJapaneseName();
        if (japaneseName != null) {
            stmt.bindString(2, japaneseName);
        }
 
        String romajiName = entity.getRomajiName();
        if (romajiName != null) {
            stmt.bindString(3, romajiName);
        }
 
        String englishName = entity.getEnglishName();
        if (englishName != null) {
            stmt.bindString(4, englishName);
        }
 
        String image = entity.getImage();
        if (image != null) {
            stmt.bindString(5, image);
        }
 
        String englishImage = entity.getEnglishImage();
        if (englishImage != null) {
            stmt.bindString(6, englishImage);
        }
 
        String beginning = entity.getBeginning();
        if (beginning != null) {
            stmt.bindString(7, beginning);
        }
 
        String end = entity.getEnd();
        if (end != null) {
            stmt.bindString(8, end);
        }
 
        String englishBeginning = entity.getEnglishBeginning();
        if (englishBeginning != null) {
            stmt.bindString(9, englishBeginning);
        }
 
        String englishEnd = entity.getEnglishEnd();
        if (englishEnd != null) {
            stmt.bindString(10, englishEnd);
        }
 
        Boolean japanCurrent = entity.getJapanCurrent();
        if (japanCurrent != null) {
            stmt.bindLong(11, japanCurrent ? 1L: 0L);
        }
 
        Boolean worldCurrent = entity.getWorldCurrent();
        if (worldCurrent != null) {
            stmt.bindLong(12, worldCurrent ? 1L: 0L);
        }
 
        String song = entity.getSong();
        if (song != null) {
            stmt.bindString(13, song);
        }
 
        Long japaneseT1Points = entity.getJapaneseT1Points();
        if (japaneseT1Points != null) {
            stmt.bindLong(14, japaneseT1Points);
        }
 
        Long japaneseT1Rank = entity.getJapaneseT1Rank();
        if (japaneseT1Rank != null) {
            stmt.bindLong(15, japaneseT1Rank);
        }
 
        Long japaneseT2Points = entity.getJapaneseT2Points();
        if (japaneseT2Points != null) {
            stmt.bindLong(16, japaneseT2Points);
        }
 
        Long japaneseT2Rank = entity.getJapaneseT2Rank();
        if (japaneseT2Rank != null) {
            stmt.bindLong(17, japaneseT2Rank);
        }
 
        Long englishT1Points = entity.getEnglishT1Points();
        if (englishT1Points != null) {
            stmt.bindLong(18, englishT1Points);
        }
 
        Long englishT1Rank = entity.getEnglishT1Rank();
        if (englishT1Rank != null) {
            stmt.bindLong(19, englishT1Rank);
        }
 
        Long englishT2Points = entity.getEnglishT2Points();
        if (englishT2Points != null) {
            stmt.bindLong(20, englishT2Points);
        }
 
        Long englishT2Rank = entity.getEnglishT2Rank();
        if (englishT2Rank != null) {
            stmt.bindLong(21, englishT2Rank);
        }
 
        String note = entity.getNote();
        if (note != null) {
            stmt.bindString(22, note);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Event readEntity(Cursor cursor, int offset) {
        Event entity = new Event( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // japaneseName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // romajiName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // englishName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // image
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // englishImage
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // beginning
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // end
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // englishBeginning
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // englishEnd
            cursor.isNull(offset + 10) ? null : cursor.getShort(offset + 10) != 0, // japanCurrent
            cursor.isNull(offset + 11) ? null : cursor.getShort(offset + 11) != 0, // worldCurrent
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // song
            cursor.isNull(offset + 13) ? null : cursor.getLong(offset + 13), // japaneseT1Points
            cursor.isNull(offset + 14) ? null : cursor.getLong(offset + 14), // japaneseT1Rank
            cursor.isNull(offset + 15) ? null : cursor.getLong(offset + 15), // japaneseT2Points
            cursor.isNull(offset + 16) ? null : cursor.getLong(offset + 16), // japaneseT2Rank
            cursor.isNull(offset + 17) ? null : cursor.getLong(offset + 17), // englishT1Points
            cursor.isNull(offset + 18) ? null : cursor.getLong(offset + 18), // englishT1Rank
            cursor.isNull(offset + 19) ? null : cursor.getLong(offset + 19), // englishT2Points
            cursor.isNull(offset + 20) ? null : cursor.getLong(offset + 20), // englishT2Rank
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21) // note
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Event entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setJapaneseName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setRomajiName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setEnglishName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setImage(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setEnglishImage(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setBeginning(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setEnd(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setEnglishBeginning(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setEnglishEnd(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setJapanCurrent(cursor.isNull(offset + 10) ? null : cursor.getShort(offset + 10) != 0);
        entity.setWorldCurrent(cursor.isNull(offset + 11) ? null : cursor.getShort(offset + 11) != 0);
        entity.setSong(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setJapaneseT1Points(cursor.isNull(offset + 13) ? null : cursor.getLong(offset + 13));
        entity.setJapaneseT1Rank(cursor.isNull(offset + 14) ? null : cursor.getLong(offset + 14));
        entity.setJapaneseT2Points(cursor.isNull(offset + 15) ? null : cursor.getLong(offset + 15));
        entity.setJapaneseT2Rank(cursor.isNull(offset + 16) ? null : cursor.getLong(offset + 16));
        entity.setEnglishT1Points(cursor.isNull(offset + 17) ? null : cursor.getLong(offset + 17));
        entity.setEnglishT1Rank(cursor.isNull(offset + 18) ? null : cursor.getLong(offset + 18));
        entity.setEnglishT2Points(cursor.isNull(offset + 19) ? null : cursor.getLong(offset + 19));
        entity.setEnglishT2Rank(cursor.isNull(offset + 20) ? null : cursor.getLong(offset + 20));
        entity.setNote(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Event entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Event entity) {
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
    
}
