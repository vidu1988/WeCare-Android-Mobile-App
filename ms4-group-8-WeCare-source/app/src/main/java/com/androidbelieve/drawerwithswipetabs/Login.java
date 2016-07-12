package com.androidbelieve.drawerwithswipetabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    Button bLogin;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bLogin = (Button) findViewById(R.id.bLogin);

        bLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                String email = etEmail.getText().toString();
                EditText etPassword = (EditText) findViewById(R.id.etPassword);
                String password = etPassword.getText().toString();
                String usertype;
                //String pwd = helper.searchPassword(email);

                String[] userdetails = new String[4];
                userdetails = helper.searchPassword(email);

                if(password.equals(userdetails[0])) {
                    //Check if usertype is charity
                    if (userdetails[3].equals("Charity")) {
                        message = "Welcome to "+userdetails[3]+" Home page!!";
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity_Charity.class);
                        String userId = email;
                        i.putExtra("UserId", userId);
                        startActivity(i);
                    }
                    //Check if usertype is Donor/Volunteer
                    else {

                        message = "Welcome to "+userdetails[3]+" Home page!!";
                        // //Toast.makeText(getApplicationContext(), userdetails[1], Toast.LENGTH_LONG).show();
                        //Toast.makeText(getApplicationContext(), userdetails[2], Toast.LENGTH_LONG).show();
                        //Toast.makeText(getApplicationContext(), userdetails[3], Toast.LENGTH_LONG).show();
                        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), DonorVolunteerMainActivity.class);
                        String userId = email;
                        i.putExtra("UserId", userId);
                        startActivity(i);
                        finish();
                    }
                }
                else
                {
                    message = "Login details are incorrect";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    return;
                }

            }
        });

    }
}



