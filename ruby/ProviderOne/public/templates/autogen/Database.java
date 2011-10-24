package {PackageName}.database.autogen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

{TableInfoImports}

public class {ProjectName}Database extends SQLiteOpenHelper {


    public static final String DB_NAME = "{DbFileName}";
    public static final int DB_VERSION = {DbVersion};

{IndexDefs}

    public {ProjectName}Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
{TableCreates}
{IndexExecutes}
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
{TableUpgrades}
{IndexExecutes}
    }

}
