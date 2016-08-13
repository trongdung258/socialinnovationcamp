package com.example.yuu.socialinnovationcamp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yuu.socialinnovationcamp.base.BaseActivityToolbar;
import com.example.yuu.socialinnovationcamp.customview.SlidingTabLayout;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;

public class MainActivity extends BaseActivityToolbar {

    private String[] mTabItem;
    private ViewPager mViewPager;
    HomeFragment homeFragment;
    private SlidingTabLayout mSlidingTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext(), new FacebookSdk.InitializeCallback() {
            @Override
            public void onInitialized() {
                if(AccessToken.getCurrentAccessToken() == null){
                    Log.e("Cxz","login again");
                    MainActivity.this.finish();
                    Intent i  = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(i);
                } else {
                    Log.e("cxz","logged in");
                }
            }
        });

        homeFragment = new HomeFragment();

        mTabItem = getResources().getStringArray(R.array.sliding_tab_items);


        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager()));

        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class SampleFragmentPagerAdapter extends FragmentPagerAdapter implements SlidingTabLayout.TabIconProvider{

        private final int iconRes[]={
            R.drawable.ic_profile,
                R.drawable.ic_exercise,
                R.drawable.ic_chat,
                R.drawable.ic_survey,
                R.drawable.ic_contacts
        };

        SampleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int i) {
            switch (i){
                case 0:
                    return homeFragment;
            }
            return new HomeFragment();
        }

        @Override
        public int getCount() {
            return mTabItem.length;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }


        @Override
        public int getPageIconResId(int position) {
            return iconRes[position];
        }
    }
}
