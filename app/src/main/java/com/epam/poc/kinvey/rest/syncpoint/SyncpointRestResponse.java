package com.epam.poc.kinvey.rest.syncpoint;

import com.epam.poc.kinvey.model.Syncpoint;
import com.epam.poc.kinvey.rest.RestResponse;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

class SyncpointRestResponse extends RestResponse<Syncpoint[]> {

    SyncpointRestResponse(InputStream is, int statusCode) {
        super(is, statusCode);
    }

    @Override
    public Syncpoint[] getResult() {
        try {
            return new Gson().fromJson(new String(getData(), "UTF-8"), Syncpoint[].class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new Syncpoint[0];
    }
}
