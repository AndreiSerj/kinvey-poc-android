package com.epam.poc.kinvey.rest;

import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

public class RestResponseTest {

    private static final InputStream DATA = null;

    private class TestRestResponse extends RestResponse<String> {

        TestRestResponse(InputStream is, int statusCode) {
            super(is, statusCode);
        }

        public String getResult() {
            return null;
        }
    }

    @Test
    public void testRestResponse() {
        RestResponse okResponse = new TestRestResponse(DATA, 200);
        Assert.assertTrue(okResponse.isSuccess());

        RestResponse errorResponse = new TestRestResponse(DATA, 301);
        Assert.assertFalse(errorResponse.isSuccess());
    }
}