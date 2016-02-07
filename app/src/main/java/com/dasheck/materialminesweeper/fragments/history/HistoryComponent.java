package com.dasheck.materialminesweeper.fragments.history;

import com.dasheck.materialminesweeper.annotations.PerFragment;
import com.dasheck.materialminesweeper.di.ActivityComponent;
import dagger.Component;

/**
 * @author Stefan Neidig
 */
@PerFragment @Component(modules = HistoryModule.class, dependencies = ActivityComponent.class)
public interface HistoryComponent {

  void inject(HistoryFragment fragment);

  void inject(HistoryPresenterImpl presenter);
}
