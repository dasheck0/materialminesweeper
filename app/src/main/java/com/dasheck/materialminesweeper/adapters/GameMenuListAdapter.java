package com.dasheck.materialminesweeper.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnLongClick;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.utilities.Utilities;
import com.dasheck.model.models.GameMode;
import com.dasheck.model.models.Tile;
import java.util.List;
import timber.log.Timber;

/**
 * Created by s.neidig on 17/01/16.
 */
public class GameMenuListAdapter extends BaseAdapter<GameMode, GameMenuListAdapter.ViewHolder> {

  public GameMenuListAdapter(Context context) {
    super(context);
  }

  public GameMenuListAdapter(Context context, List<GameMode> gameMode) {
    super(context);
    addAll(gameMode);
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_menu_game_item, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    GameMode item = get(position);
    holder.rootLayout.setTag(position);
    holder.textView.setText(item.getName() + ", " + item.getMode());
  }

  public class ViewHolder extends BaseViewHolder {

    @Bind(R.id.rootLayout) View rootLayout;
    @Bind(R.id.textView) TextView textView;

    @OnClick(R.id.rootLayout) public void onRootLayoutClicked(View view) {
      if (onItemClickedListener != null) {
        int position = (int) view.getTag();
        onItemClickedListener.onItemClicked(position);
      }
    }

    @OnLongClick(R.id.rootLayout) public boolean onRootLayoutLongClicked(View view) {
      if (onItemLongClickedListener != null) {
        int position = (int) view.getTag();
        onItemLongClickedListener.onItemLongClicked(position);
      }
      return false;
    }

    public ViewHolder(View itemView) {
      super(itemView);
    }
  }
}
