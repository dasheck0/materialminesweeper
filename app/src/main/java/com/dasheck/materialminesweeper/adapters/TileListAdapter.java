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
import com.dasheck.model.models.Tile;
import timber.log.Timber;

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

    holder.rootLayout.setTag(position);
    holder.rootLayout.setActivated(item.isRevealed()); //just for testing
    holder.numberTextView.setVisibility(
        item.isRevealed() && item.getNumberOfAdjacentBombs() > 0 ? View.VISIBLE
            : View.GONE); // for testing
    holder.numberTextView.setText(String.valueOf(item.getNumberOfAdjacentBombs()));

    if (item.isRevealed()) {
      if (item.isBomb()) {
        holder.rootLayout.setBackgroundColor(Color.RED);
      } else {
        holder.rootLayout.setBackground(
            context.getResources().getDrawable(R.drawable.tile_background));
      }
    } else {
      if (item.isMarked()) {
        holder.rootLayout.setBackground(Utilities.colorDrawable(context,
            context.getResources().getDrawable(R.drawable.ic_flag_variant_grey600_24dp),
            context.getResources().getColor(R.color.colorAccent)));
      } else {
        holder.rootLayout.setBackground(
            context.getResources().getDrawable(R.drawable.tile_background));
      }
    }
  }

  public class ViewHolder extends BaseViewHolder {

    @Bind(R.id.rootLayout) View rootLayout;
    @Bind(R.id.numberTextView) TextView numberTextView;

    @OnClick(R.id.rootLayout) public void onRootLayoutClicked(View view) {
      Timber.d("Click");

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
