package com.thoughtworks.lhli.lovelive_rock.data;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

public abstract class MigrationImpl implements Migration {

    protected void prepareMigration(@NonNull SQLiteDatabase db, int currentVersion) {
        if (currentVersion < 1) {
            throw new IllegalArgumentException(
                    "Lowest supported schema version is 1, unable to prepare for migration from version: "
                            + currentVersion);
        }

        if (currentVersion < getTargetVersion()) {
            Migration previousMigration = getPreviousMigration();

            if (previousMigration == null) {
                throw new IllegalStateException(
                        "Unable to apply migration as Version: "
                                + currentVersion
                                + " is not suitable for this Migration.");
            }
            if (previousMigration.applyMigration(db, currentVersion) != getTargetVersion()) {
                throw new IllegalStateException(
                        "Error, expected migration parent to update database to appropriate version");
            }
        }
    }
}
