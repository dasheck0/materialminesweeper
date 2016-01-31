package com.dasheck.materialminesweeper.di;

import android.content.Context;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import com.dasheck.materialminesweeper.activities.BaseActivity;
import com.dasheck.materialminesweeper.activities.Navigator;
import com.dasheck.materialminesweeper.annotations.PerActivity;

import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by s.neidig on 17/01/16.
 */
@Module public class AcitivityModule {

  private BaseActivity baseActivity;

  public AcitivityModule(BaseActivity baseActivity) {
    this.baseActivity = baseActivity;
  }

  @Provides @PerActivity BaseActivity provideBaseActivity() {
    return baseActivity;
  }

  @Provides @PerActivity Context provideContext() {
    return baseActivity;
  }

  @Provides @PerActivity Navigator provideNavigator() {
    return new Navigator(baseActivity);
  }

  @Provides @PerActivity FragmentManager provideSupportFragmentManager() {
    return baseActivity.getSupportFragmentManager();
  }

  @Provides @PerActivity Gson provideGson() {
    return new Gson();
  }

  @Provides @PerActivity SharedPreferences provideSharedPreferences(Context context) {
    return context.getSharedPreferences("minesweeper", Context.MODE_PRIVATE);
  }
}
