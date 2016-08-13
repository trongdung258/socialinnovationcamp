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
import android.widget.RatingBar;

import com.example.yuu.socialinnovationcamp.DataBase.DiaryDataBaseHelper;
import com.example.yuu.socialinnovationcamp.Model.Diary;
import com.example.yuu.socialinnovationcamp.R;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class RatingFeelingFragment extends DialogFragment {
    private int mood;
    private RatingBar ratingBar;
    private Button btnChoose;
    private DiaryDataBaseHelper diaryDataBaseHelper;

    public RatingFeelingFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rating_feeling, container, false);
        init(view);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle("Chọn mức tâm trạng của bạn hiện tại !");
        return dialog;
    }

    private void init(View view)
    {
        ratingBar = (RatingBar)view.findViewById(R.id.rating_mood);
        btnChoose = (Button)view.findViewById(R.id.btn_choose);
        ratingBar.setMax(5);
        diaryDataBaseHelper = new DiaryDataBaseHelper(getActivity(),DiaryDataBaseHelper.DB_NAME,null,DiaryDataBaseHelper.DATABASE_VERSION);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = ratingBar.getNumStars();
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
                Date currentLocalTime = cal.getTime();
                DateFormat date = new DateFormat();
                cal.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
                String curentTime = (String) date.format("dd/MM/yyyy",currentLocalTime);
                diaryDataBaseHelper.addMood(curentTime,mood);
                dismiss();
            }
        });
    }
}
