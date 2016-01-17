package com.dasheck.materialminesweeper.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by s.neidig on 17/01/16.
 */
public abstract class BaseAdapter<T, V extends BaseViewHolder> extends RecyclerView.Adapter<V> {

  protected Context context;
  private List<T> items;

  public BaseAdapter(Context context) {
    this.context = context;
    this.items = new ArrayList<>();
  }

  public void clear() {
    items.clear();
    notifyDataSetChanged();
  }

  public void add(T item) {
    items.add(item);
    notifyDataSetChanged();
  }

  public void addAll(Collection<? extends T> collection) {
    items.addAll(collection);
    notifyDataSetChanged();
  }

  public T get(int position) {
    return items.get(position);
  }

  public List<T> get() {
    return items;
  }

  @Override public int getItemCount() {
    return items.size();
  }
}
