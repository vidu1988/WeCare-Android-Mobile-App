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

/**
 * Created by kgupta1 on 5/9/2016.
 */
public class Benefits extends AppCompatActivity {

    private ImageButton tab1Button, tab2Button, tab3Button, tab4Button;
    private TextView benifits;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.benefits_activity);

        android.support.v7.app.ActionBar ab = getSupportActionBar();

        String benefits_wecare =  "\n" +
                "\n" +
                "Join We Care and start earning goodwill points. \n" +
                "\n" +
                "\n" +
                "You earn points for: \n" +
                "\n" +
                "(1) Donation : Donating 1 clothe or 1 lb food adds 1 point to your profile . \n" +
                "\n" +
                "(2) Volunteer : For one volunteer work you will get 1 point.\n" +
                "\n" +
                "\n" +
                "We will list our top donors and volunteers in our buddy page.\n" +
                "\n" +
                "\n" +
                "We encourage you to join we care because:\n" +
                "With very donation you are making, you are helping someone..\n" +
                "\n" +
                "\n" +
                "You can contact us at: wecare268@gmail.com" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "";
        benifits = (TextView) findViewById(R.id.benifits);
        benifits.setText(benefits_wecare);

        tab1Button = (ImageButton) findViewById(R.id.tab1_icon);
        tab2Button = (ImageButton) findViewById(R.id.tab2_icon);
        tab3Button = (ImageButton) findViewById(R.id.tab3_icon);
        tab4Button = (ImageButton) findViewById(R.id.tab4_icon);


        tab3Button.setColorFilter(Color.rgb(128,128,128));

        tab1Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        tab2Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),BuddyActivity.class);
                startActivity(i);
                finish();
            }
        });

      /*  tab3Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),Benefits.class);
                startActivity(i);
                finish();
            }
        });*/

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
