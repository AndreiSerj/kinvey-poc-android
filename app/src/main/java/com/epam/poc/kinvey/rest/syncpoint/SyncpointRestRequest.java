package com.epam.poc.kinvey.rest.syncpoint;

import com.epam.poc.kinvey.SyncApplication;
import com.epam.poc.kinvey.common.PreferenceStore;
import com.epam.poc.kinvey.rest.RestRequest;

import java.util.HashMap;
import java.util.Map;

class SyncpointRestRequest extends RestRequest {
    final private static Map<String, String> HEADERS = new HashMap<String, String>() {{
        put("Content-Type", "application/json");
        put("Accept", "application/json");
        put("AppKey", "l5GC4PTU1GAgY3fGwkfKXP4vtjYKFOUZ");
        put("Authorization", "Bearer " + SyncApplication.getPreferenceStore().loadString(PreferenceStore.ACCESS_TOKEN_KEY));
    }};

    SyncpointRestRequest() {
        super(RestMethod.GET, RestAction.GET_SYNCPOINTS.getPath(), HEADERS);
    }
}
