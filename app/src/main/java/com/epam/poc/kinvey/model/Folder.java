package com.epam.poc.kinvey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Folder {
    @SerializedName("SyncpointId")
    @Expose
    private String syncpointId;
    @SerializedName("FolderId")
    @Expose
    private String folderId;
    @SerializedName("Name")
    @Expose
    private String name;

    public String getSyncpointId() {
        return syncpointId;
    }

    public String getFolderId() {
        return folderId;
    }

    public String getName() {
        return name;
    }


}
