package com.epam.poc.kinvey.rest.content;

import com.epam.poc.kinvey.rest.RestClientFactory;
import com.epam.poc.kinvey.rest.RestRequest;
import com.epam.poc.kinvey.rest.RestResponse;

import java.io.InputStream;

public class BinaryRestClient extends RestClientFactory<byte[]> {

    @Override
    public RestRequest createRequest(Object... params) {
        return new FileContentRestRequest(params);
    }

    @Override
    public RestResponse<byte[]> createResponse(InputStream is, int statusCode) {
        return new BinaryContentRestResponse(is, statusCode);
    }
}
