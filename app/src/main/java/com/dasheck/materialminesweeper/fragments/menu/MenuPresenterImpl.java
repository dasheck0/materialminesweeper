package com.dasheck.materialminesweeper.fragments.menu;

import com.dasheck.materialminesweeper.fragments.BasePresenterImpl;
import com.dasheck.materialminesweeper.fragments.menu.interactors.GetGameModesInteractor;
import com.dasheck.materialminesweeper.fragments.menu.interactors.SetupSoundEffectsInteractor;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * @author Stefan Neidig
 */
public class MenuPresenterImpl extends BasePresenterImpl implements MenuPresenter {

  @Inject MenuView view;
  @Inject SetupSoundEffectsInteractor setupSoundEffectsInteractor;
  @Inject GetGameModesInteractor getGameModesInteractor;

  @Override public void onResume() {
    super.onResume();

    setupSoundEffectsInteractor.execute().subscribe();
    getGameModesInteractor.execute().subscribe(view::setGameModes);
  }
}
