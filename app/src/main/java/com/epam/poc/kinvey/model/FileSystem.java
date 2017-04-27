package com.epam.poc.kinvey.model;

import java.util.ArrayList;
import java.util.List;

public class FileSystem {
    private List<Folder> mFolders;
    private List<File> mFiles;

    public List<Folder> getFolders() {
        return mFolders != null ? mFolders : new ArrayList<Folder>();
    }

    public void setFolders(List<Folder> folders) {
        this.mFolders = folders;
    }

    public List<File> getFiles() {
        return mFiles != null ? mFiles : new ArrayList<File>();
    }

    public void setFiles(List<File> files) {
        this.mFiles = files;
    }
}
