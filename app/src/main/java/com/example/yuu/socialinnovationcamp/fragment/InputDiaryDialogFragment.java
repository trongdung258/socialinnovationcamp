package com.example.yuu.socialinnovationcamp.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yuu.socialinnovationcamp.Adapter.ListDiaryAdapter;
import com.example.yuu.socialinnovationcamp.DataBase.DiaryDataBaseHelper;
import com.example.yuu.socialinnovationcamp.Model.Diary;
import com.example.yuu.socialinnovationcamp.R;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 */
public class InputDiaryDialogFragment extends DialogFragment implements View.OnClickListener {
    private EditText edtContent;
    private Button btnSend;
    private Button btnSave;
    private Button btnCancel;
    private DiaryDataBaseHelper diaryDataBaseHelper;

    public InputDiaryDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input_diary_dialog, container, false);
        init(view);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    private void init(View view) {
        edtContent = (EditText) view.findViewById(R.id.edt_input_content);
        btnSend = (Button) view.findViewById(R.id.btn_send);
        btnSave = (Button) view.findViewById(R.id.btn_save);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        btnSend.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        diaryDataBaseHelper = new DiaryDataBaseHelper(getActivity(), DiaryDataBaseHelper.DB_NAME,
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
