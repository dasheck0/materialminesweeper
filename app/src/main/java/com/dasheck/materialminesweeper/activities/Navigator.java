package com.dasheck.materialminesweeper.activities;

import android.support.v4.app.Fragment;
import com.dasheck.materialminesweeper.fragments.game.DaggerGameComponent;
import com.dasheck.materialminesweeper.fragments.game.GameFragment;
import com.dasheck.materialminesweeper.fragments.game.GameModule;
import com.dasheck.materialminesweeper.fragments.game.GameComponent;
import com.dasheck.materialminesweeper.fragments.game.GamePresenterImpl;
import com.dasheck.materialminesweeper.fragments.menu.DaggerMenuComponent;
import com.dasheck.materialminesweeper.fragments.menu.MenuComponent;
import com.dasheck.materialminesweeper.fragments.menu.MenuFragment;
import com.dasheck.materialminesweeper.fragments.menu.MenuModule;
import com.dasheck.materialminesweeper.fragments.menu.MenuPresenterImpl;

/**
 * Created by s.neidig on 17/01/16.
 */
public class Navigator {

  private BaseActivity baseActivity;

  public Navigator(BaseActivity baseActivity) {
    this.baseActivity = baseActivity;
  }

  public void showMenu() {
    MenuFragment fragment = new MenuFragment();
    MenuPresenterImpl presenter = new MenuPresenterImpl();

    MenuComponent component = DaggerMenuComponent.builder()
        .activityComponent(baseActivity.getActivityComponent())
        .menuModule(new MenuModule(fragment, presenter))
        .build();

    component.inject(fragment);
    component.inject(presenter);

    transist(fragment);
  }

  public void showGame() {
    GameFragment fragment = new GameFragment();
    GamePresenterImpl presenter = new GamePresenterImpl();

    GameComponent component = DaggerGameComponent.builder()
        .activityComponent(baseActivity.getActivityComponent())
        .gameModule(new GameModule(fragment, presenter))
        .build();

    component.inject(fragment);
    component.inject(presenter);

    transist(fragment);
  }

  // TODO: 26/01/16 Write an awesome transition controller supporting shared element transition

  private void transist(Fragment fragment) {
    baseActivity.getSupportFragmentManager()
        .beginTransaction()
        .replace(baseActivity.getFragmentContainerId(), fragment, fragment.getClass().getName())
        .addToBackStack(fragment.getClass().getName())
        .commit();
  }
}
