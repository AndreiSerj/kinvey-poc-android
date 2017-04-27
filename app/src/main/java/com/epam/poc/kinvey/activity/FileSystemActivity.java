package com.epam.poc.kinvey.activity;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Loader;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.epam.poc.kinvey.SyncApplication;
import com.epam.poc.kinvey.R;
import com.epam.poc.kinvey.common.ActivityUtils;
import com.epam.poc.kinvey.events.FileEvent;
import com.epam.poc.kinvey.events.FolderEvent;
import com.epam.poc.kinvey.loader.AppAsyncLoaderResult;
import com.epam.poc.kinvey.loader.FileSystemLoader;
import com.epam.poc.kinvey.model.File;
import com.epam.poc.kinvey.model.FileSystem;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class FileSystemActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<AppAsyncLoaderResult<FileSystem>> {

    private static final int LOADER_FILE_SYSTEM_CONTENT = 200;

    public static final String NAME = "com.epam.synchronoss.orbitsync.name";
    public static final String SYNCPOINT_ID = "com.epam.synchronoss.orbitsync.syncpointId";
    public static final String FOLDER_ID = "com.epam.synchronoss.orbitsync.folderId";

    private ProgressDialog mProgressDialog;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private FileSystem mFileSystem;

    private String mSyncpointId;
    private String mFolderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_file_system);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mSyncpointId = getIntent().getStringExtra(SYNCPOINT_ID);
        mFolderId = getIntent().getStringExtra(FOLDER_ID);

        setTitle(getIntent().getStringExtra(NAME));

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new FileSystemAdapter(mFileSystem != null ? mFileSystem : new FileSystem()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, R.drawable.divider));

        mProgressDialog = ProgressDialog.show(this, null, getString(R.string.loading));

        getLoaderManager().initLoader(LOADER_FILE_SYSTEM_CONTENT, null, this);
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
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(final FolderEvent event) {
        ActivityUtils.startFileSystemActivity(this, event.getName(), event.getSyncpointId(), event.getFolderId());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(final FileEvent event) {
        final File.Type type = event.getType();

        if (type == File.Type.TEXT) {
            ActivityUtils.startTextPreviewActivity(this, event.getName(), event.getSyncpointId(), event.getLatestVersionId());
        } else if (type == File.Type.IMAGE){
            ActivityUtils.startImagePreviewActivity(this, event.getName(), event.getSyncpointId(), event.getLatestVersionId());
        } else {
            showToast(getString(R.string.unknown_file));
        }
    }

    @Override
    public Loader<AppAsyncLoaderResult<FileSystem>> onCreateLoader(int id, Bundle args) {
        if (LOADER_FILE_SYSTEM_CONTENT == id) {
            return new FileSystemLoader(getApplicationContext(), mSyncpointId, mFolderId);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<AppAsyncLoaderResult<FileSystem>> loader, AppAsyncLoaderResult<FileSystem> result) {
        mProgressDialog.dismiss();
        mSwipeRefreshLayout.setRefreshing(false);

        if (result.getException() != null) {
            ActivityUtils.showErrorAlert(this, result.getException().getMessage());
        } else {
            mFileSystem = result.getData();
            mRecyclerView.setAdapter(new FileSystemAdapter(mFileSystem));
        }
    }

    @Override
    public void onLoaderReset(Loader<AppAsyncLoaderResult<FileSystem>> loader) {
        // do nothing
    }

    private void refreshItems() {
        getLoaderManager().restartLoader(LOADER_FILE_SYSTEM_CONTENT, null, this);
    }

    private void showToast(String message) {
        SuperActivityToast.create(this, new Style(), Style.TYPE_STANDARD)
                .setText(message)
                .setDuration(Style.DURATION_MEDIUM)
                .setFrame(Style.FRAME_STANDARD)
                .setColor(getResources().getColor(R.color.colorPrimary))
                .setAnimations(Style.ANIMATIONS_FADE).show();
    }
}