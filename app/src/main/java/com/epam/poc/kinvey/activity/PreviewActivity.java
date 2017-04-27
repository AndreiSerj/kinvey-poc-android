package com.epam.poc.kinvey.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.epam.poc.kinvey.R;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;

public abstract class PreviewActivity<T> extends AppCompatActivity {

    public static String FILE_NAME = "com.epam.synchronoss.orbitsync.fileName";
    public static String SYNCPOINT_ID = "com.epam.synchronoss.orbitsync.syncpointId";
    public static String LATEST_VERSION_ID = "com.epam.synchronoss.orbitsync.latestVersionId";

    protected void showToast(String message) {
        SuperActivityToast.create(this, new Style(), Style.TYPE_STANDARD)
                .setText(message)
                .setDuration(Style.DURATION_MEDIUM)
                .setFrame(Style.FRAME_STANDARD)
                .setColor(getResources().getColor(R.color.colorPrimary))
                .setAnimations(Style.ANIMATIONS_FADE).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.file_preview_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        } else if (item.getItemId() == R.id.actionSave) {
            saveContent();
        }
        return(super.onOptionsItemSelected(item));
    }

    protected abstract void showContent(T content);
    protected abstract void saveContent();

}
