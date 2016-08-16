package com.example.yuu.socialinnovationcamp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yuu.socialinnovationcamp.Adapter.BaseTabAdapter;
import com.example.yuu.socialinnovationcamp.R;
import com.example.yuu.socialinnovationcamp.base.BaseActivityToolbar;
import com.example.yuu.socialinnovationcamp.customview.SlidingTabLayout;
import com.example.yuu.socialinnovationcamp.fragment.ChatFragment;
import com.example.yuu.socialinnovationcamp.fragment.ContactFragment;
import com.example.yuu.socialinnovationcamp.fragment.ProfileFragment;
import com.example.yuu.socialinnovationcamp.fragment.SelfHelpFragment;
import com.example.yuu.socialinnovationcamp.fragment.SurveyFragment;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;

public class MainActivity extends BaseActivityToolbar {

    public ChatFragment homeFragment;
    public ProfileFragment profileFragment;
    public SelfHelpFragment selfHelpFragment;
    public SurveyFragment surveyFragment;
    public ContactFragment contactFragment;

    private BaseTabAdapter mAdapter;
    private ViewPager mViewPager;

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

        homeFragment = new ChatFragment();
        profileFragment = new ProfileFragment();
        selfHelpFragment = new SelfHelpFragment();
        surveyFragment = new SurveyFragment();
        contactFragment = new ContactFragment();

        mAdapter = new BaseTabAdapter(this);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setTitle(mAdapter.getItemTitle(position));
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initViews(MENU_TYPE.TAB_IMAGE);
    }

    private void initViews(MENU_TYPE type) {
        mAdapter.setMenuType(type);

        SlidingTabLayout mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        if (type == MENU_TYPE.TAB_IMAGE) {
            mSlidingTabLayout.setCustomTabView(R.layout.tab_img_layout, R.id.tab_name_img);
        } else if (type == MENU_TYPE.TAB_TEXT) {
            mSlidingTabLayout.setCustomTabView(R.layout.tab_txt_layout, R.id.tab_name_txt);
        }

        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.WHITE;
            }

        });
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

    public enum MENU_TYPE {
        TAB_IMAGE,
        TAB_TEXT
    }

}
