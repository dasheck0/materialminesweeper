package com.dasheck.materialminesweeper.fragments.game;

import com.dasheck.materialminesweeper.annotations.PerFragment;
import com.dasheck.materialminesweeper.di.ActivityComponent;
import dagger.Component;

/**
 * Created by s.neidig on 17/01/16.
 */
@PerFragment @Component(modules = GameModule.class, dependencies = ActivityComponent.class)
public interface GameComponent {

  void inject(GameFragment fragment);

  void inject(GamePresenterImpl presenter);
}
