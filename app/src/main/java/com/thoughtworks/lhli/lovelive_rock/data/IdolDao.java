package com.thoughtworks.lhli.lovelive_rock.data;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;

import com.thoughtworks.lhli.lovelive_rock.data.Idol;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "IDOL".
*/
public class IdolDao extends AbstractDao<Idol, Long> {

    public static final String TABLENAME = "IDOL";

    /**
     * Properties of entity Idol.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property JapaneseName = new Property(2, String.class, "japaneseName", false, "JAPANESE_NAME");
        public final static Property Main = new Property(3, Boolean.class, "main", false, "MAIN");
        public final static Property Age = new Property(4, Short.class, "age", false, "AGE");
        public final static Property Birthday = new Property(5, String.class, "birthday", false, "BIRTHDAY");
        public final static Property AstrologicalSign = new Property(6, String.class, "astrologicalSign", false, "ASTROLOGICAL_SIGN");
        public final static Property Blood = new Property(7, String.class, "blood", false, "BLOOD");
        public final static Property Height = new Property(8, Short.class, "height", false, "HEIGHT");
        public final static Property Measurements = new Property(9, String.class, "measurements", false, "MEASUREMENTS");
        public final static Property FavoriteFood = new Property(10, String.class, "favoriteFood", false, "FAVORITE_FOOD");
        public final static Property LeastFavoriteFood = new Property(11, String.class, "leastFavoriteFood", false, "LEAST_FAVORITE_FOOD");
        public final static Property Hobbies = new Property(12, String.class, "hobbies", false, "HOBBIES");
        public final static Property Attribute = new Property(13, String.class, "attribute", false, "ATTRIBUTE");
        public final static Property Year = new Property(14, String.class, "year", false, "YEAR");
        public final static Property SubUnit = new Property(15, String.class, "subUnit", false, "SUB_UNIT");
        public final static Property CharacterVoiceId = new Property(16, long.class, "characterVoiceId", false, "CHARACTER_VOICE_ID");
        public final static Property Summary = new Property(17, String.class, "summary", false, "SUMMARY");
        public final static Property WebsiteUrl = new Property(18, String.class, "websiteUrl", false, "WEBSITE_URL");
        public final static Property WikiUrl = new Property(19, String.class, "wikiUrl", false, "WIKI_URL");
        public final static Property WikiaUrl = new Property(20, String.class, "wikiaUrl", false, "WIKIA_URL");
        public final static Property OfficialUrl = new Property(21, String.class, "officialUrl", false, "OFFICIAL_URL");
        public final static Property Chibi = new Property(22, String.class, "chibi", false, "CHIBI");
        public final static Property ChibiSmall = new Property(23, String.class, "chibiSmall", false, "CHIBI_SMALL");
    };

    private DaoSession daoSession;


    public IdolDao(DaoConfig config) {
        super(config);
    }
    
    public IdolDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"IDOL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"JAPANESE_NAME\" TEXT," + // 2: japaneseName
                "\"MAIN\" INTEGER," + // 3: main
                "\"AGE\" INTEGER," + // 4: age
                "\"BIRTHDAY\" TEXT," + // 5: birthday
                "\"ASTROLOGICAL_SIGN\" TEXT," + // 6: astrologicalSign
                "\"BLOOD\" TEXT," + // 7: blood
                "\"HEIGHT\" INTEGER," + // 8: height
                "\"MEASUREMENTS\" TEXT," + // 9: measurements
                "\"FAVORITE_FOOD\" TEXT," + // 10: favoriteFood
                "\"LEAST_FAVORITE_FOOD\" TEXT," + // 11: leastFavoriteFood
                "\"HOBBIES\" TEXT," + // 12: hobbies
                "\"ATTRIBUTE\" TEXT," + // 13: attribute
                "\"YEAR\" TEXT," + // 14: year
                "\"SUB_UNIT\" TEXT," + // 15: subUnit
                "\"CHARACTER_VOICE_ID\" INTEGER NOT NULL ," + // 16: characterVoiceId
                "\"SUMMARY\" TEXT," + // 17: summary
                "\"WEBSITE_URL\" TEXT," + // 18: websiteUrl
                "\"WIKI_URL\" TEXT," + // 19: wikiUrl
                "\"WIKIA_URL\" TEXT," + // 20: wikiaUrl
                "\"OFFICIAL_URL\" TEXT," + // 21: officialUrl
                "\"CHIBI\" TEXT," + // 22: chibi
                "\"CHIBI_SMALL\" TEXT);"); // 23: chibiSmall
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"IDOL\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Idol entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String japaneseName = entity.getJapaneseName();
        if (japaneseName != null) {
            stmt.bindString(3, japaneseName);
        }
 
        Boolean main = entity.getMain();
        if (main != null) {
            stmt.bindLong(4, main ? 1L: 0L);
        }
 
        Short age = entity.getAge();
        if (age != null) {
            stmt.bindLong(5, age);
        }
 
        String birthday = entity.getBirthday();
        if (birthday != null) {
            stmt.bindString(6, birthday);
        }
 
        String astrologicalSign = entity.getAstrologicalSign();
        if (astrologicalSign != null) {
            stmt.bindString(7, astrologicalSign);
        }
 
        String blood = entity.getBlood();
        if (blood != null) {
            stmt.bindString(8, blood);
        }
 
        Short height = entity.getHeight();
        if (height != null) {
            stmt.bindLong(9, height);
        }
 
        String measurements = entity.getMeasurements();
        if (measurements != null) {
            stmt.bindString(10, measurements);
        }
 
        String favoriteFood = entity.getFavoriteFood();
        if (favoriteFood != null) {
            stmt.bindString(11, favoriteFood);
        }
 
        String leastFavoriteFood = entity.getLeastFavoriteFood();
        if (leastFavoriteFood != null) {
            stmt.bindString(12, leastFavoriteFood);
        }
 
        String hobbies = entity.getHobbies();
        if (hobbies != null) {
            stmt.bindString(13, hobbies);
        }
 
        String attribute = entity.getAttribute();
        if (attribute != null) {
            stmt.bindString(14, attribute);
        }
 
        String year = entity.getYear();
        if (year != null) {
            stmt.bindString(15, year);
        }
 
        String subUnit = entity.getSubUnit();
        if (subUnit != null) {
            stmt.bindString(16, subUnit);
        }
        stmt.bindLong(17, entity.getCharacterVoiceId());
 
        String summary = entity.getSummary();
        if (summary != null) {
            stmt.bindString(18, summary);
        }
 
        String websiteUrl = entity.getWebsiteUrl();
        if (websiteUrl != null) {
            stmt.bindString(19, websiteUrl);
        }
 
        String wikiUrl = entity.getWikiUrl();
        if (wikiUrl != null) {
            stmt.bindString(20, wikiUrl);
        }
 
        String wikiaUrl = entity.getWikiaUrl();
        if (wikiaUrl != null) {
            stmt.bindString(21, wikiaUrl);
        }
 
        String officialUrl = entity.getOfficialUrl();
        if (officialUrl != null) {
            stmt.bindString(22, officialUrl);
        }
 
        String chibi = entity.getChibi();
        if (chibi != null) {
            stmt.bindString(23, chibi);
        }
 
        String chibiSmall = entity.getChibiSmall();
        if (chibiSmall != null) {
            stmt.bindString(24, chibiSmall);
        }
    }

    @Override
    protected void attachEntity(Idol entity) {
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
    public Idol readEntity(Cursor cursor, int offset) {
        Idol entity = new Idol( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // japaneseName
            cursor.isNull(offset + 3) ? null : cursor.getShort(offset + 3) != 0, // main
            cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4), // age
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // birthday
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // astrologicalSign
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // blood
            cursor.isNull(offset + 8) ? null : cursor.getShort(offset + 8), // height
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // measurements
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // favoriteFood
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // leastFavoriteFood
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // hobbies
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // attribute
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // year
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // subUnit
            cursor.getLong(offset + 16), // characterVoiceId
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // summary
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // websiteUrl
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // wikiUrl
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // wikiaUrl
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // officialUrl
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // chibi
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23) // chibiSmall
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Idol entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setJapaneseName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMain(cursor.isNull(offset + 3) ? null : cursor.getShort(offset + 3) != 0);
        entity.setAge(cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4));
        entity.setBirthday(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setAstrologicalSign(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setBlood(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setHeight(cursor.isNull(offset + 8) ? null : cursor.getShort(offset + 8));
        entity.setMeasurements(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setFavoriteFood(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setLeastFavoriteFood(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setHobbies(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setAttribute(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setYear(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setSubUnit(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setCharacterVoiceId(cursor.getLong(offset + 16));
        entity.setSummary(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setWebsiteUrl(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setWikiUrl(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setWikiaUrl(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setOfficialUrl(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setChibi(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setChibiSmall(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Idol entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Idol entity) {
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
            SqlUtils.appendColumns(builder, "T0", daoSession.getCharacterVoiceDao().getAllColumns());
            builder.append(" FROM IDOL T");
            builder.append(" LEFT JOIN CHARACTER_VOICE T0 ON T.\"CHARACTER_VOICE_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Idol loadCurrentDeep(Cursor cursor, boolean lock) {
        Idol entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        CharacterVoice characterVoice = loadCurrentOther(daoSession.getCharacterVoiceDao(), cursor, offset);
         if(characterVoice != null) {
            entity.setCharacterVoice(characterVoice);
        }

        return entity;    
    }

    public Idol loadDeep(Long key) {
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
    public List<Idol> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Idol> list = new ArrayList<Idol>(count);
        
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
    
    protected List<Idol> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Idol> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
