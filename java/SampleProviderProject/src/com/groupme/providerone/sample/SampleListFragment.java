package com.groupme.providerone.sample;

import java.util.ArrayList;

import android.app.Activity;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.widget.Toast;

import com.groupme.providerone.sample.database.SampleDatabase;
import com.groupme.providerone.sample.database.autogen.SamplePersistentObject;
import com.groupme.providerone.sample.database.objects.MyTable;
import com.groupme.providerone.sample.database.tables.MyTableInfo;

public class SampleListFragment extends ListFragment implements LoaderCallbacks<Cursor> {
    
    private final Handler mHandler = new Handler();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new SampleListAdapter(getActivity()));

    }

    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        getLoaderManager().initLoader(0, null, (LoaderCallbacks<Cursor>) this);
        mHandler.postDelayed(new Runnable() {
            
            @Override
            public void run() {
                new DBTask().execute((Void)null);
            }
        }, 2000);
    }


    @Override
    public void onDetach() {
        getLoaderManager().destroyLoader(0);
        super.onDetach();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        // TODO Auto-generated method stub
        return SampleListAdapter.createNewCursorLoader(getActivity(), null, null, MyTableInfo.Columns.MY_INT + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
        arg1.setNotificationUri(getActivity().getContentResolver(), MyTableInfo.CONTENT_URI);
        ((CursorAdapter)getListAdapter()).swapCursor(arg1);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        ((CursorAdapter)getListAdapter()).swapCursor(null);
    }

    
 

    private static class DBTask extends AsyncTask<Void, Void, Void> {
        

        @Override
        protected Void doInBackground(Void... params) {
            MyTable.deleteWhere(null, null);
            ArrayList<SamplePersistentObject> batchOps = new ArrayList<SamplePersistentObject>();
            for (int i = 0; i<100; i++) {
                MyTable table = new MyTable();
                table.setMyString("test_lookup_" + i);
                table.setMyInt(i);
                table.setMyDouble(i*Math.PI);
                batchOps.add(table);
//                table.save();
//                Log.e("LISTFRAGMENT", "saved a new myTable with id " + table.getId());
//                publishProgress("Saved record with MyString = " + table.getMyString() + " and got id " + table.getId());
//                
//                MyTable table2 = MyTable.findOneByMyString("test_lookup_" + i);
//                publishProgress("Loaded record with myString = " + table2.getMyString() + " and got id " + table2.getId() + " and int value " + table2.getMyInt() + "\n");
            }
            try {
            	SamplePersistentObject.applyBatchSave(batchOps);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (OperationApplicationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            boolean didVacuum = SampleDatabase.vacuumDatabase();
            Log.e("DBTask", "db vacuum was succesful = " + didVacuum);
            return null;
        }
    }
    
}
