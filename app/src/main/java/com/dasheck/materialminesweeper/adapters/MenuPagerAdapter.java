package com.dasheck.materialminesweeper.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dasheck.materialminesweeper.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import timber.log.Timber;

/**
 * @author Stefan Neidig
 */
public class MenuPagerAdapter<T> extends PagerAdapter {

  private Context context;
  private List<T> items;

  public MenuPagerAdapter(Context context) {
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

  @Override public int getCount() {
    return items.size();
  }

  @Override public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override public Object instantiateItem(ViewGroup container, int position) {
    Timber.d("Updating layout");
    View layout = LayoutInflater.from(context).inflate(R.layout.item_menu_game_item, null);

    String item = (String) get(position);

    //TextView gameTitle = (TextView) layout.findViewById(R.id.gameTitleTextView);
    //gameTitle.setText(item);

    ((ViewPager) container).addView(layout);
    return layout;
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    ((ViewPager) container).removeView((View) object);
  }

  @Override public CharSequence getPageTitle(int position) {
    return String.valueOf(position);
  }
}