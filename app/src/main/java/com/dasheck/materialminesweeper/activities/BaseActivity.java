package com.dasheck.materialminesweeper.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.dasheck.materialminesweeper.annotations.Layout;

import java.lang.annotation.Annotation;

import timber.log.Timber;

/**
 * Created by s.neidig on 17/01/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
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
