package com.androidbelieve.drawerwithswipetabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DonateFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    String location;
    String date;
    String time;
    String charity;
    String user;
    private EditText subject;
    private EditText body;
    private Context mycontext;

    private static final int REQUEST_CODE_GET_LOCATION = 1;
    private static final int REQUEST_CODE_GET_DONOR_DATE_TIME = 2;

    String[] charityArray;


    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private Uri fileUri = null;
    private static final String IMAGE_DIRECTORY_NAME = "Notes";
    private ImageView imageView;
    private TextView textPreview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        List<String> charityList = new ArrayList<>() ;

        View view = inflater.inflate(R.layout.donate_layout,container,false);

       imageView = (ImageView) view.findViewById(R.id.imageView);
        textPreview = (TextView)view.findViewById(R.id.textPreview);

        // Handles donor's location
        ImageButton locationButton = (ImageButton) view.findViewById(R.id.btnLocation);
        locationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startDonorLocationActivity();

            }
        });

        // handles Donor pickup date and time.
        ImageButton donorDateButton = (ImageButton) view.findViewById(R.id.btnDate);
        donorDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startDonorDateTimeActivity();

            }
        });

        // Takes picture of donations using camera
        ImageButton donorCameraButton = (ImageButton) view.findViewById(R.id.buttonFoodImage);
        donorCameraButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dispatchTakePictureIntent();

            }
        });

        DBHelper helper = new DBHelper(getActivity());
        Cursor cursor = helper.getCharityInfo();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {

                    charityList.add(cursor.getString(0));

                } while (cursor.moveToNext());

            }
        }

        //charityList = helper.getCharityInfo();
        charityArray = new String[charityList.size()];
        charityArray = charityList.toArray(charityArray);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<String>(
                        getActivity(), android.R.layout.simple_spinner_dropdown_item,
                        // android.R.layout.simple_list_item_1,
                        charityArray)
        );
        spinner.setOnItemSelectedListener(this);


        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        final DBHelper helper = new DBHelper(getActivity());

        // This is working
        Bundle extras = getActivity().getIntent().getExtras();
        final String user = extras.getString("UserId");

        final EditText txtWeight = (EditText) view.findViewById(R.id.editTextEnterWeight);
        final EditText txtClothes = (EditText) view.findViewById(R.id.editTextEnterNoOfClothes);



        // Save button which will insert all donation details into database
        Button saveDetailsButton = (Button) view.findViewById(R.id.sendDetails);
        saveDetailsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Getting total (weight) lbs
                String strWeight = txtWeight.getText().toString();
                int weight = Integer.parseInt(strWeight);

                // Getting total no. clothes
                String strClothesNum = txtClothes.getText().toString();
                int clothesNum = Integer.parseInt(strClothesNum);

                // Calculating points of donor
                final int points =  weight + clothesNum;

                final String donationUserId = user;
                final String donationLocation = location;
                final String donationTime = time;
                final String donationDate = date;
                final String donationCharity = charity;

                if(fileUri==null )
                {
                    Toast.makeText(getActivity().getApplicationContext(), "Click Image of your donation", Toast.LENGTH_SHORT).show();
                }

                helper.insertDonation(donationUserId, donationDate, donationTime, donationLocation, points, donationCharity, fileUri.toString());
                Toast.makeText(getActivity().getApplicationContext(), "Donations saved!", Toast.LENGTH_SHORT).show();
                startSendDonationActivity(donationCharity);

            }
        });


    }


    private void startDonorLocationActivity() {
        Intent i = new Intent(getActivity(), FindCurrentLocation.class);
        startActivityForResult(i, REQUEST_CODE_GET_LOCATION);
    }

    private void startDonorDateTimeActivity() {
        Intent i = new Intent(getActivity(), DonorDate.class);
        startActivityForResult(i, REQUEST_CODE_GET_DONOR_DATE_TIME);
    }

    private void startSendDonationActivity(String donationCharity) {

        Intent i = new Intent(getActivity().getApplicationContext(), SendDonationDetails.class);
        String emailId = donationCharity;
        i.putExtra("emailId", emailId);
        startActivity(i);
    }




    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {

            if(resultCode == Activity.RESULT_OK){
                location = data.getStringExtra(FindCurrentLocation.EXTRA_CAR_ID);
                Log.v("Shashi", location);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }

        }

        if (requestCode == 2) {

            if(resultCode == Activity.RESULT_OK){
                date = data.getStringExtra(DonorDate.EXTRA_DONOR_DATE_ID);
                time = data.getStringExtra(DonorDate.EXTRA_DONOR_TIME_ID);
                Log.v("Shashi",date);
                Log.v("Shashi",time);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }

        }
//new addition for camera
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                // Display image in image view (Successful Capture)
                previewCapturedImage();

            } else if (resultCode == Activity.RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getActivity().getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getActivity().getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
      //  Toast.makeText(getActivity().getApplicationContext(), "Position: " + position + ", Data: " + charityArray[position], Toast.LENGTH_SHORT).show();
        charity = charityArray[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getActivity().getApplicationContext(), "Nothing selected", Toast.LENGTH_SHORT).show();
    }

    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // Display image in image view (Successful Capture)
                previewCapturedImage();

            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }*/

    private void previewCapturedImage() {
        try{

            //final TouchDrawView view = (TouchDrawView) findViewById(R.id.myview);
            //final ImageView view = (ImageView) getActivity().getApplicationContext().findViewById(R.id.imageView);
            if (imageView == null) {
                Log.e("Vidushi", "we have a problem");
            }
            //imageView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            // Reducing size of image file to handle OutOfMemory Exception for large images
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);
            //comment
            // To create bitmap of size which has same width and height as of custom view.
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, imageView.getWidth(), imageView.getHeight(), true);
            imageView.setImageBitmap(scaledBitmap);

            //imageView.setImageBitmap(bitmap);
            textPreview.setVisibility(View.INVISIBLE);
            //view.setImageBitmap(bitmap);
        }

        catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

     public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Saving file url in bundle as it will be null on screen orientation changes
        outState.putParcelable("file_uri", fileUri);
    }


   /* @Override
     public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }*/



    private void dispatchTakePictureIntent() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    public Uri getOutputMediaFileUri(int type) {

        return Uri.fromFile(getOutputMediaFile(type));
    }

    private File getOutputMediaFile(int type) {

        // External sdcard location //sdcard/Pictures/Hello Camera
        File mediaStorageDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }


        String timeStamp = new SimpleDateFormat("yyyy_MMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        }
        else {
            return null;
        }

        return mediaFile;
    }




}











