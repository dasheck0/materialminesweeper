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
import com.dasheck.model.models.Configuration;
import com.dasheck.model.models.GameMode;
import com.dasheck.model.utilities.Utilities;
import java.util.List;

/**
 * Created by s.neidig on 17/01/16.
 */
public class GameMenuListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

  private static final int VIEW_TYPE_CONFIGURATION = 0;

  private Context context;
  private List<Configuration> configurations;

  private OnGameMenuItemClickListener onGameMenuItemClickListener;

  public GameMenuListAdapter(Context context, GameMode gameMode, OnGameMenuItemClickListener listener) {
    this.context = context;
    this.configurations = gameMode.getConfigurations();
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
    }

    return null;
  }

  @Override public int getItemCount() {
    return configurations.size();
  }

  @Override public int getItemViewType(int position) {
    if (position < configurations.size()) {
      return VIEW_TYPE_CONFIGURATION;
    }

    return -1;
  }

  public Configuration getConfiguration(int absolutePosition) {
    return configurations.get(absolutePosition);
  }

  @Override public void onBindViewHolder(BaseViewHolder holder, int position) {
    switch (getItemViewType(position)) {
      case VIEW_TYPE_CONFIGURATION:
        Configuration item = getConfiguration(position);
        ConfigurationViewHolder viewHolder = (ConfigurationViewHolder) holder;

        viewHolder.widthTextView.setText(String.valueOf(item.getWidth()));
        viewHolder.heightTextView.setText(String.valueOf(item.getHeight()));
        viewHolder.difficultyTextView.setText(Utilities.getReadbaleNameFromDifficulty(item.getDifficulty()));
        viewHolder.startButton.setTag(position);
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

  public interface OnGameMenuItemClickListener {
    void onConfigurationStartClicked(int position);
  }
}
