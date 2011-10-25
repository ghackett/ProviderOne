{CopyrightMessage}
package {PackageName}.database.objects;

import android.os.Parcel;

import {PackageName}.database.autogen.objects.Base{CapCamelTableName};

public class {CapCamelTableName} extends Base{CapCamelTableName} {

    public {CapCamelTableName}() {

    }

    public {CapCamelTableName}(Parcel in) {
        super(in);
    }


    public static final Creator<{CapCamelTableName}> CREATOR = new Creator<{CapCamelTableName}>() {
        public {CapCamelTableName} createFromParcel(Parcel in) {
            return new {CapCamelTableName}(in);
        }

        public {CapCamelTableName}[] newArray(int size) {
            return new {CapCamelTableName}[size];
        }
    };

}
