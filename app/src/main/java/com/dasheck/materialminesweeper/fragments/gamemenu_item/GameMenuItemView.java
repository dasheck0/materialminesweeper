package com.dasheck.materialminesweeper.fragments.gamemenu_item;

import com.dasheck.materialminesweeper.fragments.BaseView;
import com.dasheck.model.models.GameMode;

/**
 * @author Stefan Neidig
 */
public interface GameMenuItemView extends BaseView{

  void setGameMode(GameMode gameMode);
}
