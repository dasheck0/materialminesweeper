package com.dasheck.materialminesweeper.fragments.test;

import com.dasheck.materialminesweeper.annotations.PerFragment;
import com.dasheck.materialminesweeper.di.ActivityComponent;
import dagger.Component;

/**
 * Created by s.neidig on 17/01/16.
 */
@PerFragment @Component(modules = TestModule.class, dependencies = ActivityComponent.class)
public interface TestComponent {

  void inject(TestFragment fragment);

  void inject(TestPresenterImpl presenter);
}
