package com.dasheck.materialminesweeper.fragments.settings;

import com.dasheck.materialminesweeper.annotations.PerFragment;
import com.dasheck.materialminesweeper.di.ActivityComponent;
import dagger.Component;

/**
 * @author Stefan Neidig
 */
@PerFragment @Component(modules = SettingsModule.class, dependencies = ActivityComponent.class)
public interface SettingsComponent {

  void inject(SettingsFragment fragment);

  void inject(SettingsPresenterImpl presenter);
}
