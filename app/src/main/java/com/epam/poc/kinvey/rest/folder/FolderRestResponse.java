package com.epam.poc.kinvey.rest.folder;

import com.epam.poc.kinvey.model.Folder;
import com.epam.poc.kinvey.rest.RestResponse;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

class FolderRestResponse extends RestResponse<Folder[]> {

    FolderRestResponse(InputStream is, int statusCode) {
        super(is, statusCode);
    }

    @Override
    public Folder[] getResult() {
        try {
            return new Gson().fromJson(new String(getData(), "UTF-8"), Folder[].class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new Folder[0];
    }
}
