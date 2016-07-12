package com.androidbelieve.drawerwithswipetabs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ForgotPassword extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    Button bForgot;
    private EditText forgotEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        forgotEmail = (EditText) findViewById(R.id.forgetPwdEmail);
        bForgot = (Button) findViewById(R.id.bLogin);

        //  mycontext = this.getContext();

        bForgot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
               // Toast.makeText(getApplicationContext(), "bye", Toast.LENGTH_LONG).show();
                String email = forgotEmail.getText().toString().trim();
                //retrieve pwd from database

                String retrievedPwd = helper.searchPasswordForEmail(email);

                //Toast.makeText(getApplicationContext(), retrievedPwd, Toast.LENGTH_LONG).show();

                sendPwdViaEmail(email,retrievedPwd);
                forgotEmail.setText("");
                Toast.makeText(getApplicationContext(), "Password Sent on your email!", Toast.LENGTH_LONG).show();

            }
        });


    }

    protected void sendPwdViaEmail(String email,String retrievedPwd) {

        //String email = recipient.getText().toString().trim();
        String sub = "Your Password!";
        //  String message = body.getText().toString().trim();

        //Creating SendMail object
        SendMail sm = new SendMail(this,email,sub,retrievedPwd);

        //Executing sendmail to send email
        sm.execute();
    }
}
