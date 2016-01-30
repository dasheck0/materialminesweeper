package com.dasheck.materialminesweeper.fragments.gamemenu_item;

import com.dasheck.materialminesweeper.annotations.PerFragment;
import com.dasheck.materialminesweeper.di.ActivityComponent;
import dagger.Component;

/**
 * @author Stefan Neidig
 */
@PerFragment @Component(modules = GameMenuItemModule.class, dependencies = ActivityComponent.class)
public interface GameMenuItemComponent {

  void inject(GameMenuItemFragment fragment);

  void inject(GameMenuItemPresenterImpl presenter);
}
