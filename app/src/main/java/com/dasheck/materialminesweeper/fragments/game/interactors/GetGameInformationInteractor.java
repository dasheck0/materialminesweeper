package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.models.GameInformation;
import rx.Observable;

/**
 * Created by s.neidig on 24/01/16.
 */
public interface GetGameInformationInteractor {

  Observable<GameInformation> execute();
}
