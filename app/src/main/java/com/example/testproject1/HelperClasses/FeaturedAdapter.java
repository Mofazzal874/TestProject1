package com.example.testproject1.HelperClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testproject1.DetailedActivity;
import com.example.testproject1.R;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {


    ArrayList<FeaturedHelperClass> featuredLocations ;

    // Constructor
    public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredLocations) {
        this.featuredLocations = featuredLocations;
    }


    // Methods
    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Initialize view
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_design, parent, false);
         return new FeaturedViewHolder(view);


    }

    //this is the main method .This will bind the data to the views
    // Updated onBindViewHolder method using Glide
    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        // Initialize FeaturedHelperClass
        FeaturedHelperClass featuredHelperClass = featuredLocations.get(position);

        // Set the data using Glide for image loading
        Glide.with(holder.itemView.getContext())
                .load(featuredHelperClass.getImage()) // Load the image from the URL
                .placeholder(R.drawable.img_13) // Placeholder image while loading
                .error(R.drawable.__icon__image_) // Error image if Glide fails to load
                .into(holder.image);

        holder.title.setText(featuredHelperClass.getTitle());
        holder.desc.setText(featuredHelperClass.getDescription());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailedActivity.class) ;
                intent.putExtra("title" , featuredHelperClass.getTitle());
                v.getContext().startActivity(intent);
            }
        });
    }


    //this method will return the size of the list
    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }
    //end of methods
    public static class FeaturedViewHolder extends RecyclerView.ViewHolder {
        // Initialize variables
        // TextView, ImageView, etc.
          TextView title , desc ;
          ImageView image;

        public FeaturedViewHolder(View itemView) {
            super(itemView);

            //hooks
            // Assign variables
             title = itemView.findViewById(R.id.featured_title);
             desc = itemView.findViewById(R.id.featured_description);
             image = itemView.findViewById(R.id.featured_image);


        }
    }
}
