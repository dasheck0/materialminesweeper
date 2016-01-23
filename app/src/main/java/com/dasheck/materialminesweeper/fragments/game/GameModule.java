package com.dasheck.materialminesweeper.fragments.game;

import android.content.Context;
import com.dasheck.materialminesweeper.adapters.TileListAdapter;
import com.dasheck.materialminesweeper.annotations.PerFragment;
import com.dasheck.materialminesweeper.fragments.game.interactors.CreateFieldInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.CreateFieldInteractorImpl;
import com.dasheck.materialminesweeper.fragments.game.interactors.GetTileListInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.GetTileListInteractorImpl;
import com.dasheck.materialminesweeper.fragments.game.interactors.RevealTileInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.RevealTileInteractorImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by s.neidig on 17/01/16.
 */
@PerFragment @Module public class GameModule {

  private GameView view;
  private GamePresenter presenter;

  public GameModule(GameView view, GamePresenter presenter) {
    this.view = view;
    this.presenter = presenter;
  }

  @Provides @PerFragment GameView provideView() {
    return view;
  }

  @Provides @PerFragment GamePresenter providePresenter() {
    return presenter;
  }

  @Provides @PerFragment TileListAdapter provideTileListAdapter(Context context) {
    return new TileListAdapter(context);
  }

  @Provides @PerFragment GetTileListInteractor provideGetTileListInteractor(
      GetTileListInteractorImpl getTileListInteractorImpl) {
    return getTileListInteractorImpl;
  }

  @Provides @PerFragment CreateFieldInteractor provideCreateFieldInteractor(
      CreateFieldInteractorImpl createFieldInteractorImpl) {
    return createFieldInteractorImpl;
  }

  @Provides @PerFragment RevealTileInteractor provideRevealTileInteractor(
      RevealTileInteractorImpl revealTileInteractorImpl) {
    return revealTileInteractorImpl;
  }
}
