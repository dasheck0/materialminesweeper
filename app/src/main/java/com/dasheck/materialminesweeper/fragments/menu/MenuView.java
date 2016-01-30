package com.dasheck.materialminesweeper.fragments.menu;

import com.dasheck.materialminesweeper.fragments.BaseView;
import com.dasheck.model.models.GameMode;
import java.util.List;

/**
 * @author Stefan Neidig
 */
public interface MenuView extends BaseView {

  void setGameModes(List<GameMode> gameModes);
}
