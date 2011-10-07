package com.episode6.providerone.sample.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.episode6.providerone.sample.database.tables.MyTableInfo;

public abstract class BaseSampleProvider extends ContentProvider {
    
    public static final int MY_TABLE = 0xFFFF;
    public static final int MY_TABLE_ID = 0xFFFE;
    
    private static Uri sBaseContentUri = null;
    private static Context sApplicationContext = null;
    
    public static Context getAppContext() {
        return sApplicationContext;
    }
    
    public static String getContentAuthority() {
        return "com.episode6.providerone.sample";
    }
    
    public static Uri getBaseContentUri() {
        if (sBaseContentUri == null)
            sBaseContentUri = Uri.parse("content://" + getContentAuthority());
        return sBaseContentUri;
    }
    
    private SampleDatabase mDatabase;
    private UriMatcher mUriMatcher = null;
    
    protected abstract void buildCustomUriMatcher(UriMatcher matcher, String authority);
    protected abstract String getCustomType(Uri uri, int match);
    protected abstract Integer delete(Uri uri, String selection, String[] selectionArgs, int match);
    protected abstract Uri insert(Uri uri, ContentValues values, int match);
    protected abstract Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, int match);
    protected abstract Integer update(Uri uri, ContentValues values, String selection, String[] selectionArgs, int match);
    protected abstract boolean buildSimpleSelection(Uri uri, int match, SelectionBuilder builder);
    
    @Override
    public boolean onCreate() {
        sApplicationContext = getContext().getApplicationContext();
        mDatabase = new SampleDatabase(getContext());
        return false;
    }
    
    protected void buildUriMatcher(UriMatcher matcher) {
        final String authority = getContentAuthority();
        buildCustomUriMatcher(matcher, authority);
        
        matcher.addURI(authority, MyTableInfo.PATH, MY_TABLE);
        matcher.addURI(authority, MyTableInfo.PATH + "/*", MY_TABLE_ID);
    }
    
    protected UriMatcher getUriMatcher() {
        if (mUriMatcher == null) {
            mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
            buildUriMatcher(mUriMatcher);
        }
        return mUriMatcher;
    }
    
    @Override
    public String getType(Uri uri) {
        final int match = getUriMatcher().match(uri);
        String result = getCustomType(uri, match);
        if (result != null)
            return result;
        
        switch(match) {
            case MY_TABLE:
                return MyTableInfo.CONTENT_TYPE;
            case MY_TABLE_ID:
                return MyTableInfo.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final int match = getUriMatcher().match(uri);
        Integer result = delete(uri, selection, selectionArgs, match);
        if (result != null)
            return result.intValue();
        
        final SelectionBuilder builder = buildSimpleSelection(uri, match);
        return builder.where(selection, selectionArgs).delete(mDatabase.getWritableDatabase());
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final int match = getUriMatcher().match(uri);
        Uri result = insert(uri, values, match);
        if (result != null)
            return result;
        
        PlatformDatabaseUtils db = new PlatformDatabaseUtils(mDatabase);
        switch(match) {
            case MY_TABLE: {
                long id = db.insertWithOnConflict(MyTableInfo.TABLE_NAME, null, values, MyTableInfo.INSERT_ALGORITHM);
                return MyTableInfo.buildUri(id);
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final int match = getUriMatcher().match(uri);
        Cursor result = query(uri, projection, selection, selectionArgs, sortOrder, match);
        if (result != null)
            return result;
        
        
        final SelectionBuilder builder = buildSimpleSelection(uri, match);
        return builder.where(selection, selectionArgs).query(mDatabase.getReadableDatabase(), projection, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final int match = getUriMatcher().match(uri);
        Integer result = update(uri, values, selection, selectionArgs, match);
        if (result != null)
            return result;
        
        final SelectionBuilder builder = buildSimpleSelection(uri, match);
        int algorithm = SQLiteDatabase.CONFLICT_FAIL;
        switch(match) {
            case MY_TABLE:
            case MY_TABLE_ID:
                algorithm = MyTableInfo.UPDATE_ALGORITHM;
                break;
        }
        return builder.where(selection, selectionArgs).updateWithOnConflict(mDatabase, values, algorithm);
    }
    
    private SelectionBuilder buildSimpleSelection(Uri uri, int match) {
        final SelectionBuilder builder = new SelectionBuilder();
        
        if (buildSimpleSelection(uri, match, builder))
            return builder;
        
        switch(match) {
            case MY_TABLE:
                return builder.table(MyTableInfo.TABLE_NAME);
            case MY_TABLE_ID:
                builder.where(MyTableInfo.ID_COLUMN + "=?", uri.getLastPathSegment());
                return builder.table(MyTableInfo.TABLE_NAME);
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

}
