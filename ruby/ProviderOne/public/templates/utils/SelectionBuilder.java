{AndroidCopyrightMessage}
package {PackageName}.database.autogen.util;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SelectionBuilder {
    private String mTable = null;
    private Map<String, String> mProjectionMap = new HashMap<String, String>();
    private StringBuilder mSelection = new StringBuilder();
    private ArrayList<String> mSelectionArgs = new ArrayList<String>();

    public SelectionBuilder reset() {
        mTable = null;
        mSelection.setLength(0);
        mSelectionArgs.clear();

        return this;
    }

    public SelectionBuilder where(String selection, String... selectionArgs) {
        if (TextUtils.isEmpty(selection)) {
            if (selectionArgs != null && selectionArgs.length > 0) {
                throw new IllegalStateException("Valid selection required when including arguments");
            }

            return this;
        }

        if (mSelection.length() > 0) {
            mSelection.append(" AND ");
        }

        mSelection.append("(").append(selection).append(")");

        if (selectionArgs != null) {
            mSelectionArgs.addAll(Arrays.asList(selectionArgs));
        }

        return this;
    }

    private void assertTable() {
        if (mTable == null) {
            throw new IllegalStateException("Table not specified");
        }
    }

    public SelectionBuilder table(String table) {
        mTable = table;
        return this;
    }

    public SelectionBuilder mapToTable(String column, String table) {
        mProjectionMap.put(column, table + "." + column);
        return this;
    }

    public SelectionBuilder map(String fromColumn, String toClause) {
        mProjectionMap.put(fromColumn, toClause + " AS " + fromColumn);
        return this;
    }

    public String getSelection() {
        return mSelection.toString();
    }

    public String[] getSelectionArgs() {
        return mSelectionArgs.toArray(new String[mSelectionArgs.size()]);
    }

    private void mapColumns(String[] columns) {
        for (int i = 0; i < columns.length; i++) {
            final String target = mProjectionMap.get(columns[i]);

            if (target != null) {
                columns[i] = target;
            }
        }
    }

    @Override
    public String toString() {
        return "SelectionBuilder[table=" + mTable + ", selection=" + getSelection()
            + ", selectionArgs=" + Arrays.toString(getSelectionArgs()) + "]";
    }

    public Cursor query(SQLiteDatabase db, String[] columns, String orderBy) {
        return query(db, columns, null, null, orderBy, null);
    }

    public Cursor query(SQLiteDatabase db, String[] columns, String groupBy, String having, String orderBy, String limit) {
        assertTable();

        if (columns != null) {
            mapColumns(columns);
        }

        return db.query(mTable, columns, getSelection(), getSelectionArgs(), groupBy, having, orderBy, limit);
    }

    public int update(SQLiteDatabase db, ContentValues values) {
        assertTable();

        return db.update(mTable, values, getSelection(), getSelectionArgs());
    }

    public int updateWithOnConflict(SQLiteOpenHelper db, ContentValues values, int algorithm) {
        assertTable();

        PlatformDatabaseUtils pdb = new PlatformDatabaseUtils(db);
        return pdb.updateWithOnConflict(mTable, values, getSelection(), getSelectionArgs(), algorithm);
    }

    public int delete(SQLiteDatabase db) {
        assertTable();

        return db.delete(mTable, getSelection(), getSelectionArgs());
    }
}
