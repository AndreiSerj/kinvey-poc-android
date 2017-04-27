package com.epam.poc.kinvey.rest.content;

import com.epam.poc.kinvey.rest.RestResponse;

import java.io.InputStream;

class BinaryContentRestResponse extends RestResponse<byte[]> {

    BinaryContentRestResponse(InputStream is, int statusCode) {
        super(is, statusCode);
    }

    @Override
    public byte[] getResult() {
        return getData();
    }
}
