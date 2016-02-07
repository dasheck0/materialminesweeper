package com.dasheck.materialminesweeper.fragments.settings.interactors;

import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface IsVibrationEnabledInteractor {

  Observable<Boolean> execute();
}
