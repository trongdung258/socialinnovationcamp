package com.example.yuu.socialinnovationcamp.Adapter;

import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yuu.socialinnovationcamp.R;
import com.example.yuu.socialinnovationcamp.activity.MainActivity;
import com.example.yuu.socialinnovationcamp.customview.SlidingTabLayout;
import com.example.yuu.socialinnovationcamp.fragment.HomeFragment;

public class BaseTabAdapter extends FragmentPagerAdapter implements SlidingTabLayout.TabIconProvider {
    private static final String TAG = BaseTabAdapter.class.getSimpleName();

    private static final int iconRes[] = {
            R.drawable.ic_profile,
            R.drawable.ic_exercise,
            R.drawable.ic_chat,
            R.drawable.ic_survey,
            R.drawable.ic_contacts
    };
    private MainActivity mainActivity;

    private String iconTxt[];

    private MainActivity.MENU_TYPE menuType;

    public BaseTabAdapter(MainActivity activity) {
        super(activity.getSupportFragmentManager());
        mainActivity = activity;
        iconTxt = activity.getResources().getStringArray(R.array.sliding_tab_items);
    }

    public String getItemTitle(int position) {
        return iconTxt[position];
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mainActivity.profileFragment;
            case 1:
                return mainActivity.selfHelpFragment;
            case 2:
                return mainActivity.homeFragment;
            case 3:
                return mainActivity.surveyFragment;
        }
        return new HomeFragment();
    }

    @Override
    public int getCount() {
        if (menuType == MainActivity.MENU_TYPE.TAB_IMAGE) {
            return iconRes.length;
        } else {
            return iconTxt.length;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return iconTxt[position];
    }

    @Override
    public int getPageIconResId(int position) {
        return iconRes[position];
    }

    public void setMenuType(MainActivity.MENU_TYPE menuType) {
        this.menuType = menuType;
    }
}

