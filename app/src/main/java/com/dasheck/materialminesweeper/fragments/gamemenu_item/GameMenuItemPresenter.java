package com.dasheck.materialminesweeper.fragments.gamemenu_item;

import com.dasheck.materialminesweeper.fragments.BasePresenter;
import com.dasheck.model.models.GameMode;

/**
 * @author Stefan Neidig
 */
public interface GameMenuItemPresenter extends BasePresenter {

  void setGameMode(GameMode gameMode);

  void startGame(int position);

  void resetGameStatistics(int position);
}
