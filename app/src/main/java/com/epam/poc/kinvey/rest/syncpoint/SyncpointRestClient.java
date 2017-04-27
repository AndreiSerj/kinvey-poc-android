package com.epam.poc.kinvey.rest.syncpoint;

import com.epam.poc.kinvey.model.Syncpoint;
import com.epam.poc.kinvey.rest.RestClientFactory;
import com.epam.poc.kinvey.rest.RestRequest;
import com.epam.poc.kinvey.rest.RestResponse;

import java.io.InputStream;

public class SyncpointRestClient extends RestClientFactory<Syncpoint[]> {

    @Override
    public RestRequest createRequest(Object... params) {
        return new SyncpointRestRequest();
    }

    @Override
    public RestResponse<Syncpoint[]> createResponse(InputStream is, int statusCode) {
        return new SyncpointRestResponse(is, statusCode);
    }
}
