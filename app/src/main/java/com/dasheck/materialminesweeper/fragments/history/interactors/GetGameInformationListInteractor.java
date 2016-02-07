package com.dasheck.materialminesweeper.fragments.history.interactors;

import com.dasheck.model.models.GameInformation;
import java.util.List;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface GetGameInformationListInteractor {

  Observable<List<GameInformation>> execute();
}
