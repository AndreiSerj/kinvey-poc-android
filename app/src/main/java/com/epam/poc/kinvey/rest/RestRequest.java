package com.epam.poc.kinvey.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class RestRequest {
    private static final String HOST_URL = "https://api.syncplicity.com";
    private static final String DATA_URL = "https://data.syncplicity.com";

    protected enum RestMethod {
        GET, POST
    }

    protected enum RestAction {
        GET_OAUTH_TOKEN("/oauth/token"),
        GET_SYNCPOINTS("/syncpoint/syncpoints.svc/"),
        GET_FOLDERS("/sync/folder_folders.svc/%s/folder/%s/folders"),
        GET_FILES("/sync/folder_files.svc/%s/folder/%s/files"),
        GET_FILE_CONTENT("/retrieveFile.php?vToken=%s-%s");

        private final String mPathTemplate;

        RestAction(String pathTemplate) {
            mPathTemplate = pathTemplate;
        }

        public String getPath(Object... args) {
            return HOST_URL + String.format(mPathTemplate, args);
        }

        public String getContentPath(Object... args) {
            return DATA_URL + String.format(mPathTemplate, args);
        }
    }

    private RestMethod mMethod;
    private String mPath;
    private Map<String, String> mHeaders;
    private Map<String, String> mUrlParameters;

    public RestRequest(RestMethod method, String path, Map<String, String> headers) {
        mMethod = method;
        mPath = path;
        mHeaders = headers;
    }

    public RestRequest(RestMethod method, String path, Map<String, String> headers, Map<String, String> urlParameters) {
        this(method, path, headers);
        mUrlParameters = urlParameters;
    }

    RestMethod getMethod() {
        return mMethod;
    }

    String getPath() {
        return mPath;
    }

    String getUrlParameters() throws UnsupportedEncodingException {
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, String> param : mUrlParameters.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        return postData.toString();
    }

    Map<String, String> getHeaders () {
        return mHeaders;
    }

    @Override
    public String toString() {
        return mMethod + " " + mPath;
    }

}
