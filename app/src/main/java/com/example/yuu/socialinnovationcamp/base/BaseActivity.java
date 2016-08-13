package com.example.yuu.socialinnovationcamp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by huylv on 13-Aug-16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private int layoutId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        ButterKnife.bind(this);
    }

    protected abstract int getLayoutId();
}
