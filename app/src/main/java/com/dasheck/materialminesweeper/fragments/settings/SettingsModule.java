package com.dasheck.materialminesweeper.fragments.settings;

import com.dasheck.materialminesweeper.annotations.PerFragment;
import com.dasheck.materialminesweeper.controllers.ShareController;
import com.dasheck.materialminesweeper.controllers.ShareControllerImpl;
import com.dasheck.materialminesweeper.fragments.settings.interactors.OpenWebsiteInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.OpenWebsiteInteractorImpl;
import com.dasheck.materialminesweeper.fragments.settings.interactors.ShareAppInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.ShareAppInteractorImpl;
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

  @Provides @PerFragment
  public OpenWebsiteInteractor provideOpenWebsiteInteractor(OpenWebsiteInteractorImpl openWebsiteInteractorImpl) {
    return openWebsiteInteractorImpl;
  }

  @Provides @PerFragment
  public ShareAppInteractor provideShareAppInteractor(ShareAppInteractorImpl shareAppInteractorImpl) {
    return shareAppInteractorImpl;
  }
}
