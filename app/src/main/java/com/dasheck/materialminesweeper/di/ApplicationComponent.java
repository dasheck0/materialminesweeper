package com.dasheck.materialminesweeper.di;

import android.content.Context;

import com.dasheck.materialminesweeper.Application;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by s.neidig on 17/01/16.
 */

@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {

  Application application();
}
