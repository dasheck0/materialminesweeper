package com.dasheck.materialminesweeper.fragments.menu;

import android.support.v4.view.ViewPager;
import butterknife.Bind;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.adapters.MenuPagerAdapter;
import com.dasheck.materialminesweeper.annotations.Layout;
import com.dasheck.materialminesweeper.annotations.Title;
import com.dasheck.materialminesweeper.fragments.BaseFragment;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * @author Stefan Neidig
 */
@Layout(R.layout.fragment_menu) @Title(R.string.title_menu) public class MenuFragment extends BaseFragment
    implements MenuView {

  @Bind(R.id.viewPager) ViewPager viewPager;

  @Inject MenuPresenter presenter;
  @Inject MenuPagerAdapter<String> menuPagerAdapter;

  @Override public void initializeViews() {
    setPresenter(presenter);

    viewPager.setAdapter(menuPagerAdapter);
  }

  @Override public void setGameModes(List<String> gameModes) {
    Timber.d("Setting game modes" + gameModes);
    menuPagerAdapter.clear();
    menuPagerAdapter.addAll(gameModes);
  }
}
