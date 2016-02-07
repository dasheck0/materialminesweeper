package com.dasheck.materialminesweeper.utilities;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.util.Pair;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.dasheck.materialminesweeper.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import timber.log.Timber;

/**
 * Created by s.neidig on 24/01/16.
 */
public class Utilities {

  public static Drawable colorDrawable(Context context, Drawable drawable, int color) {
    //lets see
    drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
    return drawable;
  }

  public static Pair<Integer, Integer> getViewDimension(View view) {
    ViewGroup.LayoutParams params = view.getLayoutParams();
    return new Pair<>(params.width, params.height);
  }

  public static Pair<Integer, Integer> getWindowDimensions(Context context) {
    WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = manager.getDefaultDisplay();
    Point point = new Point();
    display.getSize(point);

    // TODO: 24/01/16 This is defintively not the best way to do this!
    int toolbarHeight = (int) context.getResources().getDimension(R.dimen.height_toolbar);
    int statusBarHeight = 0;

    int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (resourceId > 0) {
      statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
    }

    return new Pair<Integer, Integer>(point.x, point.y - statusBarHeight);
  }

  /**
   * This method converts dp unit to equivalent pixels, depending on device density.
   *
   * @param dp A value in dp (density independent pixels) unit. Which we need to convert into
   * pixels
   * @param context Context to get resources and device specific display metrics
   * @return A float value to represent px equivalent to dp depending on device density
   */
  public static float convertDpToPixel(float dp, Context context) {
    Resources resources = context.getResources();
    DisplayMetrics metrics = resources.getDisplayMetrics();
    return dp * (metrics.densityDpi / 160f);
  }

  /**
   * This method converts device specific pixels to density independent pixels.
   *
   * @param px A value in px (pixels) unit. Which we need to convert into db
   * @param context Context to get resources and device specific display metrics
   * @return A float value to represent dp equivalent to px value
   */
  public static float convertPixelsToDp(float px, Context context) {
    Resources resources = context.getResources();
    DisplayMetrics metrics = resources.getDisplayMetrics();
    return px / (metrics.densityDpi / 160f);
  }

  public static String timespanToReadable(long timespan) {
    long seconds = timespan % 60;
    long minutes = (timespan / 60) % 60;
    long hours = (timespan / (60 * 60)) % 24;

    if (hours > 0) {
      return String.format("%dh %02dm %02ds", hours, minutes, seconds);
    } else if (minutes > 0) {
      return String.format("%dm %02ds", minutes, seconds);
    } else {
      return String.format("%ds", seconds);
    }
  }

  public static String timestampToReadble(long timestamp) {
    return timestampToReadble(timestamp, "dd/MM/yyyy HH:mm:ss");
  }

  public static String timestampToReadble(long timestamp, String format) {
    return new SimpleDateFormat(format).format(new Date(timestamp));
  }
}
