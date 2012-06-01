/*
 * This file has been auto-generated by ProviderOne
 *
 * Copyright (C) 2011 GroupMe, Inc.
 */
package com.groupme.providerone.sample.database.autogen.objects;

import java.nio.ByteBuffer;
import java.util.Date;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.text.TextUtils;

import com.groupme.providerone.sample.database.SampleProvider;
import com.groupme.providerone.sample.database.autogen.PersistentObject;
import com.groupme.providerone.sample.database.objects.MyView;
import com.groupme.providerone.sample.database.tables.MyViewInfo;

public abstract class BaseMyView extends PersistentObject {

	public static final boolean IS_EDITABLE = false;

    public static MyView fromCursor(Cursor cursor, MyView.ColumnHelper helper) {
        MyView obj = new MyView();
        obj.hydrate(cursor, helper);
        return obj;
    }

    public static MyView fromJson(JSONObject obj) {
        if (obj == null)
            return null;
        MyView myView = new MyView();
        myView.hydrate(obj);
        return myView;
    }

    public static int getCount(String selection, String[] selectionArgs) {
        return getSingleIntResult(MyViewInfo.COUNT_URI, null, selection, selectionArgs, null);
    }

    public static int getIntSum(String columnName, String selection, String[] selectionArgs) {
        return getSingleIntResult(MyViewInfo.SUM_URI, new String[] {columnName}, selection, selectionArgs, null);
    }

    public static long getLongSum(String columnName, String selection, String[] selectionArgs) {
        return getSingleLongResult(MyViewInfo.SUM_URI, new String[] {columnName}, selection, selectionArgs, null);
    }

    public static double getDoubleSum(String columnName, String selection, String[] selectionArgs) {
        return getSingleDoubleResult(MyViewInfo.SUM_URI, new String[] {columnName}, selection, selectionArgs, null);
    }

    public static float getFloatSum(String columnName, String selection, String[] selectionArgs) {
        return getSingleFloatResult(MyViewInfo.SUM_URI, new String[] {columnName}, selection, selectionArgs, null);
    }

    public static ArrayList<MyView> findAllWhere(String selection, String[] selectionArgs, String sortOrder) {
        return findAllWhere(MyViewInfo.ALL_COLUMNS_HELPER, selection, selectionArgs, sortOrder);
    }

    public static ArrayList<MyView> findAllWhere(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return findAllWhere(projection == null ? MyViewInfo.ALL_COLUMNS_HELPER : new MyViewInfo.ColumnHelper(projection), selection, selectionArgs, sortOrder);
    }

    public static ArrayList<MyView> findAllWhere(MyViewInfo.ColumnHelper helper, String selection, String[] selectionArgs, String sortOrder) {
        return findAllByUri(MyViewInfo.CONTENT_URI, helper, selection, selectionArgs, sortOrder);
    }

    public static MyView findOneWhere(String selection, String[] selectionArgs) {
        return findOneWhere(MyViewInfo.ALL_COLUMNS_HELPER, selection, selectionArgs);
    }

    public static MyView findOneWhere(String[] projection, String selection, String[] selectionArgs) {
        return findOneWhere(projection == null ? MyViewInfo.ALL_COLUMNS_HELPER : new MyViewInfo.ColumnHelper(projection), selection, selectionArgs);
    }

    public static MyView findOneWhere(MyViewInfo.ColumnHelper helper, String selection, String[] selectionArgs) {
        return findOneByUri(MyViewInfo.CONTENT_URI, helper, selection, selectionArgs, null);
    }

    public static MyView findOneById(long id) {
        return findOneById(id, MyViewInfo.ALL_COLUMNS_HELPER);
    }

    public static MyView findOneById(long id, String[] projection) {
        return findOneById(id, projection == null ? MyViewInfo.ALL_COLUMNS_HELPER : new MyViewInfo.ColumnHelper(projection));
    }

    public static MyView findOneById(long id, MyViewInfo.ColumnHelper helper) {
        return findOneByUri(MyViewInfo.buildIdLookupUri(id), helper, null, null, null);
    }


    public static MyView findOneByMyLong(Long myLong) {
        return findOneByMyLong(myLong, MyViewInfo.ALL_COLUMNS_HELPER);
    }
            
    public static MyView findOneByMyLong(Long myLong, String[] projection) {
        return findOneByMyLong(myLong, projection == null ? MyViewInfo.ALL_COLUMNS_HELPER : new MyViewInfo.ColumnHelper(projection));
    }
    
    public static MyView findOneByMyLong(Long myLong, MyViewInfo.ColumnHelper helper) {
        return findOneByUri(MyViewInfo.buildMyLongLookupUri(myLong.toString()), helper, null, null, null);
    }

    public static MyView findOneWithMyLongInArray(Long myLong, ArrayList<MyView> myViewList) {
		if (myLong == null || myViewList == null || myViewList.isEmpty())
            return null;
        for (MyView myView : myViewList) {
            if (myView.mMyLongSet && myView.mMyLong != null && myView.mMyLong.equals(myLong))
                return myView;
        }
        return null;
    }


	public static MyView findOneWithIdInArray(long id, ArrayList<MyView> myViewList) {
	    if (myViewList == null || myViewList.isEmpty())
	        return null;
	    for (MyView myView : myViewList) {
	        if (myView.mIdSet && myView.mId != null && myView.mId.longValue() == id)
	            return myView;
	    }
	    return null;
	}

    public static MyView findOneByUri(Uri uri, MyViewInfo.ColumnHelper helper, String selection, String[] selectionArgs, String sortOrder) {
        MyView rtr = null;

        if (helper == null)
            helper = MyViewInfo.ALL_COLUMNS_HELPER;
        
        if (TextUtils.isEmpty(sortOrder))
            sortOrder = MyViewInfo.Columns._ID;

        Cursor c = SampleProvider.getAppContext().getContentResolver().query(
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

    public static ArrayList<MyView> findAllByUri(Uri uri, MyViewInfo.ColumnHelper helper, String selection, String[] selectionArgs, String sortOrder) {
        if (helper == null)
            helper = MyViewInfo.ALL_COLUMNS_HELPER;

        Cursor c = SampleProvider.getAppContext().getContentResolver().query(uri, helper.projection, selection, selectionArgs, sortOrder);
        ArrayList<MyView> rtr = new ArrayList<MyView>();
        if (c != null) {
            while(c.moveToNext()) {
                rtr.add(MyView.fromCursor(c, helper));
            }
            c.close();
        }
        return rtr;
    }

	protected Long mId = null;
	protected boolean mIdSet = false;
	protected Boolean mMyBoolean = null;
	protected boolean mMyBooleanSet = false;
	protected Double mMyDouble = null;
	protected boolean mMyDoubleSet = false;
	protected Float mMyFloat = null;
	protected boolean mMyFloatSet = false;
	protected Integer mMyInt = null;
	protected boolean mMyIntSet = false;
	protected Long mMyLong = null;
	protected boolean mMyLongSet = false;
	protected Character mMyChar = null;
	protected boolean mMyCharSet = false;
	protected String mMyString = null;
	protected boolean mMyStringSet = false;
	protected ByteBuffer mMyBlob = null;
	protected boolean mMyBlobSet = false;
	protected Long mMyTime = null;
	protected boolean mMyTimeSet = false;


    public BaseMyView() {
        super();
    }
    public BaseMyView(Parcel in) {
        readFromParcel(in);
    }

    private void assertColumnHelper(ColumnHelper helper, boolean allowNull) {
        if (helper == null) {
            if (allowNull)
                return;
            else
                throw new IllegalArgumentException("Trying to use a null column helper with MyView");
        }
        if (!(helper instanceof MyViewInfo.ColumnHelper))
            throw new IllegalArgumentException("Trying to use wrong type of ColumnHelper with MyView - " + helper.getClass().getName());
    }

    @Override
    protected void hydrate(Cursor c, ColumnHelper helper) {
        assertColumnHelper(helper, false);
        hydrate(c, (MyViewInfo.ColumnHelper)helper);
    }

    protected void hydrate(Cursor c, MyViewInfo.ColumnHelper h) {
        if (h.col__id != -1) {
            mId = c.isNull(h.col__id) ? null : c.getLong(h.col__id);
            mIdSet = true;
        } else {
            mId = null;
            mIdSet = false;
        }
        if (h.col_my_boolean != -1) {
            mMyBoolean = c.isNull(h.col_my_boolean) ? null : (c.getInt(h.col_my_boolean) == 1);
            mMyBooleanSet = true;
        } else {
            mMyBoolean = null;
            mMyBooleanSet = false;
        }
        if (h.col_my_double != -1) {
            mMyDouble = c.isNull(h.col_my_double) ? null : c.getDouble(h.col_my_double);
            mMyDoubleSet = true;
        } else {
            mMyDouble = null;
            mMyDoubleSet = false;
        }
        if (h.col_my_float != -1) {
            mMyFloat = c.isNull(h.col_my_float) ? null : c.getFloat(h.col_my_float);
            mMyFloatSet = true;
        } else {
            mMyFloat = null;
            mMyFloatSet = false;
        }
        if (h.col_my_int != -1) {
            mMyInt = c.isNull(h.col_my_int) ? null : c.getInt(h.col_my_int);
            mMyIntSet = true;
        } else {
            mMyInt = null;
            mMyIntSet = false;
        }
        if (h.col_my_long != -1) {
            mMyLong = c.isNull(h.col_my_long) ? null : c.getLong(h.col_my_long);
            mMyLongSet = true;
        } else {
            mMyLong = null;
            mMyLongSet = false;
        }
        if (h.col_my_char != -1) {
            mMyChar = null;
            if (!c.isNull(h.col_my_char)) {
                String myChar = c.getString(h.col_my_char);
                if (myChar.length() > 0)
                    mMyChar = myChar.charAt(0);
            }
            mMyCharSet = true;
        } else {
            mMyChar = null;
            mMyCharSet = false;
        }
        if (h.col_my_string != -1) {
            mMyString = c.getString(h.col_my_string);
            mMyStringSet = true;
        } else {
            mMyString = null;
            mMyStringSet = false;
        }
        if (h.col_my_blob != -1) {
            mMyBlob = c.isNull(h.col_my_blob) ? null : ByteBuffer.wrap(c.getBlob(h.col_my_blob));
            mMyBlobSet = true;
        } else {
            mMyBlob = null;
            mMyBlobSet = false;
        }
        if (h.col_my_time != -1) {
            mMyTime = c.isNull(h.col_my_time) ? null : c.getLong(h.col_my_time);
            mMyTimeSet = true;
        } else {
            mMyTime = null;
            mMyTimeSet = false;
        }

        mIsNew = false;
    }

	protected void hydrate(JSONObject obj) {
		if (obj == null)
			return;
//_id doesnt get hydrated from json
		if (obj.has(MyViewInfo.Columns.MY_BOOLEAN)) {
		    try {
		        mMyBoolean = obj.getBoolean(MyViewInfo.Columns.MY_BOOLEAN);
		    } catch (JSONException e) {
		        mMyBoolean = false;
		    }
		    mMyBooleanSet = true;
		}
		if (obj.has(MyViewInfo.Columns.MY_DOUBLE)) {
		    try {
		        mMyDouble = obj.getDouble(MyViewInfo.Columns.MY_DOUBLE);
		    } catch (JSONException e) {
		        mMyDouble = null;
		    }
		    mMyDoubleSet = true;
		}
		if (obj.has(MyViewInfo.Columns.MY_FLOAT)) {
		    try {
		        mMyFloat = (float)obj.getDouble(MyViewInfo.Columns.MY_FLOAT);
		    } catch (JSONException e) {
		        mMyFloat = null;
		    }
		    mMyFloatSet = true;
		}
		if (obj.has(MyViewInfo.Columns.MY_INT)) {
		    try {
		        mMyInt = obj.getInt(MyViewInfo.Columns.MY_INT);
		    } catch (JSONException e) {
		        mMyInt = null;
		    }
		    mMyIntSet = true;
		}
		if (obj.has(MyViewInfo.Columns.MY_LONG)) {
		    try {
		        mMyLong = obj.getLong(MyViewInfo.Columns.MY_LONG);
		    } catch (JSONException e) {
		        mMyLong = null;
		    }
		    mMyLongSet = true;
		}
		if (obj.has(MyViewInfo.Columns.MY_CHAR)) {
			String myChar = null;
		    try {
		        myChar = obj.getString(MyViewInfo.Columns.MY_CHAR);
				if (myChar == null || myChar.length() == 0)
					mMyChar = null;
				else
					mMyChar = myChar.charAt(0);
		    } catch (JSONException e) {
		        mMyChar = null;
		    }
		    mMyCharSet = true;
		}
		if (obj.has(MyViewInfo.Columns.MY_STRING)) {
		    try {
		        mMyString = obj.getString(MyViewInfo.Columns.MY_STRING);
		    } catch (JSONException e) {
		        mMyString = null;
		    }
		    mMyStringSet = true;
		}
//Can't hydrate a BLOB from JSON
		if (obj.has(MyViewInfo.Columns.MY_TIME)) {
		    try {
		        mMyTime = obj.getLong(MyViewInfo.Columns.MY_TIME);
		    } catch (JSONException e) {
		        mMyTime = null;
		    }
		    mMyTimeSet = true;
		}

	}

    @Override
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
		if (mIdSet)
			values.put(MyViewInfo.Columns._ID, mId);
		if (mMyBooleanSet)
			values.put(MyViewInfo.Columns.MY_BOOLEAN, mMyBoolean);
		if (mMyDoubleSet)
			values.put(MyViewInfo.Columns.MY_DOUBLE, mMyDouble);
		if (mMyFloatSet)
			values.put(MyViewInfo.Columns.MY_FLOAT, mMyFloat);
		if (mMyIntSet)
			values.put(MyViewInfo.Columns.MY_INT, mMyInt);
		if (mMyLongSet)
			values.put(MyViewInfo.Columns.MY_LONG, mMyLong);
		if (mMyCharSet)
			values.put(MyViewInfo.Columns.MY_CHAR, mMyChar == null ? null : String.valueOf(mMyChar));
		if (mMyStringSet)
			values.put(MyViewInfo.Columns.MY_STRING, mMyString);
		if (mMyBlobSet)
			values.put(MyViewInfo.Columns.MY_BLOB, mMyBlob == null ? null : mMyBlob.array());
		if (mMyTimeSet)
			values.put(MyViewInfo.Columns.MY_TIME, mMyTime);

        return values;
    }

    @Override
    public JSONObject toJson(ColumnHelper helper) throws JSONException {
        assertColumnHelper(helper, true);
        return toJson((MyViewInfo.ColumnHelper)helper);
    }

    @Override
    public JSONObject toJson(String[] projection) throws JSONException {
        return toJson(projection == null ? MyViewInfo.ALL_COLUMNS_HELPER : new MyViewInfo.ColumnHelper(projection));
    }

    public JSONObject toJson(MyViewInfo.ColumnHelper h) throws JSONException {
        if (h == null)
            h = MyViewInfo.ALL_COLUMNS_HELPER;
        JSONObject rtr = new JSONObject();
		if (mIdSet && h.col__id != -1)
			rtr.put(MyViewInfo.Columns._ID, mId);
		if (mMyBooleanSet && h.col_my_boolean != -1)
			rtr.put(MyViewInfo.Columns.MY_BOOLEAN, mMyBoolean);
		if (mMyDoubleSet && h.col_my_double != -1)
			rtr.put(MyViewInfo.Columns.MY_DOUBLE, mMyDouble);
		if (mMyFloatSet && h.col_my_float != -1)
			rtr.put(MyViewInfo.Columns.MY_FLOAT, mMyFloat.doubleValue());
		if (mMyIntSet && h.col_my_int != -1)
			rtr.put(MyViewInfo.Columns.MY_INT, mMyInt);
		if (mMyLongSet && h.col_my_long != -1)
			rtr.put(MyViewInfo.Columns.MY_LONG, mMyLong);
		if (mMyCharSet && h.col_my_char != -1)
			rtr.put(MyViewInfo.Columns.MY_CHAR, String.valueOf(mMyChar));
		if (mMyStringSet && h.col_my_string != -1)
			rtr.put(MyViewInfo.Columns.MY_STRING, mMyString);
		//Cannot add blobs to Json objects so MyBlob is skipped
		if (mMyTimeSet && h.col_my_time != -1)
			rtr.put(MyViewInfo.Columns.MY_TIME, mMyTime);

        return rtr;
    }

    @Override
    public void save() {
		throw new UnsupportedOperationException("Cannot save or delete a MyView because it's a sqlite view");
    }

    @Override
    public ContentProviderOperation getSaveProviderOperation() {
		throw new UnsupportedOperationException("Cannot save or delete a MyView because it's a sqlite view");
    }

    @Override
    public int delete() {
		throw new UnsupportedOperationException("Cannot save or delete a MyView because it's a sqlite view");
    }
    
    @Override
    public boolean reload() {
        return reload(MyViewInfo.ALL_COLUMNS_HELPER);
    }

    @Override
    public boolean reload(String[] projection) {
        return reload(projection == null ? MyViewInfo.ALL_COLUMNS_HELPER : new MyViewInfo.ColumnHelper(projection));
    }

    @Override
    public boolean reload(ColumnHelper helper) {
        assertColumnHelper(helper, true);
        return reload((MyViewInfo.ColumnHelper)helper);
    }
    
    public boolean reload(MyViewInfo.ColumnHelper helper) {
        if (isNew() || !mIdSet)
            throw new IllegalArgumentException("Trying to reload a record without an id");
        if (helper == null)
            helper = MyViewInfo.ALL_COLUMNS_HELPER;
        
        boolean result = false;
        
        Cursor c = SampleProvider.getAppContext().getContentResolver().query(
                MyViewInfo.buildIdLookupUri(mId),
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

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
        mIdSet = true;
        mIsNew = id != null;
    }

    public Boolean getMyBoolean() {
        return mMyBoolean;
    }

    public void setMyBoolean(Boolean myBoolean) {
        mMyBoolean = myBoolean;
        mMyBooleanSet = true;
    }

    public Double getMyDouble() {
        return mMyDouble;
    }

    public void setMyDouble(Double myDouble) {
        mMyDouble = myDouble;
        mMyDoubleSet = true;
    }

    public Float getMyFloat() {
        return mMyFloat;
    }

    public void setMyFloat(Float myFloat) {
        mMyFloat = myFloat;
        mMyFloatSet = true;
    }

    public Integer getMyInt() {
        return mMyInt;
    }

    public void setMyInt(Integer myInt) {
        mMyInt = myInt;
        mMyIntSet = true;
    }

    public Long getMyLong() {
        return mMyLong;
    }

    public void setMyLong(Long myLong) {
        mMyLong = myLong;
        mMyLongSet = true;
    }

    public Character getMyChar() {
        return mMyChar;
    }

    public void setMyChar(Character myChar) {
        mMyChar = myChar;
        mMyCharSet = true;
    }

    public String getMyString() {
        return mMyString;
    }

    public void setMyString(String myString) {
        mMyString = myString;
        mMyStringSet = true;
    }

    public ByteBuffer getMyBlob() {
        return mMyBlob;
    }

    public void setMyBlob(ByteBuffer myBlob) {
        mMyBlob = myBlob;
        mMyBlobSet = true;
    }

   public Long getMyTimeInMillis() {
       return mMyTime;
   }

   public Integer getMyTimeInSeconds() {
       if (mMyTime == null)
           return null;
       return (int)(mMyTime.longValue()/1000);
   }

   public Date getMyTimeAsDate() {
       if (mMyTime == null)
           return null;
       return new Date(mMyTime);
   }

   public void setMyTimeInMillis(Long myTime) {
       mMyTime = myTime;
       mMyTimeSet = true;
   }

   public void setMyTimeInSeconds(Integer myTime) {
       if (myTime == null) {
           mMyTime = null;
           return;
       }
       mMyTime = myTime.longValue() * 1000;
   }

   public void setMyTimeAsDate(Date myTime) {
       if (myTime == null) {
           mMyTime = null;
           return;
       } else {
           mMyTime = myTime.getTime();
       }
   }


    public boolean isIdSet() {
        return mIdSet;
    }
    public boolean isMyBooleanSet() {
        return mMyBooleanSet;
    }
    public boolean isMyDoubleSet() {
        return mMyDoubleSet;
    }
    public boolean isMyFloatSet() {
        return mMyFloatSet;
    }
    public boolean isMyIntSet() {
        return mMyIntSet;
    }
    public boolean isMyLongSet() {
        return mMyLongSet;
    }
    public boolean isMyCharSet() {
        return mMyCharSet;
    }
    public boolean isMyStringSet() {
        return mMyStringSet;
    }
    public boolean isMyBlobSet() {
        return mMyBlobSet;
    }
    public boolean isMyTimeSet() {
        return mMyTimeSet;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

		dest.writeValue(mId);
		dest.writeInt(mIdSet ? 1 : 0);

		dest.writeValue(mMyBoolean);
		dest.writeInt(mMyBooleanSet ? 1 : 0);

		dest.writeValue(mMyDouble);
		dest.writeInt(mMyDoubleSet ? 1 : 0);

		dest.writeValue(mMyFloat);
		dest.writeInt(mMyFloatSet ? 1 : 0);

		dest.writeValue(mMyInt);
		dest.writeInt(mMyIntSet ? 1 : 0);

		dest.writeValue(mMyLong);
		dest.writeInt(mMyLongSet ? 1 : 0);

		dest.writeValue(mMyChar == null ? null : String.valueOf(mMyChar));
		dest.writeInt(mMyCharSet ? 1 : 0);

		dest.writeValue(mMyString);
		dest.writeInt(mMyStringSet ? 1 : 0);

		dest.writeValue(mMyBlob == null ? null : mMyBlob.array());
		dest.writeInt(mMyBlobSet ? 1 : 0);

		dest.writeValue(mMyTime);
		dest.writeInt(mMyTimeSet ? 1 : 0);


    }

    @Override
    public void readFromParcel(Parcel in) {
        super.readFromParcel(in);

		mId = (Long) in.readValue(Long.class.getClassLoader());
		mIdSet = in.readInt() == 1;

		mMyBoolean = (Boolean) in.readValue(Boolean.class.getClassLoader());
		mMyBooleanSet = in.readInt() == 1;

		mMyDouble = (Double) in.readValue(Double.class.getClassLoader());
		mMyDoubleSet = in.readInt() == 1;

		mMyFloat = (Float) in.readValue(Float.class.getClassLoader());
		mMyFloatSet = in.readInt() == 1;

		mMyInt = (Integer) in.readValue(Integer.class.getClassLoader());
		mMyIntSet = in.readInt() == 1;

		mMyLong = (Long) in.readValue(Long.class.getClassLoader());
		mMyLongSet = in.readInt() == 1;

        String my_char_tmp = in.readString();
        mMyChar = my_char_tmp == null || my_char_tmp.length() <= 0 ? null : my_char_tmp.charAt(0);
		mMyCharSet = in.readInt() == 1;

		mMyString = (String) in.readValue(String.class.getClassLoader());
		mMyStringSet = in.readInt() == 1;

        byte[] my_blob_temp = (byte[]) in.readValue(byte[].class.getClassLoader());
        mMyBlob = my_blob_temp == null ? null : ByteBuffer.wrap(my_blob_temp);
		mMyBlobSet = in.readInt() == 1;

		mMyTime = (Long) in.readValue(Long.class.getClassLoader());
		mMyTimeSet = in.readInt() == 1;


    }

}
