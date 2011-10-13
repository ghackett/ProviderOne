package {PackageName}.database.autogen;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public abstract class PersistentObject implements Parcelable {

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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mIsNew ? 1 : 0);
    }

    public void readFromParcel(Parcel in) {
        mIsNew = in.readInt() == 1;
    }

    abstract public static class ColumnHelper {

    }

}
