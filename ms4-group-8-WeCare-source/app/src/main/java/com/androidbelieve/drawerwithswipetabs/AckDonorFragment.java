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
public class AckDonorFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private EditText recipient;
    private EditText subject;
    private EditText body;
    private Context mycontext;
    private Spinner spinner;
    List<String> DonorList = new ArrayList<>();
    String[] DonorArray;
    // String[] values = { "shruti.gottumukkala@gmail.com", "volunteer2@gmail.com", "volunteer3@gmail.com", "volunteer4@gmail.com", "volunteer5@gmail.com", "volunteer6@gmail.com", "volunteer7@gmail.com", "volunteer8@gmail.com" };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.ack_donor_layout, container, false);

        DBHelper helper = new DBHelper(getActivity());
        Cursor cursor = helper.getDonorId();
        //String email, fName = "";
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    //email = cursor.getString(0);
                    DonorList.add(cursor.getString(0));

                } while (cursor.moveToNext());

            }
        }
        DonorArray = new String[DonorList.size()];
        DonorArray = DonorList.toArray(DonorArray);
        spinner = (Spinner) v.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, DonorArray);
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
        // Toast.makeText(getActivity().getApplicationContext(), "Position: " + position + ", Data: " + DonorArray[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getActivity().getApplicationContext(), "Nothing selected", Toast.LENGTH_SHORT).show();
    }
}
