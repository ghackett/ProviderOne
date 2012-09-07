{AutoGenCopyrightMessage}
package {PackageName}.database.autogen;

import java.util.ArrayList;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import {PackageName}.database.{ProjectName}Provider;
{TableInfoImports}

public abstract class Base{ProjectName}Database extends SQLiteOpenHelper {

    public static boolean deleteAllDatabaseRecords() {
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
{BatchDeleteOps}
        try {
            {ProjectName}Provider.getAppContext().getContentResolver().applyBatch({ProjectName}Provider.getContentAuthority(), ops);
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean vacuumDatabase() {
    	try {
    		{ProjectName}Provider.getAppContext().getContentResolver().update(Uri.withAppendedPath({ProjectName}Provider.getBaseContentUri(), {ProjectName}Provider.PATH_VACUUM), null, null, null);
    		return true;
    	} catch (Throwable t) {
    		t.printStackTrace();
    		return false;
    	}
    }

    public static final String DB_NAME = "{DbFileName}";
    public static final int DB_VERSION = {DbVersion};

{IndexDefs}

    public Base{ProjectName}Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
{TableCreates}
{IndexExecutes}
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
{TableUpgrades}
{IndexExecutes}
    }

}
