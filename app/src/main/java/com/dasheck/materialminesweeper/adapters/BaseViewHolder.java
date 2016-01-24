package com.dasheck.materialminesweeper.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by s.neidig on 17/01/16.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

  public BaseViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
