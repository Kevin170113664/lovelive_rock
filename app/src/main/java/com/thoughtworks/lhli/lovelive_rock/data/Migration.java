package com.thoughtworks.lhli.lovelive_rock.data;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface Migration {

    int applyMigration(@NonNull SQLiteDatabase db, int currentVersion);

    @Nullable
    Migration getPreviousMigration();

    int getTargetVersion();

    int getMigratedVersion();
}
