/*
 * Copyright (C) 2011 GroupMe, Inc.
 */
package com.groupme.providerone.sample.database;

import com.groupme.providerone.sample.database.autogen.BaseSampleProvider;
import com.groupme.providerone.sample.database.autogen.util.SelectionBuilder;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class SampleProvider extends BaseSampleProvider {

    /**
     * Add your own custom uri paths to the matcher here
     * These priority patterns will take precedence to the
     * default patterns added by the base provider
     */
    @Override
    protected void buildPriorityCustomUriMatcher(UriMatcher matcher, String authority) {
        // TODO Auto-generated method stub
    }

    /**
     * Add your own custom uri paths to the matcher here
     * These secondary patterns will only be matched if
     * none of the priority or default uri patterns are matched
     */
    @Override
    protected void buildSecondaryCustomUriMatcher(UriMatcher matcher, String authority) {
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
     * handle inserts for your custom uri matchings, return null if un-matched
     * IT IS YOUR RESPONSABILITY TO NOTIFY THE CONTENT RESOLVER OF A CHANGE
     * You can use getAppContext().getContentResolver() to retrieve the
     * ContentResolver
     */
    @Override
    protected Uri insert(Uri uri, ContentValues values, int match) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * if your custom uri matching corresponds directly to a table or view, you can
     * build a selection here using the SelectionBuilder that is passed to you. This
     * avoids the need to fill in the query, update and delete methods.
     * 
     * If you use this method, as opposed to query, update and delete, then 
     * the ContentResolver WILL be notified on your behalf for updates and deletes
     *
     * return true if you matched and modified the builder, false otherwise (to let
     * the base provider do its thing)
     */
    @Override
    protected boolean buildSimpleSelection(Uri uri, int match, SelectionBuilder builder) {
        // TODO Auto-generated method stub
        return false;
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
     * IT IS YOUR RESPONSABILITY TO NOTIFY THE CONTENT RESOLVER OF A CHANGE
     * You can use getAppContext().getContentResolver() to retrieve the
     * ContentResolver
     */
    @Override
    protected Integer update(Uri uri, ContentValues values, String selection, String[] selectionArgs, int match) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * handle deletes for your custom uri matchings, return null if un-matched
     * IT IS YOUR RESPONSABILITY TO NOTIFY THE CONTENT RESOLVER OF A CHANGE
     * You can use getAppContext().getContentResolver() to retrieve the
     * ContentResolver
     */
    @Override
    protected Integer delete(Uri uri, String selection, String[] selectionArgs, int match) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * if you are leaving updates up to the selection builder (i.e. trusting buildSimpleSelection)
     * then you should return the appropriate sqlite update algorithm here. You can also use this method
     * to define a default update algorithm for any updates not matched by the base provider
     */
    @Override
    protected int getCustomUpdateAlgorithm(Uri uri, int match) {
        return -1;
    }

}
