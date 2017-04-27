package com.epam.poc.kinvey.common;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.epam.poc.kinvey.R;
import com.epam.poc.kinvey.activity.FileSystemActivity;
import com.epam.poc.kinvey.activity.ImagePreviewActivity;
import com.epam.poc.kinvey.activity.PreviewActivity;
import com.epam.poc.kinvey.activity.TextPreviewActivity;

public class ActivityUtils {

    private ActivityUtils() {}

    public static void startFileSystemActivity(Context context, String name, String syncpointId, String folderId) {
        Intent intent = new Intent(context, FileSystemActivity.class);
        intent.putExtra(FileSystemActivity.NAME, name);
        intent.putExtra(FileSystemActivity.SYNCPOINT_ID, syncpointId);
        intent.putExtra(FileSystemActivity.FOLDER_ID, folderId);
        context.startActivity(intent);
    }

    public static void startTextPreviewActivity(Context context, String name, String syncpointId, String latestVersionId) {
        Intent intent = new Intent(context, TextPreviewActivity.class);
        intent.putExtra(PreviewActivity.FILE_NAME, name);
        intent.putExtra(PreviewActivity.SYNCPOINT_ID, syncpointId);
        intent.putExtra(PreviewActivity.LATEST_VERSION_ID, latestVersionId);
        context.startActivity(intent);
    }

    public static void startImagePreviewActivity(Context context, String name, String syncpointId, String latestVersionId) {
        Intent intent = new Intent(context, ImagePreviewActivity.class);
        intent.putExtra(PreviewActivity.FILE_NAME, name);
        intent.putExtra(PreviewActivity.SYNCPOINT_ID, syncpointId);
        intent.putExtra(PreviewActivity.LATEST_VERSION_ID, latestVersionId);
        context.startActivity(intent);
    }

    public static void showErrorAlert(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(String.format(context.getString(R.string.dialog_error_message), message));
        builder.setTitle(R.string.dialog_error_title);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
}
