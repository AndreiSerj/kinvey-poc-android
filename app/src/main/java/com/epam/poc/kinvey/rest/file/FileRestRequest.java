package com.epam.poc.kinvey.rest.file;

import com.epam.poc.kinvey.SyncApplication;
import com.epam.poc.kinvey.common.PreferenceStore;
import com.epam.poc.kinvey.rest.RestRequest;

import java.util.HashMap;
import java.util.Map;

class FileRestRequest extends RestRequest {
    final private static Map<String, String> HEADERS = new HashMap<String, String>() {{
        put("Content-Type", "application/json");
        put("Accept", "application/json");
        put("AppKey", "l5GC4PTU1GAgY3fGwkfKXP4vtjYKFOUZ");
        put("Authorization", "Bearer " + SyncApplication.getPreferenceStore().loadString(PreferenceStore.ACCESS_TOKEN_KEY));
    }};

    FileRestRequest(Object... params) {
        super(RestMethod.GET, RestAction.GET_FILES.getPath(params), HEADERS);
    }
}