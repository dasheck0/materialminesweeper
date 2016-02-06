package com.dasheck.materialminesweeper.fragments.game;

import com.dasheck.materialminesweeper.fragments.BasePresenter;
import com.dasheck.model.models.Configuration;
import com.dasheck.model.models.Tile;

/**
 * Created by s.neidig on 17/01/16.
 */
public interface GamePresenter extends BasePresenter {
    void revealTile(Tile tile);

  void markTile(Tile tile);

  void startGame(Configuration configuration);

  void setConfiguration(Configuration configuration);

  void showMenu();

  void restartGame();

  void loadMenu();

  void pauseGame();

  void unpauseGame();

  void interruptGame();
}
