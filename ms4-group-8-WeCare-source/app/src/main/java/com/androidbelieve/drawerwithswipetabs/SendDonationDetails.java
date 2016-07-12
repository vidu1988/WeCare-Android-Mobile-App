package com.androidbelieve.drawerwithswipetabs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by shruti on 5/21/16.
 */
public class SendDonationDetails extends AppCompatActivity  {

    private EditText recipient;
    private EditText subject;
    private EditText body;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_donation_layout);

        Bundle extras = getIntent().getExtras();
        final String newString= extras.getString("emailId");
        recipient=(EditText) findViewById(R.id.email);
        recipient.setText(newString);
        subject = (EditText) findViewById(R.id.subject);
        body = (EditText) findViewById(R.id.body);
        Button sendBtn = (Button) findViewById(R.id.sendEmail);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail(newString);
                // after sending the email, clear the fields
                //recipient.setText("");
                subject.setText("");
                body.setText("");
            }
        });

    }

    protected void sendEmail(String email) {

        String charityEmail=email;
        String sub = subject.getText().toString().trim();
        String message = body.getText().toString().trim();

        //Creating SendMail object
        SendMail sm = new SendMail(this,charityEmail,sub,message);

        //Executing sendmail to send email
        sm.execute();

    }
}
