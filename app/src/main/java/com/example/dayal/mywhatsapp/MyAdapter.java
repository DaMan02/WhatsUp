package com.example.dayal.mywhatsapp;

import android.app.Fragment;
import android.content.Context;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
         //TODO make concrete class
public abstract class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context mContext;
    private List<Message> msgs;
    private List<Fragment> mFragList=new ArrayList<>();
    private List<String> titleList=new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        public MyViewHolder(View view) {
            super(view);

        }
        @Override
        public boolean onLongClick(View view) {
            return true;
        }
    }
    public MyAdapter(Context mContext,List<Message> msgs){
        this.mContext=mContext;
        this.msgs=msgs;
    }

  // @Override
  //  public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    //    return new MyViewHolder();
   // }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {

    }



 //   @Override
  //      public long getItemId(int position) {
   //         return msgs.get(position).getId();
     //   }
    @Override
    public int getItemCount() {
        return msgs.size();
    }

    }



