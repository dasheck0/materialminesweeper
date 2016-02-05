package com.dasheck.materialminesweeper.fragments.game;

import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.adapters.BaseAdapter;
import com.dasheck.materialminesweeper.adapters.TileListAdapter;
import com.dasheck.materialminesweeper.annotations.Layout;
import com.dasheck.materialminesweeper.annotations.Title;
import com.dasheck.materialminesweeper.fragments.BaseFragment;
import com.dasheck.materialminesweeper.layoutmanagers.FixedGridLayoutManager;
import com.dasheck.materialminesweeper.utilities.Utilities;
import com.dasheck.model.models.GameInformation;
import com.dasheck.model.models.Tile;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by s.neidig on 17/01/16.
 */
@Layout(R.layout.fragment_test) @Title(R.string.game_title) public class GameFragment extends BaseFragment
    implements GameView, BaseAdapter.OnItemClickedListener, BaseAdapter.OnItemLongClickedListener {

  @Bind(R.id.tileMapContainer) View tileMapContainer;
  @Bind(R.id.tileMap) RecyclerView tileMap;
  @Bind(R.id.bombTextView) TextView bombTextView;
  @Bind(R.id.timeTextView) TextView timeTextView;

  @Inject GamePresenter presenter;
  @Inject TileListAdapter adapter;

  private FixedGridLayoutManager gridLayoutManager;

  @Override public void initializeViews() {
    setPresenter(presenter);

    gridLayoutManager = new FixedGridLayoutManager();

    tileMap.setAdapter(adapter);
    tileMap.setHasFixedSize(true);
    tileMap.setLayoutManager(gridLayoutManager);
    adapter.setOnItemClickedListener(this);
    adapter.setOnItemLongClickedListener(this);
  }

  @Override public void setTiles(List<Tile> tiles) {
    adapter.clear();
    adapter.addAll(tiles);
  }

  @Override public void setDimension(int width, int height) {
    gridLayoutManager.setTotalColumnCount(width);
  }

  @Override public void repositionGrid(int columns, int rows) {
    float sizeOfTile = getContext().getResources().getDimension(R.dimen.tile_size);
    float width = columns * sizeOfTile;
    float height = rows * sizeOfTile;
    Pair<Integer, Integer> displaySize = Utilities.getWindowDimensions(getContext());

    int screenWidth = displaySize.first;
    int screenHeight = (int) (displaySize.second - Utilities.convertDpToPixel(48, getContext()));

    int left = (int) Utilities.convertDpToPixel(16, getContext());
    int right = (int) Utilities.convertDpToPixel(16, getContext());
    int top = (int) Utilities.convertDpToPixel(16, getContext());
    int bottom = (int) Utilities.convertDpToPixel(16, getContext());

    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tileMap.getLayoutParams();

    if (width < screenWidth - (left + right)) {
      int difference = (int) (screenWidth - width);
      left = difference / 2;
      right = difference / 2;
    }

    if (height < screenHeight - (top + bottom)) {
      int difference = (int) (screenHeight - height);
      top = difference / 2;
      bottom = difference / 2;
    }

    params.setMargins(left, top, right, bottom);
    tileMap.setLayoutParams(params);
  }

  @Override public void showGameLostDialog(GameInformation gameInformation) {
    MaterialDialog lostDialog = new MaterialDialog.Builder(getContext()).theme(Theme.LIGHT)
        .title("You lost")
        .customView(R.layout.dialog_game_lost, true)
        .positiveText("YES")
        .negativeText("NO")
        .cancelable(false)
        .onPositive((dialog, which) -> {
          presenter.restartGame();
          dialog.dismiss();
        })
        .onNegative((dialog, which) -> {
          presenter.showMenu();
          dialog.dismiss();
        })
        .build();

    View root = lostDialog.getCustomView();

    if (root != null) {
      TextView markedTilesTextView = (TextView) root.findViewById(R.id.markedTilesTextView);
      TextView revealedTilesTextView = (TextView) root.findViewById(R.id.revealedTilesTextView);
      TextView elapsedTimeTextView = (TextView) root.findViewById(R.id.elapsedTimeTextView);

      markedTilesTextView.setText(String.valueOf(gameInformation.getMarkedTilesCount()));
      revealedTilesTextView.setText(String.valueOf(gameInformation.getRevealedTilesCount()));
      elapsedTimeTextView.setText(String.format("%d sec", gameInformation.getElapsedTime()));
    }

    lostDialog.show();
  }

  @Override public void setNumberOfRemainingBombs(int remainingBombs) {
    bombTextView.setText(String.valueOf(remainingBombs));
  }

  @Override public void setElapsedTime(long elapsedTimeInSeconds) {
    timeTextView.setText(String.format("%03d", elapsedTimeInSeconds));
  }

  @Override public void showGameWonDialog() {
    MaterialDialog lostDialog = new MaterialDialog.Builder(getContext()).theme(Theme.LIGHT)
        .title("You won")
        .content("Do you want to play again")
        .positiveText("YES")
        .negativeText("NO")
        .cancelable(false)
        .onPositive((dialog, which) -> {
          presenter.restartGame();
          dialog.dismiss();
        })
        .onNegative((dialog, which) -> {
          presenter.showMenu();
          dialog.dismiss();
        })
        .show();
  }

  @Override public void onItemClicked(int position) {
    presenter.revealTile(adapter.get(position));
  }

  @Override public void onItemLongClicked(int position) {
    presenter.markTile(adapter.get(position));
  }

}
