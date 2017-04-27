package com.epam.poc.kinvey.model;

import com.google.gson.Gson;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileTest {

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

    @Test
    public void testFileTypes() {
        File[] files = new Gson().fromJson(FILE_JSON, File[].class);

        assertEquals(3, files.length);
        assertEquals(File.Type.IMAGE, files[0].getFileType());
        assertEquals(File.Type.TEXT, files[1].getFileType());
        assertEquals(File.Type.UNKNOWN, files[2].getFileType());
    }
}