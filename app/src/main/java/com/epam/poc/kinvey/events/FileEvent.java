package com.epam.poc.kinvey.events;

import com.epam.poc.kinvey.model.File;

public class FileEvent  extends BaseEvent {
    private File.Type mType;
    private String mName;
    private String mSyncpointId;
    private String mLatestVersionId;

    public FileEvent(File.Type type, String name, String syncpointId, String latestVersionId) {
        mType = type;
        mName = name;
        mSyncpointId = syncpointId;
        mLatestVersionId = latestVersionId;
    }

    public String getName() {
        return mName;
    }

    public String getSyncpointId() {
        return mSyncpointId;
    }

    public String getLatestVersionId() {
        return mLatestVersionId;
    }

    public File.Type getType() {
        return mType;
    }

}
