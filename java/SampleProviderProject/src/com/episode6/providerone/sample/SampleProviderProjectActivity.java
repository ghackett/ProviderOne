package com.episode6.providerone.sample;

import java.lang.ref.WeakReference;

import com.episode6.providerone.sample.database.autogen.tables.BaseMyTableInfo;
import com.episode6.providerone.sample.database.objects.MyTable;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class SampleProviderProjectActivity extends Activity {
    
    public TextView mTextView;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mTextView = (TextView) findViewById(R.id.textview);
        new TesterTask(this).execute((Void)null);
    }
    
    private static class TesterTask extends AsyncTask<Void, String, String> {
        private WeakReference<SampleProviderProjectActivity> mActivity;
        int count = -1;
        
        public TesterTask(SampleProviderProjectActivity activity) {
            mActivity = new WeakReference<SampleProviderProjectActivity>(activity);
        }

        @Override
        protected String doInBackground(Void... params) {
            int count = MyTable.getCount(null, null);
            publishProgress("\nCurrent count = " + count);
            if (count > 0) {
                publishProgress("deleting all records");
                MyTable.deleteWhere(null, null);
                publishProgress("Current count = " + MyTable.getCount(null, null) + "\n");                
            } else {
                publishProgress("");
            }
            for (int i = 0; i<10; i++) {
                MyTable table = new MyTable();
                table.setMyString("test_lookup_" + i);
                table.setMyInt(i);
                table.save();
                publishProgress("Saved record with MyString = " + table.getMyString() + " and got id " + table.get_Id());
                
                MyTable table2 = MyTable.findOneByMyString("test_lookup_" + i, null);
                publishProgress("Loaded record with myString = " + table2.getMyString() + " and got id " + table2.get_Id() + " and int value " + table2.getMyInt() + "\n");
            }
            
            return "final table count = " + MyTable.getCount(null, null);
        }

        @Override
        protected void onPostExecute(String result) {
            SampleProviderProjectActivity a = mActivity.get();
            if (a != null && result != null) {
                a.updateText(result);
            }
            super.onPostExecute(result);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            SampleProviderProjectActivity a = mActivity.get();
            if (a != null && values != null) {
                a.updateText(values[0]);
            }
            super.onProgressUpdate(values);
        }
        
        
    }
    
    public void updateText(String text) {
        String current = mTextView.getText().toString();
        current += "\n" + text;
        mTextView.setText(current);
    }
}