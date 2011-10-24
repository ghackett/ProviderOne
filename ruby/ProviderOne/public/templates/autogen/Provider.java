package {PackageName}.sample.database;

import {PackageName}.database.autogen.Base{ProjectName}Provider;
import {PackageName}.database.autogen.util.SelectionBuilder;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class {ProjectName}Provider extends Base{ProjectName}Provider {

    /**
     * Add your own custom uri paths to the matcher here
     */
    @Override
    protected void buildCustomUriMatcher(UriMatcher matcher, String authority) {
        // TODO Auto-generated method stub
    }

    /**
     * return the proper type for you custom uri matchings, return null if un-matched
     */
    @Override
    protected String getCustomType(Uri uri, int match) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * handle deletes for your custom uri matchings, return null if un-matched
     */
    @Override
    protected Integer delete(Uri uri, String selection, String[] selectionArgs, int match) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * handle inserts for your custom uri matchings, return null if un-matched
     */
    @Override
    protected Uri insert(Uri uri, ContentValues values, int match) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * handle queries for your custom uri matchings, return null if un-matched
     */
    @Override
    protected Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, int match) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * handle updates for your custom uri matchings, return null if un-matched
     */
    @Override
    protected Integer update(Uri uri, ContentValues values, String selection, String[] selectionArgs, int match) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * if your custom uri matching corresponds directly to a table or view, you can
     * build a selection here using the SelectionBuilder that is passed to you. This
     * avoids the need to fill in the query, update and delete methods
     *
     * return true if you matched and modified the builder, false otherwise (to let
     * the base provider do its thing)
     */
    @Override
    protected boolean buildSimpleSelection(Uri uri, int match, SelectionBuilder builder) {
        // TODO Auto-generated method stub
        return false;
    }

}
