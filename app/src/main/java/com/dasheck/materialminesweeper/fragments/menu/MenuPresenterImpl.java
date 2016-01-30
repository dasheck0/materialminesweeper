package com.dasheck.materialminesweeper.fragments.menu;

import com.dasheck.materialminesweeper.fragments.BasePresenter;
import com.dasheck.materialminesweeper.fragments.BasePresenterImpl;
import com.dasheck.materialminesweeper.fragments.menu.interactors.GetGameModesInteractor;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * @author Stefan Neidig
 */
public class MenuPresenterImpl extends BasePresenterImpl implements MenuPresenter {

  @Inject MenuView view;
  @Inject GetGameModesInteractor getGameModesInteractor;

  @Override public void onResume() {
    super.onResume();

    getGameModesInteractor.execute().doOnNext(modes -> Timber.d("Emitting: " + modes)).subscribe(view::setGameModes);
  }
}
