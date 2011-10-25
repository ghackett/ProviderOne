/*
 * Copyright (C) 2011 GroupMe, Inc.
 */
package com.groupme.providerone.sample.database.objects;

import android.os.Parcel;

import com.groupme.providerone.sample.database.autogen.objects.BaseMyTable;

public class MyTable extends BaseMyTable {

    public MyTable() {

    }

    public MyTable(Parcel in) {
        super(in);
    }


    public static final Creator<MyTable> CREATOR = new Creator<MyTable>() {
        public MyTable createFromParcel(Parcel in) {
            return new MyTable(in);
        }

        public MyTable[] newArray(int size) {
            return new MyTable[size];
        }
    };

}
