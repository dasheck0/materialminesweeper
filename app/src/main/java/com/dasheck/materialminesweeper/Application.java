package com.dasheck.materialminesweeper;

import com.dasheck.materialminesweeper.di.ApplicationComponent;
import com.dasheck.materialminesweeper.di.ApplicationModule;
import com.dasheck.materialminesweeper.di.DaggerApplicationComponent;

import timber.log.Timber;

/**
 * Created by s.neidig on 17/01/16.
 */
public class Application extends android.app.Application {

    private ApplicationComponent applicationComponent;

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        setupTimber();
        setupInjector();
    }

    private void setupInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
