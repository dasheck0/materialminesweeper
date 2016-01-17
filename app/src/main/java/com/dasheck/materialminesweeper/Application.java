package com.dasheck.materialminesweeper;

import timber.log.Timber;

/**
 * Created by s.neidig on 17/01/16.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        setupTimber();
    }

    private void setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
