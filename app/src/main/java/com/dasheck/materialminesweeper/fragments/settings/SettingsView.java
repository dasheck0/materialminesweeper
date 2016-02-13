package com.dasheck.materialminesweeper.fragments.settings;

import com.dasheck.materialminesweeper.fragments.BaseView;
import com.dasheck.model.models.BackgroundMusic;
import java.util.List;

/**
 * @author Stefan Neidig
 */
public interface SettingsView extends BaseView {

  void setVibrationEnabled(boolean enabled);

  void setSoundEnabled(boolean enabled);

  void setAvailableBackgroundMusic(List<BackgroundMusic> availableBackgroundMusic);

  void enableBackgroundMusicSpinner(boolean enabled);

  void setSelectedBackgroundMusic(BackgroundMusic backgroundMusic);

  void setVolume(int volume);
}
