{AutoGenCopyrightMessage}
package {PackageName}.database.autogen;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import {PackageName}.database.{ProjectName}Provider;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public abstract class PersistentObject implements Parcelable {

    public static ContentProviderResult[] applyBatchSave(ArrayList<PersistentObject> objects) throws RemoteException, OperationApplicationException {
        return {ProjectName}Provider.getAppContext().getContentResolver().applyBatch({ProjectName}Provider.getContentAuthority(), getSaveProviderOperations(objects));
    }

    public static ArrayList<ContentProviderOperation> getSaveProviderOperations(ArrayList<PersistentObject> objects) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>(objects.size());
        for (PersistentObject obj : objects)
            ops.add(obj.getSaveProviderOperation());
        return ops;
    }

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
    abstract public JSONObject toJson(ColumnHelper helper) throws JSONException;
    abstract public JSONObject toJson(String[] projection) throws JSONException;
    abstract public ContentProviderOperation getSaveProviderOperation();

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mIsNew ? 1 : 0);
    }

    public void readFromParcel(Parcel in) {
        mIsNew = in.readInt() == 1;
    }

    abstract public static class ColumnHelper {

        public String[] projection;

        public ColumnHelper(String[] projection) {
            this.projection = projection;
        }

        protected int getColumnIndex(String columnName) {
            if (columnName == null || projection == null)
                return -1;
            for (int i = 0; i<projection.length; i++) {
                if (columnName.equals(projection[i]))
                    return i;
            }
            return -1;
        }

    }

}