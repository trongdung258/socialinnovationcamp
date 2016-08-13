package com.example.yuu.socialinnovationcamp.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Yuu on 8/2/2016.
 */
public class ScreenManager {
    private FragmentManager fragmentManager;
    private int containerID ;

    public ScreenManager(FragmentManager fragmentManager, int containerID) {
        this.fragmentManager = fragmentManager;
        this.containerID = containerID;
    }
    //Repplace a fragment
    public void openFragment(Fragment fragment, boolean addtobackStack)
    {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerID,fragment);
        if(addtobackStack ){
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();
    }
    public boolean back()
    {
        if(fragmentManager.getBackStackEntryCount()>0)
        {
            fragmentManager.popBackStack();
            return true;
        }
        return false;
    }
}
