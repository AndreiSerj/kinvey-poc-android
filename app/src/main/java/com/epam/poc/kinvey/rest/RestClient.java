package com.epam.poc.kinvey.rest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;

class RestClient<T> {
    private static final int CONNECTION_TIMEOUT = 30000;
    private static final int DATA_RETRIEVAL_TIMEOUT = 20000;

    RestResponse<T> sendSync(RestClientFactory<T> restFactory, Object...params) throws RestException {
        RestRequest restRequest = restFactory.createRequest(params);

        final RestRequest.RestMethod method = restRequest.getMethod();
        final String url = restRequest.getPath();

        HttpURLConnection urlConnection = null;
        try {
            URL resultUrl = new URL(url);
            urlConnection = (HttpURLConnection) resultUrl.openConnection();
            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConnection.setReadTimeout(DATA_RETRIEVAL_TIMEOUT);
            if (restRequest.getHeaders() != null) {
                for (Map.Entry<String, String> param : restRequest.getHeaders().entrySet()) {
                    urlConnection.setRequestProperty(param.getKey(), param.getValue());
                }
            }
            urlConnection.setRequestMethod(method.name());

            if (method == RestRequest.RestMethod.POST) {
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                final String urlParams = restRequest.getUrlParameters();
                if (urlParams != null) {
                    sendPostData(urlConnection.getOutputStream(), urlParams);
                }
            }

            return restFactory.createResponse(urlConnection.getInputStream(), urlConnection.getResponseCode());
        } catch (MalformedURLException e) {
            throw new RestException("Malformed url: " + url, e);
        } catch (SocketTimeoutException e) {
             throw new RestException("Socket timeout", e);
        } catch (UnknownHostException e) {
            throw new RestException("Unknown host", e);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RestException("Connection error", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    private static void sendPostData(OutputStream os, String urlParams) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(urlParams);
            writer.flush();
            writer.close();
        } finally {
            try {
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
