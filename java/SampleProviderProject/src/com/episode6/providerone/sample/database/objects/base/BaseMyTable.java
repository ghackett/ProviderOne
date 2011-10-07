package com.episode6.providerone.sample.database.objects.base;

import java.nio.ByteBuffer;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.episode6.providerone.sample.database.tables.MyTableInfo;

public class BaseMyTable implements Parcelable {
    
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
    
    public BaseMyTable() {}
    public BaseMyTable(Parcel in) {
        readFromParcel(in);
    }
    
    public void hydrate(Cursor c, MyTableInfo.ColumnHelper h) {
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
    }

    public Long get_Id() {
        return m_Id;
    }

    public void set_Id(Long m_Id) {
        this.m_Id = m_Id;
    }

    public Boolean getMyBoolean() {
        return mMyBoolean;
    }

    public void setMyBoolean(Boolean mMyBoolean) {
        this.mMyBoolean = mMyBoolean;
    }

    public Double getMyDouble() {
        return mMyDouble;
    }

    public void setMyDouble(Double mMyDouble) {
        this.mMyDouble = mMyDouble;
    }

    public Float getMyFloat() {
        return mMyFloat;
    }

    public void setMyFloat(Float mMyFloat) {
        this.mMyFloat = mMyFloat;
    }

    public Integer getMyInt() {
        return mMyInt;
    }

    public void setMyInt(Integer mMyInt) {
        this.mMyInt = mMyInt;
    }

    public Long getMyLong() {
        return mMyLong;
    }

    public void setMyLong(Long mMyLong) {
        this.mMyLong = mMyLong;
    }

    public Character getMyChar() {
        return mMyChar;
    }

    public void setMyChar(Character mMyChar) {
        this.mMyChar = mMyChar;
    }

    public String getMyString() {
        return mMyString;
    }

    public void setMyString(String mMyString) {
        this.mMyString = mMyString;
    }

    public ByteBuffer getMyBlob() {
        return mMyBlob;
    }

    public void setMyBlob(ByteBuffer mMyBlob) {
        this.mMyBlob = mMyBlob;
    }

    public Long getMyTime() {
        return mMyTime;
    }

    public void setMyTime(Long mMyTime) {
        this.mMyTime = mMyTime;
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
    
    private void readFromParcel(Parcel in) {
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

    
    public static final Creator<BaseMyTable> CREATOR = new Creator<BaseMyTable>() {
        public BaseMyTable createFromParcel(Parcel in) {
            return new BaseMyTable(in);
        }

        public BaseMyTable[] newArray(int size) {
            return new BaseMyTable[size];
        }
    };

}
