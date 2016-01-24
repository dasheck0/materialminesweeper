package com.dasheck.materialminesweeper.di;

import android.content.Context;

import com.dasheck.materialminesweeper.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by s.neidig on 17/01/16.
 */
@Module public class ApplicationModule {

  private Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides @Singleton Application provideApplication() {
    return application;
  }
}
