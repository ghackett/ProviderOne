/*
 * Copyright (C) 2011 GroupMe, Inc.
 */
package com.groupme.providerone.sample.database.objects;

import android.os.Parcel;

import com.groupme.providerone.sample.database.autogen.objects.BaseMyView;

public class MyView extends BaseMyView {

    public MyView() {

    }

    public MyView(Parcel in) {
        super(in);
    }


    public static final Creator<MyView> CREATOR = new Creator<MyView>() {
        public MyView createFromParcel(Parcel in) {
            return new MyView(in);
        }

        public MyView[] newArray(int size) {
            return new MyView[size];
        }
    };

}
