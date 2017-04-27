package com.epam.poc.kinvey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Syncpoint {
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("RootFolderId")
    @Expose
    private String rootFolderId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRootFolderId() {
        return rootFolderId;
    }

    public void setRootFolderId(String rootFolderId) {
        this.rootFolderId = rootFolderId;
    }

}
