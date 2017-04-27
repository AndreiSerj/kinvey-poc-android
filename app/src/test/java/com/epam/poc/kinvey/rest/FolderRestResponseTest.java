package com.epam.poc.kinvey.rest;

import com.epam.poc.kinvey.model.Folder;
import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class FolderRestResponseTest {

    private static final String FOLDER_JSON = "[{"
            + "\"SyncpointId\" : 5723790,"
            + "\"FolderId\" : 869997930,"
            + "\"VirtualPath\" : \"Subfolder-1\","
            + "\"Status\" : 1,"
            + "\"Name\" : \"Subfolder-1\""
            + "}, {"
            + "\"SyncpointId\" : 5723790,"
            + "\"FolderId\" : 869997931,"
            + "\"VirtualPath\" : \"Subfolder-2\","
            + "\"Status\" : 1,"
            + "\"Name\" : \"Subfolder-2\""
            + "}, {"
            + "\"SyncpointId\" : 5723790,"
            + "\"FolderId\" : 869997932,"
            + "\"VirtualPath\" : \"Subfolder-3\","
            + "\"Status\" : 1,"
            + "\"Name\" : \"Subfolder-3\""
            + "}"
            + "]";

    private static final InputStream DATA = new ByteArrayInputStream(FOLDER_JSON.getBytes());;

    private class TestFolderRestResponse extends RestResponse<Folder[]> {

        TestFolderRestResponse(InputStream is, int statusCode) {
            super(is, statusCode);
        }

        @Override
        public Folder[] getResult() {
            try {
                return new Gson().fromJson(new String(getData(), "UTF-8"), Folder[].class);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return new Folder[0];
        }
    }

    @Test
    public void testRestResponse() {
        RestResponse<Folder[]> okResponse = new FolderRestResponseTest.TestFolderRestResponse(DATA, 200);
        Assert.assertTrue(okResponse.isSuccess());
        Assert.assertEquals(3, okResponse.getResult().length);

        RestResponse errorResponse = new FolderRestResponseTest.TestFolderRestResponse(DATA, 301);
        Assert.assertFalse(errorResponse.isSuccess());
    }
}
