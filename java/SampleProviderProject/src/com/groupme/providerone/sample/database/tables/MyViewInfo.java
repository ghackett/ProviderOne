/*
 * Copyright (C) 2011 GroupMe, Inc.
 */
package com.groupme.providerone.sample.database.tables;

import android.database.sqlite.SQLiteDatabase;

import com.groupme.providerone.sample.database.autogen.tables.BaseMyViewInfo;

public class MyViewInfo extends BaseMyViewInfo {

    public interface Columns extends BaseMyViewInfo.Columns {

    }

    public static void upgradeTable(SQLiteDatabase db, int oldVersion, int newVersion) {
        BaseMyViewInfo.upgradeTable(db, oldVersion, newVersion);
    }

    public static class ColumnHelper extends BaseMyViewInfo.ColumnHelper {

        public ColumnHelper(String[] projection) {
            super(projection);
        }

    }
}
