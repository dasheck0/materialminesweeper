package com.dasheck.materialminesweeper.fragments.history;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import butterknife.Bind;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.adapters.GameInformationListAdapter;
import com.dasheck.materialminesweeper.annotations.Layout;
import com.dasheck.materialminesweeper.annotations.Title;
import com.dasheck.materialminesweeper.fragments.BaseFragment;
import com.dasheck.model.models.GameInformation;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * @author Stefan Neidig
 */
@Layout(R.layout.fragment_history) @Title(R.string.history_title) public class HistoryFragment extends BaseFragment
    implements HistoryView, GameInformationListAdapter.OnShareItemClickedListener {

  @Bind(R.id.gameInformationList) RecyclerView gameInformationList;
  @Bind(R.id.toolbar) Toolbar toolbar;

  @Inject HistoryPresenter presenter;
  @Inject GameInformationListAdapter adapter;

  @Override public void initializeViews() {
    setPresenter(presenter);
    setHasOptionsMenu(true);

    if (gameInformationList.getAdapter() == null) {
      gameInformationList.setHasFixedSize(true);
      gameInformationList.setLayoutManager(new LinearLayoutManager(getContext()));
      gameInformationList.setAdapter(adapter);
      gameInformationList.addItemDecoration(new StickyRecyclerHeadersDecoration(adapter));
      adapter.setOnShareItemClickedListener(this);
    }

    getBaseActivity().setSupportActionBar(toolbar);
  }

  @Override public void setupToolbar() {
    super.setupToolbar();
    getBaseActivity().setupDrawerLayout(toolbar);
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.history, menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.ic_charts:
        Timber.d("HistoryFragment:60: " + "charts");
        return true;

      case R.id.ic_filter:
        Timber.d("HistoryFragment:64: " + "filter");
        return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override public void setGameInformationList(List<GameInformation> gameInformationList) {
    adapter.clear();
    adapter.addAll(gameInformationList);
    adapter.getHighscore();
  }

  @Override public void onShareItemClicked(int position) {
    Timber.d("HistoryFragment:54: " + "Shared item at position " + position);
  }
}
