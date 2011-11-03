{AutoGenCopyrightMessage}
package {PackageName}.database.autogen.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import {PackageName}.database.{ProjectName}Provider;
import {PackageName}.database.autogen.PersistentObject;
import {PackageName}.database.tables.{CapCamelTableName}Info;

public class Base{CapCamelTableName}Info  {

    public interface Columns {
{ColumnDefs}
    }

    public static final String TABLE_NAME = "{SqlTableName}";
    public static final String SQL_CREATE = "{CreateStatement}";
    public static final String SQL_DROP = "{DropStatement}";

    public static final String[] ALL_COLUMNS = new String[] {
{ColumnList}
    };

    public static final {CapCamelTableName}Info.ColumnHelper ALL_COLUMNS_HELPER = new {CapCamelTableName}Info.ColumnHelper(ALL_COLUMNS);


    public static final String PATH = "{SqlTableName}";
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/{PackageName}.{LowerTableName}";
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/{PackageName}.{LowerTableName}";
    public static final Uri CONTENT_URI = Uri.withAppendedPath({ProjectName}Provider.getBaseContentUri(), PATH);
    public static final Uri COUNT_URI = Uri.withAppendedPath(CONTENT_URI, {ProjectName}Provider.RAW_PATH_COUNT);
    public static final Uri SUM_URI = Uri.withAppendedPath(CONTENT_URI, {ProjectName}Provider.RAW_PATH_SUM);
    public static final Uri ID_LOOKUP_URI = Uri.withAppendedPath(CONTENT_URI, {ProjectName}Provider.RAW_PATH_ID);



    public static final int INSERT_ALGORITHM = SQLiteDatabase.{InsertAlgorithm};
    public static final int UPDATE_ALGORITHM = SQLiteDatabase.{UpdateAlgorithm};

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



    public static class ColumnHelper extends PersistentObject.ColumnHelper {
{ColumnHelperContent}
    }



{LookupStart}
    public static final Uri LOOKUP_URI = Uri.withAppendedPath(CONTENT_URI, {ProjectName}Provider.RAW_PATH_LOOKUP);
    public static Uri build{LookupCapCamelName}LookupUri(String {LookupCamelName}) {
        return Uri.withAppendedPath(LOOKUP_URI, Uri.encode({LookupCamelName}));
    }
{LookupEnd}
}
