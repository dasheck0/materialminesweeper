package com.dasheck.materialminesweeper.fragments.menu;

import com.dasheck.materialminesweeper.annotations.PerFragment;
import com.dasheck.materialminesweeper.di.ActivityComponent;
import dagger.Component;

/**
 * @author Stefan Neidig
 */
@PerFragment @Component(modules = MenuModule.class, dependencies = ActivityComponent.class)
public interface MenuComponent {

  void inject(MenuFragment fragment);

  void inject(MenuPresenterImpl presenter);
}
