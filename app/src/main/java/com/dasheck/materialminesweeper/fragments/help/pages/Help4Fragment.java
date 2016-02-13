package com.dasheck.materialminesweeper.fragments.help.pages;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import com.cleveroad.slidingtutorial.PageFragment;
import com.cleveroad.slidingtutorial.TransformItem;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.views.BabushkaText;

/**
 * @author Stefan Neidig
 */
public class Help4Fragment extends PageFragment {

  private BabushkaText text;

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    text = (BabushkaText) view.findViewById(R.id.hintTextView);

    int accentColor = ContextCompat.getColor(getContext(), R.color.colorAccent);
    int textColor = ContextCompat.getColor(getContext(), R.color.colorSecondaryText);

    text.addPiece(new BabushkaText.Piece.Builder("Tap ").textColor(accentColor).build());
    text.addPiece(new BabushkaText.Piece.Builder("on a properly flagged number to ").textColor(textColor).build());
    text.addPiece(new BabushkaText.Piece.Builder("quick reveal ").textColor(accentColor).build());
    text.addPiece(new BabushkaText.Piece.Builder("adjacent tiles").textColor(textColor).build());
    text.display();
  }

  @Override protected TransformItem[] provideTransformItems() {
    return new TransformItem[] {
        new TransformItem(R.id.hintTextView, true, 5),
        new TransformItem(R.id.hintImageView, true, 500),

    };
  }

  @Override protected int getLayoutResId() {
    return R.layout.fragment_help_4;
  }
}
