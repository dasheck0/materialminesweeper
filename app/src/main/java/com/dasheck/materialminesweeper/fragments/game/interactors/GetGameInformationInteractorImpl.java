package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.controllers.CurrentGameController;
import com.dasheck.model.models.GameInformation;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by s.neidig on 24/01/16.
 */
public class GetGameInformationInteractorImpl implements GetGameInformationInteractor {

  @Inject CurrentGameController currentGameController;

  @Inject public GetGameInformationInteractorImpl() {
  }

  @Override public Observable<GameInformation> execute() {
    return currentGameController.createGameInformation();
  }
}
