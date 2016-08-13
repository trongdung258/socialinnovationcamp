package com.example.yuu.socialinnovationcamp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.Profile;

/**
 * Created by huylv on 13-Aug-16.
 */
public class HomeFragment extends Fragment {
    private AccessTokenTracker accessTokenTracker;
    private AccessToken accessToken;

    Button fb_messenger;
    TextView tvName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container,false);

        tvName = (TextView)v.findViewById(R.id.section_label);
        fb_messenger = (Button)v.findViewById(R.id.fb_messenger);

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }
        };


        Log.e("cxz","user:"+ Profile.getCurrentProfile().getName());
        tvName.setText("Hello::"+Profile.getCurrentProfile().getName());





        fb_messenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String YOUR_APP_ID = getResources().getString(R.string.facebook_app_id);
//                String mimeType = "image/*";
//
//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.setPackage("com.facebook.orca");
//                intent.setType(mimeType);
//                intent.putExtra(EXTRA_PROTOCOL_VERSION, PROTOCOL_VERSION);
//                intent.putExtra(EXTRA_APP_ID, YOUR_APP_ID);
//                startActivityForResult(intent, SHARE_TO_MESSENGER_REQUEST_CODE);

                String userId = "158155621008618";
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("fb://messaging/" + userId)));

            }
        });

        return v;
    }

    private static final String EXTRA_PROTOCOL_VERSION = "com.facebook.orca.extra.PROTOCOL_VERSION";
    private static final String EXTRA_APP_ID = "com.facebook.orca.extra.APPLICATION_ID";
    private static final int PROTOCOL_VERSION = 20150314;
//    private static final String YOUR_APP_ID = this.getResources().getString(R.string.facebook_app_id);
    private static final int SHARE_TO_MESSENGER_REQUEST_CODE = 1;
}
