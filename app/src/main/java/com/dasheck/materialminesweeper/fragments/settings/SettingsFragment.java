package com.dasheck.materialminesweeper.fragments.settings;

import android.graphics.PorterDuff;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;
import android.widget.Switch;
import butterknife.Bind;
import butterknife.BindColor;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.OnTouch;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.adapters.BackgroundMusicAdapter;
import com.dasheck.materialminesweeper.annotations.Layout;
import com.dasheck.materialminesweeper.annotations.Title;
import com.dasheck.materialminesweeper.fragments.BaseFragment;
import com.dasheck.model.models.BackgroundMusic;
import java.util.List;
import javax.inject.Inject;
import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;
import timber.log.Timber;

/**
 * @author Stefan Neidig
 */
@Layout(R.layout.fragment_settings) @Title(R.string.settings_title) public class SettingsFragment extends BaseFragment
    implements SettingsView, DiscreteSeekBar.OnProgressChangeListener {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.vibrationSwitch) Switch vibrationSwitch;
  @Bind(R.id.backgroundMusicSpinner) Spinner backgroundMusicSpinner;
  @Bind(R.id.soundSwitch) Switch soundSwitch;
  @Bind(R.id.volumeSeekbar) DiscreteSeekBar volumeSeekbar;


  @BindColor(R.color.colorAccent) int colorAccent;
  @Inject SettingsPresenter presenter;
  @Inject BackgroundMusicAdapter backgroundMusicAdapter;

  private boolean vibrationTouched = false;
  private boolean soundTouched = false;
  private boolean soundSpinnerTouched = false;

  @OnClick(R.id.twitterContainer) public void onTwitterContainerClicked(View view) {
    presenter.openTwitterPage();
  }

  @OnClick(R.id.googlePlayContainer) public void onGooglePlayContainerClicked(View view) {
    presenter.openGooglePlayPage(getBaseActivity().getPackageName());
  }

  @OnClick(R.id.shareContainer) public void onShareContainerClicked(View view) {
    presenter.shareApp();
  }

  @OnItemSelected(R.id.backgroundMusicSpinner) public void onBackgroundMusicSpinnerSelected(int position) {
    if (soundSpinnerTouched) {
      BackgroundMusic bgm = backgroundMusicAdapter.getItem(position);
      presenter.selectBackgroundMusic(bgm);
      soundSpinnerTouched = false;
    }
  }



  @OnCheckedChanged(R.id.vibrationSwitch) void onVibrationSwitchChecked(boolean checked) {
    if (vibrationTouched) {
      presenter.setVibrationEnabled(checked);
      vibrationTouched = false;
    }
  }

  @OnCheckedChanged(R.id.soundSwitch) void onSoundSwitchChecked(boolean checked) {
    if (soundTouched) {
      presenter.setSoundEnabled(checked);
      soundTouched = false;
    }
  }

  @OnTouch(R.id.vibrationSwitch) public boolean onVibrationSwitchTouched() {
    vibrationTouched = true;
    return false;
  }

  @OnTouch(R.id.soundSwitch) public boolean onSoundSwitchTouched() {
    soundTouched = true;
    return false;
  }

  @OnTouch(R.id.backgroundMusicSpinner) public boolean onBackgroundMusicSpinnerTouched() {
    soundSpinnerTouched = true;
    return false;
  }

  @Override public void initializeViews() {
    setPresenter(presenter);

    getBaseActivity().setSupportActionBar(toolbar);
    backgroundMusicSpinner.setAdapter(backgroundMusicAdapter);
    backgroundMusicSpinner.getBackground().setColorFilter(colorAccent, PorterDuff.Mode.SRC_ATOP);

    volumeSeekbar.setOnProgressChangeListener(this);
    volumeSeekbar.setIndicatorFormatter("%1$d%%");
  }

  @Override public void setupToolbar() {
    super.setupToolbar();

    getBaseActivity().setupDrawerLayout(toolbar);
  }

  @Override public void setVibrationEnabled(boolean enabled) {
    vibrationSwitch.setChecked(enabled);
  }

  @Override public void setSoundEnabled(boolean enabled) {
    soundSwitch.setChecked(enabled);
    backgroundMusicSpinner.setEnabled(enabled);
  }

  @Override public void setAvailableBackgroundMusic(List<BackgroundMusic> availableBackgroundMusic) {
    backgroundMusicAdapter.clear();
    backgroundMusicAdapter.addAll(availableBackgroundMusic);
  }

  @Override public void enableBackgroundMusicSpinner(boolean enabled) {
    backgroundMusicSpinner.setEnabled(enabled);
  }

  @Override public void setSelectedBackgroundMusic(BackgroundMusic backgroundMusic) {
    int index = backgroundMusicAdapter.indexOf(backgroundMusic);
    backgroundMusicSpinner.setSelection(index);
  }

  @Override public void setVolume(int volume) {
    volumeSeekbar.setProgress(volume);
  }

  @Override public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
    if(fromUser) {
      presenter.setVolume(value);
    }
  }

  @Override public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
  }

  @Override public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
    presenter.setVolume(seekBar.getProgress());
  }
}
