package com.example.uadnd.cou8901.popularmoviesapp2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 * Created by dd2568 on 5/15/2017.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsAdapterViewHolder>{
    private String[] mReviewsData;

    final private ReviewsAdapterOnClickHandler mClickHandler;

    @Override
    public ReviewsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.review_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean attachNow = false;

        View view = inflater.inflate(layoutIdForListItem, parent, attachNow);
        return new ReviewsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewsAdapterViewHolder holder, int position) {
        String reviewAtPosition = mReviewsData[position];
        holder.mReviewsTextView.setText(reviewAtPosition);

    }

    @Override
    public int getItemCount() {
        if(null == mReviewsData)
            return 0;
        else
            return mReviewsData.length;
    }

    public interface ReviewsAdapterOnClickHandler {
        void onClick(String reviewClicked);
    }
    public ReviewsAdapter(ReviewsAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }
    public void setReviewsData(String[] reviewsData) {
        mReviewsData = reviewsData;
        notifyDataSetChanged();
    }

    public class ReviewsAdapterViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        public final TextView mReviewsTextView;

        public ReviewsAdapterViewHolder(View itemView) {
            super(itemView);
            mReviewsTextView = (TextView) itemView.findViewById(R.id.tv_review_data);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String reviewClicked = mReviewsData[adapterPosition];
            mClickHandler.onClick(reviewClicked);
        }

    }
}
