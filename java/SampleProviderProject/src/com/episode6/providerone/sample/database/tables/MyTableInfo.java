package com.episode6.providerone.sample.database.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
    
//    public interface ColumnDefinitions {
//        String _ID = "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT";
//        String MY_BOOLEAN = "\"my_boolean\" BOOL";
//        String MY_DOUBLE = "\"my_double\" DOUBLE";
//        String MY_FLOAT = "\"my_float\" FLOAT";
//        String MY_INT = "\"my_int\" INTEGER";
//        String MY_LONG = "\"my_long\" LONG";
//        String MY_CHAR = "\"my_char\" CHAR";
//        String MY_STRING = "\"my_string\" TEXT";
//        String MY_BLOB = "\"my_blob\" BLOB";
//        String MY_TIME = "\"my_time\" DATETIME";
//    }
    
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
    
    public static final String ID_COLUMN = Columns._ID;
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.episode6.providerone.sample.my_table";
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.episode6.providerone.sample.my_table";
    
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

}
