package com.androidbelieve.drawerwithswipetabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class FindCurrentLocation extends Activity {

    public static final String EXTRA_CAR_ID = "com.my.application.FindCurrentLocation.EXTRA_CAR_ID";

    TextView tvAddress;
    AppLocationService appLocationService;
    RadioButton rbGPS, rbManual;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_current_location);

        rbGPS = (RadioButton) findViewById(R.id.radioGPS);
        rbManual = (RadioButton) findViewById(R.id.radioManually);
        tvAddress = (TextView) findViewById(R.id.txtLocation);


        rbGPS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                appLocationService = new AppLocationService(FindCurrentLocation.this);
             //   tvAddress.setEnabled(false);
                tvAddress.setEnabled(false);
                Location location = appLocationService.getLocation(LocationManager.GPS_PROVIDER);

                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LocationAddress locationAddress = new LocationAddress();
                    locationAddress.getAddressFromLocation(latitude, longitude,
                            getApplicationContext(), new GeocoderHandler());
                } else {
                    showSettingsAlert();
                }

            }
        });

        rbManual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
            Toast.makeText(getApplicationContext(), "Enter Your Address", Toast.LENGTH_SHORT).show();
                tvAddress.setText("");
            tvAddress.setEnabled(true);
        }
        });

        btnSave = (Button)findViewById(R.id.btnSaveLocation);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                EditText loc = (EditText) findViewById(R.id.txtLocation);

                // Save the address obtained from above textbox and pass it to DonateFragment Activity.
                Toast.makeText(getApplicationContext(), "Location Saved", Toast.LENGTH_SHORT).show();

                Intent i = new Intent();
                // Throw in some identifier
                i.putExtra(EXTRA_CAR_ID, loc.getText().toString());
                // Set the result with this data, and finish the activity
                setResult(RESULT_OK, i);
                finish();

            }
        });


        }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                FindCurrentLocation.this);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        FindCurrentLocation.this.startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            tvAddress.setText(locationAddress);
        }
    }

    }

