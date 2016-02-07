package com.dasheck.materialminesweeper.fragments.history;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.Bind;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.adapters.GameInformationListAdapter;
import com.dasheck.materialminesweeper.annotations.Layout;
import com.dasheck.materialminesweeper.annotations.Title;
import com.dasheck.materialminesweeper.fragments.BaseFragment;
import com.dasheck.model.models.GameInformation;
import java.util.List;
import javax.inject.Inject;

/**
 * @author Stefan Neidig
 */
@Layout(R.layout.fragment_history) @Title(R.string.history_title) public class HistoryFragment extends BaseFragment
    implements HistoryView {

  @Bind(R.id.gameInformationList) RecyclerView gameInformationList;

  @Inject HistoryPresenter presenter;
  @Inject GameInformationListAdapter adapter;

  @Override public void initializeViews() {
    setPresenter(presenter);

    gameInformationList.setHasFixedSize(true);
    gameInformationList.setLayoutManager(new LinearLayoutManager(getContext()));
    gameInformationList.setAdapter(adapter);
  }

  @Override public void setGameInformationList(List<GameInformation> gameInformationList) {
    adapter.clear();
    adapter.addAll(gameInformationList);
  }
}
