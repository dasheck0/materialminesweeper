package com.dasheck.materialminesweeper.fragments.menu;

import com.dasheck.materialminesweeper.annotations.PerFragment;
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
}
