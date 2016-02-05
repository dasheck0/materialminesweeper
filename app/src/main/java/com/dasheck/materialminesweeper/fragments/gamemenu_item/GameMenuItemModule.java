package com.dasheck.materialminesweeper.fragments.gamemenu_item;

import com.dasheck.materialminesweeper.annotations.PerFragment;
import dagger.Module;
import dagger.Provides;

/**
 * @author Stefan Neidig
 */
@Module public class GameMenuItemModule {

  private GameMenuItemView view;
  private GameMenuItemPresenter presenter;

  public GameMenuItemModule(GameMenuItemView view, GameMenuItemPresenter presenter) {
    this.view = view;
    this.presenter = presenter;
  }

  @Provides @PerFragment public GameMenuItemView provideView() {
    return view;
  }

  @Provides @PerFragment public GameMenuItemPresenter providePresenter() {
    return presenter;
  }

  /*@Provides @PerFragment public RecyclerViewMaterialAdapter provideGameMenuListAdapter(Context context) {
    return new RecyclerViewMaterialAdapter(new GameMenuListAdapter(context));
  }*/
}
