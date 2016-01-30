package com.dasheck.materialminesweeper.fragments.menu;

import com.dasheck.materialminesweeper.fragments.BasePresenter;
import com.dasheck.materialminesweeper.fragments.BasePresenterImpl;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;

/**
 * @author Stefan Neidig
 */
public class MenuPresenterImpl extends BasePresenterImpl implements MenuPresenter {

  @Inject MenuView view;

  @Override public void onResume() {
    super.onResume();

    List<String> gameModes = Arrays.asList("Easy", "Medium", "Hard", "Expert");
    view.setGameModes(gameModes);
  }
}
