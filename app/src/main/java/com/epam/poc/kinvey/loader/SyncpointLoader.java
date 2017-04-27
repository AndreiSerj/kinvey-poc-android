package com.epam.poc.kinvey.loader;

import android.content.Context;

import com.epam.poc.kinvey.model.Syncpoint;
import com.epam.poc.kinvey.rest.RestResponse;
import com.epam.poc.kinvey.rest.syncpoint.SyncpointRestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SyncpointLoader extends AppAsyncLoader<List<Syncpoint>> {

    public SyncpointLoader(Context context) {
        super(context);
    }

    @Override
    public List<Syncpoint> internalLoadInBackground() throws Exception {
        List<Syncpoint> syncpoints = null;
        final RestResponse<Syncpoint[]> response = new SyncpointRestClient().sendRequest();

        if (response.isSuccess()) {
            syncpoints = (Arrays.asList(response.getResult()));
        }
        return syncpoints != null ? syncpoints : new ArrayList<Syncpoint>();
    }
}
