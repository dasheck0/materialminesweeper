package com.dasheck.materialminesweeper.fragments.settings;

import android.content.Context;
import com.dasheck.materialminesweeper.adapters.BackgroundMusicAdapter;
import com.dasheck.materialminesweeper.annotations.PerFragment;
import com.dasheck.materialminesweeper.controllers.ShareController;
import com.dasheck.materialminesweeper.controllers.ShareControllerImpl;
import com.dasheck.materialminesweeper.fragments.settings.interactors.GetAvailableBackgroundMusicInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.GetAvailableBackgroundMusicInteractorImpl;
import com.dasheck.materialminesweeper.fragments.settings.interactors.GetSelectedBackgroundMusicInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.GetSelectedBackgroundMusicInteractorImpl;
import com.dasheck.materialminesweeper.fragments.settings.interactors.GetVolumeInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.GetVolumeInteractorImpl;
import com.dasheck.materialminesweeper.fragments.settings.interactors.IsSoundEnabledInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.IsSoundEnabledInteractorImpl;
import com.dasheck.materialminesweeper.fragments.settings.interactors.IsVibrationEnabledInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.IsVibrationEnabledInteractorImpl;
import com.dasheck.materialminesweeper.fragments.settings.interactors.OpenWebsiteInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.OpenWebsiteInteractorImpl;
import com.dasheck.materialminesweeper.fragments.settings.interactors.SelectBackgroundMusicInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.SelectBackgroundMusicInteractorImpl;
import com.dasheck.materialminesweeper.fragments.settings.interactors.SetSoundEnabledInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.SetSoundEnabledInteractorImpl;
import com.dasheck.materialminesweeper.fragments.settings.interactors.SetVibrationEnabledInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.SetVibrationEnabledInteractorImpl;
import com.dasheck.materialminesweeper.fragments.settings.interactors.SetVolumeInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.SetVolumeInteractorImpl;
import com.dasheck.materialminesweeper.fragments.settings.interactors.ShareAppInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.ShareAppInteractorImpl;
import dagger.Module;
import dagger.Provides;

/**
 * @author Stefan Neidig
 */
@Module public class SettingsModule {

  private SettingsView view;
  private SettingsPresenter presenter;

  public SettingsModule(SettingsView view, SettingsPresenter presenter) {
    this.view = view;
    this.presenter = presenter;
  }

  @PerFragment @Provides SettingsView provideView() {
    return view;
  }

  @PerFragment @Provides SettingsPresenter providePresenter() {
    return presenter;
  }

  @Provides @PerFragment
  public OpenWebsiteInteractor provideOpenWebsiteInteractor(OpenWebsiteInteractorImpl openWebsiteInteractorImpl) {
    return openWebsiteInteractorImpl;
  }

  @Provides @PerFragment
  public ShareAppInteractor provideShareAppInteractor(ShareAppInteractorImpl shareAppInteractorImpl) {
    return shareAppInteractorImpl;
  }

  @Provides @PerFragment public IsVibrationEnabledInteractor provideIsVibrationEnabledInteractor(
      IsVibrationEnabledInteractorImpl isVibrationEnabledInteractorImpl) {
    return isVibrationEnabledInteractorImpl;
  }

  @Provides @PerFragment public SetVibrationEnabledInteractor provideSetVibrationEnabledInteractor(
      SetVibrationEnabledInteractorImpl setVibrationEnabledInteractorImpl) {
    return setVibrationEnabledInteractorImpl;
  }

  @Provides @PerFragment public BackgroundMusicAdapter provideBackgroundMusicAdapter(Context context) {
    return new BackgroundMusicAdapter(context);
  }

  @Provides @PerFragment public GetAvailableBackgroundMusicInteractor provideGetAvailableBackgroundMusicInteractor(
      GetAvailableBackgroundMusicInteractorImpl getAvailableBackgroundMusicInteractorImpl) {
    return getAvailableBackgroundMusicInteractorImpl;
  }

  @Provides @PerFragment public IsSoundEnabledInteractor provideIsSoundEnabledInteractor(
      IsSoundEnabledInteractorImpl isSoundEnabledInteractorImpl) {
    return isSoundEnabledInteractorImpl;
  }

  @Provides @PerFragment public SetSoundEnabledInteractor provideSetSoundEnabledInteractor(
      SetSoundEnabledInteractorImpl setSoundEnabledInteractorImpl) {
    return setSoundEnabledInteractorImpl;
  }

  @Provides @PerFragment public SelectBackgroundMusicInteractor provideSelectBackgroundMusicInteractor(
      SelectBackgroundMusicInteractorImpl selectBackgroundMusicInteractorImpl) {
    return selectBackgroundMusicInteractorImpl;
  }

  @Provides @PerFragment public GetSelectedBackgroundMusicInteractor provideGetSelectedBackgroundMusicInteractor(
      GetSelectedBackgroundMusicInteractorImpl getSelectedBackgroundMusicInteractorImpl) {
    return getSelectedBackgroundMusicInteractorImpl;
  }

  @Provides @PerFragment
  public SetVolumeInteractor provideSetVolumeInteractor(SetVolumeInteractorImpl setVolumeInteractorImpl) {
    return setVolumeInteractorImpl;
  }

  @Provides @PerFragment
  public GetVolumeInteractor provideGetVolumeInteractor(GetVolumeInteractorImpl getVolumeInteractorImpl) {
    return getVolumeInteractorImpl;
  }
}
