package com.epam.poc.kinvey.loader;

public class AppAsyncLoaderResult<T> {

    private T mData;
    private Exception mException;

    AppAsyncLoaderResult(T data) {
        mData = data;
    }

    AppAsyncLoaderResult(Exception exception) {
        mException = exception;
    }

    public T getData() {
        return mData;
    }

    public Exception getException() {
        return mException;
    }
}