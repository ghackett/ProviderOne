package com.groupme.providerone.sample;

import android.content.Context;
import android.support.v4.content.CursorLoader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.groupme.providerone.sample.database.autogen.adapters.MyTableAdapter;
import com.groupme.providerone.sample.database.objects.MyTable;
import com.groupme.providerone.sample.database.tables.MyTableInfo;

public class SampleListAdapter extends MyTableAdapter {
    
    public static final MyTableInfo.ColumnHelper COLUMNS = new MyTableInfo.ColumnHelper(MyTableInfo.ALL_COLUMNS);
    
    public static CursorLoader createNewCursorLoader(Context ctx, String selection, String[] selectionArgs, String sortOrder) {
        return createCursorLoader(ctx, COLUMNS, selection, selectionArgs, sortOrder);
    }

    public SampleListAdapter(Context context) {
        super(context, COLUMNS);
    }

    @Override
    public View newView(Context ctx, MyTable myTable, ViewGroup parent, int position) {
        return LayoutInflater.from(ctx).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context ctx, MyTable myTable, int position) {
        ((TextView)view.findViewById(R.id.tv_1)).setText(myTable.getMyString());
        ((TextView)view.findViewById(R.id.tv_2)).setText(myTable.getMyInt().toString());
        ((TextView)view.findViewById(R.id.tv_3)).setText(myTable.getMyDouble().toString());
    }

}
