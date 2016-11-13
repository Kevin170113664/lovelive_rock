package com.thoughtworks.lhli.lovelive_rock.data;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MigrateV3ToV4 extends MigrationImpl {
    @Override
    public int applyMigration(@NonNull SQLiteDatabase db, int currentVersion) {
        prepareMigration(db, currentVersion);

        db.execSQL("ALTER TABLE SONG ADD COLUMN 'MASTER_DIFFICULTY' INTEGER;");
        db.execSQL("ALTER TABLE SONG ADD COLUMN 'MASTER_NOTES' INTEGER;");

        return getMigratedVersion();
    }

    @Nullable
    @Override
    public Migration getPreviousMigration() {
        return new MigrateV2ToV3();
    }

    @Override
    public int getTargetVersion() {
        return 3;
    }

    @Override
    public int getMigratedVersion() {
        return 4;
    }
}
