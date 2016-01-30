package com.dasheck.materialminesweeper.fragments.gamemenu_item;

import com.dasheck.materialminesweeper.fragments.BasePresenterImpl;
import com.dasheck.model.models.GameMode;
import javax.inject.Inject;

/**
 * @author Stefan Neidig
 */
public class GameMenuItemPresenterImpl extends BasePresenterImpl implements GameMenuItemPresenter {

  @Inject GameMenuItemView view;

  private GameMode gameMode;

  @Override public void onResume() {
    super.onResume();
    view.setGameMode(gameMode);
  }

  @Override public void setGameMode(GameMode gameMode) {
    this.gameMode = gameMode;
  }
}
