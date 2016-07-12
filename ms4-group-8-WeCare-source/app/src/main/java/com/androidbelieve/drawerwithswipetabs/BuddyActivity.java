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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class BuddyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] items = { "All Categories",  "Charity Home", "Church", "Donors", "Food Distributor", "Resturant", "School"};
    private List<BuddyData> buddy = new ArrayList<BuddyData>();
    static BuddyData buddyClicked;

    private GoogleMap map;

    private ImageButton tab1Button, tab2Button, tab3Button, tab4Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_buddy);

        setContentView(R.layout.buddy_activity);

        android.support.v7.app.ActionBar ab = getSupportActionBar();

        tab1Button = (ImageButton) findViewById(R.id.tab1_icon);
        tab2Button = (ImageButton) findViewById(R.id.tab2_icon);
        tab3Button = (ImageButton) findViewById(R.id.tab3_icon);
        tab4Button = (ImageButton) findViewById(R.id.tab4_icon);

        //   ImageButton tab2Button = (ImageButton) findViewById(R.id.tab2_icon);


        tab2Button.setColorFilter(Color.rgb(128,128,128));



        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<String>(
                        this,
                        //  android.R.layout.simple_spinner_dropdown_item,
                        android.R.layout.simple_list_item_1,
                        items)
        );
        spinner.setOnItemSelectedListener(this);


        tab1Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        });
/*
        tab2Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),BuddyActivity.class);
                startActivity(i);
                finish();
            }
        });*/

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



    public void buddylist(String item){

        String charity1desc = "What do we do?\n" +
                "\n" +
                "Through the power of work, Goodwill of Silicon Valley generates revenue and re-invests  into the community through programs that give people the tools to live independent lives. Most people think of Goodwill as a donation and retail operation, but it is so much more than that! It’s the homeless veteran who is given the opportunity to change his life. It’s the underemployed or unemployed with a lack of job skills who receive training and paid work experience. It’s the local and international markets that benefit from our products. It’s local companies that partner with us through our GoodSource and GoodEx services. It’s the 20.4 million pounds of goods that Goodwill diverts from the landfill annually. But most importantly, it’s the community members who receive services through our programs each year.\n" +
                "\n" +
                "Give Goodwill, live Goodwill.\n" +
                "\n" +
                "Our Story\n" +
                "\n" +
                "Founded in Santa Clara County in 1928, Goodwill of Silicon Valley (GWSV) is part of Goodwill Industries International, a federation of over 200 autonomous, community-based Goodwill organizations worldwide. Together we are one of the largest social service organizations in the world.\n" +
                "\n" +
                "We are dedicated to improving employment opportunities, increasing standards of living, providing economic independence, and restoring our clients sense of self value. We do this through workforce creation, vocational training and environmental stewardship.\n" +
                "\n" +
                "With 19 retail stores, an online store, an extensive reuse/recycling operation, and our Contract Services division (GoodSource) we help people overcome barriers to employment, build sustainable livelihoods, and transform their lives and communities.\n" +
                "\n" +
                "The revenues generated from these enterprises, coupled with grant and financial donation support, fund the Institute for Career Development (ICD), the heart of the organization. ICD provides employment services to over 10,000 adult and youth annually.";

        String charity1name = "Goodwill of Silicon Valley";
        String charity1addr = "2800 El Camino Real, Santa Clara, CA 95051";
        double charity1lat = 37.3518174;
        double charity1long = -122.048257 ;

        String charity2desc = "No appointment needed. Sell While You Shop.\n" +
                "We are not consignment, and we don't even want to think about how complicated that would be.  We pay cash on the spot, or you can choose 20% more in store credit.\n" +
                "Generally, the closer to new-condition your items are, the more likely we are to buy them.  After all, we're in business to offer families great values, and not just to buy everything that comes in.  Everything we buy must be clean and ready to put straight on the sales floor.\n" +
                "Now buying Spring and Summer season. Most needed sizes: girls 5 to 8, and boys 3 to 8.  Please note that we are usually much more limited in what we can buy in 0-9 months, 10-14 and maternity, simply because of supply and demand.\n" +
                "The best way to bring clothes to sell is freshly laundered and laying flat (not folded) in a laundry basket or bin.  One basket or bin per visit (we can look at more only if no one else is waiting).\n" +
                "Toys and equipment should be gently used, clean, complete, and have working batteries.  Most needed:  high chairs and boosters, pack 'n' plays, toddler beds, walkers, exersaucers and jumpers.  The most common reasons we turn away toys and equipment are not being clean or not having all the pieces.  Washing the fabric part of a exersaucer or swing can often make the difference in if we buy it or not.\n" +
                "Thank you for your donations!\n" +
                "Our customers are amazing! Thanks to your donations, our store raised $255 to donate to buildOn. That contributed to a total of $51,613 raised by Uptown Cheapskate and Kid to Kid stores around the country to build schools in Africa. We made enough for our first school, and are 2/3 of the way to our second.  Thanks to your generosity, we are so excited to change the lives of kids around the world.";
        String charity2name = "Kid to Kid";
        String charity2addr = "2666 Homestead Rd, Santa Clara, CA 95051";
        double charity2lat = 37.3385719;
        double charity2long = -122.0435074 ;


        String charity3desc = "We’re celebrating our fourth birthday next week with a KEENGala. I joined KEEN in May and hit the ground running doing programs, organizing the gala, and trying to raise awareness. Before that, I worked for an international children's charity. It was a a lot of travel because I worked with four or five countries across three continents, and I wanted to take a step back.\n" +
                "\n" +
                "The beauty of KEEN is that it's very hands-on and child-focused, so I get a better balance of work and family life. Now, on any given Sunday you can find me running around coaching kids in the gym.\n" +
                "\n" +
                "\n" +
                "\n" +
                "What are some common misconceptions about kids with disabilities that KEEN tries to address?\n" +
                "The misconception is that children with disabilities, physical or mental, are different. KEEN believes that a child is a child, and children should be encouraged to have fun, no matter what their age or cognitive abilities are. We’re just providing an average experience where they can choose an activity that's not so structured. I like to call myself the Executive Director of Fun!\n" +
                "\n" +
                "\n" +
                "\n" +
                "How do people get involved aside from donating?\n" +
                "\n" +
                "You can sign up on our website to get involved on lots of different levels, whether it's being an intern or a volunteer coach. Having the coaches to provide the programs is our core need and because we provide them free of charge for families, we do all our fundraising in the community. It's great when companies come out to help. They become really invested in the cause.\n" +
                "\n" +
                "It’s all about resourcing the necessary funding and mobilizing the volunteers because they’re the backbone of our organization. As the only staff member, I rely 100% on volunteers. KEEN San Francisco is an affiliate in a national organization and our goal is to one day grow to more of a KEEN Greater Bay Area, in addition to KEEN San Francisco\n" +
                "\n" +
                "\n" +
                "\n" +
                "What are some of the most rewarding experiences you’ve had with the kids? \n" +
                "There was a boy named Brian. He was always standing at the door watching everyone and looking confused. He had trouble connecting with the other kids and coaches. All of a sudden, a coach came in, who happened to be the president of our board, Scott Wiley, and I don’t know what it was. That day, he reached Brian, and Brian got on a scooter with jump ropes attached to it, and Scott was pulling him around the gym and he had this smile on his face! We had never seen him smile before that. It was such a huge breakthrough.\n" +
                "\n" +
                "\n" +
                "We also had a brand new mom to our program who came to our summer picnic on Crissy Field. We posted a bunch of photos on the website of all the kids from the picnic and her son was smiling in all of them. She was in tears on the phone with us because she didn't have a single picture of her son smiling. She was thrilled. \n" +
                "\n" +
                "Keen San Francisco";
        String charity3name = "Keen San Francisco";
        String charity3addr = "500 Post Street, San Francisco, CA 94102";
        double charity3lat = 37.7883396;
        double charity3long = -122.4124916 ;

        String charity4name = "Catholic Charities";
        String charity4addr = "990 Eddy Street, San Francisco, CA 94109";
        double charity4lat = 37.7825175;
        double charity4long = -122.4261782 ;
        String charity4desc = "The crisis of homelessness can be completely devastating to a family system and compound the challenges that come with poverty and instability. Through shelter program, housing subsidies, eviction prevention services and permanent supportive housing programs, Catholic Charities ensure vulnerable people have homes to provide a foundation to focus on restoring stability and hope.";


        String church1desc = "Our Lady of Peace is a Church and Shrine dedicated to the greater Glory of God and the salvation of souls.  It is a place of prayer & pilgrimage with:\n" +
                "\n" +
                "Perpetual adoration of Jesus in the Blessed Sacrament\n" +
                "Devotion to the Blessed Virgin Mary\n" +
                "Frequent celebration of the Sacraments, particularly the Eucharist and reconciliation\n" +
                "Integrated formation of the person (spiritual, moral, intellectual and emotional)\n" +
                "We welcome you to visit our serene and peaceful place of worship that maintains the rich traditions of the Roman Catholic Church.  The Shrine of our Blessed Mother has been a focal point for many people seeking inspiration, comfort, or to pray. Our doors are open 24 hours a day, all year round.  ";
        String church1name = "Our Lady of Peace Church & Shrine";
        String church1addr = "2800 Mission College Blvd, Santa Clara, CA 95054";
        double church1lat = 37.3893072 ;
        double church1long = -122.0473344 ;


        String church2desc = "Visiting a church service can be an unsettling experience. Whether you're coming back to church or you're trying it out for the first time, we know this is a big step for you. Our prayer is that, after your visit, you will begin to rethink your perspective on God and the Church.\n" +
                "\n" +
                "THE FOLLOWING POINTS WILL HELP MAKE YOUR VISIT MORE PLEASANT:\n" +
                "Sunday morning gatherings are at 10:30am. The service last about one hour and a half. Come a few minutes early to make sure you get a good seat.\n" +
                "When you walk in the lobby, you will see banners and sign-up tables. Most of the directional signs will lead you to high-energy and friendly environments we have created for kids. For more information on these environments, click on the Ministries link on this site.\n" +
                "Stop at our Information counter when you enter the building and let us show you around. Our greeters are waiting to familiarize you with the ins and outs of First Baptist.\n" +
                "Adults and teenagers gather in the sanctuary for the morning services. The atmosphere is casual. We dress comfortably. We use music, video, and other creative elements to communicate truth in a contemporary way. People of all ages seem to enjoy our services.\n" +
                "If you have kids 0-5th grade, you can make your check-in process easier by visiting our children check-in center in the lobby of our Fellowship Hall building. Let us know if you have any questions. We look forward to meeting your family soon!\n" +
                "There are lots of great churches in Santa Clara. We hope you'll give ours a try. See you Sunday!";

        String church2name = "Santa Clara First Baptist Church";
        String church2addr = "3111 Benton St, Santa Clara, CA 95051";
        double church2lat = 37.3467596 ;
        double church2long = -122.0543125 ;


        String fd1name = "FAMILY HARVEST SANTA CLARA COUNTY";
        String fd1addr = "750 Curtner Avenue, San Jose CA 95125";
        double fd1lat = 37.2916857 ;
        double fd1long = -121.8793946 ;

        String sc1name = "Saint Lawrence Academy";
        String sc1addr = "2000 Lawrence Ct, Santa Clara, CA 95051";
        double sc1lat = 37.358898 ;
        double sc1long = -121.9974752 ;

        String fd1desc = "Who We Are\n" +
                "Second Harvest Food Bank of Santa Clara and San Mateo Counties is the trusted leader dedicated to ending local hunger. Since its inception in 1974, Second Harvest has become one of the largest food banks in the nation, providing food to nearly a quarter of a million people each month. The Food Bank mobilizes individuals, companies, and community partners to connect people to the nutritious food they need. More than half of the food distributed is fresh produce. Second Harvest also plays a leading role in promoting federal nutrition programs and educating families on how to make healthier food choices.\n" +
                "\n" +
                "What We Do\n" +
                "In fiscal year 2015 (July 2014 - June 2015), we distributed more than one million pounds of nutritious food each week to low-income people in need in every zip code of our service area from Daly City to Gilroy, and from the ocean to the bay.\n" +
                "\n" +
                "We provide food efficiently through our innovative direct-service programs (see below) and by collaborating with a network of nearly 330 partner non-profit agencies operating at 700 different food distribution sites. Partner agencies include shelters, pantries, soup kitchens, children's programs, senior meal sites, and residential programs.\n" +
                "\n" +
                "Throughout the fiscal year, volunteers contributed nearly 314,000 hours of service, which saved us $6.4 million in equivalent personnel costs.";

        String sc1desc = " \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "Dear Alumni, Parents, and Friends of Saint Lawrence Academy,\n" +
                "\n" +
                "We are committed to providing our students at Saint Lawrence Academy with a faith-filled community that will provide a compassionate and diverse environment where they can embrace the opportunities they desire and develop the skills they need in order to discover their individual voices. We are committed to not only their academic success, but also their personal, spiritual, and social success. Our rigorous curriculum, co-curricular activities, personal faith development, advanced technology offerings, and athletic opportunities ensure they will all enjoy a balanced experience during their time here.\n" +
                "\n" +
                "The mission of the advancement department is to keep Saint Lawrence Academy accessible to all qualified students and to secure its financial resources for its sustainable future. However, we cannot do this without your help. Your help and support is critical to the goals of our students and the financial stability of Saint Lawrence Academy. We are focused on the following major need areas for stewarding donations: immediate facility needs for the athletics and performing arts departments, scholarship assistance for deserving students in families with need, and operational budget support.  \n" +
                "\n" +
                "We have been blessed by the support and generosity of many of our parents, alumni, parents of alumni, grandparents, faculty, and friends. \n" +
                "\n" +
                "Please consider your personal level of giving in the knowledge that your giving ensures our growth and success. We need, and greatly appreciate, your support. Click here to make your donation today.  ";


        if("All Categories".equals(item)) {
            buddy.clear();
            buddy.add(new BuddyData(charity1name, "Charity Home", charity1addr, charity1desc, R.drawable.charity1img, 255, charity1lat, charity1long));

            buddy.add(new BuddyData(charity2name, "Charity Home", charity2addr, charity2desc, R.drawable.charity2img, 350, charity2lat, charity2long));
            buddy.add(new BuddyData(charity3name, "Charity Home", charity3addr, charity3desc, R.drawable.charity3img, 150, charity3lat, charity3long));
            buddy.add(new BuddyData(charity4name, "Charity Home", charity4addr, charity4desc, R.drawable.charity4img, 300, charity4lat, charity4long));

            buddy.add(new BuddyData(church1name, "Church", church1addr, church1desc, R.drawable.church1img, 320, church1lat, church1long));
            buddy.add(new BuddyData(church2name, "Church", church2addr, church2desc, R.drawable.church2img, 450, church2lat, church2long));
            buddy.add(new BuddyData(fd1name, "Food Distributor", fd1addr, fd1desc, R.drawable.fd1img, 255, fd1lat, fd1long));
            buddy.add(new BuddyData(sc1name, "School", sc1addr, sc1desc, R.drawable.sc1img, 320, sc1lat, sc1long));

            LatLng LOCATION1 = new LatLng(charity1lat, charity1long);
            LatLng LOCATION2 = new LatLng(charity2lat, charity2long);
            LatLng LOCATION3 = new LatLng(charity3lat, charity3long);
            LatLng LOCATION4 = new LatLng(charity4lat, charity4long);

            LatLng LOCATION5 = new LatLng(church1lat, church1long);
            LatLng LOCATION6 = new LatLng(church2lat, church2long);

            LatLng LOCATION7 = new LatLng(fd1lat, fd1long);
            LatLng LOCATION8 = new LatLng(sc1lat, sc1long);


            map  = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            map.addMarker(new MarkerOptions().position(LOCATION1).title(charity1name));
            map.addMarker(new MarkerOptions().position(LOCATION2).title(charity2name));
            map.addMarker(new MarkerOptions().position(LOCATION3).title(charity3name));
            map.addMarker(new MarkerOptions().position(LOCATION4).title(charity4name));

            map.addMarker(new MarkerOptions().position(LOCATION5).title(church1name));
            map.addMarker(new MarkerOptions().position(LOCATION6).title(church2name));
            map.addMarker(new MarkerOptions().position(LOCATION7).title(fd1name));
            map.addMarker(new MarkerOptions().position(LOCATION8).title(sc1name));

            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION8, 7);

            map.animateCamera(update);

        }

        else if ("Church".equals(item)) {
            buddy.clear();
            map.clear();
            buddy.add(new BuddyData(church1name, "Church", church1addr, church1desc, R.drawable.church1img, 320, church1lat, church1long));
            buddy.add(new BuddyData(church2name, "Church", church2addr, church2desc, R.drawable.church2img, 450, church2lat, church2long));


            LatLng LOCATION1 = new LatLng(church1lat, church1long);
            LatLng LOCATION2 = new LatLng(church2lat, church2long);


            map  = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            map.addMarker(new MarkerOptions().position(LOCATION1).title(church1name));
            map.addMarker(new MarkerOptions().position(LOCATION2).title(church2name));

            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION2, 10);

            map.animateCamera(update);


        }

        else if ("Charity Home".equals(item)) {
            buddy.clear();
            map.clear();
            buddy.add(new BuddyData(charity1name, "Charity Home", charity1addr, charity1desc, R.drawable.charity1img, 255, charity1lat, charity1long));
            buddy.add(new BuddyData(charity2name, "Charity Home", charity2addr, charity2desc, R.drawable.charity2img, 350, charity2lat, charity2long));
            buddy.add(new BuddyData(charity3name, "Charity Home", charity3addr, charity3desc, R.drawable.charity3img, 150, charity3lat, charity3long));
            buddy.add(new BuddyData(charity4name, "Charity Home", charity4addr, charity4desc, R.drawable.charity4img, 300, charity4lat, charity4long));


            LatLng LOCATION1 = new LatLng(charity1lat, charity1long);
            LatLng LOCATION2 = new LatLng(charity2lat, charity2long);
            LatLng LOCATION3 = new LatLng(charity3lat, charity3long);
            LatLng LOCATION4 = new LatLng(charity4lat, charity4long);


            map  = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

            map.addMarker(new MarkerOptions().position(LOCATION1).title(charity1name));
            map.addMarker(new MarkerOptions().position(LOCATION2).title(charity2name));
            map.addMarker(new MarkerOptions().position(LOCATION3).title(charity3name));
            map.addMarker(new MarkerOptions().position(LOCATION4).title(charity4name));

            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION2, 8);

            map.animateCamera(update);

        }


        else if ("Donors".equals(item)) {
            buddy.clear();
            map.clear();
            buddy.add(new BuddyData(church1name, "Church", church1addr, church1desc, R.drawable.church1img, 320, church1lat, church1long));
            buddy.add(new BuddyData(charity1name, "Charity Home", charity1addr, charity1desc, R.drawable.charity1img, 255, charity1lat, charity1long));
            buddy.add(new BuddyData(fd1name, "Food Distributor", fd1addr, fd1desc, R.drawable.fd1img, 255, fd1lat, fd1long));




            LatLng LOCATION1 = new LatLng(church1lat, church1long);
            LatLng LOCATION2 = new LatLng(charity1lat, charity1long);
            LatLng LOCATION3 = new LatLng(fd1lat, fd1long);



            map  = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

            map.addMarker(new MarkerOptions().position(LOCATION1).title(church1name));
            map.addMarker(new MarkerOptions().position(LOCATION2).title(charity1name));
            map.addMarker(new MarkerOptions().position(LOCATION3).title(fd1name));


            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION2, 10);

            map.animateCamera(update);

        }

        else if ("Food Distributor".equals(item)) {
            buddy.clear();
            map.clear();
            buddy.add(new BuddyData(fd1name, "Food Distributor", fd1addr, fd1desc, R.drawable.fd1img, 255, fd1lat, fd1long));

            LatLng LOCATION1 = new LatLng(fd1lat, fd1long);

            map  = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

            map.addMarker(new MarkerOptions().position(LOCATION1).title(fd1name));
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION1, 10);

            map.animateCamera(update);

        }

        else if ("School".equals(item)) {
            buddy.clear();
            map.clear();
            buddy.add(new BuddyData(sc1name, "School", sc1addr, sc1desc, R.drawable.sc1img, 320, sc1lat, sc1long));

            LatLng LOCATION1 = new LatLng(sc1lat, sc1long);

            map  = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

            map.addMarker(new MarkerOptions().position(LOCATION1).title(sc1name));
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION1, 10);

            map.animateCamera(update);
        }
//Resturants
        else {
            buddy.clear();

        }

        // CheckBox btn_info = (CheckBox)findViewById(R.id.btn_info);



        ArrayAdapter<BuddyData> adapter = new MyListAdapter();

        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);


        ListView list = (ListView)findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {



                buddyClicked = buddy.get(position);

                Intent myIntent = new Intent(BuddyActivity.this,BuddyHome.class);
                myIntent.putExtra(buddyClicked.getBuddyName(), position);
                startActivity(myIntent);


            }

        });





    }

    /*public void setLocation(View view)
    {
        double lat = buddyClicked.getLatitude();
        double lon = buddyClicked.getLongitude();
        LOCATION_SURRREY = new LatLng(lat, lon);
        map  = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.addMarker(new MarkerOptions().position(LOCATION_SURRREY).title("Find me here!"));
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_SURRREY, 16);
        map.animateCamera(update);
    }*/

    private class MyListAdapter extends ArrayAdapter<BuddyData> {
        public MyListAdapter(){
            super(BuddyActivity.this, R.layout.buddy_list, buddy);
        }
        @Override
        public View getView(int position, View convertView,ViewGroup parent){


            View itemView = convertView;
            ImageView imageview=null;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.buddy_list,parent,false);
            }

            BuddyData currentAnimal = buddy.get(position);


            imageview = (ImageView)itemView.findViewById(R.id.item_icon);
            imageview.setImageResource(currentAnimal.getId());


            TextView itemText = (TextView) itemView.findViewById(R.id.item_text);
            itemText.setText("" + currentAnimal.getBuddyName());

            return itemView;
        }






    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        //  Toast.makeText(getApplicationContext(), "Position: " + position + ", Data: " + items[position], Toast.LENGTH_SHORT).show();
        String item = items[position];
        buddylist(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
