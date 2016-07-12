package com.androidbelieve.drawerwithswipetabs;//


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FBSelectUserType extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    Button user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firsttime_select_user_fb);

        user = (Button) findViewById(R.id.user);

                // TODO Auto-generated method stub
        user.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

        final String email;
        Bundle extras = getIntent().getExtras();
        email= extras.getString("email");

                String usertype = "";

                RadioGroup radioUsertype = (RadioGroup) findViewById(R.id.usertype);
                RadioButton rbUser ;



                // get selected radio button from radioGroup
                int selectedId = radioUsertype.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                rbUser = (RadioButton) findViewById(selectedId);
                usertype = rbUser.getText().toString();

        Log.v("Kanika",  "select user type");

              if("charity".equals(usertype))
                {
                    Log.v("Kanika",  "usertype selected charity");
                    User user = new User();
                    user.setFirstName("");
                    user.setLastName("");
                    user.setEmail(email);
                    user.setPassword("");

                    user.setPhonenumber("");
                    user.setAdress("");
                    user.setUserType(usertype);

                    helper.insertUser(user);

                    // intent to charity
                    Intent i = new Intent(getApplicationContext(),MainActivity_Charity.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Log.v("Kanika",  "usertype selected donor");
                    User user = new User();
                    user.setFirstName("");
                    user.setLastName("");
                    user.setEmail(email);
                    user.setPassword("");

                    user.setPhonenumber("");
                    user.setAdress("");
                    user.setUserType(usertype);

                    helper.insertUser(user);


                  //intent to donor

                    Intent i = new Intent(getApplicationContext(),DonorVolunteerMainActivity.class);
                    i.putExtra("UserId", email);
                    startActivity(i);
                    finish();
                }
            }
        });
    }


            }


