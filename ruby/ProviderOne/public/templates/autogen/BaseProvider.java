{AutoGenCopyrightMessage}
package {PackageName}.database.autogen;

import java.util.ArrayList;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import {PackageName}.database.{ProjectName}Database;
import {PackageName}.database.autogen.util.PlatformDatabaseUtils;
import {PackageName}.database.autogen.util.SelectionBuilder;
{TableInfoImports}

public abstract class Base{ProjectName}Provider extends ContentProvider {

    public static final String PATH_COUNT = "/count";
    public static final String PATH_SUM = "/sum";
    public static final String PATH_LOOKUP = "/lookup/*";
    public static final String PATH_ID = "/id/*";
	public static final String PATH_VACUUM = "vacuum";

    public static final String RAW_PATH_COUNT = "count";
    public static final String RAW_PATH_SUM = "sum";
    public static final String RAW_PATH_LOOKUP = "lookup";
    public static final String RAW_PATH_ID = "id";

	public static final int VACUUM = 0xffff;
{TableProviderMatchDefs}
    private static Uri sBaseContentUri = null;
    private static Context sApplicationContext = null;

    public static Context getAppContext() {
        return sApplicationContext;
    }

    public static String getContentAuthority() {
        return {ContentAuthority};
    }

    public static Uri getBaseContentUri() {
        if (sBaseContentUri == null)
            sBaseContentUri = Uri.parse("content://" + getContentAuthority());
        return sBaseContentUri;
    }

    protected {ProjectName}Database mDatabase;
    private UriMatcher mUriMatcher = null;

    protected abstract void buildPriorityCustomUriMatcher(UriMatcher matcher, String authority);
    protected abstract void buildSecondaryCustomUriMatcher(UriMatcher matcher, String authority);
    protected abstract String getCustomType(Uri uri, int match);
	protected abstract void onNotityChanges(ContentResolver contentResolver, Uri uri, int match);
    protected abstract Integer delete(Uri uri, String selection, String[] selectionArgs, int match);
    protected abstract Uri insert(Uri uri, ContentValues values, int match);
    protected abstract Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, int match);
    protected abstract Integer update(Uri uri, ContentValues values, String selection, String[] selectionArgs, int match);
    protected abstract boolean buildSimpleSelection(Uri uri, int match, SelectionBuilder builder);
    protected abstract int getCustomUpdateAlgorithm(Uri uri, int match);

    @Override
    public boolean onCreate() {
        sApplicationContext = getContext().getApplicationContext();
        mDatabase = new {ProjectName}Database(getContext());
        return false;
    }

    protected void buildUriMatcher(UriMatcher matcher) {
        final String authority = getContentAuthority();

        buildPriorityCustomUriMatcher(matcher, authority);
		matcher.addURI(authority, PATH_VACUUM, VACUUM);
{UriMatcherBuildProc}
        buildSecondaryCustomUriMatcher(matcher, authority);
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
			case VACUUM:
				return null;
{UriMatchTypeCases}
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        return applyBatch(operations, true);
    }

    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations, boolean withTransaction) throws OperationApplicationException {
        
        if (!withTransaction)
            return super.applyBatch(operations);
        
        ContentProviderResult[] result = null;
        SQLiteDatabase db = mDatabase.getWritableDatabase();
        db.beginTransaction();
        try {
            result =  super.applyBatch(operations);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return result;
    }

    private void notifyUri(Uri notifyUri, int match) {
    	notifyUri(null, notifyUri, match);
    }
    
    private void notifyUri(ContentResolver resolver, Uri notifyUri, int match) {
   		if (resolver == null)
   			resolver = getAppContext().getContentResolver();
   		resolver.notifyChange(notifyUri, null);
   		onNotityChanges(resolver, notifyUri, match);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final int match = getUriMatcher().match(uri);
        Integer result = delete(uri, selection, selectionArgs, match);
        if (result != null)
            return result.intValue();

		switch(match) {
{UriDeleteInvalidMatches}
		}

        final SelectionBuilder builder = buildSimpleSelection(uri, match);
        int delResult = builder.where(selection, selectionArgs).delete(mDatabase.getWritableDatabase());
        notifyUri(uri, match);
		return delResult;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final int match = getUriMatcher().match(uri);
        Uri result = insert(uri, values, match);
        if (result != null)
            return result;

        PlatformDatabaseUtils db = new PlatformDatabaseUtils(mDatabase);
        switch(match) {
{UriInsertMatches}
            default:
                throw new UnsupportedOperationException("Invalid uri for insert: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final int match = getUriMatcher().match(uri);
        Cursor result = query(uri, projection, selection, selectionArgs, sortOrder, match);
        if (result != null)
            return result;


        final SelectionBuilder builder = buildSimpleSelection(uri, match).where(selection, selectionArgs);
        switch(match) {
{UriCountMatches}
{UriSumMatches}
            default:
                return builder.query(mDatabase.getReadableDatabase(), projection, sortOrder);
        }

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final int match = getUriMatcher().match(uri);
        Integer result = update(uri, values, selection, selectionArgs, match);
        if (result != null)
            return result;

		switch(match) {
{UriUpdateInvalidMatches}
		}
		
		if (match == VACUUM) {
			mDatabase.getWritableDatabase().execSQL("VACUUM;");
			notifyUri(uri, match);
			return 1;
		}

        final SelectionBuilder builder = buildSimpleSelection(uri, match);
        int algorithm = SQLiteDatabase.CONFLICT_FAIL;
        switch(match) {
{UriUpdateAlgorithmMatches}
			default:
				algorithm = getCustomUpdateAlgorithm(uri, match);
				if (algorithm == -1)
				    algorithm = SQLiteDatabase.CONFLICT_FAIL;
        }
        int updateResult = builder.where(selection, selectionArgs).updateWithOnConflict(mDatabase, values, algorithm);
        notifyUri(uri, match);
        return updateResult;
    }

    private SelectionBuilder buildSimpleSelection(Uri uri, int match) {
        final SelectionBuilder builder = new SelectionBuilder();

        if (buildSimpleSelection(uri, match, builder))
            return builder;

        switch(match) {
{UriSimpleSelectionMatches}
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }
}
