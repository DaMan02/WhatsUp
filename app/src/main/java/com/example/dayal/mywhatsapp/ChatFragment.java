package com.example.dayal.mywhatsapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {
   // RecyclerView recyclerView;
    TextView time;
    TextView chatLast;
    public ChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters
     * @return A new instance of fragment ChatFragment.
     */
    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chat, container, false);
       // recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
       // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //recyclerView.setAdapter(new MyAdapter());
        CardView mcardView=(CardView)view.findViewById(R.id.card_view);
        mcardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent chatIntent= new Intent(getActivity(),EnterChatActivity.class);
                startActivity(chatIntent);
                return true;
            }
        });
                //get time
        time=(TextView)view.findViewById(R.id.time_text);
        setTime(time);

              return view;
    }
    @TargetApi(24)
  public void setTime(TextView mTextView){
    /*  int hh,mm;
     Date currTime=Calendar.getInstance().getTime();
      //SimpleDateFormat mFormat=SimpleDateFormat.getDateInstance();
      hh=android.icu.util.Calendar.get(Calendar.HOUR_OF_DAY);

      mTextView.setText(currTime.toString());  */

       Calendar calendar = Calendar.getInstance();
        String time;
        SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm");
        time=mFormat.format(calendar.getTime());
      mTextView.setText(time);

  }
    @Override
    public void onStart(){
        super.onStart();
        //MainActivity mainActivity=(MainActivity)getActivity();
        //mainActivity.onTabSelected(1);
    }

}
