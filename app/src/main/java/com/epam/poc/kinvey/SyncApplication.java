package com.epam.poc.kinvey;

import android.support.multidex.MultiDexApplication;

import com.epam.poc.kinvey.common.PreferenceStore;
import com.epam.poc.kinvey.events.BaseEvent;

import org.greenrobot.eventbus.EventBus;

public class SyncApplication extends MultiDexApplication {

    private static PreferenceStore sPreferenceStore;

    @Override
    public void onCreate() {
        super.onCreate();

        sPreferenceStore = new PreferenceStore(this);
    }

    public static PreferenceStore getPreferenceStore() {
        return sPreferenceStore;
    }

    public static void busRegister(Object object) {
        EventBus.getDefault().register(object);
    }

    public static void busUnregister(Object object) {
        EventBus.getDefault().unregister(object);
    }

    public static void busPost(BaseEvent event) {
        EventBus.getDefault().post(event);
    }
}
