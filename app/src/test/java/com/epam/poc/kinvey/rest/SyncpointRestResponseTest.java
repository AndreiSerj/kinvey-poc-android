package com.epam.poc.kinvey.rest;

import com.epam.poc.kinvey.model.Syncpoint;
import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class SyncpointRestResponseTest {

    private static final String SYNCPOINT_JSON = "[{"
            + "\"Id\" : 5723790,"
            + "\"Type\" : 6,"
            + "\"RootFolderId\" : 123456,"
            + "\"Name\" : \"Syncpoint-1\","
            + "\"Permission\" : 1"
            + "}, {"
            + "\"Id\" : 5723791,"
            + "\"Type\" : 5,"
            + "\"RootFolderId\" : 123456,"
            + "\"Name\" : \"Syncpoint-2\","
            + "\"Permission\" : 1"
            + "}, {"
            + "\"Id\" : 5723792,"
            + "\"Type\" : 6,"
            + "\"RootFolderId\" : 123456,"
            + "\"Name\" : \"Syncpoint-3\","
            + "\"Permission\" : 1"
            + "}"
            + "]";

    private static final InputStream DATA = new ByteArrayInputStream(SYNCPOINT_JSON.getBytes());;

    private class TestSyncpointRestResponse extends RestResponse<Syncpoint[]> {

        TestSyncpointRestResponse(InputStream is, int statusCode) {
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

    @Test
    public void testRestResponse() {
        RestResponse<Syncpoint[]> okResponse = new SyncpointRestResponseTest.TestSyncpointRestResponse(DATA, 200);
        Assert.assertTrue(okResponse.isSuccess());
        Assert.assertEquals(3, okResponse.getResult().length);

        RestResponse errorResponse = new SyncpointRestResponseTest.TestSyncpointRestResponse(DATA, 301);
        Assert.assertFalse(errorResponse.isSuccess());
    }
}
