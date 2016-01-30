package com.dasheck.materialminesweeper.fragments.menu.interactors;

import com.dasheck.model.datastores.GameModeDatastore;
import com.dasheck.model.models.GameMode;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import timber.log.Timber;

/**
 * @author Stefan Neidig
 */
public class GetGameModesInteractorImpl implements GetGameModesInteractor {

  @Inject GameModeDatastore gameModeDatastore;

  @Inject public GetGameModesInteractorImpl() {
  }

  @Override public Observable<List<GameMode>> execute() {
    return gameModeDatastore.get();
  }
}
