package com.androidbelieve.drawerwithswipetabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    Button bRegister;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                EditText etFirstName = (EditText) findViewById(R.id.etFirstName);
                EditText etLastName = (EditText) findViewById(R.id.etLastName);
                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                EditText etPassword = (EditText) findViewById(R.id.etPassword);
                EditText etPassword2 = (EditText) findViewById(R.id.etPassword2);

                EditText etPhoneNmuber = (EditText) findViewById(R.id.etPhoneNmuber);
                EditText etAddress = (EditText) findViewById(R.id.etAddress);


                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String password2 = etPassword2.getText().toString();
                String phonenumber = etPhoneNmuber.getText().toString();
                String address = etAddress.getText().toString();

                String usertype = "";

                RadioGroup radioUsertype = (RadioGroup) findViewById(R.id.usertype);
                RadioButton rbUser ;


                if(etFirstName == null || etFirstName.getText().toString().trim().isEmpty() || etLastName == null || etLastName.getText().toString().trim().isEmpty() || etEmail == null || etEmail.getText().toString().trim().isEmpty() || etPassword == null || etPassword.getText().toString().trim().isEmpty() || etPassword2 == null || etPassword2.getText().toString().trim().isEmpty() || etPhoneNmuber == null || etPhoneNmuber.getText().toString().trim().isEmpty() ||  etAddress == null || etAddress.getText().toString().trim().isEmpty()) {
                    message = "Please enter all the details to register.";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    return;
                }

                // get selected radio button from radioGroup
                int selectedId = radioUsertype.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                rbUser = (RadioButton) findViewById(selectedId);
                usertype = rbUser.getText().toString();


                Toast.makeText(getApplicationContext(), usertype, Toast.LENGTH_LONG).show();

                if(! password2.equals(password))
                {
                    message = "Passwords do not match";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    User user = new User();
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                    user.setPassword(password);

                    user.setPhonenumber(phonenumber);
                    user.setAdress(address);
                    user.setUserType(usertype);

                    helper.insertUser(user);
                    message = "You are successfully registered with We Care! Proceed to login.";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getApplicationContext(),Login.class);
                    startActivity(i);
                    finish();
                }


            }
        });
    }


}
