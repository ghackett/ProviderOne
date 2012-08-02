{CopyrightMessage}
package {PackageName}.database.autogen.loaders;


import android.content.Context;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;

import {PackageName}.database.objects.{CapCamelTableName};
import {PackageName}.database.tables.{CapCamelTableName}Info;


public class {CapCamelTableName}Loader extends AsyncTaskLoader<{CapCamelTableName}> {
	
	protected Long mId = null;
{LookupStart}
	protected {LookupJavaType} m{LookupCapCamelName} = null;
{LookupEnd}
	protected {CapCamelTableName} m{CapCamelTableName} = null;
	protected ForceLoadContentObserver mContentObserver = null;
	protected boolean mDidFallBackToFullTableObserver = false;

	public {CapCamelTableName}Loader(Context context, Long id) {
		super(context);
		mId = id;
{LookupStart}
		m{LookupCapCamelName} = null;
{LookupEnd}
		if (mId == null)
			throw new RuntimeException("Tried to construct a {CapCamelTableName}Loader with a null id");
	}
{LookupStart}
	public {CapCamelTableName}Loader({LookupJavaType} {LookupCamelName}, Context context) {
		super(context);
		m{LookupCapCamelName} = {LookupCamelName};
		mId = null;
		if (m{LookupCapCamelName} == null)
			throw new RuntimeException("Tried to construct a {CapCamelTableName}Loader with a null {LookupCamelName}");
	}
{LookupEnd}
	@Override
	public {CapCamelTableName} loadInBackground() {
		if (mId != null) {
			m{CapCamelTableName} = {CapCamelTableName}.findOneById(mId);
		} 
{LookupStart}
		if (m{LookupCapCamelName} != null) {
			m{CapCamelTableName} = {CapCamelTableName}.findOneBy{LookupCapCamelName}(m{LookupCapCamelName});
		}
{LookupEnd}
		if (mContentObserver == null && m{CapCamelTableName} != null) {
			Uri notifUri = m{CapCamelTableName}.getIdLookupUri();
			if (notifUri != null) {
				mContentObserver = new ForceLoadContentObserver();
				getContext().getContentResolver().registerContentObserver(notifUri, true, mContentObserver);
			}
		} else if (mContentObserver == null && m{CapCamelTableName} == null) {
			mDidFallBackToFullTableObserver = true;
			mContentObserver = new ForceLoadContentObserver();
			getContext().getContentResolver().registerContentObserver({CapCamelTableName}Info.CONTENT_URI, true, mContentObserver);
		} else if (mContentObserver != null && m{CapCamelTableName} != null && mDidFallBackToFullTableObserver) {
			Uri notifUri = m{CapCamelTableName}.getIdLookupUri();
			if (notifUri != null) {
				mDidFallBackToFullTableObserver = false;
				getContext().getContentResolver().unregisterContentObserver(mContentObserver);
				getContext().getContentResolver().registerContentObserver(notifUri, true, mContentObserver);
			}
		}
		return m{CapCamelTableName};
	}
	
	@Override
	protected void onStartLoading() {
		if (m{CapCamelTableName} != null)
			deliverResult(m{CapCamelTableName});
		if (takeContentChanged() || m{CapCamelTableName} == null)
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
		m{CapCamelTableName} = null;
		super.onReset();
	}

}
