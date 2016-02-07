package com.dasheck.materialminesweeper.fragments.gamemenu_item;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.Bind;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.adapters.GameMenuListAdapter;
import com.dasheck.materialminesweeper.annotations.Layout;
import com.dasheck.materialminesweeper.fragments.BaseFragment;
import com.dasheck.model.models.GameMode;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * @author Stefan Neidig
 */
@Layout(R.layout.fragment_gamemenu_item) public class GameMenuItemFragment extends BaseFragment
    implements GameMenuItemView, GameMenuListAdapter.OnGameMenuItemClickListener {

  @Bind(R.id.gameMenuItemList) RecyclerView gameMenuItemList;

  @Inject GameMenuItemPresenter presenter;

  private RecyclerViewMaterialAdapter gameMenuListAdapter;
  private GameMenuListAdapter adapter;

  @Override public void initializeViews() {
    setPresenter(presenter);
  }

  @Override public void setGameMode(GameMode gameMode) {
    adapter = new GameMenuListAdapter(getContext(), gameMode, this);
    gameMenuListAdapter = new RecyclerViewMaterialAdapter(adapter);

    gameMenuItemList.setAdapter(gameMenuListAdapter);
    gameMenuItemList.setHasFixedSize(true);
    gameMenuItemList.setLayoutManager(new LinearLayoutManager(getContext()));

    MaterialViewPagerHelper.registerRecyclerView(getActivity(), gameMenuItemList, null);
  }

  @Override public void refreshGameStatistics(int position) {
    gameMenuListAdapter.mvp_notifyDataSetChanged();

    Timber.d("GameMenuItemFragment:52: " + "Updated data");
  }

  @Override public void onConfigurationStartClicked(int position) {
    presenter.startGame(position);
  }

  @Override public void onGameInformationShareClicked(int position) {

  }

  @Override public void onGameStatisticsResetClicked(int position) {
    presenter.resetGameStatistics(position);
  }
}
