/*
 * Copyright (C) 2011 GroupMe, Inc.
 */
package com.groupme.providerone.sample.database.autogen.loaders;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.groupme.providerone.sample.database.objects.MyTable;
import com.groupme.providerone.sample.database.tables.MyTableInfo;


public class MyTableLoader extends AsyncTaskLoader<MyTable> {
	
	protected Long mId = null;

	protected String mMyString = null;

	protected MyTable mMyTable = null;
	protected ForceLoadContentObserver mContentObserver;

	public MyTableLoader(Context context, Long id) {
		super(context);
		mId = id;

		mMyString = null;

		if (mId == null)
			throw new RuntimeException("Tried to construct a MyTableLoader with a null id");
		mContentObserver = new ForceLoadContentObserver();
	}

	public MyTableLoader(String myString, Context context) {
		super(context);
		mMyString = myString;
		mId = null;
		if (mMyString == null)
			throw new RuntimeException("Tried to construct a MyTableLoader with a null myString");
		mContentObserver = new ForceLoadContentObserver();
	}

	@Override
	public MyTable loadInBackground() {
		if (mId != null) {
			mMyTable = MyTable.findOneById(mId);
		} 

		if (mMyString != null) {
			mMyTable = MyTable.findOneByMyString(mMyString);
		}

		return mMyTable;
	}
	
	@Override
	protected void onStartLoading() {
		if (mMyTable != null)
			deliverResult(mMyTable);
		if (takeContentChanged() || mMyTable == null)
			forceLoad();
		getContext().getContentResolver().registerContentObserver(MyTableInfo.CONTENT_URI, true, mContentObserver);
	}
	
	@Override
	protected void onStopLoading() {
		cancelLoad();
		getContext().getContentResolver().unregisterContentObserver(mContentObserver);
	}

	@Override
	protected void onReset() {
		onStopLoading();
		mMyTable = null;
		super.onReset();
	}

}
