package com.dasheck.materialminesweeper.fragments.settings;

import com.dasheck.materialminesweeper.fragments.BasePresenter;
import com.dasheck.model.models.BackgroundMusic;

/**
 * @author Stefan Neidig
 */
public interface SettingsPresenter extends BasePresenter {

  void openTwitterPage();

  void openGooglePlayPage(String packageName);

  void shareApp();

  void setVibrationEnabled(boolean enabled);

  void setSoundEnabled(boolean enabled);

  void selectBackgroundMusic(BackgroundMusic backgroundMusic);

  void setVolume(int value);
}
