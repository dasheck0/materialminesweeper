package com.dasheck.materialminesweeper.fragments.settings;

import com.dasheck.materialminesweeper.fragments.BasePresenterImpl;
import com.dasheck.materialminesweeper.fragments.settings.interactors.IsVibrationEnabledInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.OpenWebsiteInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.SetVibrationEnabledInteractor;
import com.dasheck.materialminesweeper.fragments.settings.interactors.ShareAppInteractor;
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

  @Override public void onResume() {
    super.onResume();
    isVibrationEnabledInteractor.execute().subscribe(view::setVibrationEnabled);
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
}
