package com.dasheck.materialminesweeper.activities;

import com.dasheck.materialminesweeper.fragments.game.DaggerGameComponent;
import com.dasheck.materialminesweeper.fragments.game.GameFragment;
import com.dasheck.materialminesweeper.fragments.game.GameModule;
import com.dasheck.materialminesweeper.fragments.game.GameComponent;
import com.dasheck.materialminesweeper.fragments.game.GamePresenterImpl;

/**
 * Created by s.neidig on 17/01/16.
 */
public class Navigator {

  private BaseActivity baseActivity;

  public Navigator(BaseActivity baseActivity) {
    this.baseActivity = baseActivity;
  }

  public void showTest() {
    GameFragment fragment = new GameFragment();
    GamePresenterImpl presenter = new GamePresenterImpl();

    GameComponent component = DaggerGameComponent.builder()
        .activityComponent(baseActivity.getActivityComponent())
        .gameModule(new GameModule(fragment, presenter))
        .build();

    component.inject(fragment);
    component.inject(presenter);

    baseActivity.getSupportFragmentManager()
        .beginTransaction()
        .add(baseActivity.getFragmentContainerId(), fragment, fragment.getClass().getName())
        .addToBackStack(fragment.getClass().getName())
        .commit();
  }
}
