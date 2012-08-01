{CopyrightMessage}
package {PackageName}.database.autogen.loaders;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import {PackageName}.database.objects.{CapCamelTableName};
import {PackageName}.database.tables.{CapCamelTableName}Info;


public class {CapCamelTableName}Loader extends AsyncTaskLoader<{CapCamelTableName}> {
	
	protected Long mId = null;
{LookupStart}
	protected {LookupJavaType} m{LookupCapCamelName} = null;
{LookupEnd}
	protected {CapCamelTableName} m{CapCamelTableName} = null;
	protected ForceLoadContentObserver mContentObserver;

	public {CapCamelTableName}Loader(Context context, Long id) {
		super(context);
		mId = id;
{LookupStart}
		m{LookupCapCamelName} = null;
{LookupEnd}
		if (mId == null)
			throw new RuntimeException("Tried to construct a {CapCamelTableName}Loader with a null id");
		mContentObserver = new ForceLoadContentObserver();
	}
{LookupStart}
	public {CapCamelTableName}Loader({LookupJavaType} {LookupCamelName}, Context context) {
		super(context);
		m{LookupCapCamelName} = {LookupCamelName};
		mId = null;
		if (m{LookupCapCamelName} == null)
			throw new RuntimeException("Tried to construct a {CapCamelTableName}Loader with a null {LookupCamelName}");
		mContentObserver = new ForceLoadContentObserver();
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
		return m{CapCamelTableName};
	}
	
	@Override
	protected void onStartLoading() {
		if (m{CapCamelTableName} != null)
			deliverResult(m{CapCamelTableName});
		if (takeContentChanged() || m{CapCamelTableName} == null)
			forceLoad();
		getContext().getContentResolver().registerContentObserver({CapCamelTableName}Info.CONTENT_URI, true, mContentObserver);
	}
	
	@Override
	protected void onStopLoading() {
		cancelLoad();
		getContext().getContentResolver().unregisterContentObserver(mContentObserver);
	}

	@Override
	protected void onReset() {
		onStopLoading();
		m{CapCamelTableName} = null;
		super.onReset();
	}

}
