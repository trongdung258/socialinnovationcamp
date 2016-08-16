package com.example.yuu.socialinnovationcamp.dialog;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.example.yuu.socialinnovationcamp.DataBase.DiaryDataBaseHelper;
import com.example.yuu.socialinnovationcamp.Model.Diary;
import com.example.yuu.socialinnovationcamp.R;
import com.example.yuu.socialinnovationcamp.activity.ExerciseActivity;
import com.gc.materialdesign.views.Button;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 */
public class InputDiaryDialog extends Dialog implements View.OnClickListener {
    private EditText edtContent;
    private Button btnSave;
    private Button btnCancel;
    private DiaryDataBaseHelper diaryDataBaseHelper;
    private ExerciseActivity ownerActivity;

    public InputDiaryDialog(ExerciseActivity exerciseActivity) {
        super(exerciseActivity);
        this.ownerActivity = exerciseActivity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_input_diary_dialog);

        edtContent = (EditText) findViewById(R.id.edt_input_content);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        diaryDataBaseHelper = new DiaryDataBaseHelper(this.getContext(), DiaryDataBaseHelper.DB_NAME,
                null, DiaryDataBaseHelper.DATABASE_VERSION);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                dismiss();
                ownerActivity.finish();
                break;
            case R.id.btn_cancel:
                ownerActivity.finish();
//                clickCancel();
                break;
        }
    }

    private void clickSend() {
        clickSave();
        dismiss();
    }

    private void clickSave() {
        String content = edtContent.getText().toString();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new DateFormat();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
        String curentTime = (String) date.format("dd/MM/yyyy", currentLocalTime);
        Log.d("Diary", "Time " + curentTime + " Content " + content);
        diaryDataBaseHelper.addDiary(new Diary(content, curentTime));
        dismiss();
    }

    private void clickCancel() {
        dismiss();
    }
}
