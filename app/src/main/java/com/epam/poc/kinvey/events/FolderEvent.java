package com.epam.poc.kinvey.events;

public class FolderEvent extends BaseEvent {
    private String mName;
    private String mSyncpointId;
    private String mFolderId;

    public FolderEvent(String name, String syncpointId, String folderId) {
        mName = name;
        mSyncpointId = syncpointId;
        mFolderId = folderId;
    }

    public String getName() {
        return mName;
    }

    public String getFolderId() {
        return mFolderId;
    }

    public String getSyncpointId() {
        return mSyncpointId;
    }

}
