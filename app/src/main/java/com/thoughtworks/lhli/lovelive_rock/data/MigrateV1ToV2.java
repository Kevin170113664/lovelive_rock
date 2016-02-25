package com.thoughtworks.lhli.lovelive_rock.data;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MigrateV1ToV2 extends MigrationImpl {
    @Override
    public int applyMigration(@NonNull SQLiteDatabase db, int currentVersion) {
        prepareMigration(db, currentVersion);

        SongDao.dropTable(db, true);
        SongDao.createTable(db, true);

        return getMigratedVersion();
    }

    @Nullable
    @Override
    public Migration getPreviousMigration() {
        return null;
    }

    @Override
    public int getTargetVersion() {
        return 1;
    }

    @Override
    public int getMigratedVersion() {
        return 2;
    }
}
