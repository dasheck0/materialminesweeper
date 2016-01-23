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
import com.dasheck.model.models.Tile;

/**
 * Created by s.neidig on 17/01/16.
 */
public class TileListAdapter extends BaseAdapter<Tile, TileListAdapter.ViewHolder> {

  public TileListAdapter(Context context) {
    super(context);
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tile, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    Tile item = get(position);
    holder.rootLayout.setBackgroundColor(item.isBomb() ? Color.RED : Color.WHITE);
    holder.rootLayout.setTag(position);
    holder.numberTextView.setText(String.valueOf(item.getNumberOfAdjacentBombs()));
  }

  public class ViewHolder extends BaseViewHolder {

    @Bind(R.id.rootLayout) View rootLayout;
    @Bind(R.id.numberTextView) TextView numberTextView;

    @OnClick(R.id.rootLayout) public void onRootLayoutClicked(View view) {
      if(onItemClickedListener != null) {
        int position = (int) view.getTag();
        onItemClickedListener.onItemClicked(position);
      }
    }

    @OnLongClick(R.id.rootLayout) public boolean onRootLayoutLongClicked(View view) {
      if(onItemLongClickedListener != null) {
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
