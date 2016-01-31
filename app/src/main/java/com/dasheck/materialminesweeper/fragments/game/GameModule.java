package com.dasheck.materialminesweeper.fragments.game;

import android.content.Context;
import com.dasheck.materialminesweeper.adapters.TileListAdapter;
import com.dasheck.materialminesweeper.annotations.PerFragment;
import com.dasheck.materialminesweeper.fragments.game.interactors.CreateFieldInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.CreateFieldInteractorImpl;
import com.dasheck.materialminesweeper.fragments.game.interactors.GetElapsedTimeInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.GetElapsedTimeInteractorImpl;
import com.dasheck.materialminesweeper.fragments.game.interactors.GetGameInformationInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.GetGameInformationInteractorImpl;
import com.dasheck.materialminesweeper.fragments.game.interactors.GetRemainingBombsInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.GetRemainingBombsInteractorImpl;
import com.dasheck.materialminesweeper.fragments.game.interactors.GetTileListInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.GetTileListInteractorImpl;
import com.dasheck.materialminesweeper.fragments.game.interactors.IsGameWonInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.IsGameWonInteractorImpl;
import com.dasheck.materialminesweeper.fragments.game.interactors.IsTileABombInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.IsTileABombInteractorImpl;
import com.dasheck.materialminesweeper.fragments.game.interactors.IsTileRevealedInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.IsTileRevealedInteractorImpl;
import com.dasheck.materialminesweeper.fragments.game.interactors.MarkTileInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.MarkTileInteractorImpl;
import com.dasheck.materialminesweeper.fragments.game.interactors.QuickRevealTileInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.QuickRevealTileInteractorImpl;
import com.dasheck.materialminesweeper.fragments.game.interactors.RevealTileInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.RevealTileInteractorImpl;
import com.dasheck.materialminesweeper.fragments.game.interactors.SaveLatestGameInformationInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.SaveLatestGameInformationInteractorImpl;
import com.dasheck.materialminesweeper.fragments.game.interactors.StartGameTimeInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.StartGameTimeInteractorImpl;
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

  @Provides @PerFragment MarkTileInteractor provideMarkTileInteractor(MarkTileInteractorImpl markTileInteractorImpl) {
    return markTileInteractorImpl;
  }

  @Provides @PerFragment IsTileABombInteractor provideIsTileABombInteractor(
      IsTileABombInteractorImpl isTileABombInteractorImpl) {
    return isTileABombInteractorImpl;
  }

  @Provides @PerFragment GetRemainingBombsInteractor provideGetRemainingBombsInteractor(
      GetRemainingBombsInteractorImpl getRemainingBombsInteractorImpl) {
    return getRemainingBombsInteractorImpl;
  }

  @Provides @PerFragment GetElapsedTimeInteractor provideGetElapsedTimeInteractor(
      GetElapsedTimeInteractorImpl getElapsedTimeInteractorImpl) {
    return getElapsedTimeInteractorImpl;
  }

  @Provides @PerFragment StartGameTimeInteractor provideStartGameTimeInteractor(
      StartGameTimeInteractorImpl startGameTimeInteractorImpl) {
    return startGameTimeInteractorImpl;
  }

  @Provides @PerFragment GetGameInformationInteractor provideGetGameInformationInteractor(
      GetGameInformationInteractorImpl getGameInformationInteractorImpl) {
    return getGameInformationInteractorImpl;
  }

  @Provides @PerFragment
  public IsGameWonInteractor provideIsGameWonInteractor(IsGameWonInteractorImpl isGameWonInteractorImpl) {
    return isGameWonInteractorImpl;
  }

  @Provides @PerFragment public IsTileRevealedInteractor provideIsTileRevealedInteractor(
      IsTileRevealedInteractorImpl isTileRevealedInteractorImpl) {
    return isTileRevealedInteractorImpl;
  }

  @Provides @PerFragment public QuickRevealTileInteractor provideQuickRevealTileInteractor(
      QuickRevealTileInteractorImpl quickRevealTileInteractorImpl) {
    return quickRevealTileInteractorImpl;
  }

  @Provides @PerFragment public SaveLatestGameInformationInteractor provideSaveLatestGameInformationInteractor(
      SaveLatestGameInformationInteractorImpl saveLatestGameInformationInteractorImpl) {
    return saveLatestGameInformationInteractorImpl;
  }
}
