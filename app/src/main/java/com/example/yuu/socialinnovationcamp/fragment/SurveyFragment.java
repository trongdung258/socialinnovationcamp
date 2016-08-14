package com.example.yuu.socialinnovationcamp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.androidadvance.androidsurvey.SurveyActivity;
import com.example.yuu.socialinnovationcamp.R;
import com.example.yuu.socialinnovationcamp.base.BaseFragment;
import com.gc.materialdesign.views.Button;

import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;

/**
 * Created by huylv on 14-Aug-16.
 */
public class SurveyFragment extends BaseFragment {
    private static final int SURVEY_REQUEST = 1337;
    @Bind(R.id.start_survey_bt)
    Button start_survey_bt;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        start_survey_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_survey = new Intent(getActivity(), SurveyActivity.class);
                i_survey.putExtra("json_survey", loadSurveyJson("example_survey_1.json"));
                startActivityForResult(i_survey, SURVEY_REQUEST);
            }
        });
    }

    private String loadSurveyJson(String filename) {
        try {
            InputStream is = getActivity().getApplicationContext().getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SURVEY_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {

                String answers_json = data.getExtras().getString("answers");
                Log.d("****", "****************** WE HAVE ANSWERS ******************");
                Log.v("ANSWERS JSON", answers_json);
                Log.d("****", "*****************************************************");

                //do whatever you want with them...
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_survey;
    }
}
