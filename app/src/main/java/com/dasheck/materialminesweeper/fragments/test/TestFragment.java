package com.dasheck.materialminesweeper.fragments.test;

import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.annotations.Layout;
import com.dasheck.materialminesweeper.fragments.BaseFragment;
import com.dasheck.materialminesweeper.fragments.BaseView;
import com.dasheck.model.entities.TestEntity;
import com.dasheck.model.transformators.TestTransformer;
import javax.inject.Inject;

/**
 * Created by s.neidig on 17/01/16.
 */
@Layout(R.layout.fragment_test) public class TestFragment extends BaseFragment implements TestView {

  @Bind(R.id.testView) TextView testView;

  @Inject TestTransformer testTransformer;

  @Override public void intializeViews() {

    TestEntity fromWeb = new TestEntity("10");
    testTransformer.toModel(fromWeb).subscribe(result -> {
      testView.setText(String.valueOf(result.getId()));
    });
  }
}
