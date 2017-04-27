package com.epam.poc.kinvey.rest.token;

import com.epam.poc.kinvey.model.Token;
import com.epam.poc.kinvey.rest.RestClientFactory;
import com.epam.poc.kinvey.rest.RestRequest;
import com.epam.poc.kinvey.rest.RestResponse;

import java.io.InputStream;

public class TokenRestClient extends RestClientFactory<Token> {

    @Override
    public RestRequest createRequest(Object... params) {
        return new TokenRestRequest();
    }

    @Override
    public RestResponse<Token> createResponse(InputStream is, int statusCode) {
        return new TokenRestResponse(is, statusCode);
    }
}
