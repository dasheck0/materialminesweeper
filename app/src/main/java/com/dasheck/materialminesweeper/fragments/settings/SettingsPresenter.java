package com.dasheck.materialminesweeper.fragments.settings;

import com.dasheck.materialminesweeper.fragments.BasePresenter;

/**
 * @author Stefan Neidig
 */
public interface SettingsPresenter extends BasePresenter {

  void openTwitterPage();

  void openGooglePlayPage(String packageName);
}
