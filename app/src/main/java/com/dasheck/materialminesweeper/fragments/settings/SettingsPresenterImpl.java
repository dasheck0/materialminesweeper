package com.dasheck.materialminesweeper.fragments.settings;

import com.dasheck.materialminesweeper.fragments.BasePresenterImpl;
import com.dasheck.materialminesweeper.fragments.settings.interactors.GetAvailableBackgroundMusicInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.GetSelectedBackgroundMusicInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.GetVolumeInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.IsSoundEnabledInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.IsVibrationEnabledInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.OpenWebsiteInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.SelectBackgroundMusicInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.SetSoundEnabledInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.SetVibrationEnabledInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.SetVolumeInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.ShareAppInteractor;
import com.dasheck.model.models.BackgroundMusic;
import javax.inject.Inject;

/**
 * @author Stefan Neidig
 */
public class SettingsPresenterImpl extends BasePresenterImpl implements SettingsPresenter {

  @Inject SettingsView view;
  @Inject OpenWebsiteInteractor openWebsiteInteractor;
  @Inject ShareAppInteractor shareAppInteractor;
  @Inject IsVibrationEnabledInteractor isVibrationEnabledInteractor;
  @Inject SetVibrationEnabledInteractor setVibrationEnabledInteractor;
  @Inject GetAvailableBackgroundMusicInteractor getAvailableBackgroundMusicInteractor;
  @Inject IsSoundEnabledInteractor isSoundEnabledInteractor;
  @Inject SetSoundEnabledInteractor setSoundEnabledInteractor;
  @Inject SelectBackgroundMusicInteractor selectBackgroundMusicInteractor;
  @Inject GetSelectedBackgroundMusicInteractor getSelectedBackgroundMusicInteractor;
  @Inject SetVolumeInteractor setVolumeInteractor;
  @Inject GetVolumeInteractor getVolumeInteractor;

  @Override public void onResume() {
    super.onResume();
    isVibrationEnabledInteractor.execute().subscribe(view::setVibrationEnabled);
    isSoundEnabledInteractor.execute().subscribe(view::setSoundEnabled);
    getVolumeInteractor.execute().subscribe(this::setVolume);
    getAvailableBackgroundMusicInteractor.execute()
        .doOnNext(view::setAvailableBackgroundMusic)
        .flatMap(x -> getSelectedBackgroundMusicInteractor.execute())
        .subscribe(view::setSelectedBackgroundMusic);
  }

  @Override public void openTwitterPage() {
    openWebsiteInteractor.execute("https://twitter.com/dasheck")
        .subscribe(); // TODO: 07/02/16 Create twitter account for minesweeper
  }

  @Override public void openGooglePlayPage(String packageName) {
    openWebsiteInteractor.execute("market://details?id=" + packageName).subscribe();
  }

  @Override public void shareApp() {
    shareAppInteractor.execute().subscribe();
  }

  @Override public void setVibrationEnabled(boolean enabled) {
    setVibrationEnabledInteractor.execute(enabled).subscribe();
  }

  @Override public void setSoundEnabled(boolean enabled) {
    view.enableBackgroundMusicSpinner(enabled);
    setSoundEnabledInteractor.execute(enabled).subscribe();
  }

  @Override public void selectBackgroundMusic(BackgroundMusic backgroundMusic) {
    selectBackgroundMusicInteractor.execute(backgroundMusic).subscribe();
  }

  @Override public void setVolume(int value) {
    setVolumeInteractor.execute(value / 100.0f).subscribe();
    view.setSoundEnabled(value > 0);
  }

  private void setVolume(float volume) {
    view.setVolume((int) (volume * 100.0f));
    view.setSoundEnabled(volume > 0.0f);
  }
}
