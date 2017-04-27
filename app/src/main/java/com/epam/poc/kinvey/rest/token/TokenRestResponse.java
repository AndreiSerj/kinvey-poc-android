package com.epam.poc.kinvey.rest.token;

import com.epam.poc.kinvey.model.Token;
import com.epam.poc.kinvey.rest.RestResponse;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

class TokenRestResponse extends RestResponse<Token> {

    TokenRestResponse(InputStream is, int statusCode) {
        super(is, statusCode);
    }

    @Override
    public Token getResult() {
        try {
            return new Gson().fromJson(new String(getData(), "UTF-8"), Token.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new Token();
    }
}
