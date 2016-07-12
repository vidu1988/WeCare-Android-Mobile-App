package com.androidbelieve.drawerwithswipetabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;

//import com.facebook.FacebookCallback;
//import com.facebook.FacebookException;
//import com.facebook.FacebookSdk;
//import com.facebook.login.LoginResult;
//import com.facebook.login.widget.LoginButton;


/**
 * Created by kgupta1 on 5/10/2016.
 */
public class SocialLogin extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    private Button  signupButton, loginButton;
    private LoginButton fbloginButton;


    private TextView forgotPwdLink;
    private CallbackManager callbackManager;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.social_login_activity);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add( "public_profile");
        arrayList.add( "email");
        //  fbloginButton.setReadPermissions(arrayList.asList(                "public_profile", "email", "user_birthday", "user_friends"));





        //  fbButton = (Button) findViewById(R.id.fbButton);
        signupButton = (Button) findViewById(R.id.signupButton);
        loginButton = (Button) findViewById(R.id.loginButton);

        forgotPwdLink = (TextView) findViewById(R.id.tvForgotPasswordLink);

        // info = (TextView)findViewById(R.id.info);
        fbloginButton = (LoginButton)findViewById(R.id.login_button);
        fbloginButton.setReadPermissions(arrayList);

        // Callback registration
        fbloginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                Log.v("LoginActivity", response.toString());

                                JSONObject temp = response.getJSONObject();

                                try {

                                    String email = temp.getString("email"); // 01/31/1980 format
                                    String name = temp.getString("name");

                                    Log.v("Kanika",  email);
                                    Log.v("email",  name);

                                    /*DB*/
                                    //String pwd = helper.searchPassword(email);

                                    String usertype = "";
                                    usertype = helper.searchEmail(email);

                                    if(usertype == null || usertype.trim().length() == 0) {
                                        //first time social logged in .. select user type and insert to db

                                        Log.v("Kanika",  "usertype null");

                                        Intent i = new Intent(getApplicationContext(),FBSelectUserType.class);
                                        i.putExtra("email", email);
                                        startActivity(i);


                                    }
                                    else
                                    {
                                        //redirect to the perticular page
                                        if("charity".equals(usertype))
                                        {
                                            Log.v("Kanika",  "usertype charity");
                                             // intent to charity
                                            Intent i = new Intent(getApplicationContext(),MainActivity_Charity.class);
                                            startActivity(i);
                                            finish();
                                        }
                                        else
                                        {
                                            Log.v("Kanika",  "usertype donor");
                                            //intent to donor

                                            Intent i = new Intent(getApplicationContext(),DonorVolunteerMainActivity.class);
                                            i.putExtra("UserId", email);
                                            startActivity(i);
                                            finish();
                                        }
                                    }



                                    /**/

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                //String email =   parameters.get("id").toString();
                //  Log.v("email ", email);
                request.setParameters(parameters);
                //  request.get
                request.executeAsync();
            }


            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });




        forgotPwdLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),ForgotPassword.class);
                startActivity(i);
            }
        });

     /*   fbButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });*/

        signupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),Register.class);
                startActivity(i);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
