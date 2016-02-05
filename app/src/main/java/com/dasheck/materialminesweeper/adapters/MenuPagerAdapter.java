package com.dasheck.materialminesweeper.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.dasheck.materialminesweeper.activities.Navigator;
import com.dasheck.model.models.GameMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Stefan Neidig
 */
public class MenuPagerAdapter<T> extends FragmentStatePagerAdapter {

  private Context context;
  private Navigator navigator;
  private List<T> items;

  public MenuPagerAdapter(FragmentManager fragmentManager, Navigator navigator, List<T> items) {
    super(fragmentManager);
    this.navigator = navigator;
    this.items = items;
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
    return new ArrayList<>(items);
  }

  @Override public int getCount() {
    return items.size();
  }

  @Override public Fragment getItem(int position) {
    switch (position % 4) {
      default:
        return navigator.createGameMenuItem((GameMode) items.get(position));
    }
  }

  /*@Override public Object instantiateItem(ViewGroup container, int position) {
    Timber.d("Updating layout");
    View layout = LayoutInflater.from(context).inflate(R.layout.item_menu_game_item, null);

    GameMode item = (GameMode) get(position);

    TextView textView = (TextView) layout.findViewById(R.id.textView);
    textView.setText(String.valueOf(item.getMode()));

    ((ViewPager) container).addView(layout);
    return layout;
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    ((ViewPager) container).removeView((View) object);
  }*/

  @Override public CharSequence getPageTitle(int position) {
    GameMode item = (GameMode) get(position);
    return item.getName();
  }
}