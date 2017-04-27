package com.epam.poc.kinvey.rest.content;

import com.epam.poc.kinvey.SyncApplication;
import com.epam.poc.kinvey.common.PreferenceStore;
import com.epam.poc.kinvey.rest.RestRequest;

import java.util.HashMap;
import java.util.Map;

class FileContentRestRequest extends RestRequest {
    final private static Map<String, String> HEADERS = new HashMap<String, String>() {{
        put("AppKey", "l5GC4PTU1GAgY3fGwkfKXP4vtjYKFOUZ");
        put("Authorization", "Bearer " + SyncApplication.getPreferenceStore().loadString(PreferenceStore.ACCESS_TOKEN_KEY));
    }};

    FileContentRestRequest(Object... params) {
        super(RestMethod.GET, RestAction.GET_FILE_CONTENT.getContentPath(params), HEADERS);
    }
}
