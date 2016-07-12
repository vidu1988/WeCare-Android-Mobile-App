/*import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidbelieve.drawerwithswipetabs.DBHelper;
import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.SendMailFragment;

import java.util.ArrayList;
import java.util.List;

package com.androidbelieve.drawerwithswipetabs;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shruti on 5/21/16.
 */
/*
public class NotifyVolunteerFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    private EditText subject;
    private EditText body;
    private Context mycontext;
    private Spinner spinner;
    List<String> volunteerList = new ArrayList<>();
    String[] volunteerArray;
    // String[] values = { "shruti.gottumukkala@gmail.com", "volunteer2@gmail.com", "volunteer3@gmail.com", "volunteer4@gmail.com", "volunteer5@gmail.com", "volunteer6@gmail.com", "volunteer7@gmail.com", "volunteer8@gmail.com" };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.notify_volunteer_fragment, container, false);

        DBHelper helper = new DBHelper(getActivity());
        Cursor cursor = helper.getVolunteerId();
        //String email, fName = "";
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    //email = cursor.getString(0);
                    volunteerList.add(cursor.getString(0));

                } while (cursor.moveToNext());

            }
        }
        volunteerArray = new String[volunteerList.size()];
        volunteerArray = volunteerList.toArray(volunteerArray);
        spinner = (Spinner) v.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, volunteerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        mycontext=container.getContext();

        // recipient = (EditText) v.findViewById(R.id.spinner);
        subject = (EditText) v.findViewById(R.id.subject);
        body = (EditText) v.findViewById(R.id.body);
        Button sendBtn = (Button) v.findViewById(R.id.sendEmail);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
                // after sending the email, clear the fields
                //recipient.setText("");
                subject.setText("");
                body.setText("");
            }
        });
        spinner.setOnItemSelectedListener(this);
        return v;

    }

    protected void sendEmail() {

        String email = spinner.getSelectedItem().toString();
        String sub = subject.getText().toString().trim();
        String message = body.getText().toString().trim();

        //Creating SendMail object
        SendMailFragment sm = new SendMailFragment(mycontext,email,sub,message);

        //Executing sendmail to send email
        sm.execute();
    }

    public void onClick(View v) {
        sendEmail();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Toast.makeText(getActivity().getApplicationContext(), "Position: " + position + ", Data: " + volunteerArray[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getActivity().getApplicationContext(), "Nothing selected", Toast.LENGTH_SHORT).show();
    }
}*/


package com.androidbelieve.drawerwithswipetabs;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shruti on 5/21/16.
 */
public class NotifyVolunteerFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    private EditText subject;
    private EditText body;
    private Context mycontext;
    private Spinner spinner;
    List<String> volunteerList = new ArrayList<>();
    String[] volunteerArray;
    private String location;
    private String time;
    private String date;
    private String sub;
    // String[] values = { "shruti.gottumukkala@gmail.com", "volunteer2@gmail.com", "volunteer3@gmail.com", "volunteer4@gmail.com", "volunteer5@gmail.com", "volunteer6@gmail.com", "volunteer7@gmail.com", "volunteer8@gmail.com" };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.notify_volunteer_fragment, container, false);
        Bundle extras = getActivity().getIntent().getExtras();
        String newString= extras.getString("UserId");
        DBHelper helper = new DBHelper(getActivity());
        Cursor cursor = helper.getVolunteerId();
        //String email, fName = "";
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    //email = cursor.getString(0);
                    volunteerList.add(cursor.getString(0));

                } while (cursor.moveToNext());

            }
        }

        Cursor cursor1 = helper.getLastId();
        location=cursor1.getString(0);
        date=cursor1.getString(1);
        time=cursor1.getString(2);
        sub="You have a pickup at "+location+"\n at time "+time+"PM\n on the date "+date;
        body = (EditText) v.findViewById(R.id.body);
        body.setText(sub);


        //Bundle extras = getActivity().getIntent().getExtras();
        // final String location = extras.getString("location");
        volunteerArray = new String[volunteerList.size()];
        volunteerArray = volunteerList.toArray(volunteerArray);
        spinner = (Spinner) v.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, volunteerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        mycontext=container.getContext();

        // recipient = (EditText) v.findViewById(R.id.spinner);
        subject = (EditText) v.findViewById(R.id.subject);

        Button sendBtn = (Button) v.findViewById(R.id.sendEmail);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail(sub);
                // after sending the email, clear the fields
                //recipient.setText("");
                subject.setText("");
                body.setText("");
            }
        });
        spinner.setOnItemSelectedListener(this);
        return v;

    }

    protected void sendEmail(String subject) {

        String email = spinner.getSelectedItem().toString();
        String sub=subject;
        String message = body.getText().toString().trim();

        //Creating SendMail object
        SendMailFragment sm = new SendMailFragment(mycontext,email,sub,message);

        //Executing sendmail to send email
        sm.execute();
    }

    public void onClick(View v) {
        sendEmail(location);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Toast.makeText(getActivity().getApplicationContext(), "Position: " + position + ", Data: " + volunteerArray[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getActivity().getApplicationContext(), "Nothing selected", Toast.LENGTH_SHORT).show();
    }
}

