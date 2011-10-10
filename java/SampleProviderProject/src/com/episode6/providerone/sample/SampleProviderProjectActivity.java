package com.episode6.providerone.sample;

import java.lang.ref.WeakReference;

import com.episode6.providerone.sample.database.objects.MyTable;
import com.episode6.providerone.sample.database.tables.MyTableInfo;

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
        
        public TesterTask(SampleProviderProjectActivity activity) {
            mActivity = new WeakReference<SampleProviderProjectActivity>(activity);
        }

        @Override
        protected String doInBackground(Void... params) {
            MyTable table = new MyTable();
            table.setMyString("So this is a test String");
            table.save();
            long newId = table.get_Id();
            publishProgress("Saved a record and got id " + table.get_Id());
            
            MyTable table2 = MyTable.findOneById(newId, new String[] {MyTableInfo.Columns._ID, MyTableInfo.Columns.MY_STRING});
            
            return "loaded record back and got string of " + table2.getMyString();
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