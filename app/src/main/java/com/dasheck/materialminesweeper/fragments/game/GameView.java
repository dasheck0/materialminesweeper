package com.dasheck.materialminesweeper.fragments.game;

import com.dasheck.materialminesweeper.fragments.BaseView;
import com.dasheck.model.models.GameInformation;
import com.dasheck.model.models.Tile;
import java.util.List;

/**
 * Created by s.neidig on 17/01/16.
 */
public interface GameView extends BaseView {

  void setTiles(List<Tile> tiles);

  void setDimension(int width, int height);

  void repositionGrid(int columns, int rows);

  void showGameLostDialog(GameInformation gameInformation);

  void setNumberOfRemainingBombs(int remainingBombs);

  void setElapsedTime(long elapsedTimeInSeconds);
}
