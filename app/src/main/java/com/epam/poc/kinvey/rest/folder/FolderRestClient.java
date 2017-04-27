package com.epam.poc.kinvey.rest.folder;

import com.epam.poc.kinvey.model.Folder;
import com.epam.poc.kinvey.rest.RestClientFactory;
import com.epam.poc.kinvey.rest.RestRequest;
import com.epam.poc.kinvey.rest.RestResponse;

import java.io.InputStream;

public class FolderRestClient extends RestClientFactory<Folder[]> {

    @Override
    public RestRequest createRequest(Object... params) {
        return new FolderRestRequest(params);
    }

    @Override
    public RestResponse<Folder[]> createResponse(InputStream is, int statusCode) {
        return new FolderRestResponse(is, statusCode);
    }
}
