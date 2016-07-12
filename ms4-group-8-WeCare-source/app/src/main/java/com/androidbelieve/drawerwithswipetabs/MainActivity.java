package com.androidbelieve.drawerwithswipetabs;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

//import com.facebook.FacebookSdk;

public class MainActivity extends AppCompatActivity {

    private ImageButton tab1Button, tab2Button, tab3Button, tab4Button;
    private TextView tab1Text, tab2Text, tab3Text, tab4Text, headingText, aboutus;


    //  private Color selectedColor, deselectedColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.app.ActionBar ab = getSupportActionBar();

        //new <code></code>
       // ab.setTitle("WeCare");
        ab.setSubtitle("Connect donors with charity homes!");
        //ab.setIcon(R.mipmap.wecare);
       // ab.setDisplayHomeAsUpEnabled(true);
        //ab.setDisplayShowHomeEnabled(true);
        //ab.setHomeButtonEnabled(true);

        tab1Button = (ImageButton) findViewById(R.id.tab1_icon);
        tab2Button = (ImageButton) findViewById(R.id.tab2_icon);
        tab3Button = (ImageButton) findViewById(R.id.tab3_icon);
        tab4Button = (ImageButton) findViewById(R.id.tab4_icon);


        //   tab1Button.setColorFilter(Color.argb(255, 255, 255, 255));  rgb(128,128,128)

        tab1Button.setColorFilter(Color.rgb(128,128,128));

        String about_wecare =  "\n" +
                "\n" +
                "The purpose of We Care is to makes it easy for people to donate food and clothes. We care link Donors to the people in need. \n" +
                "\n" +
                "\n" +
                "We Care supports the following basic models: \n" +
                "\n" +
                "(1) Donor : Any body can donate food or clothes through our app. you only need to fill out a simple donation form and set pick up time and location. Our volunteer will pick up the donations and drop them to the Charity/Goodwill home you select. \n" +
                "\n" +
                "(2) Volunteer : Any registered user can volunteer to pick up and drop off the donation items.\n" +
                "\n" +
                "(3) Charity/Goodwill Homes : If you are an organization which is helping needy people, register with us. We care will link you with the people who want to help you.  \n" +
                "\n" +
                "\n" +
                "Join We Care as:\n" +
                "1. Donor/Volunteer : Register yourself as Donor/Volunteer with We Care. You will earn good will points for every donation and volunteer work you do.\n" +
                "2. Charity : Register with us as Charity. You will get notified when any donation is made to you.\n" +
                "\n" +
                "\n" +
                "Become a Buddy:\n" +
                "If you are a School, church, charity home, food supplier or an active volunteer, join We Care. \n" +
                "\n" +
                "\n" +
                "When you donate food, please remember to donate only non-perishable items. Clothes, toys, books, blankets etc are also appreciated as donations." +
                "\n" +
                "You can contact us at: wecare268@gmail.com" +
                "\n" +
                "\n" +
                "\n" +
                "";

        aboutus = (TextView) findViewById(R.id.aboutus);
        aboutus.setText(about_wecare);


        tab1Text = (TextView) findViewById(R.id.tab1_text);
        tab2Text = (TextView) findViewById(R.id.tab2_text);
        tab3Text = (TextView) findViewById(R.id.tab3_text);
        tab4Text = (TextView) findViewById(R.id.tab4_text);

    /*   tab1Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        });*/

        tab2Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),BuddyActivity.class);
                startActivity(i);
                finish();
            }
        });

        tab3Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),Benefits.class);
                startActivity(i);
                finish();
            }
        });

        tab4Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //  Intent i = new Intent(getApplicationContext(),ShareToCare.class);
                //  startActivity(i);

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Yes, We Care!! Download our app and start participating.";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));


            }
        });

    }

    public boolean onCreateOptionsMenu(Menu myMenu){

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity,myMenu);
        return super.onCreateOptionsMenu(myMenu);

    }

    public boolean onOptionsItemSelected(MenuItem item){
        int itemSelected = item.getItemId();

        if(itemSelected == R.id.login) {
            Intent informationIntent = new Intent(this, SocialLogin.class);
            startActivity(informationIntent);
        }


        return super.onOptionsItemSelected(item);
    }

    public void onConfigurationChanged(Configuration newConfig){

        super.onConfigurationChanged(newConfig);

    }
}
