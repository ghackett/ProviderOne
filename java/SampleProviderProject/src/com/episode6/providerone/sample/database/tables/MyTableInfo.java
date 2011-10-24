package com.episode6.providerone.sample.database.tables;

import android.database.sqlite.SQLiteDatabase;

import com.episode6.providerone.sample.database.autogen.tables.BaseMyTableInfo;

public class MyTableInfo extends BaseMyTableInfo {

    public static void upgradeTable(SQLiteDatabase db, int oldVersion, int newVersion) {
        BaseMyTableInfo.upgradeTable(db, oldVersion, newVersion);
    }
    
    public static class ColumnHelper extends BaseMyTableInfo.ColumnHelper {

        public ColumnHelper(String[] projection) {
            super(projection);
        }
        
    }
}
