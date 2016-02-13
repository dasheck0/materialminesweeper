package com.dasheck.materialminesweeper.fragments.menu;

import com.dasheck.materialminesweeper.annotations.PerFragment;
import com.dasheck.materialminesweeper.fragments.menu.interactors.GetGameModesInteractor;
import com.dasheck.materialminesweeper.fragments.menu.interactors.GetGameModesInteractorImpl;
import com.dasheck.materialminesweeper.fragments.menu.interactors.SetupSoundEffectsInteractor;
import com.dasheck.materialminesweeper.fragments.menu.interactors.SetupSoundEffectsInteractorImpl;
import dagger.Module;
import dagger.Provides;

/**
 * @author Stefan Neidig
 */
@Module public class MenuModule {

  private MenuView view;
  private MenuPresenter presenter;

  public MenuModule(MenuView view, MenuPresenter presenter) {
    this.view = view;
    this.presenter = presenter;
  }

  @Provides @PerFragment MenuView provideView() {
    return view;
  }

  @Provides @PerFragment MenuPresenter providePresenter() {
    return presenter;
  }

  @Provides @PerFragment
  public GetGameModesInteractor provideGetGameModesInteractor(GetGameModesInteractorImpl getGameModesInteractorImpl) {
    return getGameModesInteractorImpl;
  }

  @Provides @PerFragment public SetupSoundEffectsInteractor provideSetupSoundEffectsInteractor(
      SetupSoundEffectsInteractorImpl setupSoundEffectsInteractorImpl) {
    return setupSoundEffectsInteractorImpl;
  }
}
