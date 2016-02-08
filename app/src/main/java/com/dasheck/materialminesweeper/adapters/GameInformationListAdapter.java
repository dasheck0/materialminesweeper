package com.dasheck.materialminesweeper.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.utilities.Utilities;
import com.dasheck.model.models.GameInformation;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

/**
 * @author Stefan Neidig
 */
public class GameInformationListAdapter extends BaseAdapter<GameInformation, GameInformationListAdapter.ViewHolder>
    implements StickyRecyclerHeadersAdapter<GameInformationListAdapter.HeaderViewHolder> {

  private OnShareItemClickedListener onShareItemClickedListener;
  private GameInformation highScore;

  public GameInformationListAdapter(Context context) {
    super(context);
    this.highScore = null;
  }

  public void setOnShareItemClickedListener(OnShareItemClickedListener onShareItemClickedListener) {
    this.onShareItemClickedListener = onShareItemClickedListener;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_game_information_alternative, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    GameInformation gameInformation = get(position);
    long difference = 0;

    if (highScore != null) {
      difference = gameInformation.getElapsedTime() - highScore.getElapsedTime();
    }

    holder.wonImageView.setImageDrawable(context.getResources()
        .getDrawable(gameInformation.isWon() ? R.drawable.ic_smileay_won : R.drawable.ic_smiley_dead));
    holder.elapsedTimeTextView.setText(
        Utilities.timespanToReadable(gameInformation.getElapsedTime()) + (gameInformation.isWon() ? (difference == 0
            ? " best time!" : String.format(" +%ds", difference)) : ""));
    holder.difficultyTextView.setText(
        String.format("%s (%d bombs)", Utilities.difficultyToReadable(gameInformation.getDifficulty()),
            gameInformation.getBombCount()));
    holder.shareButton.setTag(position);
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

  public void getHighscore() {
    if (getItemCount() > 0) {
      long elapsedTime = Long.MAX_VALUE;

      for (GameInformation gameInformation : get()) {
        if (gameInformation.isWon() && gameInformation.getElapsedTime() < elapsedTime) {
          highScore = gameInformation;
          elapsedTime = highScore.getElapsedTime();
        }
      }
    }
  }

  public class ViewHolder extends BaseViewHolder {

    @Bind(R.id.wonImageView) ImageView wonImageView;
    @Bind(R.id.difficultyTextView) TextView difficultyTextView;
    @Bind(R.id.elapsedTimeTextView) TextView elapsedTimeTextView;
    @Bind(R.id.shareButton) ImageView shareButton;

    @OnClick(R.id.shareButton) public void onShareButtonClicked(View view) {
      if (onShareItemClickedListener != null) {
        onShareItemClickedListener.onShareItemClicked((int) view.getTag());
      }
    }

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

  public interface OnShareItemClickedListener {
    void onShareItemClicked(int position);
  }
}
