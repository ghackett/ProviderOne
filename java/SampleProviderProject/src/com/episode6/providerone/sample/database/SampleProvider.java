package com.episode6.providerone.sample.database;

import com.episode6.providerone.sample.database.autogen.BaseSampleProvider;
import com.episode6.providerone.sample.database.autogen.util.SelectionBuilder;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class SampleProvider extends BaseSampleProvider {

    @Override
    protected void buildCustomUriMatcher(UriMatcher matcher, String authority) {
        // TODO Auto-generated method stub

    }

    @Override
    protected String getCustomType(Uri uri, int match) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Integer delete(Uri uri, String selection, String[] selectionArgs,
            int match) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Uri insert(Uri uri, ContentValues values, int match) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder, int match) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Integer update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs, int match) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected boolean buildSimpleSelection(Uri uri, int match,
            SelectionBuilder builder) {
        // TODO Auto-generated method stub
        return false;
    }

}
