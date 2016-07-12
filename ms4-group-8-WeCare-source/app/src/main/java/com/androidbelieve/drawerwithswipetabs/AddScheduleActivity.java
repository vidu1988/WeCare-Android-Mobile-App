package com.androidbelieve.drawerwithswipetabs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddScheduleActivity extends AppCompatActivity {

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        setCurrentDateOnView();
        addListenerOnButtonDate();

        setCurrentTimeOnView();
        addListenerOnButtonTime();

        Bundle extras = getIntent().getExtras();
        final String userId = extras.getString("UserId");
        final DBHelper helper = new DBHelper(this);

        Button buttonUpdateSchedule = (Button) findViewById(R.id.btnUpdateSchedule);
        buttonUpdateSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add code to add this date and time to VounteerDuty table
                //insertVolunteerDuty(String email,String date, String time) from DBHelper class
                TextView txtDate = (TextView)findViewById(R.id.tvDate);
                TextView txtTime = (TextView)findViewById(R.id.tvTime);
               // Log.v("Vidushi:update", userId);
                helper.insertVolunteerDuty(userId,txtDate.getText().toString(),txtTime.getText().toString());
                Toast.makeText(getApplicationContext(),"Information Saved", Toast.LENGTH_SHORT).show();


            }
        });
        /*

        Button buttonAddMoreSchedule = (Button)findViewById(R.id.btnAddMoreSchedule);
        buttonAddMoreSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Come back to same activity, i.e. AddScheduleActivity. (MAY BE WE DON'T WANT THIS BUTTON)
                Intent myIntent = new Intent(AddScheduleActivity.this, AddScheduleActivity.class);
                startActivity(myIntent);
            }
        });*/

        Button buttonDoneSchedule = (Button)findViewById(R.id.btnDoneAddingSchedule);
        if (buttonDoneSchedule != null) {
            buttonDoneSchedule.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Intent myIntent = new Intent(AddScheduleActivity.this, AddScheduleActivity.this);
                   // startActivity(myIntent);

                    finish();
                }
            });
        }


    }

    public void setCurrentDateOnView() {

        tvDisplayDate = (TextView) findViewById(R.id.tvDate);
        dpResult = (DatePicker) findViewById(R.id.datePicker);

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

        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);

        btnChangeDate.setOnClickListener(new OnClickListener() {

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

        tvDisplayTime = (TextView) findViewById(R.id.tvTime);
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);

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

        btnChangeTime = (Button) findViewById(R.id.btnChangeTime);

        btnChangeTime.setOnClickListener(new OnClickListener() {

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
