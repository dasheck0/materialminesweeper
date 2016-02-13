package com.dasheck.model.datastores;

import com.dasheck.model.R;
import com.dasheck.model.controllers.PreferencesController;
import com.dasheck.model.models.BackgroundMusic;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

/**
 * @author Stefan Neidig
 */
public class SettingsDatastoreImpl implements SettingsDatastore {

  private static final String SETTINGS_VIBRATION = "settingsVibration";
  private static final String SETTINGS_BGM = "settingsBGM";
  private static final String SETTINGS_BGM_ENABLED = "settingsBGMEnabled";
  private static final String SETTINGS_BGM_VOLUME = "settingsBGMVolume";
  private static final String SETTINGS_FIRST_START = "settingsFirstStart";

  @Inject PreferencesController preferencesController;

  @Inject public SettingsDatastoreImpl() {
  }

  @Override public Observable<Boolean> isVibrationEnabled() {
    return preferencesController.readBoolean(SETTINGS_VIBRATION);
  }

  @Override public Observable<Void> enableVibration(boolean enabled) {
    return preferencesController.writeBoolean(SETTINGS_VIBRATION, enabled);
  }

  @Override public Observable<List<BackgroundMusic>> getAvailableBackgroundMusic() {
    return Observable.create(new Observable.OnSubscribe<List<BackgroundMusic>>() {
      @Override public void call(Subscriber<? super List<BackgroundMusic>> subscriber) {
        List<BackgroundMusic> availableBackgroundMusic = new ArrayList<>();

        availableBackgroundMusic.add(new BackgroundMusic("Overworld", "knarmahfox", "overworld"));
        availableBackgroundMusic.add(new BackgroundMusic("pixel-song", "hmmm101", "pixelsong"));
        availableBackgroundMusic.add(new BackgroundMusic("Merx market song", "axtoncrolley", "merxmarketsong"));

        subscriber.onNext(availableBackgroundMusic);
        subscriber.onCompleted();
      }
    });
  }

  @Override public Observable<BackgroundMusic> getSelectedBackgroundMusic() {
    return Observable.zip(getAvailableBackgroundMusic(), preferencesController.readString(SETTINGS_BGM),
        (availableBGMs, selectedBGM) -> {
          for (BackgroundMusic availableBGM : availableBGMs) {
            if (availableBGM.getTitle().equalsIgnoreCase(selectedBGM)) {
              return availableBGM;
            }
          }
          return null;
        });
  }

  @Override public Observable<Void> selectBackgroundMusic(String title) {
    return preferencesController.writeString(SETTINGS_BGM, title);
  }

  @Override public Observable<Boolean> isSoundEnabled() {
    return preferencesController.readBoolean(SETTINGS_BGM_ENABLED);
  }

  @Override public Observable<Void> enableSound(boolean enabled) {
    return preferencesController.writeBoolean(SETTINGS_BGM_ENABLED, enabled);
  }

  @Override public Observable<Void> setVolume(float volume) {
    return preferencesController.writeFloat(SETTINGS_BGM_VOLUME, volume);
  }

  @Override public Observable<Float> getVolume() {
    return preferencesController.readFloat(SETTINGS_BGM_VOLUME);
  }

  @Override public Observable<Boolean> isFirstStart() {
    return preferencesController.readBoolean(SETTINGS_FIRST_START);
  }

  @Override public Observable<Void> setFirstStart(boolean firstStart) {
    return preferencesController.writeBoolean(SETTINGS_FIRST_START, firstStart);
  }
}
