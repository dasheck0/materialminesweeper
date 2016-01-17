package com.dasheck.materialminesweeper.fragments.test;

import com.dasheck.materialminesweeper.fragments.BaseView;
import com.dasheck.model.models.Tile;
import java.util.List;

/**
 * Created by s.neidig on 17/01/16.
 */
public interface TestView extends BaseView {

  void setBombs(List<Tile> bombs);
}
