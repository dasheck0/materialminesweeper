package com.dasheck.materialminesweeper.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.utilities.Utilities;
import com.dasheck.model.models.Configuration;
import com.dasheck.model.models.GameInformation;
import com.dasheck.model.models.GameMode;
import com.dasheck.model.models.GameStatistics;
import java.util.List;
import timber.log.Timber;

/**
 * Created by s.neidig on 17/01/16.
 */
public class GameMenuListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

  private static final int VIEW_TYPE_CONFIGURATION = 0;

  private static final int VIEW_TYPE_GAME_INFORMATION = 1;

  private static final int VIEW_TYPE_GAME_STATISTICS = 2;

  private Context context;
  private List<Configuration> configurations;
  private List<GameInformation> gameInformationList;
  private List<GameStatistics> gameStatisticsList;

  private OnGameMenuItemClickListener onGameMenuItemClickListener;

  public GameMenuListAdapter(Context context, GameMode gameMode, OnGameMenuItemClickListener listener) {
    this.context = context;
    this.configurations = gameMode.getConfigurations();
    this.gameInformationList = gameMode.getGameInformationList();
    this.gameStatisticsList = gameMode.getGameStatisticses();
    this.onGameMenuItemClickListener = listener;
  }

  public void setOnGameMenuItemClickListener(OnGameMenuItemClickListener onGameMenuItemClickListener) {
    this.onGameMenuItemClickListener = onGameMenuItemClickListener;
  }

  @Override public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    switch (viewType) {
      case VIEW_TYPE_CONFIGURATION:
        return new ConfigurationViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_configuration, parent, false));
      case VIEW_TYPE_GAME_INFORMATION:
        return new GameInformationViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_game_information, parent, false));
      case VIEW_TYPE_GAME_STATISTICS:
        return new GameStatisticsViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_game_statistics, parent, false));
    }

    return null;
  }

  @Override public int getItemCount() {
    return configurations.size() + gameInformationList.size() + gameStatisticsList.size();
  }

  @Override public int getItemViewType(int position) {
    if (position < configurations.size()) {
      return VIEW_TYPE_CONFIGURATION;
    } else if (position < configurations.size() + gameInformationList.size()) {
      return VIEW_TYPE_GAME_INFORMATION;
    } else if (position < configurations.size() + gameInformationList.size() + gameStatisticsList.size()) {
      return VIEW_TYPE_GAME_STATISTICS;
    }

    return -1;
  }

  public Configuration getConfiguration(int absolutePosition) {
    return configurations.get(absolutePosition);
  }

  public GameInformation getGameInformation(int absolutePosition) {
    return gameInformationList.get(absolutePosition - configurations.size());
  }

  public GameStatistics getGameStatistics(int absolutePosition) {
    return gameStatisticsList.get(absolutePosition - configurations.size() - gameInformationList.size());
  }

  @Override public void onBindViewHolder(BaseViewHolder holder, int position) {
    switch (getItemViewType(position)) {
      case VIEW_TYPE_CONFIGURATION:
        Configuration configuration = getConfiguration(position);
        ConfigurationViewHolder configurationHolder = (ConfigurationViewHolder) holder;

        configurationHolder.widthTextView.setText(String.valueOf(configuration.getWidth()));
        configurationHolder.heightTextView.setText(String.valueOf(configuration.getHeight()));
        configurationHolder.difficultyTextView.setText(String.valueOf(configuration.getBombCount()));
        configurationHolder.startButton.setTag(position);
        break;

      case VIEW_TYPE_GAME_INFORMATION:
        GameInformation gameInformation = getGameInformation(position);
        GameInformationViewHolder gameInformationHolder = (GameInformationViewHolder) holder;

        if (gameInformation != null) {
          gameInformationHolder.wonTextView.setText(gameInformation.isWon() ? "Yes" : "No");
          gameInformationHolder.bombCountTextView.setText(String.valueOf(gameInformation.getBombCount()));
          gameInformationHolder.elapsedTimeTextView.setText(
              com.dasheck.materialminesweeper.utilities.Utilities.timespanToReadable(gameInformation.getElapsedTime()));
          gameInformationHolder.revealedTilesTextView.setText(String.valueOf(gameInformation.getRevealedTilesCount()));
          gameInformationHolder.markedTilesTextView.setText(String.valueOf(gameInformation.getMarkedTilesCount()));
        }

        break;

      case VIEW_TYPE_GAME_STATISTICS:
        GameStatistics gameStatistics = getGameStatistics(position);
        GameStatisticsViewHolder gameStatisticsViewHolder = (GameStatisticsViewHolder) holder;

        gameStatisticsViewHolder.gamesCountTextView.setText(String.valueOf(gameStatistics.getGamesCount()));
        gameStatisticsViewHolder.totalTimeTextView.setText(
            com.dasheck.materialminesweeper.utilities.Utilities.timespanToReadable(gameStatistics.getTotalTime()));
        gameStatisticsViewHolder.averageTimeTextView.setText(
            com.dasheck.materialminesweeper.utilities.Utilities.timespanToReadable(gameStatistics.getAverageTime()));
        gameStatisticsViewHolder.streakTextView.setText(String.valueOf(gameStatistics.getStreak()));
        gameStatisticsViewHolder.winningRateTextView.setText(String.format("%.2f%%", gameStatistics.getWinningRate() * 100.0f));
        gameStatisticsViewHolder.resetButton.setTag(position);
        break;
    }
  }

  public class ConfigurationViewHolder extends BaseViewHolder {

    @Bind(R.id.widthTextView) TextView widthTextView;
    @Bind(R.id.heightTextView) TextView heightTextView;
    @Bind(R.id.difficultyTextView) TextView difficultyTextView;
    @Bind(R.id.startButton) Button startButton;

    @OnClick(R.id.startButton) public void onStartButtonClicked(View view) {
      if (onGameMenuItemClickListener != null) {
        onGameMenuItemClickListener.onConfigurationStartClicked((int) view.getTag());
      }
    }

    public ConfigurationViewHolder(View itemView) {
      super(itemView);
    }
  }

  public class GameInformationViewHolder extends BaseViewHolder {

    @Bind(R.id.wonTextView) TextView wonTextView;
    @Bind(R.id.revealedTilesTextView) TextView revealedTilesTextView;
    @Bind(R.id.markedTilesTextView) TextView markedTilesTextView;
    @Bind(R.id.bombCountTextView) TextView bombCountTextView;
    @Bind(R.id.elapsedTimeTextView) TextView elapsedTimeTextView;

    @OnClick(R.id.shareButton) public void onShareButtonClicked(View view) {

    }

    public GameInformationViewHolder(View itemView) {
      super(itemView);
    }
  }

  public class GameStatisticsViewHolder extends BaseViewHolder {

    @Bind(R.id.gamesCountTextView) TextView gamesCountTextView;
    @Bind(R.id.winningRateTextView) TextView winningRateTextView;
    @Bind(R.id.totalTimeTextView) TextView totalTimeTextView;
    @Bind(R.id.averageTimeTextView) TextView averageTimeTextView;
    @Bind(R.id.streakTextView) TextView streakTextView;
    @Bind(R.id.resetButton) Button resetButton;

    @OnClick(R.id.resetButton) public void onResetButtonClicked(View view) {
      if (onGameMenuItemClickListener != null) {
        onGameMenuItemClickListener.onGameStatisticsResetClicked((int) view.getTag());
      }
    }

    public GameStatisticsViewHolder(View itemView) {
      super(itemView);
    }
  }

  public interface OnGameMenuItemClickListener {
    void onConfigurationStartClicked(int position);

    void onGameInformationShareClicked(int position);

    void onGameStatisticsResetClicked(int position);
  }
}
