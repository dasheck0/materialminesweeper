package com.dasheck.materialminesweeper.fragments.test;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.adapters.TileListAdapter;
import com.dasheck.materialminesweeper.annotations.Layout;
import com.dasheck.materialminesweeper.fragments.BaseFragment;
import com.dasheck.materialminesweeper.fragments.BaseView;
import com.dasheck.model.entities.TestEntity;
import com.dasheck.model.models.Field;
import com.dasheck.model.models.Tile;
import com.dasheck.model.transformators.TestTransformer;
import com.dasheck.model.utilities.Utilities;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by s.neidig on 17/01/16.
 */
@Layout(R.layout.fragment_test) public class TestFragment extends BaseFragment implements TestView {

  @Bind(R.id.tileMap) RecyclerView tileMap;

  @Inject TestPresenter presenter;
  @Inject TestTransformer testTransformer;
  @Inject TileListAdapter adapter;

  @Override public void initializeViews() {
    setPresenter(presenter);

    tileMap.setAdapter(adapter);
    tileMap.setHasFixedSize(true);
    tileMap.setLayoutManager(new GridLayoutManager(getContext(), 10));
  }

  @Override public void setBombs(List<Tile> bombs) {
    adapter.clear();
    adapter.addAll(bombs);

    Timber.d("Adding " + bombs.size() + " bombs");
  }

  // TODO: 17/01/16 THe problem is that the recyclerview breaks at the display width causing the actual width to be 10 columns instead of 10
  // we need to implement a new expanding gridlayout manager, which scrolls to both directions horizontally and vertically allowing us to navigate
  // properly in our tilemap.

  // But this will be an excercise for the next episode, since I am too tired to think about that now :D Sorry

  // I will tweet my schedule once I have figured it out ;)
}
