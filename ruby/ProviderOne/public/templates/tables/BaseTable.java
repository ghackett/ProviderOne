{AutoGenCopyrightMessage}
package {PackageName}.database.autogen.objects;

{Imports}
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.text.TextUtils;

import {PackageName}.database.{ProjectName}Provider;
import {PackageName}.database.autogen.PersistentObject;
import {PackageName}.database.objects.{CapCamelTableName};
import {PackageName}.database.tables.{CapCamelTableName}Info;

public abstract class Base{CapCamelTableName} extends PersistentObject {


    public static {CapCamelTableName} fromCursor(Cursor cursor, {CapCamelTableName}.ColumnHelper helper) {
        {CapCamelTableName} obj = new {CapCamelTableName}();
        obj.hydrate(cursor, helper);
        return obj;
    }

    public static {CapCamelTableName} fromJson(JSONObject obj) {
        if (obj == null)
            return null;
        {CapCamelTableName} {CamelTableName} = new {CapCamelTableName}();
        {CamelTableName}.hydrate(obj);
        return {CamelTableName};
    }

    public static int getCount(String selection, String[] selectionArgs) {
        return getSingleIntResult({CapCamelTableName}Info.COUNT_URI, null, selection, selectionArgs, null);
    }

    public static int getIntSum(String columnName, String selection, String[] selectionArgs) {
        return getSingleIntResult({CapCamelTableName}Info.SUM_URI, new String[] {columnName}, selection, selectionArgs, null);
    }

    public static long getLongSum(String columnName, String selection, String[] selectionArgs) {
        return getSingleLongResult({CapCamelTableName}Info.SUM_URI, new String[] {columnName}, selection, selectionArgs, null);
    }

    public static double getDoubleSum(String columnName, String selection, String[] selectionArgs) {
        return getSingleDoubleResult({CapCamelTableName}Info.SUM_URI, new String[] {columnName}, selection, selectionArgs, null);
    }

    public static float getFloatSum(String columnName, String selection, String[] selectionArgs) {
        return getSingleFloatResult({CapCamelTableName}Info.SUM_URI, new String[] {columnName}, selection, selectionArgs, null);
    }

    public static ArrayList<{CapCamelTableName}> findAllWhere(String selection, String[] selectionArgs, String sortOrder) {
        return findAllWhere({CapCamelTableName}Info.ALL_COLUMNS, selection, selectionArgs, sortOrder);
    }

    public static ArrayList<{CapCamelTableName}> findAllWhere(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return findAllWhere(projection == null ? {CapCamelTableName}Info.ALL_COLUMNS_HELPER : new {CapCamelTableName}Info.ColumnHelper(projection), selection, selectionArgs, sortOrder);
    }

    public static ArrayList<{CapCamelTableName}> findAllWhere({CapCamelTableName}Info.ColumnHelper helper, String selection, String[] selectionArgs, String sortOrder) {
        return findAllByUri({CapCamelTableName}Info.CONTENT_URI, helper, selection, selectionArgs, sortOrder);
    }

    public static {CapCamelTableName} findOneWhere(String selection, String[] selectionArgs) {
        return findOneWhere({CapCamelTableName}Info.ALL_COLUMNS, selection, selectionArgs);
    }

    public static {CapCamelTableName} findOneWhere(String[] projection, String selection, String[] selectionArgs) {
        return findOneWhere(projection == null ? {CapCamelTableName}Info.ALL_COLUMNS_HELPER : new {CapCamelTableName}Info.ColumnHelper(projection), selection, selectionArgs);
    }

    public static {CapCamelTableName} findOneWhere({CapCamelTableName}Info.ColumnHelper helper, String selection, String[] selectionArgs) {
        return findOneByUri({CapCamelTableName}Info.CONTENT_URI, helper, selection, selectionArgs, null);
    }

    public static int deleteWhere(String where, String[] selectionArgs) {
        return deleteByUri({CapCamelTableName}Info.CONTENT_URI, where, selectionArgs);
    }

    public static {CapCamelTableName} findOneById(long id) {
        return findOneById(id, {CapCamelTableName}Info.ALL_COLUMNS);
    }

    public static {CapCamelTableName} findOneById(long id, String[] projection) {
        return findOneById(id, projection == null ? {CapCamelTableName}Info.ALL_COLUMNS_HELPER : new {CapCamelTableName}Info.ColumnHelper(projection));
    }

    public static {CapCamelTableName} findOneById(long id, {CapCamelTableName}Info.ColumnHelper helper) {
        return findOneByUri({CapCamelTableName}Info.buildIdLookupUri(id), helper, null, null, null);
    }

    public static int deleteOneById(long id) {
        return deleteByUri({CapCamelTableName}Info.buildIdLookupUri(id), null, null);
    }

{LookupStart}
    public static {CapCamelTableName} findOneBy{LookupCapCamelName}(String {LookupCamelName}) {
        return findOneBy{LookupCapCamelName}({LookupCamelName}, {CapCamelTableName}Info.ALL_COLUMNS);
    }
            
    public static {CapCamelTableName} findOneBy{LookupCapCamelName}(String {LookupCamelName}, String[] projection) {
        return findOneBy{LookupCapCamelName}({LookupCamelName}, projection == null ? {CapCamelTableName}Info.ALL_COLUMNS_HELPER : new {CapCamelTableName}Info.ColumnHelper(projection));
    }
    
    public static {CapCamelTableName} findOneBy{LookupCapCamelName}(String {LookupCamelName}, {CapCamelTableName}Info.ColumnHelper helper) {
        return findOneByUri({CapCamelTableName}Info.build{LookupCapCamelName}LookupUri({LookupCamelName}), helper, null, null, null);
    }

    public static int deleteOneBy{LookupCapCamelName}(String {LookupCamelName}) {
        return deleteByUri({CapCamelTableName}Info.build{LookupCapCamelName}LookupUri({LookupCamelName}), null, null);
    }
{LookupEnd}

    public static {CapCamelTableName} findOneByUri(Uri uri, {CapCamelTableName}Info.ColumnHelper helper, String selection, String[] selectionArgs, String sortOrder) {
        {CapCamelTableName} rtr = null;

        if (helper == null)
            helper = {CapCamelTableName}Info.ALL_COLUMNS_HELPER;
        
        if (TextUtils.isEmpty(sortOrder))
            sortOrder = {CapCamelTableName}Info.Columns._ID;

        Cursor c = {ProjectName}Provider.getAppContext().getContentResolver().query(
                uri,
                helper.projection,
                selection,
                selectionArgs,
                sortOrder + " LIMIT 1");
        if (c != null) {
            if (c.moveToFirst()) {
                rtr = fromCursor(c, helper);
            }
            c.close();
        }
        return rtr;
    }

    public static ArrayList<{CapCamelTableName}> findAllByUri(Uri uri, {CapCamelTableName}Info.ColumnHelper helper, String selection, String[] selectionArgs, String sortOrder) {
        if (helper == null)
            helper = {CapCamelTableName}Info.ALL_COLUMNS_HELPER;

        Cursor c = {ProjectName}Provider.getAppContext().getContentResolver().query(uri, helper.projection, selection, selectionArgs, sortOrder);
        ArrayList<{CapCamelTableName}> rtr = new ArrayList<{CapCamelTableName}>();
        if (c != null) {
            while(c.moveToNext()) {
                rtr.add({CapCamelTableName}.fromCursor(c, helper));
            }
            c.close();
        }
        return rtr;
    }

{JavaDefs}

    public Base{CapCamelTableName}() {
        super();
    }
    public Base{CapCamelTableName}(Parcel in) {
        readFromParcel(in);
    }

    private void assertColumnHelper(ColumnHelper helper, boolean allowNull) {
        if (helper == null) {
            if (allowNull)
                return;
            else
                throw new IllegalArgumentException("Trying to use a null column helper with {CapCamelTableName}");
        }
        if (!(helper instanceof {CapCamelTableName}Info.ColumnHelper))
            throw new IllegalArgumentException("Trying to use wrong type of ColumnHelper with {CapCamelTableName} - " + helper.getClass().getName());
    }

    @Override
    protected void hydrate(Cursor c, ColumnHelper helper) {
        assertColumnHelper(helper, false);
        hydrate(c, ({CapCamelTableName}Info.ColumnHelper)helper);
    }

    protected void hydrate(Cursor c, {CapCamelTableName}Info.ColumnHelper h) {
{HydrateProc}
        mIsNew = false;
    }

	protected void hydrate(JSONObject obj) {
		if (obj == null)
			return;
{HydrateJsonProc}
	}

    @Override
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
{ToContentValues}
        return values;
    }

    @Override
    public JSONObject toJson(ColumnHelper helper) throws JSONException {
        assertColumnHelper(helper, true);
        return toJson(({CapCamelTableName}Info.ColumnHelper)helper);
    }

    @Override
    public JSONObject toJson(String[] projection) throws JSONException {
        return toJson(projection == null ? {CapCamelTableName}Info.ALL_COLUMNS_HELPER : new {CapCamelTableName}Info.ColumnHelper(projection));
    }

    public JSONObject toJson({CapCamelTableName}Info.ColumnHelper h) throws JSONException {
        if (h == null)
            h = {CapCamelTableName}Info.ALL_COLUMNS_HELPER;
        JSONObject rtr = new JSONObject();
{ToJson}
        return rtr;
    }

    @Override
    public void save() {
        if (isNew()) {
            Uri result = {ProjectName}Provider.getAppContext().getContentResolver().insert({CapCamelTableName}Info.CONTENT_URI, toContentValues());
            if (result != null) {
                setId(Long.valueOf(result.getLastPathSegment()));
            }
            mIsNew = false;
        } else {
            if (!mIdSet) {
                throw new IllegalArgumentException("Trying to save an existing persistant object when ID column is not set");
            }
            Uri updateUri = {CapCamelTableName}Info.buildIdLookupUri(mId);
            {ProjectName}Provider.getAppContext().getContentResolver().update(updateUri, toContentValues(), null, null);
        }
    }

    @Override
    public ContentProviderOperation getSaveProviderOperation() {
        ContentProviderOperation op = null;
        if (isNew()) {
            op = ContentProviderOperation.newInsert({CapCamelTableName}Info.CONTENT_URI).withValues(toContentValues()).build();
        } else {
            if (!mIdSet) {
                throw new IllegalArgumentException("Trying to save an existing persistant object when ID column is not set");
            }
            Uri updateUri = {CapCamelTableName}Info.buildIdLookupUri(mId);
            op = ContentProviderOperation.newUpdate(updateUri).withValues(toContentValues()).build();
        }
        return op;
    }

    @Override
    public int delete() {
        if (isNew())
            throw new IllegalArgumentException("Trying to delete a {CapCamelTableName} record that has never been saved");
        if (!mIdSet)
            throw new IllegalArgumentException("Trying to delete a {CapCamelTableName} record that doesnt have its ID column set");

        Uri delUri = {CapCamelTableName}Info.buildIdLookupUri(mId);
        return {ProjectName}Provider.getAppContext().getContentResolver().delete(delUri, null, null);
    }
    
    @Override
    public boolean reload() {
        return reload({CapCamelTableName}Info.ALL_COLUMNS_HELPER);
    }

    @Override
    public boolean reload(String[] projection) {
        return reload(projection == null ? {CapCamelTableName}Info.ALL_COLUMNS_HELPER : new {CapCamelTableName}Info.ColumnHelper(projection));
    }

    @Override
    public boolean reload(ColumnHelper helper) {
        assertColumnHelper(helper, true);
        return reload(({CapCamelTableName}Info.ColumnHelper)helper);
    }
    
    public boolean reload({CapCamelTableName}Info.ColumnHelper helper) {
        if (isNew() || !mIdSet)
            throw new IllegalArgumentException("Trying to reload a record without an id");
        if (helper == null)
            helper = {CapCamelTableName}Info.ALL_COLUMNS_HELPER;
        
        boolean result = false;
        
        Cursor c = {ProjectName}Provider.getAppContext().getContentResolver().query(
                {CapCamelTableName}Info.buildIdLookupUri(mId),
                helper.projection,
                null,
                null,
                null);
        if (c != null) {
            if (c.moveToFirst()) {
                hydrate(c, helper);
                result = true;
            }
            c.close();
        }
        return result;
    }

{BaseTableMethods}

{IsSetMethods}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

{WriteToParcel}
    }

    @Override
    public void readFromParcel(Parcel in) {
        super.readFromParcel(in);

{ReadFromParcel}
    }

}
