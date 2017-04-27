package com.epam.poc.kinvey.rest.file;

import com.epam.poc.kinvey.model.File;
import com.epam.poc.kinvey.rest.RestResponse;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

class FileRestResponse extends RestResponse<File[]> {

    FileRestResponse(InputStream is, int statusCode) {
        super(is, statusCode);
    }

    @Override
    public File[] getResult() {
        try {
            return new Gson().fromJson(new String(getData(), "UTF-8"), File[].class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new File[0];
    }
}
