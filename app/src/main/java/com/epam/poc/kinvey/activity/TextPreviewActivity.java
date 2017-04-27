package com.epam.poc.kinvey.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.TextView;

import com.epam.poc.kinvey.R;
import com.epam.poc.kinvey.rest.RestClientFactory;
import com.epam.poc.kinvey.rest.RestResponse;
import com.epam.poc.kinvey.rest.content.TextRestClient;

public class TextPreviewActivity extends PreviewActivity<String> {
    private static final String TAG = TextPreviewActivity.class.getSimpleName();

    private TextView mTextPreview;
    private String mFileName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_preview);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mTextPreview = (TextView) findViewById(R.id.textPreview);

        mFileName = getIntent().getStringExtra(FILE_NAME);
        final String syncpointId = getIntent().getStringExtra(SYNCPOINT_ID);
        final String latestVersionId = getIntent().getStringExtra(LATEST_VERSION_ID);

        setTitle(mFileName);

        final ProgressDialog progressDialog = ProgressDialog.show(this, null, getString(R.string.text_downloading));
        new TextRestClient().sendRequestAsync(new RestClientFactory.RestRequestCallback<String>() {
            @Override
            public void onSuccess(RestResponse<String> response) {
                progressDialog.dismiss();

                if (response.isSuccess()) {
                    showContent(response.getResult());
                }
            }

            @Override
            public void onError(Exception exception) {
                Log.e(TAG, "Retrieve text file request error", exception);
                progressDialog.dismiss();
            }
        }, syncpointId, latestVersionId);
    }

    @Override
    protected void showContent(final String content) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextPreview.setText(content);
            }
        });
    }

    @Override
    protected void saveContent() {
        //TODO Add saving content
        showToast(String.format(getString(R.string.file_saved_notification), mFileName));
    }

}
