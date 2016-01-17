package com.dasheck.materialminesweeper.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.annotations.Layout;

import butterknife.Bind;
import timber.log.Timber;

@Layout(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @Bind(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        textView.setText("Hello stranger");
    }
}