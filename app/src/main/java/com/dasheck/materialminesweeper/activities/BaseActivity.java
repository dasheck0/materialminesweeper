package com.dasheck.materialminesweeper.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import com.dasheck.materialminesweeper.Application;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.annotations.Layout;
import com.dasheck.materialminesweeper.di.AcitivityModule;
import com.dasheck.materialminesweeper.di.ActivityComponent;
import com.dasheck.materialminesweeper.di.DaggerActivityComponent;
import java.lang.annotation.Annotation;

/**
 * Created by s.neidig on 17/01/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);

        setupInjector();
    }

    private void setupInjector() {
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((Application) getApplication()).getApplicationComponent())
                .acitivityModule(new AcitivityModule(this))
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    public int getFragmentContainerId() {
        return R.id.fragmentContainer;
    }

    public Navigator getNavigator() {
        return activityComponent.navigator();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private int getLayoutResId() {
        Annotation annotation = this.getClass().getAnnotation(Layout.class);
        if(annotation != null) {
            return ((Layout) annotation).value();
        } else {
            throw new IllegalStateException("You must provide a layout via the @Layout annotation");
        }
    }
}
