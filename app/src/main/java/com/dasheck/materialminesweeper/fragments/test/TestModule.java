package com.dasheck.materialminesweeper.fragments.test;

import com.dasheck.materialminesweeper.annotations.PerFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by s.neidig on 17/01/16.
 */
@PerFragment @Module public class TestModule {

  private TestView view;
  private TestPresenter presenter;

  public TestModule(TestView view, TestPresenter presenter) {
    this.view = view;
    this.presenter = presenter;
  }

  @Provides @PerFragment TestView provideView() {
    return view;
  }

  @Provides @PerFragment TestPresenter providePresenter() {
    return presenter;
  }
}
