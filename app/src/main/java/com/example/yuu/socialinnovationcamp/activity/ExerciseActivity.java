package com.example.yuu.socialinnovationcamp.activity;

import android.annotation.TargetApi;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuu.socialinnovationcamp.R;
import com.example.yuu.socialinnovationcamp.base.BaseActivity;
import com.example.yuu.socialinnovationcamp.dialog.InputDiaryDialog;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;

/**
 * Created by huylv on 13-Aug-16.
 */
public class ExerciseActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    private final long interval = 1000;
    @Bind(R.id.exercise_timer_tv)
    TextView exercise_timer_tv;
    @Bind(R.id.exercise_description_tv)
    TextView exercise_description_tv;
    @Bind(R.id.exercise_icon_iv)
    ImageView exercise_icon_iv;
    @Bind(R.id.self_help_timer_pb)
    CircularProgressBar self_help_timer_pb;
    @Bind(R.id.self_help_check_point_rl)
    RelativeLayout self_help_check_point_rl;
    @Bind(R.id.self_help_exercise_rl)
    RelativeLayout self_help_exercise_rl;
    @Bind(R.id.exercise_checkbox_level3)
    AppCompatCheckBox exercise_checkbox_level3;
    @Bind(R.id.exercise_checkbox_level2)
    AppCompatCheckBox exercise_checkbox_level2;
    @Bind(R.id.exercise_checkbox_level1)
    AppCompatCheckBox exercise_checkbox_level1;
    @Bind(R.id.exercise_next_bt)
    Button exercise_next_bt;
    MyCountDownTimer myCountDownTimer;
    int currentTest = 0;
    private long startTime = 6000;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goToNextTest(currentTest);


        exercise_checkbox_level3.setOnCheckedChangeListener(this);
        exercise_checkbox_level2.setOnCheckedChangeListener(this);
        exercise_checkbox_level1.setOnCheckedChangeListener(this);

        exercise_next_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!exercise_checkbox_level1.isChecked() && !exercise_checkbox_level2.isChecked() && !exercise_checkbox_level3.isChecked()) {
                    Toast.makeText(ExerciseActivity.this, "Please select at least one!", Toast.LENGTH_SHORT).show();
                } else {
                    switch (currentTest) {
                        case 0:
                            currentTest += 1;
                            goToNextTest(currentTest);
                            break;
                        case 1:
                            currentTest += 1;
                            goToNextTest(currentTest);
                            break;
                        case 2:
                            InputDiaryDialog dialog = new InputDiaryDialog(ExerciseActivity.this);
                            dialog.show();
                            break;
                    }
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void goToNextTest(int stat) {

        if (stat == 2) {
            startTime = 10000;
            mediaPlayer = MediaPlayer.create(this, R.raw.relax_music);
            mediaPlayer.start();
        }

        self_help_exercise_rl.setVisibility(View.VISIBLE);
        self_help_check_point_rl.setVisibility(View.GONE);

        myCountDownTimer = new MyCountDownTimer(startTime, interval);
        myCountDownTimer.start();

        exercise_checkbox_level3.setChecked(false);
        exercise_checkbox_level2.setChecked(false);
        exercise_checkbox_level1.setChecked(false);

        switch (stat) {
            case 0:
                exercise_icon_iv.setImageDrawable(getDrawable(R.drawable.ic_person_walking));
                exercise_description_tv.setText("Stand up and sit down 10 times");
                break;
            case 1:
                exercise_icon_iv.setImageDrawable(getDrawable(R.drawable.ic_lemonade));
                exercise_description_tv.setText("Drink a glass of water");
                break;
            case 2:
                exercise_icon_iv.setImageDrawable(getDrawable(R.drawable.ic_listen_music));
                exercise_description_tv.setText("Listen to this song");
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_exercise;
    }

    private void goToCheckPoint() {

        self_help_exercise_rl.setVisibility(View.GONE);
        self_help_check_point_rl.setVisibility(View.VISIBLE);
        if (currentTest == 2) {
            exercise_next_bt.setText("Finish");
            if (mediaPlayer.isPlaying()) mediaPlayer.stop();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.exercise_checkbox_level3:
                if (b) {
                    exercise_checkbox_level2.setChecked(false);
                    exercise_checkbox_level1.setChecked(false);
                }
                break;
            case R.id.exercise_checkbox_level2:
                if (b) {
                    exercise_checkbox_level3.setChecked(false);
                    exercise_checkbox_level1.setChecked(false);
                }
                break;
            case R.id.exercise_checkbox_level1:
                if (b) {
                    exercise_checkbox_level2.setChecked(false);
                    exercise_checkbox_level3.setChecked(false);
                }
                break;
        }
    }


    public class MyCountDownTimer extends CountDownTimer {
        private static final String FORMAT = "%02d:%02d";

        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            goToCheckPoint();
        }


        @Override
        public void onTick(long millisUntilFinished) {
            exercise_timer_tv.setText("" + String.format(FORMAT,
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            Long progress = (long) ((float) millisUntilFinished / startTime * 100);
            self_help_timer_pb.setProgress((float) progress);
        }


    }
}
