package com.dasheck.materialminesweeper.fragments.settings;

import android.support.v7.widget.Toolbar;
import butterknife.Bind;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.annotations.Layout;
import com.dasheck.materialminesweeper.annotations.Title;
import com.dasheck.materialminesweeper.fragments.BaseFragment;
import javax.inject.Inject;

/**
 * @author Stefan Neidig
 */
@Layout(R.layout.fragment_settings) @Title(R.string.settings_title) public class SettingsFragment extends BaseFragment
    implements SettingsView {

  @Bind(R.id.toolbar) Toolbar toolbar;

  @Inject SettingsPresenter presenter;

  @Override public void initializeViews() {
    setPresenter(presenter);

    getBaseActivity().setSupportActionBar(toolbar);
  }

  @Override public void setupToolbar() {
    super.setupToolbar();

    getBaseActivity().setupDrawerLayout(toolbar);
  }
}
