package com.example.yuu.socialinnovationcamp.fragment;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuu.socialinnovationcamp.DataBase.DiaryDataBaseHelper;
import com.example.yuu.socialinnovationcamp.R;


public class DiaryDialogFragment extends DialogFragment {

    private String time;
    private DiaryDataBaseHelper diaryDataBaseHelper;
    private TextView txtContent;

    public static DiaryDialogFragment create(String time) {
        DiaryDialogFragment diaryDialogFragment = new DiaryDialogFragment();
        diaryDialogFragment.time = time;
        return diaryDialogFragment;
    }

    public DiaryDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diary_dialog, container, false);
        init(view);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle(time);
        return dialog;
    }

    private void init(View view) {
        txtContent = (TextView) view.findViewById(R.id.txt_content_of_diary);
        diaryDataBaseHelper = new DiaryDataBaseHelper(getActivity(), DiaryDataBaseHelper.DB_NAME
                , null, DiaryDataBaseHelper.DATABASE_VERSION);
        txtContent.setText(diaryDataBaseHelper.contentDiary(time));
    }

}
