package com.epam.poc.kinvey.rest.file;

import com.epam.poc.kinvey.model.File;
import com.epam.poc.kinvey.rest.RestClientFactory;
import com.epam.poc.kinvey.rest.RestRequest;
import com.epam.poc.kinvey.rest.RestResponse;

import java.io.InputStream;

public class FileRestClient extends RestClientFactory<File[]> {

    @Override
    public RestRequest createRequest(Object... params) {
        return new FileRestRequest(params);
    }

    @Override
    public RestResponse<File[]> createResponse(InputStream is, int statusCode) {
        return new FileRestResponse(is, statusCode);
    }
}
