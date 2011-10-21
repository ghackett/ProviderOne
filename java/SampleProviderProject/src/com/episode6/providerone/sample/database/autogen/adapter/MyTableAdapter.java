package com.episode6.providerone.sample.database.autogen.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.episode6.providerone.sample.database.objects.MyTable;
import com.episode6.providerone.sample.database.tables.MyTableInfo;

public abstract class MyTableAdapter extends CursorAdapter {
    
    public static CursorLoader createCursorLoader(Context ctx, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (projection == null)
            projection = MyTableInfo.ALL_COLUMNS;
        return new CursorLoader(ctx, MyTableInfo.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }
    
    public static CursorLoader createCursorLoader(Context ctx, MyTableInfo.ColumnHelper columnHelper, String selection, String[] selectionArgs, String sortOrder) {
        return new CursorLoader(ctx, MyTableInfo.CONTENT_URI, columnHelper == null ? MyTableInfo.ALL_COLUMNS : columnHelper.projection, selection, selectionArgs, sortOrder);
    }
    
    protected MyTableInfo.ColumnHelper mColumnHelper = null;

    public MyTableAdapter(Context context, String[] projection) {
        super(context, null, false);
        if (projection == null)
            projection = MyTableInfo.ALL_COLUMNS;
        mColumnHelper = new MyTableInfo.ColumnHelper(projection); 
    }
    
    public MyTableAdapter(Context context, MyTableInfo.ColumnHelper columnHelper) {
        super(context, null, false);
        if (columnHelper == null)
            columnHelper = new MyTableInfo.ColumnHelper(MyTableInfo.ALL_COLUMNS);
        mColumnHelper = columnHelper;
    }   
    
    public MyTableInfo.ColumnHelper getColumnHelper() {
        return mColumnHelper;
    }
    
    public String[] getProjection() {
        return mColumnHelper.projection;
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
