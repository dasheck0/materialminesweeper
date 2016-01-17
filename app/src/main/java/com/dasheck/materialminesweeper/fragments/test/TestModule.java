package com.dasheck.materialminesweeper.fragments.test;

import android.content.Context;
import com.dasheck.materialminesweeper.adapters.TileListAdapter;
import com.dasheck.materialminesweeper.annotations.PerFragment;
import com.dasheck.materialminesweeper.fragments.test.interactors.GetTileListInteractor;
import com.dasheck.materialminesweeper.fragments.test.interactors.GetTileListInteractorImpl;
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

  @Provides @PerFragment TileListAdapter provideTileListAdapter(Context context) {
    return new TileListAdapter(context);
  }

  @Provides @PerFragment GetTileListInteractor provideGetTileListInteractor(
      GetTileListInteractorImpl getTileListInteractorImpl) {
    return getTileListInteractorImpl;
  }
}
