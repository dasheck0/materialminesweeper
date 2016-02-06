package com.dasheck.materialminesweeper.fragments.gamemenu_item;

import com.dasheck.materialminesweeper.activities.Navigator;
import com.dasheck.materialminesweeper.fragments.BasePresenterImpl;
import com.dasheck.materialminesweeper.fragments.gamemenu_item.interactors.ResetGameStatisticsInteractor;
import com.dasheck.model.models.GameMode;
import javax.inject.Inject;

/**
 * @author Stefan Neidig
 */
public class GameMenuItemPresenterImpl extends BasePresenterImpl implements GameMenuItemPresenter {

  @Inject GameMenuItemView view;
  @Inject ResetGameStatisticsInteractor resetGameStatisticsInteractor;
  @Inject Navigator navigator;

  private GameMode gameMode;

  @Override public void onResume() {
    super.onResume();
    view.setGameMode(gameMode);
  }

  @Override public void setGameMode(GameMode gameMode) {
    this.gameMode = gameMode;
  }

  @Override public void startGame(int position) {
    navigator.showGame(gameMode.getConfiguration());
  }

  @Override public void resetGameStatistics(int position) {
    resetGameStatisticsInteractor.execute(gameMode.getConfiguration().getDifficulty())
        .doOnNext(gameMode::setGameStatistics)
        .subscribe(x -> view.refreshGameStatistics(position));
  }
}
