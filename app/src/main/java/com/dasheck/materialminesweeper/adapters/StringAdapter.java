package com.dasheck.materialminesweeper.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.Bind;
import com.dasheck.materialminesweeper.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Stefan Neidig
 */
public class StringAdapter extends android.widget.BaseAdapter {

  private Context context;
  private List<String> items;
  private ViewHolder viewHolder;

  public StringAdapter(Context context) {
    this.context = context;
    this.items = new ArrayList<>();
  }

  public void clear() {
    items.clear();
    notifyDataSetChanged();
  }

  public void add(String item) {
    items.add(item);
    notifyDataSetChanged();
  }

  public void addAll(Collection<? extends String> collection) {
    items.addAll(collection);
    notifyDataSetChanged();
  }

  @Override public int getCount() {
    return items.size();
  }

  @Override public String getItem(int position) {
    return items.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = LayoutInflater.from(context).inflate(R.layout.item_string, parent, false);

      viewHolder = new ViewHolder(convertView);
      viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);

      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }

    viewHolder.textView.setText(getItem(position));

    return convertView;
  }

  public int indexOf(String string) {
    return items.indexOf(string);
  }

  public class ViewHolder extends BaseViewHolder {

    @Bind(R.id.textView) TextView textView;

    public ViewHolder(View itemView) {
      super(itemView);
    }
  }
}
