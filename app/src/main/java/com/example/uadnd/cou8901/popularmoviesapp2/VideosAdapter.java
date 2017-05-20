package com.example.uadnd.cou8901.popularmoviesapp2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;




/**
 * Created by dd2568 on 5/15/2017.
 */

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideosAdapterViewHolder>{
    private String[] mVideosData;


    final private Context mContext;
    private final String TAG =this.getClass().getSimpleName();

    @Override
    public VideosAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.video_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean attachNow = false;

        View view = inflater.inflate(layoutIdForListItem, parent, attachNow);
        return new VideosAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideosAdapterViewHolder holder, int position) {
        String videoAtPosition = mVideosData[position];
        String [] title_url = videoAtPosition.split(":", 2);
        holder.mVideosTextView.setText(title_url[0]);  // Title and youtube video key/id are delimited by ":" ;

    }

    @Override
    public int getItemCount() {
        if(null == mVideosData)
            return 0;
        else
            return mVideosData.length;
    }

    public VideosAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setVideosData(String[] videosData) {
        mVideosData = videosData;
        notifyDataSetChanged();
    }

    public class VideosAdapterViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        public final TextView mVideosTextView;

        public VideosAdapterViewHolder(View itemView) {
            super(itemView);
            mVideosTextView = (TextView) itemView.findViewById(R.id.tv_video_data);
            itemView.setOnClickListener(this);
        }
        // Launch Youtube or Browser to watch trailer video
        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String videoClicked = mVideosData[adapterPosition];
            String [] title_url = videoClicked.split(":", 2);
            //Log.d(TAG, "VideosAdaptor:onClick : " + title_url[1]);
            Uri webpage = Uri.parse(title_url[1]);
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

            if(webIntent.resolveActivity(mContext.getPackageManager()) != null  )
                mContext.startActivity(webIntent);

        }

    }
}
