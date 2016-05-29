package com.thoughtworks.lhli.lovelive_rock.data;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MigrateV2ToV3 extends MigrationImpl {
    @Override
    public int applyMigration(@NonNull SQLiteDatabase db, int currentVersion) {
        prepareMigration(db, currentVersion);

        db.execSQL("ALTER TABLE CARD ADD COLUMN 'CLEAN_UR' TEXT;");
        db.execSQL("ALTER TABLE CARD ADD COLUMN 'CLEAN_UR_IDOLIZED' TEXT;");

        return getMigratedVersion();
    }

    @Nullable
    @Override
    public Migration getPreviousMigration() {
        return new MigrateV1ToV2();
    }

    @Override
    public int getTargetVersion() {
        return 2;
    }

    @Override
    public int getMigratedVersion() {
        return 3;
    }
}
