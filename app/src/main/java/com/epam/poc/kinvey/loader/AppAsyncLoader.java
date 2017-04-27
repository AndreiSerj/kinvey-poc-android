package com.epam.poc.kinvey.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

abstract class AppAsyncLoader<T> extends AsyncTaskLoader<AppAsyncLoaderResult<T>> {

    private static final long STALE_DELTA = 60000;

    private Exception mException;
    private long mLastLoad;
    private AppAsyncLoaderResult<T> mResult;

    AppAsyncLoader(Context context) {
        super(context);
    }

    public Exception getException() {
        return mException;
    }

    public void setException(Exception exception) {
        mException = exception;
    }

    public abstract T internalLoadInBackground() throws Exception;

    @Override
    public AppAsyncLoaderResult<T> loadInBackground() {
        try {
            T result = internalLoadInBackground();
            return new AppAsyncLoaderResult<>(result);
        } catch (Exception exception) {
            setException(exception);
            return new AppAsyncLoaderResult<>(exception);
        }
    }

    @Override
    public void deliverResult(AppAsyncLoaderResult<T> data) {
        mResult = data;
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        if (null == mResult || System.currentTimeMillis() - mLastLoad >= STALE_DELTA) {
            forceLoad();
        } else if (null != mException) {
            forceLoad();
        } else if (null != mResult) {
            deliverResult(mResult);
        }
        mLastLoad = System.currentTimeMillis();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();

        onStopLoading();
        mResult = null;
        mLastLoad = 0;
    }
}
