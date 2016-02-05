package com.dasheck.materialminesweeper.di;

import com.dasheck.materialminesweeper.Application;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by s.neidig on 17/01/16.
 */

@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {

  Application application();
}
