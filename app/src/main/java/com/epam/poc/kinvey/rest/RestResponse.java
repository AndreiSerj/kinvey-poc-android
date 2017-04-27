package com.epam.poc.kinvey.rest;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

public abstract class RestResponse<T> {
    private byte[] mData;
    private int mStatusCode;

    public RestResponse(InputStream is, int statusCode) {
        mData = readBody(is);
        mStatusCode = statusCode;
    }

    public byte[] getData() {
        return mData;
    }

    public boolean isSuccess() {
        return (mStatusCode >= HttpURLConnection.HTTP_OK && mStatusCode < HttpURLConnection.HTTP_MULT_CHOICE);
    }

    public abstract T getResult();

    private byte[] readBody(InputStream is) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[4096];
            int n = -1;

            while ( (n = is.read(buffer)) != -1) {
                baos.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return baos.toByteArray();
    }
}
