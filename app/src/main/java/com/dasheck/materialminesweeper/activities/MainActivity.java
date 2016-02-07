package com.dasheck.materialminesweeper.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import butterknife.Bind;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.annotations.Layout;
import com.dasheck.materialminesweeper.fragments.BaseFragment;
import com.dasheck.materialminesweeper.fragments.BaseView;
import com.dasheck.materialminesweeper.fragments.history.HistoryFragment;
import com.dasheck.materialminesweeper.fragments.menu.MenuFragment;
import com.dasheck.materialminesweeper.fragments.settings.SettingsFragment;
import timber.log.Timber;

@Layout(R.layout.activity_main) public class MainActivity extends BaseActivity {

  @Bind(R.id.drawerLayout) DrawerLayout drawerLayout;
  @Bind(R.id.navigationView) NavigationView navigationView;

  private ActionBarDrawerToggle actionBarDrawerToggle;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getActivityComponent().navigator().showMenu();

    setupNavigationItemListener();
  }

  public void setupDrawerLayout(Toolbar toolbar) {
    if (toolbar == null) {
      actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
    } else {
      actionBarDrawerToggle =
          new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
    }

    drawerLayout.setDrawerListener(actionBarDrawerToggle);
    actionBarDrawerToggle.syncState();
  }

  private void setupNavigationItemListener() {
    navigationView.setNavigationItemSelectedListener(item -> {
      if (item.isCheckable()) {
        item.setChecked(true);
      }

      boolean result = false;

      switch (item.getItemId()) {
        case R.id.ic_menu:
          if (!(getCurrentFragment() instanceof MenuFragment)) {
            getNavigator().showMenu();
            result = true;
          }
          break;

        case R.id.ic_leaderbord:
          Timber.d("MenuFragment:69: " + "loading leaderboard");
          result = true;
          break;

        case R.id.ic_history:
          if (!(getCurrentFragment() instanceof HistoryFragment)) {
            getNavigator().showHistory();
            result = true;
          }
          break;

        case R.id.ic_settings:
          if (!(getCurrentFragment() instanceof SettingsFragment)) {
            getNavigator().showSettings();
            result = true;
          }
          break;
      }

      if (result) {
        drawerLayout.closeDrawers();
      }

      return result;
    });
  }

  @Override public void onBackPressed() {
    if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
      drawerLayout.closeDrawers();
    } else {
      if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
        finish();
      } else {
        BaseFragment fragment = (BaseFragment) getCurrentFragment();
        if (fragment != null) {
          if (!fragment.onBackPressed()) {
            super.onBackPressed();
          }
        }
      }
    }
  }

  private Fragment getCurrentFragment() {
    FragmentManager fragmentManager = getSupportFragmentManager();

    if (fragmentManager.getBackStackEntryCount() > 0) {
      String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
      return getSupportFragmentManager().findFragmentByTag(fragmentTag);
    }

    return null;
  }
}