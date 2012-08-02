/*
 * Copyright (C) 2011 GroupMe, Inc.
 */
package com.groupme.providerone.sample.database.autogen.loaders;


import android.content.Context;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;

import com.groupme.providerone.sample.database.objects.MyView;
import com.groupme.providerone.sample.database.tables.MyViewInfo;


public class MyViewLoader extends AsyncTaskLoader<MyView> {
	
	protected Long mId = null;

	protected String mMyString = null;

	protected MyView mMyView = null;
	protected ForceLoadContentObserver mContentObserver = null;
	protected boolean mDidFallBackToFullTableObserver = false;

	public MyViewLoader(Context context, Long id) {
		super(context);
		mId = id;

		mMyString = null;

		if (mId == null)
			throw new RuntimeException("Tried to construct a MyViewLoader with a null id");
	}

	public MyViewLoader(String myString, Context context) {
		super(context);
		mMyString = myString;
		mId = null;
		if (mMyString == null)
			throw new RuntimeException("Tried to construct a MyViewLoader with a null myString");
	}

	@Override
	public MyView loadInBackground() {
		if (mId != null) {
			mMyView = MyView.findOneById(mId);
		} 

		if (mMyString != null) {
			mMyView = MyView.findOneByMyString(mMyString);
		}

		if (mContentObserver == null && mMyView != null) {
			Uri notifUri = mMyView.getIdLookupUri();
			if (notifUri != null) {
				mContentObserver = new ForceLoadContentObserver();
				getContext().getContentResolver().registerContentObserver(notifUri, true, mContentObserver);
			}
		} else if (mContentObserver == null && mMyView == null) {
			mDidFallBackToFullTableObserver = true;
			mContentObserver = new ForceLoadContentObserver();
			getContext().getContentResolver().registerContentObserver(MyViewInfo.CONTENT_URI, true, mContentObserver);
		} else if (mContentObserver != null && mMyView != null && mDidFallBackToFullTableObserver) {
			Uri notifUri = mMyView.getIdLookupUri();
			if (notifUri != null) {
				mDidFallBackToFullTableObserver = false;
				getContext().getContentResolver().unregisterContentObserver(mContentObserver);
				getContext().getContentResolver().registerContentObserver(notifUri, true, mContentObserver);
			}
		}
		return mMyView;
	}
	
	@Override
	protected void onStartLoading() {
		if (mMyView != null)
			deliverResult(mMyView);
		if (takeContentChanged() || mMyView == null)
			forceLoad();
	}
	
	@Override
	protected void onStopLoading() {
		cancelLoad();
		if (mContentObserver != null) {
			mDidFallBackToFullTableObserver = false;
			getContext().getContentResolver().unregisterContentObserver(mContentObserver);
		}
	}

	@Override
	protected void onReset() {
		onStopLoading();
		mMyView = null;
		super.onReset();
	}

}
