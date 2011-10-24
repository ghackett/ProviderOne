package com.episode6.providerone.sample.database.autogen.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.episode6.providerone.sample.database.SampleProvider;
import com.episode6.providerone.sample.database.autogen.PersistentObject;

public class BaseMyTableInfo  {
    
    public interface Columns {
        String _ID = "_id";
        String MY_BOOLEAN = "my_boolean";
        String MY_DOUBLE = "my_double";
        String MY_FLOAT = "my_float";
        String MY_INT = "my_int";
        String MY_LONG = "my_long";
        String MY_CHAR = "my_char";
        String MY_STRING = "my_string";
        String MY_BLOB = "my_blob";
        String MY_TIME = "my_time";
    }
    
    public static final String TABLE_NAME = "my_table";
    public static final String SQL_CREATE = "CREATE TABLE \"my_table\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT,\"my_boolean\" BOOL,\"my_double\" DOUBLE,\"my_float\" FLOAT,\"my_int\" INTEGER,\"my_long\" LONG,\"my_char\" CHAR,\"my_string\" TEXT,\"my_blob\" BLOB,\"my_time\" DATETIME)";
    public static final String SQL_DROP = "DROP TABLE IF EXISTS \"my_table\"";
    
    public static final String[] ALL_COLUMNS = new String[] {
        Columns._ID,
        Columns.MY_BOOLEAN,
        Columns.MY_DOUBLE,
        Columns.MY_FLOAT,
        Columns.MY_INT,
        Columns.MY_LONG,
        Columns.MY_CHAR,
        Columns.MY_STRING,
        Columns.MY_BLOB,
        Columns.MY_TIME
    };
    
//    public static final String LOOKUP_COLUMN = Columns.MY_STRING;
    public static final String PATH = "my_table";
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.episode6.providerone.sample.my_table";
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.episode6.providerone.sample.my_table";
    public static final Uri CONTENT_URI = Uri.withAppendedPath(SampleProvider.getBaseContentUri(), PATH);
    public static final Uri COUNT_URI = Uri.withAppendedPath(CONTENT_URI, SampleProvider.RAW_PATH_COUNT);
    public static final Uri LOOKUP_URI = Uri.withAppendedPath(CONTENT_URI, SampleProvider.RAW_PATH_LOOKUP);
    public static final Uri ID_LOOKUP_URI = Uri.withAppendedPath(CONTENT_URI, SampleProvider.RAW_PATH_ID);
    
    public static final int INSERT_ALGORITHM = SQLiteDatabase.CONFLICT_IGNORE;
    public static final int UPDATE_ALGORITHM = SQLiteDatabase.CONFLICT_IGNORE;
    
    public static void createTable(SQLiteDatabase db) {
        db.execSQL(SQL_DROP);
        db.execSQL(SQL_CREATE);
    }
    
    public static void upgradeTable(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(String.format("ALTER TABLE \"%s\" RENAME TO \"%s_old\";", TABLE_NAME, TABLE_NAME));
            StringBuilder sBuilder = null;
            Cursor c = db.rawQuery(String.format("PRAGMA table_info(\"%s_old\");", TABLE_NAME), null);
            if (c.moveToFirst()) {
                while (!c.isAfterLast()) {
                    String colName = c.getString(c.getColumnIndexOrThrow("name"));
                    if (sBuilder == null) {
                        sBuilder = new StringBuilder();
                    } else {
                        sBuilder.append(", ");
                    }
                    sBuilder.append("\"");
                    sBuilder.append(colName);
                    sBuilder.append("\"");
                    c.moveToNext();
                }
            }
            c.close();
            
            createTable(db);
            if (sBuilder != null) {
                String colList = sBuilder.toString();
                db.execSQL(String.format("INSERT INTO \"%s\" (%s) SELECT %s FROM \"%s_old\";", TABLE_NAME, colList, colList, TABLE_NAME));
            }
            db.execSQL(String.format("DROP TABLE IF EXISTS \"%s_old\";" , TABLE_NAME));

        } catch (Throwable e) {
            e.printStackTrace();
            createTable(db);
        }
    }
    
    public static Uri buildIdLookupUri(long _id) {
        return Uri.withAppendedPath(ID_LOOKUP_URI, Uri.encode(String.valueOf(_id)));
    }
    
    public static Uri buildMyStringLookupUri(String myString) {
        return Uri.withAppendedPath(LOOKUP_URI, Uri.encode(myString));
    }

    public static class ColumnHelper extends PersistentObject.ColumnHelper {
        public int col__id = -1;
        public int col_my_boolean = -1;
        public int col_my_double = -1;
        public int col_my_float = -1;
        public int col_my_int = -1;
        public int col_my_long = -1;
        public int col_my_char = -1;
        public int col_my_string = -1;
        public int col_my_blob = -1;
        public int col_my_time = -1;
        
        public ColumnHelper(String[] projection) {
            super(projection);
            col__id = getColumnIndex(Columns._ID);
            col_my_boolean = getColumnIndex(Columns.MY_BOOLEAN);
            col_my_double = getColumnIndex(Columns.MY_DOUBLE);
            col_my_float = getColumnIndex(Columns.MY_FLOAT);
            col_my_int = getColumnIndex(Columns.MY_INT);
            col_my_long = getColumnIndex(Columns.MY_LONG);
            col_my_char = getColumnIndex(Columns.MY_CHAR);
            col_my_string = getColumnIndex(Columns.MY_STRING);
            col_my_blob = getColumnIndex(Columns.MY_BLOB);
            col_my_time = getColumnIndex(Columns.MY_TIME);
        }
    }
}
