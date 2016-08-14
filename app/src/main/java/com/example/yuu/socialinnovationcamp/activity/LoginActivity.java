package com.example.yuu.socialinnovationcamp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.yuu.socialinnovationcamp.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("public_profile", "email");
        // If using in a fragment
//        loginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                requestUserProfile(loginResult);

                Log.e("cxz","login done");
                LoginActivity.this.finish();
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);

            }

            @Override
            public void onCancel() {
                // App code
                Log.e("cxz","on cancel");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.e("cxz","error:"+exception.toString());
            }
        });
    }

    public void requestUserProfile(LoginResult loginResult) {
        GraphRequest.newMeRequest(
                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject me, GraphResponse response) {
                        if (response.getError() != null) {
                            // handle error
                        } else {
                            try {
                                String email = response.getJSONObject().get("email").toString();
                                Log.e("Result", email);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String id = me.optString("id");
                            // send email and id to your web server
                            Log.e("Result1", response.getRawResponse());
                            Log.e("Result", me.toString());
                        }
                    }
                }).executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}

