package com.dasheck.materialminesweeper.fragments.settings;

import com.dasheck.materialminesweeper.annotations.PerFragment;
import dagger.Module;
import dagger.Provides;

/**
 * @author Stefan Neidig
 */
@Module public class SettingsModule {

  private SettingsView view;
  private SettingsPresenter presenter;

  public SettingsModule(SettingsView view, SettingsPresenter presenter) {
    this.view = view;
    this.presenter = presenter;
  }

  @PerFragment @Provides SettingsView provideView() {
    return view;
  }

  @PerFragment @Provides SettingsPresenter providePresenter() {
    return presenter;
  }
}
