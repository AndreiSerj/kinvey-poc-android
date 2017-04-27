package com.epam.poc.kinvey.model;

import com.google.gson.Gson;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SyncpointTest {

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

    @Test
    public void testSyncpointNames() {
        Syncpoint[] syncpoints = new Gson().fromJson(SYNCPOINT_JSON, Syncpoint[].class);

        assertEquals(3, syncpoints.length);
        assertEquals("Syncpoint-1", syncpoints[0].getName());
        assertEquals("Syncpoint-2", syncpoints[1].getName());
        assertEquals("Syncpoint-3", syncpoints[2].getName());
    }
}
