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
import {PackageName}.database.autogen.{ProjectName}PersistentObject;
import {PackageName}.database.objects.{CapCamelTableName};
import {PackageName}.database.tables.{CapCamelTableName}Info;

public abstract class Base{CapCamelTableName} extends {ProjectName}PersistentObject {

	public static final boolean IS_EDITABLE = {IsEditableValue};

    public static {CapCamelTableName} fromCursor(Cursor cursor, {CapCamelTableName}Info.ColumnHelper helper) {
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
        return findAllWhere({CapCamelTableName}Info.ALL_COLUMNS_HELPER, selection, selectionArgs, sortOrder);
    }

    public static ArrayList<{CapCamelTableName}> findAllWhere(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return findAllWhere(projection == null ? {CapCamelTableName}Info.ALL_COLUMNS_HELPER : new {CapCamelTableName}Info.ColumnHelper(projection), selection, selectionArgs, sortOrder);
    }

    public static ArrayList<{CapCamelTableName}> findAllWhere({CapCamelTableName}Info.ColumnHelper helper, String selection, String[] selectionArgs, String sortOrder) {
        return findAllByUri({CapCamelTableName}Info.CONTENT_URI, helper, selection, selectionArgs, sortOrder);
    }

    public static {CapCamelTableName} findOneWhere(String selection, String[] selectionArgs, String orderBy) {
        return findOneWhere({CapCamelTableName}Info.ALL_COLUMNS_HELPER, selection, selectionArgs, orderBy);
    }

    public static {CapCamelTableName} findOneWhere(String[] projection, String selection, String[] selectionArgs, String orderBy) {
        return findOneWhere(projection == null ? {CapCamelTableName}Info.ALL_COLUMNS_HELPER : new {CapCamelTableName}Info.ColumnHelper(projection), selection, selectionArgs, orderBy);
    }

    public static {CapCamelTableName} findOneWhere({CapCamelTableName}Info.ColumnHelper helper, String selection, String[] selectionArgs, String orderBy) {
        return findOneByUri({CapCamelTableName}Info.CONTENT_URI, helper, selection, selectionArgs, orderBy);
    }
{EditableStart}
    public static int deleteWhere(String where, String[] selectionArgs) {
        return deleteByUri({CapCamelTableName}Info.CONTENT_URI, where, selectionArgs);
    }
{EditableEnd}
    public static {CapCamelTableName} findOneById(long id) {
        return findOneById(id, {CapCamelTableName}Info.ALL_COLUMNS_HELPER);
    }

    public static {CapCamelTableName} findOneById(long id, String[] projection) {
        return findOneById(id, projection == null ? {CapCamelTableName}Info.ALL_COLUMNS_HELPER : new {CapCamelTableName}Info.ColumnHelper(projection));
    }

    public static {CapCamelTableName} findOneById(long id, {CapCamelTableName}Info.ColumnHelper helper) {
        return findOneByUri({CapCamelTableName}Info.buildIdLookupUri(id), helper, null, null, null);
    }
{EditableStart}
    public static int deleteOneById(long id) {
        return deleteByUri({CapCamelTableName}Info.buildIdLookupUri(id), null, null);
    }
{EditableEnd}
{LookupStart}
    public static {CapCamelTableName} findOneBy{LookupCapCamelName}({LookupJavaType} {LookupCamelName}) {
        return findOneBy{LookupCapCamelName}({LookupCamelName}, {CapCamelTableName}Info.ALL_COLUMNS_HELPER);
    }
            
    public static {CapCamelTableName} findOneBy{LookupCapCamelName}({LookupJavaType} {LookupCamelName}, String[] projection) {
        return findOneBy{LookupCapCamelName}({LookupCamelName}, projection == null ? {CapCamelTableName}Info.ALL_COLUMNS_HELPER : new {CapCamelTableName}Info.ColumnHelper(projection));
    }
    
    public static {CapCamelTableName} findOneBy{LookupCapCamelName}({LookupJavaType} {LookupCamelName}, {CapCamelTableName}Info.ColumnHelper helper) {
        return findOneByUri({CapCamelTableName}Info.build{LookupCapCamelName}LookupUri({LookupCamelName}.toString()), helper, null, null, null);
    }
{EditableStart}
    public static int deleteOneBy{LookupCapCamelName}({LookupJavaType} {LookupCamelName}) {
        return deleteByUri({CapCamelTableName}Info.build{LookupCapCamelName}LookupUri({LookupCamelName}.toString()), null, null);
    }
{EditableEnd}
    public static {CapCamelTableName} findOneWith{LookupCapCamelName}InArray({LookupJavaType} {LookupCamelName}, ArrayList<{CapCamelTableName}> {CamelTableName}List) {
		if ({LookupCamelName} == null || {CamelTableName}List == null || {CamelTableName}List.isEmpty())
            return null;
        for ({CapCamelTableName} {CamelTableName} : {CamelTableName}List) {
            if ({CamelTableName}.m{LookupCapCamelName}Set && {CamelTableName}.m{LookupCapCamelName} != null && {CamelTableName}.m{LookupCapCamelName}.equals({LookupCamelName}))
                return {CamelTableName};
        }
        return null;
    }
{LookupEnd}

	public static {CapCamelTableName} findOneWithIdInArray(long id, ArrayList<{CapCamelTableName}> {CamelTableName}List) {
	    if ({CamelTableName}List == null || {CamelTableName}List.isEmpty())
	        return null;
	    for ({CapCamelTableName} {CamelTableName} : {CamelTableName}List) {
	        if ({CamelTableName}.mIdSet && {CamelTableName}.mId != null && {CamelTableName}.mId.longValue() == id)
	            return {CamelTableName};
	    }
	    return null;
	}

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
    public void hydrate(Cursor c, ColumnHelper helper) {
        assertColumnHelper(helper, false);
        hydrate(c, ({CapCamelTableName}Info.ColumnHelper)helper);
    }

    public void hydrate(Cursor c, {CapCamelTableName}Info.ColumnHelper h) {
{HydrateProc}
        mIsNew = false;
    }

    @Override
	public void hydrate(JSONObject obj) {
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
{EditableStartWithException}
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
{EditableEndWithException}
    }

    @Override
    public ContentProviderOperation getSaveProviderOperation() {
{EditableStartWithException}
        ContentProviderOperation op = null;
        if (isMarkedForDeletion()) {
            op = ContentProviderOperation.newDelete({CapCamelTableName}Info.buildIdLookupUri(mId)).build();
        } else {
	        if (isNew()) {
	            op = ContentProviderOperation.newInsert({CapCamelTableName}Info.CONTENT_URI).withValues(toContentValues()).build();
	        } else {
	            if (!mIdSet) {
	                throw new IllegalArgumentException("Trying to save an existing persistant object when ID column is not set");
	            }
	            Uri updateUri = {CapCamelTableName}Info.buildIdLookupUri(mId);
	            op = ContentProviderOperation.newUpdate(updateUri).withValues(toContentValues()).build();
	        }
		}
        return op;
{EditableEndWithException}
    }

    @Override
    public void markForDeletion() {
{EditableStartWithException}
        if (!mIdSet)
            throw new IllegalArgumentException("Trying to mark {CapCamelTableName} record for deletion that doesnt have its ID column set");
        super.markForDeletion();
{EditableEndWithException}
    }

    @Override
    public int delete() {
{EditableStartWithException}
        if (isNew())
            throw new IllegalArgumentException("Trying to delete a {CapCamelTableName} record that has never been saved");
        if (!mIdSet)
            throw new IllegalArgumentException("Trying to delete a {CapCamelTableName} record that doesnt have its ID column set");

        Uri delUri = {CapCamelTableName}Info.buildIdLookupUri(mId);
        return {ProjectName}Provider.getAppContext().getContentResolver().delete(delUri, null, null);
{EditableEndWithException}
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

	public Uri getIdLookupUri() {
		if (isNew() || !mIdSet)
			return null;
		else
			return {CapCamelTableName}Info.buildIdLookupUri(mId);
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
