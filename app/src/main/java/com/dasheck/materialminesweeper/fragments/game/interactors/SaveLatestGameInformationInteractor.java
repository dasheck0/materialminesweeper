package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.models.GameInformation;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface SaveLatestGameInformationInteractor {

  Observable<Void> execute(GameInformation gameInformation);
}
