package com.example.testproject1.HelperClasses;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testproject1.DetailedActivity;
import com.example.testproject1.R;
import com.example.testproject1.TourGuideListActivity;
import com.example.testproject1.TourGuideProfile;

import java.util.ArrayList;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.WishListViewHolder> {


    ArrayList<WishListHelperClass> wishListLocations ;

    // Constructor
    public WishListAdapter(ArrayList<WishListHelperClass> wishListLocations) {
        this.wishListLocations = wishListLocations;
    }


    // Methods
    @NonNull
    @Override
    public WishListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Initialize view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_design, parent, false);
        return new WishListViewHolder(view);


    }

    //this is the main method.This will bind the data to the views
    @Override
    public void onBindViewHolder(@NonNull WishListViewHolder holder, int position) {
        // Initialize WishListHelperClass
        WishListHelperClass wishListHelperClass = wishListLocations.get(position);

        // Set the data using Glide for image loading
        Glide.with(holder.itemView.getContext())
                .load(wishListHelperClass.getImage()) // Load the image from the URL
                .placeholder(R.drawable.img_13) // Placeholder image while loading
                .error(R.drawable.__icon__image_) // Error image if Glide fails to load
                .into(holder.image);

        holder.title.setText(wishListHelperClass.getTitle());
        holder.desc.setText(wishListHelperClass.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class<?> contextClass = v.getContext().getClass();
                if(contextClass == TourGuideListActivity.class) {
                    Intent intent = new Intent(v.getContext(), TourGuideProfile.class);
                    intent.putExtra("title", wishListHelperClass.getTitle());
                    Toast.makeText(v.getContext(), "Title: " + wishListHelperClass.getTitle(), Toast.LENGTH_SHORT).show();
                    v.getContext().startActivity(intent);
                } else {
                    Intent intent = new Intent(v.getContext(), DetailedActivity.class);
                    intent.putExtra("title", wishListHelperClass.getTitle());
                    v.getContext().startActivity(intent);
                }
            }
        });


    }


    //this method will return the size of the list
    @Override
    public int getItemCount() {
        return wishListLocations.size();
    }
    //end of methods
    public static class WishListViewHolder extends RecyclerView.ViewHolder {
        // Initialize variables
        // TextView, ImageView, etc.
        TextView title , desc ;
        ImageView image;

        public WishListViewHolder(View itemView) {
            super(itemView);

            //hooks
            // Assign variables
            title = itemView.findViewById(R.id.wishlist_title);
            desc = itemView.findViewById(R.id.wishlist_description);
            image = itemView.findViewById(R.id.wishlist_image);


        }
    }
}
