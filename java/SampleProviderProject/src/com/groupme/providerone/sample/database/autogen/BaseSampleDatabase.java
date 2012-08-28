/*
 * This file has been auto-generated by ProviderOne
 *
 * Copyright (C) 2011 GroupMe, Inc.
 */
package com.groupme.providerone.sample.database.autogen;

import java.util.ArrayList;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.groupme.providerone.sample.database.SampleProvider;
import com.groupme.providerone.sample.database.tables.MyTableInfo;
import com.groupme.providerone.sample.database.tables.MyViewInfo;


public abstract class BaseSampleDatabase extends SQLiteOpenHelper {

    public static boolean deleteAllDatabaseRecords() {
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
		ops.add(ContentProviderOperation.newDelete(MyTableInfo.CONTENT_URI).build());

        try {
            SampleProvider.getAppContext().getContentResolver().applyBatch(SampleProvider.getContentAuthority(), ops);
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean vacuumDatabase() {
    	try {
    		SampleProvider.getAppContext().getContentResolver().update(Uri.withAppendedPath(SampleProvider.getBaseContentUri(), SampleProvider.PATH_VACUUM), null, null, null);
    		return true;
    	} catch (Throwable t) {
    		t.printStackTrace();
    		return false;
    	}
    }

    public static final String DB_NAME = "my_database.sqlite";
    public static final int DB_VERSION = 25;

	public static final String IDX_CREATE_SAMPLE_IDX = "CREATE INDEX \"sample_idx\" ON \"my_table\" (\"my_double\")";
	public static final String IDX_DROP_SAMPLE_IDX = "DROP INDEX IF EXISTS \"sample_idx\"";


    public BaseSampleDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
		MyTableInfo.createTable(db);
		MyViewInfo.createTable(db);

		db.execSQL(IDX_DROP_SAMPLE_IDX);
		db.execSQL(IDX_CREATE_SAMPLE_IDX);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		MyTableInfo.upgradeTable(db, oldVersion, newVersion);
		MyViewInfo.upgradeTable(db, oldVersion, newVersion);

		db.execSQL(IDX_DROP_SAMPLE_IDX);
		db.execSQL(IDX_CREATE_SAMPLE_IDX);

    }

}
