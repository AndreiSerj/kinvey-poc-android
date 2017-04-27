package com.epam.poc.kinvey.activity;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Loader;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.epam.poc.kinvey.SyncApplication;
import com.epam.poc.kinvey.R;
import com.epam.poc.kinvey.common.ActivityUtils;
import com.epam.poc.kinvey.common.PreferenceStore;
import com.epam.poc.kinvey.events.SyncpointEvent;
import com.epam.poc.kinvey.events.TokenEvent;
import com.epam.poc.kinvey.loader.AppAsyncLoaderResult;
import com.epam.poc.kinvey.loader.SyncpointLoader;
import com.epam.poc.kinvey.model.Syncpoint;
import com.epam.poc.kinvey.model.Token;
import com.epam.poc.kinvey.rest.RestClientFactory;
import com.epam.poc.kinvey.rest.RestResponse;
import com.epam.poc.kinvey.rest.token.TokenRestClient;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class SyncpointActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<AppAsyncLoaderResult<List<Syncpoint>>> {
    private static final String TAG = SyncpointActivity.class.getSimpleName();

    private static final int LOADER_SYNCPOINT = 100;

    private ProgressDialog mProgressDialog;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private List<Syncpoint> mSyncpoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_syncpoint);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new SyncpointAdapter(mSyncpoints != null ? mSyncpoints : new ArrayList<Syncpoint>()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, R.drawable.divider));

        startLogic();
    }


    @Override
    public void onStart() {
        super.onStart();
        SyncApplication.busRegister(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        SyncApplication.busUnregister(this);
    }

    @Override
    public Loader<AppAsyncLoaderResult<List<Syncpoint>>> onCreateLoader(int id, Bundle args) {
        if (LOADER_SYNCPOINT == id) {
            return new SyncpointLoader(getApplicationContext());
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<AppAsyncLoaderResult<List<Syncpoint>>> loader, AppAsyncLoaderResult<List<Syncpoint>> result) {
        mProgressDialog.dismiss();
        mSwipeRefreshLayout.setRefreshing(false);

        if (result.getException() != null) {
            ActivityUtils.showErrorAlert(this, result.getException().getMessage());
        } else {
            mSyncpoints = result.getData();
            mRecyclerView.setAdapter(new SyncpointAdapter(mSyncpoints));
        }
    }

    @Override
    public void onLoaderReset(Loader<AppAsyncLoaderResult<List<Syncpoint>>> loader) {
        // do nothing
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(final TokenEvent event) {
        getLoaderManager().initLoader(LOADER_SYNCPOINT, null, this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(final SyncpointEvent event) {
        ActivityUtils.startFileSystemActivity(this, event.getName(), event.getSyncpointId(), event.getFolderId());
    }

    private void startLogic() {
        mProgressDialog = ProgressDialog.show(this, null, getString(R.string.connect_syncplicity));

        new TokenRestClient().sendRequestAsync(new RestClientFactory.RestRequestCallback<Token>() {
           @Override
            public void onSuccess(RestResponse<Token> response) {
                if (response.isSuccess()) {
                    Log.d("AAAA", "token: " + response.getResult().getAccessToken());
                    SyncApplication.getPreferenceStore().storeString(PreferenceStore.ACCESS_TOKEN_KEY, response.getResult().getAccessToken());
                    SyncApplication.busPost(new TokenEvent());
               }
            }

            @Override
            public void onError(Exception exception) {
                Log.e(TAG, "Token request error", exception);
                mProgressDialog.dismiss();
            }
        });
    }

    private void refreshItems() {
        getLoaderManager().restartLoader(LOADER_SYNCPOINT, null, this);
    }

}
