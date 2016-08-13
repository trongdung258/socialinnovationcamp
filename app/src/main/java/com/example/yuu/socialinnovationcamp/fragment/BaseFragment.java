package com.example.yuu.socialinnovationcamp.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by Yuu on 8/2/2016.
 */
public class BaseFragment extends Fragment {

    public interface ScreenManagerHolder
    {
        ScreenManager getScreenManager();
    }


    public ScreenManager screenManager(){
        ScreenManagerHolder screenManagerHolder = (ScreenManagerHolder) this.getActivity();
        if(screenManagerHolder== null)return null;
        return screenManagerHolder.getScreenManager();
    }
}
