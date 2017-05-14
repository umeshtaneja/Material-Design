package com.example.umesh.materialme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by umesh on 5/10/2017.
 */

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.SportsViewHolder>  {

    private GradientDrawable mGradientDrawable;
    private ArrayList<Sport> mSportsData;
    private Context mContext;

    SportsAdapter(Context context, ArrayList<Sport> sportsData) {
        this.mSportsData = sportsData;
        this.mContext = context;

        mGradientDrawable = new GradientDrawable();
        mGradientDrawable.setColor(Color.GRAY);

        Drawable drawable = ContextCompat.getDrawable
                (mContext,R.drawable.img_badminton);
        if(drawable != null) {
            mGradientDrawable.setSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
    }
    @Override
    public SportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SportsViewHolder(mContext, LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false), mGradientDrawable);
    }

    @Override
    public void onBindViewHolder(SportsViewHolder holder, int position) {

        //Get the current sport
        Sport currentSport = mSportsData.get(position);

        //Bind the data to the views
        holder.bindTo(currentSport);

    }


    @Override
    public int getItemCount() {
        return mSportsData.size();
    }

    static class SportsViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        //Member Variables for the holder data
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mSportsImage;
        private Context mContext;
        private Sport mCurrentSport;
        private GradientDrawable mGradientDrawable;

        SportsViewHolder(Context context, View itemView, GradientDrawable gradientDrawable) {
            super(itemView);

            mTitleText = (TextView) itemView.findViewById(R.id.title);
            mInfoText = (TextView) itemView.findViewById(R.id.subTitle);
            mSportsImage = (ImageView) itemView.findViewById(R.id.sportsImage);

            mContext = context;
            mGradientDrawable = gradientDrawable;

            itemView.setOnClickListener(this);
        }

        void bindTo(Sport currentSport) {

            mTitleText.setText(currentSport.getTitle());
            mInfoText.setText(currentSport.getInfo());

            mCurrentSport = currentSport;

            Glide.with(mContext).load(currentSport.
                    getImageResource()).placeholder(mGradientDrawable).into(mSportsImage);
        }

        @Override
        public void onClick(View view) {

            //Set up the detail intent
            Intent detailIntent = Sport.starter(mContext, mCurrentSport.getTitle(),
                    mCurrentSport.getImageResource());


            //Start the detail activity
            mContext.startActivity(detailIntent);
        }
    }
}
