package com.androidbelieve.drawerwithswipetabs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class DonorDate extends AppCompatActivity {

    public static final String EXTRA_DONOR_DATE_ID = "com.my.application.DonorDate.EXTRA_DONOR_DATE_ID";
    public static final String EXTRA_DONOR_TIME_ID = "com.my.application.DonorDate.EXTRA_DONOR_TIME_ID";

    private int year;
    private int month;
    private int day;
    static final int DATE_DIALOG_ID = 999;
    private TextView tvDisplayDate;
    private DatePicker dpResult;
    private Button btnChangeDate;

    private TextView tvDisplayTime;
    private TimePicker timePicker1;
    private Button btnChangeTime;
    private int hour;
    private int minute;
    static final int TIME_DIALOG_ID = 998;

    Button btnSaveDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_date);

        setCurrentDateOnView();
        addListenerOnButtonDate();

        setCurrentTimeOnView();
        addListenerOnButtonTime();

        btnSaveDateTime = (Button)findViewById(R.id.btnDonorTimeSave);
        btnSaveDateTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                TextView date = (TextView) findViewById(R.id.tvDonationDate);
                TextView time = (TextView) findViewById(R.id.tvDonationTime);

                // Save the date/time obtained from above textbox and pass it to DonateFragment Activity.
                Toast.makeText(getApplicationContext(), "Date/Time details saved", Toast.LENGTH_SHORT).show();

                /*Intent i = new Intent(getApplicationContext(), DonorVolunteerMainActivity.class);
                String donorDate = date.getText().toString();
                String donorTime = time.getText().toString();
                i.putExtra("Date", donorDate);
                i.putExtra("Time", donorTime);
                startActivity(i);

                //finish();*/

                Intent in = new Intent();
                // Throw in some identifier
                in.putExtra(EXTRA_DONOR_DATE_ID, date.getText().toString());
                in.putExtra(EXTRA_DONOR_TIME_ID, time.getText().toString());
                // Set the result with this data, and finish the activity
                setResult(RESULT_OK, in);
                finish();

            }
        });
    }

    public void setCurrentDateOnView() {

        tvDisplayDate = (TextView) findViewById(R.id.tvDonationDate);
        dpResult = (DatePicker) findViewById(R.id.donorDatePicker);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        // set current date into datepicker
        dpResult.init(year, month, day, null);

    }

    public void addListenerOnButtonDate() {

        btnChangeDate = (Button) findViewById(R.id.btnChangeDonationDate);

        btnChangeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });

    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month,day);

            case TIME_DIALOG_ID:
                // set time picker as current time
                return new TimePickerDialog(this,
                        timePickerListener, hour, minute,false);

        }
        return null;
    }



    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            tvDisplayDate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

            // set selected date into datepicker also
            dpResult.init(year, month, day, null);

        }
    };

    public void setCurrentTimeOnView() {

        tvDisplayTime = (TextView) findViewById(R.id.tvDonationTime);
        timePicker1 = (TimePicker) findViewById(R.id.donorTimePicker);

        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        // set current time into textview
        tvDisplayTime.setText(
                new StringBuilder().append(pad(hour))
                        .append(":").append(pad(minute)));

        // set current time into timepicker
        timePicker1.setCurrentHour(hour);
        timePicker1.setCurrentMinute(minute);

    }
    public void addListenerOnButtonTime() {

        btnChangeTime = (Button) findViewById(R.id.btnChangeDonationTime);

        btnChangeTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(TIME_DIALOG_ID);

            }

        });

    }

    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {
                    hour = selectedHour;
                    minute = selectedMinute;

                    // set current time into textview
                    tvDisplayTime.setText(new StringBuilder().append(pad(hour))
                            .append(":").append(pad(minute)));

                    // set current time into timepicker
                    timePicker1.setCurrentHour(hour);
                    timePicker1.setCurrentMinute(minute);

                }
            };

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
}
