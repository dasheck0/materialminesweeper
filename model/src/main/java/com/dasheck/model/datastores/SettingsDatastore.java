package com.dasheck.model.datastores;

import com.dasheck.model.models.BackgroundMusic;
import java.util.List;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface SettingsDatastore {

  Observable<Boolean> isVibrationEnabled();

  Observable<Void> enableVibration(boolean enabled);

  Observable<List<BackgroundMusic>> getAvailableBackgroundMusic();

  Observable<BackgroundMusic> getSelectedBackgroundMusic();

  Observable<Void> selectBackgroundMusic(String title);

  Observable<Boolean> isSoundEnabled();

  Observable<Void> enableSound(boolean enabled);

  Observable<Void> setVolume(float volume);

  Observable<Float> getVolume();
}
