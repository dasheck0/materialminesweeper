package com.dasheck.materialminesweeper.fragments.profile;

import com.dasheck.materialminesweeper.annotations.PerFragment;
import dagger.Module;
import dagger.Provides;

/**
 * @author Stefan Neidig
 */
@Module public class ProfileModule {

  private ProfileView view;
  private ProfilePresenter presenter;

  public ProfileModule(ProfileView view, ProfilePresenter presenter) {
    this.view = view;
    this.presenter = presenter;
  }

  @Provides @PerFragment ProfileView provideView() {
    return view;
  }

  @Provides @PerFragment ProfilePresenter providePresenter() {
    return presenter;
  }
}
