package com.dasheck.materialminesweeper.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.utilities.Utilities;
import com.dasheck.model.models.GameInformation;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

/**
 * @author Stefan Neidig
 */
public class GameInformationListAdapter extends BaseAdapter<GameInformation, GameInformationListAdapter.ViewHolder>
    implements StickyRecyclerHeadersAdapter<GameInformationListAdapter.HeaderViewHolder> {

  public GameInformationListAdapter(Context context) {
    super(context);
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_game_information_alternative, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    GameInformation gameInformation = get(position);

    holder.wonTextView.setText(gameInformation.isWon() ? "Yes" : "No");
    holder.bombCountTextView.setText(String.valueOf(gameInformation.getBombCount()));
    holder.elapsedTimeTextView.setText(Utilities.timespanToReadable(gameInformation.getElapsedTime()));
    holder.revealedTilesTextView.setText(String.valueOf(gameInformation.getRevealedTilesCount()));
    holder.markedTilesTextView.setText(String.valueOf(gameInformation.getMarkedTilesCount()));
    holder.dateTextView.setText(Utilities.timestampToReadble(gameInformation.getTimestamp()));
  }

  @Override public long getHeaderId(int position) {
    GameInformation item = get(position);

    for (int i = 0; i < get().size(); i++) {
      GameInformation first = get(i);
      if (com.dasheck.model.utilities.Utilities.areOnSameDate(first.getTimestamp(), item.getTimestamp())) {
        return i;
      }
    }

    return 0;
  }

  @Override public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
    return new HeaderViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_game_information_header, parent, false));
  }

  @Override public void onBindHeaderViewHolder(HeaderViewHolder holder, int position) {
    GameInformation item = get(position);

    holder.dateTextView.setText(Utilities.timestampToReadble(item.getTimestamp(), "dd/MM/yyyy - EEEE"));
  }

  public class ViewHolder extends BaseViewHolder {

    @Bind(R.id.wonTextView) TextView wonTextView;
    @Bind(R.id.revealedTilesTextView) TextView revealedTilesTextView;
    @Bind(R.id.markedTilesTextView) TextView markedTilesTextView;
    @Bind(R.id.bombCountTextView) TextView bombCountTextView;
    @Bind(R.id.elapsedTimeTextView) TextView elapsedTimeTextView;
    @Bind(R.id.dateTextView) TextView dateTextView;

    public ViewHolder(View itemView) {
      super(itemView);
    }
  }

  public class HeaderViewHolder extends BaseViewHolder {

    @Bind(R.id.dateTextView) TextView dateTextView;

    public HeaderViewHolder(View itemView) {
      super(itemView);
    }
  }
}
