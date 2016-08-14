package com.example.yuu.socialinnovationcamp.fragment;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;

import com.example.yuu.socialinnovationcamp.DataBase.DiaryDataBaseHelper;
import com.example.yuu.socialinnovationcamp.Model.Diary;
import com.example.yuu.socialinnovationcamp.R;
import com.gc.materialdesign.views.Button;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 */
public class InputDiaryDialog extends Dialog implements View.OnClickListener {
    private EditText edtContent;
    private Button btnSend;
    private Button btnSave;
    private Button btnCancel;
    private DiaryDataBaseHelper diaryDataBaseHelper;

    public InputDiaryDialog(Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_input_diary_dialog);

        setTitle("Input diary");

        edtContent = (EditText) findViewById(R.id.edt_input_content);
        btnSend = (Button) findViewById(R.id.btn_send);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnSend.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        diaryDataBaseHelper = new DiaryDataBaseHelper(getOwnerActivity(), DiaryDataBaseHelper.DB_NAME,
                null, DiaryDataBaseHelper.DATABASE_VERSION);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                clickSend();
                break;
            case R.id.btn_save:
                clickSave();
                break;
            case R.id.btn_cancel:
                clickCancel();
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
        String curentTime = (String) date.format("dd/MM/yyyy",currentLocalTime);
        diaryDataBaseHelper.addDiary(new Diary(content,curentTime));
        dismiss();
    }

    private void clickCancel() {
        dismiss();
    }
}
