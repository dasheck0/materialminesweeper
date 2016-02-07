package com.dasheck.model.datastores;

import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface SettingsDatastore {

  Observable<Boolean> isVibrationEnabled();

  Observable<Void> enableVibration(boolean enabled);
}
