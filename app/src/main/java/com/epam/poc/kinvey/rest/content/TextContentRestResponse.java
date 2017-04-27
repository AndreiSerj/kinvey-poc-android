package com.epam.poc.kinvey.rest.content;

import com.epam.poc.kinvey.rest.RestResponse;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

class TextContentRestResponse extends RestResponse<String> {

    TextContentRestResponse(InputStream is, int statusCode) {
        super(is, statusCode);
    }

    @Override
    public String getResult() {
        try {
            return new String(getData(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
