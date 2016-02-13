package com.dasheck.materialminesweeper.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import com.dasheck.materialminesweeper.R;
import com.dasheck.model.models.BackgroundMusic;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Stefan Neidig
 */
public class BackgroundMusicAdapter extends android.widget.BaseAdapter {

  private Context context;
  private List<BackgroundMusic> items;
  private ViewHolder viewHolder;

  public BackgroundMusicAdapter(Context context) {
    this.context = context;
    this.items = new ArrayList<>();
  }

  public void clear() {
    items.clear();
    notifyDataSetChanged();
  }

  public void add(BackgroundMusic item) {
    items.add(item);
    notifyDataSetChanged();
  }

  public void addAll(Collection<? extends BackgroundMusic> collection) {
    items.addAll(collection);
    notifyDataSetChanged();
  }

  @Override public int getCount() {
    return items.size();
  }

  @Override public BackgroundMusic getItem(int position) {
    return items.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = LayoutInflater.from(context).inflate(R.layout.item_background_music, parent, false);

      viewHolder = new ViewHolder(convertView);
      viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
      viewHolder.artistTextView = (TextView) convertView.findViewById(R.id.artistTextView);

      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }

    BackgroundMusic item = getItem(position);

    viewHolder.titleTextView.setText(item.getTitle());
    viewHolder.artistTextView.setText(String.format("by %s", item.getArtist()));

    return convertView;
  }

  public int indexOf(BackgroundMusic backgroundMusic) {
    return items.indexOf(backgroundMusic);
  }

  public class ViewHolder extends BaseViewHolder {

    @Bind(R.id.titleTextView) TextView titleTextView;
    @Bind(R.id.artistTextView) TextView artistTextView;

    public ViewHolder(View itemView) {
      super(itemView);
    }
  }
}
