package com.epam.poc.kinvey.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.ImageView;

import com.epam.poc.kinvey.R;
import com.epam.poc.kinvey.rest.RestClientFactory;
import com.epam.poc.kinvey.rest.RestResponse;
import com.epam.poc.kinvey.rest.content.BinaryRestClient;

import java.io.ByteArrayOutputStream;

public class ImagePreviewActivity extends PreviewActivity<Bitmap> {
    private static final String TAG = ImagePreviewActivity.class.getSimpleName();

    private ImageView mImagePreview;
    private String mFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mImagePreview = (ImageView) findViewById(R.id.imagePreview);

        mFileName = getIntent().getStringExtra(FILE_NAME);
        final String syncpointId = getIntent().getStringExtra(SYNCPOINT_ID);
        final String latestVersionId = getIntent().getStringExtra(LATEST_VERSION_ID);

        setTitle(mFileName);

        final ProgressDialog progressDialog = ProgressDialog.show(this, null, getString(R.string.image_downloading));
        new BinaryRestClient().sendRequestAsync(new RestClientFactory.RestRequestCallback<byte[]>() {
            @Override
            public void onSuccess(RestResponse<byte[]> response) {
                progressDialog.dismiss();

                if (response.isSuccess()) {
                    try {
                        final byte[] bytes = response.getResult();
                        showContent(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Exception exception) {
                Log.e(TAG, "Retrieve image file request error", exception);
                progressDialog.dismiss();
            }
        }, syncpointId, latestVersionId);
    }

    @Override
    protected void showContent(final Bitmap content) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (content != null) {
                    mImagePreview.setImageBitmap(content);
                }
            }
        });
    }

    @Override
    protected void saveContent() {
        Bitmap bitmap = ((BitmapDrawable) mImagePreview.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageInByte = null;
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            imageInByte = baos.toByteArray();
        } catch (Exception e) {
            try {
                baos.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        if (imageInByte != null && imageInByte.length > 0) {
            // TODO: Add logic for saving
            showToast(String.format(getString(R.string.file_saved_notification), mFileName));
        }
    }

}
