package com.epam.poc.kinvey.rest;

import java.io.InputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public abstract class RestClientFactory<T> {

    public abstract RestRequest createRequest(Object... params);
    public abstract RestResponse<T> createResponse(InputStream is, int statusCode);

    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public RestResponse<T> sendRequest(Object... params) throws RestException {
        return new RestClient<T>().sendSync(this, params);
    }

    public void sendRequestAsync(final RestRequestCallback<T> callback, final Object... params) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    callback.onSuccess(sendRequest(params));
                } catch (Exception e) {
                    callback.onError(e);
                }
            }
        });
    }

    public interface RestRequestCallback<T> {
        void onSuccess(RestResponse<T> response);
        void onError(Exception exception);
    }
}

