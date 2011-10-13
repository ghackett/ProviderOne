package com.episode6.providerone.sample.database.autogen.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.episode6.providerone.sample.database.SampleProvider;
import com.episode6.providerone.sample.database.autogen.BaseSampleProvider;
import com.episode6.providerone.sample.database.autogen.PersistentObject;
import com.episode6.providerone.sample.database.autogen.util.ArrayUtils;

public class MyTableInfo  {
    
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
    public static final Uri COUNT_URI = Uri.withAppendedPath(CONTENT_URI, BaseSampleProvider.RAW_PATH_COUNT);
    public static final Uri LOOKUP_URI = Uri.withAppendedPath(CONTENT_URI, BaseSampleProvider.RAW_PATH_LOOKUP);
    public static final Uri ID_LOOKUP_URI = Uri.withAppendedPath(CONTENT_URI, BaseSampleProvider.RAW_PATH_ID);
    
    public static final int INSERT_ALGORITHM = SQLiteDatabase.CONFLICT_IGNORE;
    public static final int UPDATE_ALGORITHM = SQLiteDatabase.CONFLICT_IGNORE;
    
    public static void createTable(SQLiteDatabase db) {
        db.execSQL(SQL_DROP);
        db.execSQL(SQL_CREATE);
    }
    
    public static void upgradeTable(SQLiteDatabase db) {
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
                db.execSQL(String.format("DROP TABLE IF EXISTS \"%s_old\";" , TABLE_NAME));
            }

        } catch (Exception e) {
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
            col__id = ArrayUtils.getIndexOfStringInArray(Columns._ID, projection);
            col_my_boolean = ArrayUtils.getIndexOfStringInArray(Columns.MY_BOOLEAN, projection);
            col_my_double = ArrayUtils.getIndexOfStringInArray(Columns.MY_DOUBLE, projection);
            col_my_float = ArrayUtils.getIndexOfStringInArray(Columns.MY_FLOAT, projection);
            col_my_int = ArrayUtils.getIndexOfStringInArray(Columns.MY_INT, projection);
            col_my_long = ArrayUtils.getIndexOfStringInArray(Columns.MY_LONG, projection);
            col_my_char = ArrayUtils.getIndexOfStringInArray(Columns.MY_CHAR, projection);
            col_my_string = ArrayUtils.getIndexOfStringInArray(Columns.MY_STRING, projection);
            col_my_blob = ArrayUtils.getIndexOfStringInArray(Columns.MY_BLOB, projection);
            col_my_time = ArrayUtils.getIndexOfStringInArray(Columns.MY_TIME, projection);
        }
    }
}