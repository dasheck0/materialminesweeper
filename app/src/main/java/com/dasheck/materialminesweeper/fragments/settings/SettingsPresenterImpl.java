package com.dasheck.materialminesweeper.fragments.settings;

import com.dasheck.materialminesweeper.fragments.BasePresenterImpl;
import com.dasheck.materialminesweeper.fragments.settings.interactors.OpenWebsiteInteractor;
import javax.inject.Inject;

/**
 * @author Stefan Neidig
 */
public class SettingsPresenterImpl extends BasePresenterImpl implements SettingsPresenter {

  @Inject SettingsView view;
  @Inject OpenWebsiteInteractor openWebsiteInteractor;

  @Override public void openTwitterPage() {
    openWebsiteInteractor.execute("https://twitter.com/dasheck")
        .subscribe(); // TODO: 07/02/16 Create twitter account for minesweeper
  }

  @Override public void openGooglePlayPage(String packageName) {
    openWebsiteInteractor.execute("market://details?id=" + packageName).subscribe();
  }
}
