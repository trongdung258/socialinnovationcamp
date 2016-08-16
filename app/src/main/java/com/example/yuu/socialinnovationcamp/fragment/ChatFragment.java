package com.example.yuu.socialinnovationcamp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

import com.example.yuu.socialinnovationcamp.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;

/**
 * Created by huylv on 13-Aug-16.
 */
public class ChatFragment extends Fragment {
    private static final String EXTRA_PROTOCOL_VERSION = "com.facebook.orca.extra.PROTOCOL_VERSION";
    private static final String EXTRA_APP_ID = "com.facebook.orca.extra.APPLICATION_ID";
    private static final int PROTOCOL_VERSION = 20150314;
    //    private static final String YOUR_APP_ID = this.getResources().getString(R.string.facebook_app_id);
    private static final int SHARE_TO_MESSENGER_REQUEST_CODE = 1;
    Button fb_messenger;
    private AccessTokenTracker accessTokenTracker;
    private AccessToken accessToken;
    private RatingBar ratingBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat, container, false);

        fb_messenger = (Button)v.findViewById(R.id.fb_messenger);
        ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);
        ratingBar.setRating(4.5f);
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }
        };


        fb_messenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = "112537652101392";
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("fb://messaging/" + userId)));

            }
        });

        return v;
    }
}
