package {PackageName}.database.autogen.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;

import {PackageName}.database.objects.{CapCamelTableName};
import {PackageName}.database.tables.{CapCamelTableName}Info;

public abstract class {CapCamelTableName}Adapter extends CursorAdapter {

    public static CursorLoader createCursorLoader(Context ctx, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (projection == null)
            projection = {CapCamelTableName}Info.ALL_COLUMNS;
        return new CursorLoader(ctx, {CapCamelTableName}Info.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }

    public static CursorLoader createCursorLoader(Context ctx, {CapCamelTableName}Info.ColumnHelper columnHelper, String selection, String[] selectionArgs, String sortOrder) {
        return new CursorLoader(ctx, {CapCamelTableName}Info.CONTENT_URI, columnHelper == null ? {CapCamelTableName}Info.ALL_COLUMNS : columnHelper.projection, selection, selectionArgs, sortOrder);
    }

    protected {CapCamelTableName}Info.ColumnHelper mColumnHelper = null;

    public {CapCamelTableName}Adapter(Context context, String[] projection) {
        super(context, null, false);
        if (projection == null)
            projection = {CapCamelTableName}Info.ALL_COLUMNS;
        mColumnHelper = new {CapCamelTableName}Info.ColumnHelper(projection);
    }

    public {CapCamelTableName}Adapter(Context context, {CapCamelTableName}Info.ColumnHelper columnHelper) {
        super(context, null, false);
        if (columnHelper == null)
            columnHelper = new {CapCamelTableName}Info.ColumnHelper({CapCamelTableName}Info.ALL_COLUMNS);
        mColumnHelper = columnHelper;
    }

    public {CapCamelTableName}Info.ColumnHelper getColumnHelper() {
        return mColumnHelper;
    }

    public String[] getProjection() {
        return mColumnHelper.projection;
    }

    @Override
    public void bindView(View view, Context ctx, Cursor cursor) {
        bindView(view, ctx, {CapCamelTableName}.fromCursor(cursor, mColumnHelper));
    }

    @Override
    public View newView(Context ctx, Cursor cursor, ViewGroup parent) {
        return newView(ctx, {CapCamelTableName}.fromCursor(cursor, mColumnHelper), parent);
    }

    abstract public View newView(Context ctx, {CapCamelTableName} myTable, ViewGroup parent);
    abstract public void bindView(View view, Context ctx, {CapCamelTableName} myTable);

}
