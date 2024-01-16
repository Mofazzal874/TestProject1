package com.example.testproject1.HelperClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testproject1.R;

import java.util.ArrayList;

public class MostVisitedAdapter extends RecyclerView.Adapter<MostVisitedAdapter.MostVisitedViewHolder> {

    private ArrayList<MostVisitedHelperClass> mostVisitedLocations;
    private Context context;

    // Constructor
    public MostVisitedAdapter(ArrayList<MostVisitedHelperClass> mostVisitedLocations) {
        this.context = context;
        this.mostVisitedLocations = mostVisitedLocations;
    }

    // Methods
    @NonNull
    @Override
    public MostVisitedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Initialize view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_visited_design, parent, false);
        return new MostVisitedViewHolder(view);
    }

    // This is the main method. This will bind the data to the views
    @Override
    public void onBindViewHolder(@NonNull MostVisitedViewHolder holder, int position) {
        // Initialize MostVisitedHelperClass
        MostVisitedHelperClass mostVisitedHelperClass = mostVisitedLocations.get(position);

        // Set the data
        holder.title.setText(mostVisitedHelperClass.getTitle());
        holder.desc.setText(mostVisitedHelperClass.getDescription());

        // Load image using Glide
        Glide.with(context)
                .load(mostVisitedHelperClass.getImage()) // Assuming you have added getImageUrl() method in MostVisitedHelperClass
                .placeholder(R.drawable.img_13) // You can add a placeholder image
                .error(R.drawable.__icon__image_) // You can add an error image
                .into(holder.image);
    }

    // This method will return the size of the list
    @Override
    public int getItemCount() {
        return mostVisitedLocations.size();
    }

    // End of methods
    public static class MostVisitedViewHolder extends RecyclerView.ViewHolder {
        // Initialize variables
        // TextView, ImageView, etc.
        TextView title, desc;
        ImageView image;

        public MostVisitedViewHolder(View itemView) {
            super(itemView);

            // Hooks
            // Assign variables
            title = itemView.findViewById(R.id.most_visited_title);
            desc = itemView.findViewById(R.id.most_visited_description);
            image = itemView.findViewById(R.id.most_visited_image);
        }
    }
}
