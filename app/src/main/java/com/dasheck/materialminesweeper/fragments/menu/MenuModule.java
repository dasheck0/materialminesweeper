package com.dasheck.materialminesweeper.fragments.menu;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import com.dasheck.materialminesweeper.adapters.MenuPagerAdapter;
import com.dasheck.materialminesweeper.annotations.PerFragment;
import com.dasheck.materialminesweeper.fragments.menu.interactors.GetGameModesInteractor;
import com.dasheck.materialminesweeper.fragments.menu.interactors.GetGameModesInteractorImpl;
import com.dasheck.model.models.GameMode;
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
}
