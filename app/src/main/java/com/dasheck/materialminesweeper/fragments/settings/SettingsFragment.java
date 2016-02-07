package com.dasheck.materialminesweeper.fragments.settings;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Switch;
import butterknife.Bind;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTouch;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.annotations.Layout;
import com.dasheck.materialminesweeper.annotations.Title;
import com.dasheck.materialminesweeper.fragments.BaseFragment;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * @author Stefan Neidig
 */
@Layout(R.layout.fragment_settings) @Title(R.string.settings_title) public class SettingsFragment extends BaseFragment
    implements SettingsView {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.vibrationSwitch) Switch vibrationSwitch;

  @Inject SettingsPresenter presenter;

  private boolean vibrationTouched = false;

  @OnClick(R.id.twitterContainer) public void onTwitterContainerClicked(View view) {
    presenter.openTwitterPage();
  }

  @OnClick(R.id.googlePlayContainer) public void onGooglePlayContainerClicked(View view) {
    presenter.openGooglePlayPage(getBaseActivity().getPackageName());
  }

  @OnClick(R.id.shareContainer) public void onShareContainerClicked(View view) {
    presenter.shareApp();
  }

  @OnCheckedChanged(R.id.vibrationSwitch) void onVibrationSwitchChecked(boolean checked) {
    Timber.d("SettingsFragment:41: " + "Checked");
    // TODO: 07/02/16 Do we have to think about if user input checked this?
    if (vibrationTouched) {
      presenter.setVibrationEnabled(checked);
      vibrationTouched = false;
    }
  }

  @OnTouch(R.id.vibrationSwitch) public boolean onVibrationSwitchTouched() {
    vibrationTouched = true;
    return false;
  }

  @Override public void initializeViews() {
    setPresenter(presenter);

    getBaseActivity().setSupportActionBar(toolbar);
  }

  @Override public void setupToolbar() {
    super.setupToolbar();

    getBaseActivity().setupDrawerLayout(toolbar);
  }

  @Override public void setVibrationEnabled(boolean enabled) {
    vibrationSwitch.setChecked(enabled);
  }
}
