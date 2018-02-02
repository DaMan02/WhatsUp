package com.example.dayal.mywhatsapp;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;
import hani.momanii.supernova_emoji_library.Helper.EmojiconTextView;

import static java.security.AccessController.getContext;

public class EnterChatActivity extends AppCompatActivity {
    EmojiconEditText emojiconEditText;
    ImageView imageView;
    CoordinatorLayout rootView;
    EmojiconTextView textView;
    CardView mcardView;
    Button camBtn;
    Utility activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_chat);
        Toolbar nameToolbar=(Toolbar)findViewById(R.id.name_toolbar);
        setSupportActionBar(nameToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        // for emoji keyboard  *************
        textView=(EmojiconTextView)findViewById(R.id.emoji_text);
        rootView=(CoordinatorLayout)findViewById(R.id.root_view) ;
        imageView=(ImageView)findViewById(R.id.emoji_btn);
        mcardView=(CardView)findViewById(R.id.card_view_text);
        emojiconEditText=(EmojiconEditText)findViewById(R.id.emoji_edit);
        EmojIconActions emojIcon=new EmojIconActions(this,rootView,emojiconEditText,imageView);
        emojIcon.ShowEmojIcon();

        FloatingActionButton sendfab=(FloatingActionButton)findViewById(R.id.send_fab);
        mcardView.setVisibility(View.INVISIBLE);

        sendfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=emojiconEditText.getText().toString();
                if(text.trim()!="")
                    mcardView.setVisibility(View.VISIBLE);
                    writeChat(text,textView);

            }
        });

        //  ***********************

        /* TODO remove error on click
        camBtn=(Button)findViewById(R.id.Etext_camera);
        camBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toCamera();
            }
        });  */
    }
    public void writeChat(String editText,EmojiconTextView textView){

        if(editText!=null)
        textView.setText(editText.toString());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent back=new Intent(EnterChatActivity.this,MainActivity.class);
        startActivity(back);
        //return;

    }

    //method needs to be public & take View as parameter
   public void toCamera(){
       activity=new Utility();
       activity.dispatchTakePictureIntent();
  }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
               // NavUtils.navigateUpFromSameTask(this);
                onBackPressed();        break;
            case R.id.call_button:
                 Intent callMe=new Intent(Intent.ACTION_DIAL);
                 callMe.setData(Uri.parse("tel:8506878927"));
                 startActivity(callMe);  break;
            case R.id.video_call:
                Toast.makeText(this, "Feature not available !", Toast.LENGTH_SHORT).show();

                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
