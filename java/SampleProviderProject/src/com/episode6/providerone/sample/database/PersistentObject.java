package com.episode6.providerone.sample.database;

import android.content.ContentValues;
import android.database.Cursor;

public abstract class PersistentObject {
    
    protected boolean mIsNew;
    
    public PersistentObject() {
        super();
        mIsNew = true;
    }
    
    public boolean isNew() {
        return mIsNew;
    }
    
    abstract public ContentValues toContentValues();
    abstract protected void hydrate(Cursor c, ColumnHelper helper);
    abstract public void save();
    abstract public void delete();
    
    abstract public static class ColumnHelper {
        
    }

}
