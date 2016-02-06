package com.dasheck.materialminesweeper.fragments.menu;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
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
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setDisplayShowHomeEnabled(true);
      actionBar.setDisplayShowTitleEnabled(true);
      actionBar.setDisplayUseLogoEnabled(true);
      actionBar.setHomeButtonEnabled(true);
    }

    //viewPager.getViewPager().setAdapter(menuPagerAdapter);
    //viewPager.getPagerTitleStrip().setViewPager(viewPager.getViewPager());
  }

  @Override public void setGameModes(List<GameMode> gameModes) {
    menuPagerAdapter =
        new MenuPagerAdapter<GameMode>(getBaseActivity().getSupportFragmentManager(), getBaseActivity().getNavigator(),
            gameModes);

    viewPager.getViewPager().setAdapter(menuPagerAdapter);
    viewPager.getPagerTitleStrip().setViewPager(viewPager.getViewPager());
  }
}
