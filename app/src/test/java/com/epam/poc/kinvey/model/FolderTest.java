package com.epam.poc.kinvey.model;

import com.google.gson.Gson;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FolderTest {

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

    @Test
    public void testFolderNames() {
        Folder[] folders = new Gson().fromJson(FOLDER_JSON, Folder[].class);

        assertEquals(3, folders.length);
        assertEquals("Subfolder-1", folders[0].getName());
        assertEquals("Subfolder-2", folders[1].getName());
        assertEquals("Subfolder-3", folders[2].getName());
    }
}
