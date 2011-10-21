package com.episode6.providerone.sample.database.autogen.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.episode6.providerone.sample.database.objects.MyTable;
import com.episode6.providerone.sample.database.tables.MyTableInfo;

public abstract class MyTableAdapter extends CursorAdapter {
    
    protected MyTableInfo.ColumnHelper mColumnHelper = null;

    public MyTableAdapter(Context context, String[] projection) {
        super(context, null, false);
        mColumnHelper = new MyTableInfo.ColumnHelper(projection); 
    }
    
    public MyTableAdapter(Context context, MyTableInfo.ColumnHelper columnHelper) {
        super(context, null, false);
        mColumnHelper = columnHelper;
    }    

    @Override
    public void bindView(View view, Context ctx, Cursor cursor) {
        bindView(view, ctx, MyTable.fromCursor(cursor, mColumnHelper));
    }

    @Override
    public View newView(Context ctx, Cursor cursor, ViewGroup parent) {
        return newView(ctx, MyTable.fromCursor(cursor, mColumnHelper), parent);
    }
    
    abstract public View newView(Context ctx, MyTable myTable, ViewGroup parent);
    abstract public void bindView(View view, Context ctx, MyTable myTable);

}
