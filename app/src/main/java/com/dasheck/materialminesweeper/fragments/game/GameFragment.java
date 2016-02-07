package com.dasheck.materialminesweeper.fragments.game;

import android.graphics.drawable.Drawable;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.BindDrawable;
import butterknife.OnClick;
import com.afollestad.materialdialogs.DialogAction;
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
@Layout(R.layout.fragment_game) @Title(R.string.game_title) public class GameFragment extends BaseFragment
    implements GameView, BaseAdapter.OnItemClickedListener, BaseAdapter.OnItemLongClickedListener {

  @Bind(R.id.tileMapContainer) View tileMapContainer;
  @Bind(R.id.tileMap) RecyclerView tileMap;
  @Bind(R.id.bombTextView) TextView bombTextView;
  @Bind(R.id.timeTextView) TextView timeTextView;
  @Bind(R.id.smileyButton) ImageView smileyButton;

  @BindDrawable(R.drawable.ic_smiley) Drawable icSmiley;
  @BindDrawable(R.drawable.ic_smileay_won) Drawable icSmileayWon;
  @BindDrawable(R.drawable.ic_smiley_checking) Drawable icSmileyChecking;
  @BindDrawable(R.drawable.ic_smiley_dead) Drawable icSmileyDead;

  @Inject GamePresenter presenter;
  @Inject TileListAdapter adapter;

  private FixedGridLayoutManager gridLayoutManager;
  private boolean fieldFrozen = false;

  @OnClick(R.id.backButton) public void onBackButtonClicked(View view) {
    presenter.interruptGame();
  }

  @OnClick(R.id.smileyButton) public void onSmileyButtonClicked(View view) {
    presenter.restartGame();
  }

  @Override public boolean onBackPressed() {
    presenter.interruptGame();
    return true;
  }

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

  @Override public void setSmileyLost(GameInformation gameInformation) {
    smileyButton.setImageDrawable(icSmileyDead);
  }

  @Override public void setNumberOfRemainingBombs(int remainingBombs) {
    bombTextView.setText(String.valueOf(remainingBombs));
  }

  @Override public void setElapsedTime(long elapsedTimeInSeconds) {
    timeTextView.setText(String.format("%03d", elapsedTimeInSeconds));
  }

  @Override public void setSmileyWon() {
    smileyButton.setImageDrawable(icSmileayWon);
  }

  @Override public void setSmileyChecking() {
    smileyButton.setImageDrawable(icSmileyChecking);
  }

  @Override public void setSmileyDefault() {
    smileyButton.setImageDrawable(icSmiley);
  }

  @Override public void setSmileyLost() {
    smileyButton.setImageDrawable(icSmileyDead);
  }

  @Override public void freezeField() {
    fieldFrozen = true;
  }

  @Override public void unfreezeField() {
    fieldFrozen = false;
  }

  @Override public void showGameInterruptDialog() {
    new MaterialDialog.Builder(getContext()).title("Are you sure?")
        .content("Any progress will be lost")
        .theme(Theme.LIGHT)
        .positiveText("OK")
        .negativeText("Cancel")
        .onPositive((dialog, which) -> {
          dialog.dismiss();
          presenter.loadMenu();
        })
        .onNegative((dialog, which) -> {
          dialog.dismiss();
          presenter.unpauseGame();
        })
        .show();
  }

  @Override public void onItemClicked(int position) {
    if (!fieldFrozen) {
      presenter.revealTile(adapter.get(position));
    }
  }

  @Override public void onItemLongClicked(int position) {
    if (!fieldFrozen) {
      presenter.markTile(adapter.get(position));
    }
  }
}
