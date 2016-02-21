package com.dasheck.materialminesweeper.fragments.profile;

import com.dasheck.materialminesweeper.annotations.PerFragment;
import com.dasheck.materialminesweeper.di.ActivityComponent;
import dagger.Component;

/**
 * @author Stefan Neidig
 */
@PerFragment @Component(modules = ProfileModule.class, dependencies = ActivityComponent.class)
public interface ProfileComponent {

  void inject(ProfileFragment fragment);

  void inject(ProfilePresenterImpl presenter);

}
