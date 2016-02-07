package com.dasheck.materialminesweeper.fragments.settings.interactors;

import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface SetVibrationEnabledInteractor {

  Observable<Void> execute(boolean enabled);
}
