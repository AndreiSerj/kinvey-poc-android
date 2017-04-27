package com.epam.poc.kinvey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class File {
    @SerializedName("SyncpointId")
    @Expose
    private String syncpointId;
    @SerializedName("Filename")
    @Expose
    private String filename;
    @SerializedName("LatestVersionId")
    @Expose
    private String latestVersionId;

    public String getSyncpointId() {
        return syncpointId;
    }

    public String getFilename() {
        return filename;
    }

    public String getLatestVersionId() {
        return latestVersionId;
    }

    public enum Type {
        IMAGE, TEXT, UNKNOWN
    }

    public Type getFileType() {
        if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png") || filename.endsWith(".gif")) {
            return Type.IMAGE;
        } else if (filename.endsWith(".txt")) {
            return Type.TEXT;
        } else {
            return Type.UNKNOWN;
        }
    }

}
