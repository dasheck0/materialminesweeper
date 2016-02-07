package com.dasheck.materialminesweeper.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import com.dasheck.materialminesweeper.R;
import com.dasheck.model.models.GameInformation;

/**
 * @author Stefan Neidig
 */
public class GameInformationListAdapter extends BaseAdapter<GameInformation, GameInformationListAdapter.ViewHolder> {

  public GameInformationListAdapter(Context context) {
    super(context);
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_game_information, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    GameInformation gameInformation = get(position);

    holder.wonTextView.setText(gameInformation.isWon() ? "Yes" : "No");
    holder.bombCountTextView.setText(String.valueOf(gameInformation.getBombCount()));
    holder.elapsedTimeTextView.setText(
        com.dasheck.materialminesweeper.utilities.Utilities.timespanToReadable(gameInformation.getElapsedTime()));
    holder.revealedTilesTextView.setText(String.valueOf(gameInformation.getRevealedTilesCount()));
    holder.markedTilesTextView.setText(String.valueOf(gameInformation.getMarkedTilesCount()));
  }

  public class ViewHolder extends BaseViewHolder {

    @Bind(R.id.wonTextView) TextView wonTextView;
    @Bind(R.id.revealedTilesTextView) TextView revealedTilesTextView;
    @Bind(R.id.markedTilesTextView) TextView markedTilesTextView;
    @Bind(R.id.bombCountTextView) TextView bombCountTextView;
    @Bind(R.id.elapsedTimeTextView) TextView elapsedTimeTextView;

    public ViewHolder(View itemView) {
      super(itemView);
    }
  }
}
