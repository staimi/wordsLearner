package com.example.wordslearner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final ArrayList<example_item> localDataSet;
    public RecyclerOnClickListener mRecyclerOnClickListener;

    public Adapter(ArrayList<example_item> localDataSet ,RecyclerOnClickListener recyclerOnClickListener) {
        this.localDataSet = localDataSet;
        this.mRecyclerOnClickListener = recyclerOnClickListener;
    }
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        private final TextView textView1, textView2;
        public RecyclerOnClickListener mRecyclerOnClickListener;

        public ViewHolder(View view, RecyclerOnClickListener recyclerOnClickListener) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView1 = (TextView) view.findViewById(R.id.textView1RecyclerView);
            textView2 = (TextView) view.findViewById(R.id.textView2RecyclerView);
            this.mRecyclerOnClickListener = recyclerOnClickListener;
        }

        @Override
        public void onClick(View view) {
            mRecyclerOnClickListener.onNewsClicked(getAdapterPosition());
        }
    }
    public interface RecyclerOnClickListener{
        void onNewsClicked(int position);
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public Adapter(ArrayList<example_item> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.example_recycler_view, viewGroup, false), mRecyclerOnClickListener);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView1.setText(localDataSet.get(position).getText1());
        viewHolder.textView2.setText(localDataSet.get(position).getText2());

    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
