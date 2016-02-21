package com.dasheck.materialminesweeper.fragments.menu;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.Bind;
import butterknife.BindColor;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.adapters.MenuPagerAdapter;
import com.dasheck.materialminesweeper.annotations.Layout;
import com.dasheck.materialminesweeper.fragments.BaseFragment;
import com.dasheck.model.models.GameMode;
import com.github.florent37.materialviewpager.MaterialViewPager;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * @author Stefan Neidig
 */
@Layout(R.layout.fragment_menu) public class MenuFragment extends BaseFragment implements MenuView {

  @Bind(R.id.viewPager) MaterialViewPager viewPager;

  @BindColor(R.color.colorPrimary) int colorPrimary;

  @Inject MenuPresenter presenter;

  private MenuPagerAdapter<GameMode> menuPagerAdapter;

  @Override public void initializeViews() {
    setPresenter(presenter);

    Toolbar toolbar = viewPager.getToolbar();
    if (toolbar != null && getBaseActivity().getSupportActionBar() == null) {
      getBaseActivity().setSupportActionBar(toolbar);

      ActionBar actionBar = getBaseActivity().getSupportActionBar();
      actionBar.setDisplayShowTitleEnabled(true);
      /*actionBar.setDisplayHomeAsUpEnabled(false);
      actionBar.setDisplayShowHomeEnabled(false);
      actionBar.setDisplayUseLogoEnabled(false);
      actionBar.setHomeButtonEnabled(false);*/
    }

    /*actionBarDrawerToggle =
        new ActionBarDrawerToggle(getBaseActivity(), drawerLayout, toolbar, R.string.app_name, R.string.app_name);
    drawerLayout.setDrawerListener(actionBarDrawerToggle);
    actionBarDrawerToggle.syncState();

    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override public boolean onNavigationItemSelected(MenuItem item) {

        if (item.isCheckable()) {
          item.setChecked(true);
        }

        switch (item.getItemId()) {
          case R.id.ic_menu:
            return true;

          case R.id.ic_leaderbord:
            Timber.d("MenuFragment:69: " + "loading leaderboard");
            return true;

          case R.id.ic_history:
            Timber.d("MenuFragment:73: " + "Loading history");
            return true;

          case R.id.ic_settings:
            return true;

          case R.id.ic_twitter:
            return true;

          case R.id.ic_google_play:
            return true;
        }

        return false;
      }
    });*/
  }

  @Override public void setupToolbar() {
    super.setupToolbar();

    getBaseActivity().setupDrawerLayout(viewPager.getToolbar());
  }

  @Override public void setGameModes(List<GameMode> gameModes) {
    menuPagerAdapter =
        new MenuPagerAdapter<GameMode>(getChildFragmentManager(), getBaseActivity().getNavigator(),
            gameModes);

    viewPager.getViewPager().setAdapter(menuPagerAdapter);
    viewPager.getPagerTitleStrip().setViewPager(viewPager.getViewPager());
  }
}
