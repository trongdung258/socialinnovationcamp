package com.example.yuu.socialinnovationcamp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.yuu.socialinnovationcamp.R;
import com.example.yuu.socialinnovationcamp.activity.ExerciseActivity;
import com.example.yuu.socialinnovationcamp.base.BaseFragment;
import com.gc.materialdesign.views.Button;

import butterknife.Bind;

/**
 * Created by huylv on 13-Aug-16.
 */
public class SelfHelpFragment extends BaseFragment {

    @Bind(R.id.self_help_start_bt)
    Button self_help_start_bt;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        self_help_start_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ExerciseActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_self_help;
    }
}
