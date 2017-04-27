package com.epam.poc.kinvey.loader;

import android.content.Context;

import com.epam.poc.kinvey.model.File;
import com.epam.poc.kinvey.model.FileSystem;
import com.epam.poc.kinvey.model.Folder;
import com.epam.poc.kinvey.rest.RestResponse;
import com.epam.poc.kinvey.rest.file.FileRestClient;
import com.epam.poc.kinvey.rest.folder.FolderRestClient;

import java.util.Arrays;

public class FileSystemLoader extends AppAsyncLoader<FileSystem> {
    private static final String TAG = FileSystemLoader.class.getSimpleName();

    private String mSyncpointId;
    private String mFolderId;

    public FileSystemLoader(Context context, String syncpointId, String folderId) {
        super(context);
        mSyncpointId = syncpointId;
        mFolderId = folderId;
    }

    @Override
    public FileSystem internalLoadInBackground() throws Exception {
        FileSystem fileSystem = new FileSystem();

        final RestResponse<Folder[]> responseFolders = new FolderRestClient().sendRequest(mSyncpointId, mFolderId);
        final RestResponse<File[]> responseFiles = new FileRestClient().sendRequest(mSyncpointId, mFolderId);

        if (responseFolders.isSuccess()) {
            fileSystem.setFolders(Arrays.asList(responseFolders.getResult()));
        }

        if (responseFiles.isSuccess()) {
            fileSystem.setFiles(Arrays.asList(responseFiles.getResult()));
        }

        return fileSystem;
    }

}