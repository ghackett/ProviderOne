package com.groupme.providerone.sample;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;

public class SampleListFragment extends ListFragment implements LoaderCallbacks<Cursor> {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new SampleListAdapter(getActivity()));
        getLoaderManager().initLoader(0, null, (LoaderCallbacks<Cursor>) this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        // TODO Auto-generated method stub
        return SampleListAdapter.createNewCursorLoader(getActivity(), null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
        ((CursorAdapter)getListAdapter()).swapCursor(arg1);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        ((CursorAdapter)getListAdapter()).swapCursor(null);
    }

 

    
    
}
