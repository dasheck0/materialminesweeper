package com.dasheck.materialminesweeper.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
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
  }

  public class ViewHolder extends BaseViewHolder {

    @Bind(R.id.rootLayout) View rootLayout;

    public ViewHolder(View itemView) {
      super(itemView);
    }
  }
}
