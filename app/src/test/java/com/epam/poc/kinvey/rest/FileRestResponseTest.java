package com.epam.poc.kinvey.rest;

import com.epam.poc.kinvey.model.File;
import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class FileRestResponseTest {

    private static final String FILE_JSON = "[{"
            + "\"SyncpointId\" : 5723790,"
            + "\"FileId\" : 869997930,"
            + "\"Filename\" : \"image1.png\","
            + "\"Length\" : 135,"
            + "\"Status\" : 1,"
            + "\"Stored\" : true,"
            + "\"FolderId\" : 267572572"
            + "}, {"
            + "\"SyncpointId\" : 5723790,"
            + "\"FileId\" : 869998047,"
            + "\"Filename\" : \"text2.txt\","
            + "\"Length\" : 167,"
            + "\"Status\" : 1,"
            + "\"Stored\" : true,"
            + "\"FolderId\" : 267572572"
            + "}, {"
            + "\"SyncpointId\" : 5723790,"
            + "\"FileId\" : 869998128,"
            + "\"Filename\" : \"unknown3.diff\","
            + "\"Length\" : 106,"
            + "\"Status\" : 1,"
            + "\"Stored\" : true,"
            + "\"FolderId\" : 267572572"
            + "}"
            + "]";

    private static final InputStream DATA = new ByteArrayInputStream(FILE_JSON.getBytes());;

    private class TestFileRestResponse extends RestResponse<File[]> {

        TestFileRestResponse(InputStream is, int statusCode) {
            super(is, statusCode);
        }

        @Override
        public File[] getResult() {
            try {
                return new Gson().fromJson(new String(getData(), "UTF-8"), File[].class);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return new File[0];
        }
    }

    @Test
    public void testRestResponse() {
        RestResponse<File[]> okResponse = new FileRestResponseTest.TestFileRestResponse(DATA, 200);
        Assert.assertTrue(okResponse.isSuccess());
        Assert.assertEquals(3, okResponse.getResult().length);

        RestResponse errorResponse = new FileRestResponseTest.TestFileRestResponse(DATA, 301);
        Assert.assertFalse(errorResponse.isSuccess());
    }
}
