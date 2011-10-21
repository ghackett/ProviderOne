package com.episode6.providerone.sample.database.autogen.objects;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;

import com.episode6.providerone.sample.database.SampleProvider;
import com.episode6.providerone.sample.database.autogen.PersistentObject;
import com.episode6.providerone.sample.database.objects.MyTable;
import com.episode6.providerone.sample.database.tables.MyTableInfo;

public abstract class BaseMyTable extends PersistentObject {
    

    public static MyTable fromCursor(Cursor cursor, MyTableInfo.ColumnHelper helper) {
        MyTable obj = new MyTable();
        obj.hydrate(cursor, helper);
        return obj;
    }
    
    public static int getCount(String selection, String[] selectionArgs) {
        int count = -1;
        Cursor c = SampleProvider.getAppContext().getContentResolver().query(MyTableInfo.COUNT_URI, null, selection, selectionArgs, null);
        if (c != null) {
            if (c.moveToFirst()) {
                count = c.getInt(0);
            }
            c.close();
        }
        return count;
    }
    
    public static ArrayList<MyTable> findAllWhere(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (projection == null)
            projection = MyTableInfo.ALL_COLUMNS;
        MyTableInfo.ColumnHelper helper = new MyTableInfo.ColumnHelper(projection);
        Cursor c = SampleProvider.getAppContext().getContentResolver().query(MyTableInfo.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
        ArrayList<MyTable> rtr = new ArrayList<MyTable>();
        if (c != null) {
            while(c.moveToNext()) {
                rtr.add(MyTable.fromCursor(c, helper));
            }
            c.close();
        }
        return rtr;
    }
    
    public static void deleteWhere(String where, String[] selectionArgs) {
        SampleProvider.getAppContext().getContentResolver().delete(MyTableInfo.CONTENT_URI, where, selectionArgs);
    }
    
    public static MyTable findOneById(long id, String[] projection) {
        MyTable rtr = null;
        if (projection == null)
            projection = MyTableInfo.ALL_COLUMNS;
        Cursor c = SampleProvider.getAppContext().getContentResolver().query(
                MyTableInfo.buildIdLookupUri(id), 
                projection, 
                null, 
                null, 
                null);
        if (c != null) {
            if (c.moveToFirst()) {
                rtr = fromCursor(c, new MyTableInfo.ColumnHelper(projection));
            }
            c.close();
        }
        return rtr;
    }
    
    public static void deleteOneById(long id) {
        Uri delUri = MyTableInfo.buildIdLookupUri(id);
        SampleProvider.getAppContext().getContentResolver().delete(delUri, null, null);
    }
    
    public static MyTable findOneByMyString(String myString, String[] projection) {
        MyTable rtr = null;
        if (projection == null)
            projection = MyTableInfo.ALL_COLUMNS;
        Cursor c = SampleProvider.getAppContext().getContentResolver().query(
                MyTableInfo.buildMyStringLookupUri(myString), 
                projection, 
                null, 
                null, 
                null);
        if (c != null) {
            if (c.moveToFirst()) {
                rtr = fromCursor(c, new MyTableInfo.ColumnHelper(projection));
            }
            c.close();
        }
        return rtr;
    }
    
    public static void deleteOneByMyString(String myString) {
        Uri delUri = MyTableInfo.buildMyStringLookupUri(myString);
        SampleProvider.getAppContext().getContentResolver().delete(delUri, null, null);
    }
    
    protected Long m_Id = null;
    protected Boolean mMyBoolean = null;
    protected Double mMyDouble = null;
    protected Float mMyFloat = null;
    protected Integer mMyInt = null;
    protected Long mMyLong = null;
    protected Character mMyChar = null;
    protected String mMyString = null;
    protected ByteBuffer mMyBlob = null;
    protected Long mMyTime = null;
    
    protected boolean m_IdSet = false;
    protected boolean mMyBooleanSet = false;
    protected boolean mMyDoubleSet = false;
    protected boolean mMyFloatSet = false;
    protected boolean mMyIntSet = false;
    protected boolean mMyLongSet = false;
    protected boolean mMyCharSet = false;
    protected boolean mMyStringSet = false;
    protected boolean mMyBlobSet = false;
    protected boolean mMyTimeSet = false;
    
    public BaseMyTable() {
        super();
    }
    public BaseMyTable(Parcel in) {
        readFromParcel(in);
    }
    
    private void assertColumnHelper(ColumnHelper helper, boolean allowNull) {
        if (helper == null) {
            if (allowNull)
                return;
            else
                throw new IllegalArgumentException("Trying to use a null column helper with MyTable");
        }
        if (!(helper instanceof MyTableInfo.ColumnHelper))
            throw new IllegalArgumentException("Trying to use wrong type of ColumnHelper with MyTable - " + helper.getClass().getName());
    }
    
    @Override
    protected void hydrate(Cursor c, ColumnHelper helper) {
        assertColumnHelper(helper, false);
        hydrate(c, (MyTableInfo.ColumnHelper)helper);
    }
    
    protected void hydrate(Cursor c, MyTableInfo.ColumnHelper h) {
        if (h.col__id != -1) {
            m_Id = c.isNull(h.col__id) ? null : c.getLong(h.col__id);
            m_IdSet = true;
        } else {
            m_Id = null;
            m_IdSet = false;
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
        
        if (h.col_my_int != 0) {
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
                String tmp = c.getString(h.col_my_char);
                if (tmp.length() > 0)
                    mMyChar = tmp.charAt(0);
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
        } else {
            mMyTime = null;
            mMyTimeSet = false;
        }
        
        mIsNew = false;
    }
    
    @Override
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        if (m_IdSet)
            values.put(MyTableInfo.Columns._ID, m_Id);
        if (mMyBooleanSet)
            values.put(MyTableInfo.Columns.MY_BOOLEAN, mMyBoolean);
        if (mMyDoubleSet)
            values.put(MyTableInfo.Columns.MY_DOUBLE, mMyDouble);
        if (mMyFloatSet)
            values.put(MyTableInfo.Columns.MY_FLOAT, mMyFloat);
        if (mMyIntSet)
            values.put(MyTableInfo.Columns.MY_INT, mMyInt);
        if (mMyLongSet)
            values.put(MyTableInfo.Columns.MY_LONG, mMyLong);
        if (mMyCharSet)
            values.put(MyTableInfo.Columns.MY_CHAR, mMyChar == null ? null : String.valueOf(mMyChar));
        if (mMyStringSet)
            values.put(MyTableInfo.Columns.MY_STRING, mMyString);
        if (mMyBlobSet)
            values.put(MyTableInfo.Columns.MY_BLOB, mMyBlob == null ? null : mMyBlob.array());
        if (mMyTimeSet)
            values.put(MyTableInfo.Columns.MY_TIME, mMyTime);
        
        return values;
    }
    
    @Override
    public JSONObject toJson(ColumnHelper helper) throws JSONException {
        assertColumnHelper(helper, true);
        return toJson((MyTableInfo.ColumnHelper)helper);
    }
    
    @Override
    public JSONObject toJson(String[] projection) throws JSONException {
        if (projection == null)
            projection = MyTableInfo.ALL_COLUMNS;
        return toJson(new MyTableInfo.ColumnHelper(projection));
    }
    
    public JSONObject toJson(MyTableInfo.ColumnHelper h) throws JSONException {
        if (h == null)
            h = new MyTableInfo.ColumnHelper(MyTableInfo.ALL_COLUMNS);
        JSONObject rtr = new JSONObject();
        if (m_IdSet && h.col__id != -1)
            rtr.put(MyTableInfo.Columns._ID, m_Id);
        if (mMyBooleanSet && h.col_my_boolean != -1)
            rtr.put(MyTableInfo.Columns.MY_BOOLEAN, mMyBoolean);
        if (mMyDoubleSet && h.col_my_double != -1)
            rtr.put(MyTableInfo.Columns.MY_DOUBLE, mMyDouble);
        if (mMyFloatSet && h.col_my_float != -1)
            rtr.put(MyTableInfo.Columns.MY_FLOAT, mMyFloat == null ? null : mMyFloat.doubleValue());
        if (mMyIntSet && h.col_my_int != -1)
            rtr.put(MyTableInfo.Columns.MY_INT, mMyInt);
        if (mMyLongSet && h.col_my_long != -1)
            rtr.put(MyTableInfo.Columns.MY_LONG, mMyLong);
        if (mMyCharSet && h.col_my_char != -1)
            rtr.put(MyTableInfo.Columns.MY_CHAR, mMyChar == null ? null : String.valueOf(mMyChar));
        if (mMyStringSet && h.col_my_string != -1)
            rtr.put(MyTableInfo.Columns.MY_STRING, mMyString);
        //cannot put a blob into json
//        if (mMyBlobSet && ArrayUtils.isStringInArray(MyTableInfo.Columns., projection))
//            rtr.put(MyTableInfo.Columns.MY_BLOB, mMyBlob == null ? null : mMyBlob.array());
        if (mMyTimeSet && h.col_my_time != -1)
            rtr.put(MyTableInfo.Columns.MY_TIME, mMyTime);
        return null;
    }
    
    @Override
    public void save() {
        if (isNew()) {
            Uri result = SampleProvider.getAppContext().getContentResolver().insert(MyTableInfo.CONTENT_URI, toContentValues());
            if (result != null) {
                set_Id(Long.valueOf(result.getLastPathSegment()));
            }
            mIsNew = false;
        } else {
            if (!m_IdSet) {
                throw new IllegalArgumentException("Trying to save an existing persistant object when ID column is not set");
            }
            Uri updateUri = MyTableInfo.buildIdLookupUri(m_Id);
            SampleProvider.getAppContext().getContentResolver().update(updateUri, toContentValues(), null, null);
        }
    }
    
    @Override
    public ContentProviderOperation getSaveProviderOperation() {
        ContentProviderOperation op = null;
        if (isNew()) {
            op = ContentProviderOperation.newInsert(MyTableInfo.CONTENT_URI).withValues(toContentValues()).build();
        } else {
            if (!m_IdSet) {
                throw new IllegalArgumentException("Trying to save an existing persistant object when ID column is not set");
            }
            Uri updateUri = MyTableInfo.buildIdLookupUri(m_Id);
            op = ContentProviderOperation.newUpdate(updateUri).withValues(toContentValues()).build();
        }
        return op;
    }
    
    @Override
    public void delete() {
        if (isNew())
            throw new IllegalArgumentException("Trying to delete a MyTable record that has never been saved");
        if (!m_IdSet)
            throw new IllegalArgumentException("Trying to delete a MyTable record that doesnt have its ID column set");
        
        Uri delUri = MyTableInfo.buildIdLookupUri(m_Id);
        SampleProvider.getAppContext().getContentResolver().delete(delUri, null, null);
    }

    public Long get_Id() {
        return m_Id;
    }

    public void set_Id(Long m_Id) {
        this.m_Id = m_Id;
        m_IdSet = true;
    }

    public Boolean getMyBoolean() {
        return mMyBoolean;
    }

    public void setMyBoolean(Boolean mMyBoolean) {
        this.mMyBoolean = mMyBoolean;
        mMyBooleanSet = true;
    }

    public Double getMyDouble() {
        return mMyDouble;
    }

    public void setMyDouble(Double mMyDouble) {
        this.mMyDouble = mMyDouble;
        mMyDoubleSet = true;
    }

    public Float getMyFloat() {
        return mMyFloat;
    }

    public void setMyFloat(Float mMyFloat) {
        this.mMyFloat = mMyFloat;
        mMyFloatSet = true;
    }

    public Integer getMyInt() {
        return mMyInt;
    }

    public void setMyInt(Integer mMyInt) {
        this.mMyInt = mMyInt;
        mMyIntSet = true;
    }

    public Long getMyLong() {
        return mMyLong;
    }

    public void setMyLong(Long mMyLong) {
        this.mMyLong = mMyLong;
        mMyLongSet = true;
    }

    public Character getMyChar() {
        return mMyChar;
    }

    public void setMyChar(Character mMyChar) {
        this.mMyChar = mMyChar;
        mMyCharSet = true;
    }

    public String getMyString() {
        return mMyString;
    }

    public void setMyString(String mMyString) {
        this.mMyString = mMyString;
        mMyStringSet = true;
    }

    public ByteBuffer getMyBlob() {
        return mMyBlob;
    }

    public void setMyBlob(ByteBuffer mMyBlob) {
        this.mMyBlob = mMyBlob;
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

    public void setMyTimeInMillis(Long mMyTime) {
        this.mMyTime = mMyTime;
        mMyTimeSet = true;
    }
    
    public void setMyTimeInSeconds(Integer mMyTime) {
        if (mMyTime == null) {
            this.mMyTime = null;
            return;
        }
        this.mMyTime = mMyTime.longValue() * 1000;
    }
    
    public void setMyTimeAsDate(Date myTime) {
        if (myTime == null) {
            mMyTime = null;
            return;
        } else {
            mMyTime = myTime.getTime();
        }
    }

    
    
    public boolean is_IdSet() {
        return m_IdSet;
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
        
        dest.writeValue(m_Id);
        dest.writeInt(m_IdSet ? 1 : 0);

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

        dest.writeString(mMyChar == null ? null : String.valueOf(mMyChar));
        dest.writeInt(mMyCharSet ? 1 : 0);

        dest.writeString(mMyString);
        dest.writeInt(mMyStringSet ? 1 : 0);
        
        dest.writeValue(mMyBlob == null ? null : mMyBlob.array());
        dest.writeInt(mMyBlobSet ? 1 : 0);
        
        dest.writeValue(mMyTime);
        dest.writeInt(mMyTimeSet ? 1 : 0);
    }
    
    @Override
    public void readFromParcel(Parcel in) {
        super.readFromParcel(in);
        
        m_Id = (Long) in.readValue(Long.class.getClassLoader());
        m_IdSet = in.readInt() == 1;
        
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
        
        mMyString = in.readString();
        mMyStringSet = in.readInt() == 1;
        
        byte[] my_blob_temp = (byte[]) in.readValue(byte[].class.getClassLoader());
        mMyBlob = my_blob_temp == null ? null : ByteBuffer.wrap(my_blob_temp);
        mMyBlobSet = in.readInt() == 1;
        
        mMyTime = (Long) in.readValue(Long.class.getClassLoader());
        mMyTimeSet = in.readInt() == 1;
    }
    











}
