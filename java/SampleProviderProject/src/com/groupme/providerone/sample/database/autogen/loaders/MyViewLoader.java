/*
 * Copyright (C) 2011 GroupMe, Inc.
 */
package com.groupme.providerone.sample.database.autogen.loaders;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.groupme.providerone.sample.database.objects.MyView;
import com.groupme.providerone.sample.database.tables.MyViewInfo;


public class MyViewLoader extends AsyncTaskLoader<MyView> {
	
	protected Long mId = null;

	protected String mMyString = null;

	protected MyView mMyView = null;
	protected ForceLoadContentObserver mContentObserver;

	public MyViewLoader(Context context, Long id) {
		super(context);
		mId = id;

		mMyString = null;

		if (mId == null)
			throw new RuntimeException("Tried to construct a MyViewLoader with a null id");
		mContentObserver = new ForceLoadContentObserver();
	}

	public MyViewLoader(String myString, Context context) {
		super(context);
		mMyString = myString;
		mId = null;
		if (mMyString == null)
			throw new RuntimeException("Tried to construct a MyViewLoader with a null myString");
		mContentObserver = new ForceLoadContentObserver();
	}

	@Override
	public MyView loadInBackground() {
		if (mId != null) {
			mMyView = MyView.findOneById(mId);
		} 

		if (mMyString != null) {
			mMyView = MyView.findOneByMyString(mMyString);
		}

		return mMyView;
	}
	
	@Override
	protected void onStartLoading() {
		if (mMyView != null)
			deliverResult(mMyView);
		if (takeContentChanged() || mMyView == null)
			forceLoad();
		getContext().getContentResolver().registerContentObserver(MyViewInfo.CONTENT_URI, true, mContentObserver);
	}
	
	@Override
	protected void onStopLoading() {
		cancelLoad();
		getContext().getContentResolver().unregisterContentObserver(mContentObserver);
	}

	@Override
	protected void onReset() {
		onStopLoading();
		mMyView = null;
		super.onReset();
	}

}
