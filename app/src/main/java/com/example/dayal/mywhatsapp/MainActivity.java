package com.example.dayal.mywhatsapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.dayal.mywhatsapp.Settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    Utility mClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mClass=new Utility();
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager vPager = (ViewPager)findViewById(R.id.viewpager);
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(ChatFragment.newInstance(),"CHATS");
        viewPagerAdapter.addFragment(StatusFragment.newInstance(),"STATUS");
        viewPagerAdapter.addFragment(CallsFragment.newInstance(),"CALLS");
        vPager.setAdapter(viewPagerAdapter);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(vPager);
        vPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        fab=(FloatingActionButton)findViewById(R.id.chat_fab);
       // checkForPermissions();
        // tab selction
        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(vPager) {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        switch(tab.getPosition()) {
                            case 0: Log.i("TAG","onTabSelected-tab0");
                                fab.setImageResource(R.mipmap.msgfab);
                                break;
                            case 1: Log.i("TAG","onTabSelected-tab1");
                                fab.setImageResource(R.mipmap.camfab);
                                fab.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mClass.dispatchTakePictureIntent();
                                    }
                                });
                                break;
                            case 2: Log.i("TAG","onTabSelected-tab2");
                                fab.setImageResource(R.mipmap.callsfab);
                                fab.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {  Log.i("TAG","call btn clicked");
                                        //checkForPermissions();   //CALL_PHONE not used in the app
                                       dialIntent();
                                    }
                                });
                        }
                    }
                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {
                            Log.i("TAG", "onTabUnselected: " + tab.getPosition());
                            switch(tab.getPosition()){
                                //unregister fab button when tab is unselected
                                case 1:
                                case 2:fab.setOnClickListener(null);
                            }

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {
                            Log.i("TAG", "onTabReselected: " + tab.getPosition());
                        }

                });
        }
   //to dial a no.
      public void dialIntent(){
          Intent call = new Intent(Intent.ACTION_DIAL);
          startActivity(call);
    }
    static final int MY_PERM_REQ_CALL=1;

        public void checkForPermissions() {  Log.i("TAG","checking permission");
            int permissionCheckCall = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE);
            if (permissionCheckCall != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CALL_PHONE)) {
                    // Toast.makeText(MainActivity.this, "The app needs this permisssion to work", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERM_REQ_CALL);
                    //TODO if user sees msg, try again to request
                } else {    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERM_REQ_CALL);
                }
            }
            else onRequestPermissionsResult(MY_PERM_REQ_CALL,PackageManager.PERMISSION_GRANTED);
        }

    public void onRequestPermissionsResult(int requestCode, @NonNull int grantResults) {
        switch(requestCode){
            case MY_PERM_REQ_CALL: if(grantResults==PackageManager.PERMISSION_GRANTED){
                // permission was granted, yay! Do the
                // calls-related task you need to do
              //  Toast.makeText(this, "Thank you for allowing access to your camera.", Toast.LENGTH_SHORT).show();
                makeCall();
            }
            else {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                Toast.makeText(this, "Call permission needed to make calls !", Toast.LENGTH_SHORT).show();
            }
                return;
        }
    }

    public void makeCall(){
        try{
            Intent callIntent=new Intent(Intent.ACTION_CALL);
            startActivity(callIntent);  }
        catch (SecurityException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.search_button:

                return true;

            case R.id.menu5:
                Intent toSettings=new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(toSettings);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

    }
}
