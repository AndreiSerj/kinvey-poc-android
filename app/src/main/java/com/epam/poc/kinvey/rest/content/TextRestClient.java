package com.epam.poc.kinvey.rest.content;

import com.epam.poc.kinvey.rest.RestClientFactory;
import com.epam.poc.kinvey.rest.RestRequest;
import com.epam.poc.kinvey.rest.RestResponse;

import java.io.InputStream;

public class TextRestClient extends RestClientFactory<String> {

    @Override
    public RestRequest createRequest(Object... params) {
        return new FileContentRestRequest(params);
    }

    @Override
    public RestResponse<String> createResponse(InputStream is, int statusCode) {
        return new TextContentRestResponse(is, statusCode);
    }
}
