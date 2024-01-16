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
import com.example.testproject1.R;
import com.example.testproject1.TourGuideProfile;

import java.util.ArrayList;

public class TourGuideAdapter extends RecyclerView.Adapter<TourGuideAdapter.TourGuideViewHolder> {

    private ArrayList<TourGuideHelperClass> tourGuideList;

    // Constructor
    public TourGuideAdapter(ArrayList<TourGuideHelperClass> tourGuideList) {
        this.tourGuideList = tourGuideList;
    }

    // Methods
    @NonNull
    @Override
    public TourGuideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Initialize view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_guide_design, parent, false);
        return new TourGuideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourGuideViewHolder holder, int position) {
        // Initialize TourGuideHelperClass
        TourGuideHelperClass tourGuideHelperClass = tourGuideList.get(position);

        // Set the data using Glide for image loading
        Glide.with(holder.itemView.getContext())
                .load(tourGuideHelperClass.getImage()) // Load the image from the URL
                .placeholder(R.drawable.img_13) // Placeholder image while loading
                .error(R.drawable.__icon__image_) // Error image if Glide fails to load
                .into(holder.tourGuideImage);

        holder.tourGuideName.setText(tourGuideHelperClass.getName());
        holder.tourGuideDescription.setText(tourGuideHelperClass.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TourGuideProfile.class);
                intent.putExtra("name", tourGuideHelperClass.getName());
                intent.putExtra("division", tourGuideHelperClass.getDivision());
                intent.putExtra("region", tourGuideHelperClass.getRegion());
                intent.putExtra("phone", tourGuideHelperClass.getPhone());
                intent.putExtra("paymentNumber", tourGuideHelperClass.getPaymentNumber());
                intent.putExtra("email", tourGuideHelperClass.getEmail());
                intent.putExtra("NID", tourGuideHelperClass.getNIDno());
                intent.putExtra("age", tourGuideHelperClass.getAge());
                intent.putExtra("bloodGroup", tourGuideHelperClass.getBloodGroup());
                intent.putExtra("address", tourGuideHelperClass.getAddress());
                intent.putExtra("languageSkill", tourGuideHelperClass.getLanguageSkill());
                intent.putExtra("socialMediaLink", tourGuideHelperClass.getSocialMediaLink());
                intent.putExtra("education", tourGuideHelperClass.getEducation());
                intent.putExtra("details", tourGuideHelperClass.getDescription());
                intent.putExtra("image", tourGuideHelperClass.getImage());

                //Toast.makeText(v.getContext(), "Tour Guide Name: " + tourGuideHelperClass.getName(), Toast.LENGTH_SHORT).show();
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tourGuideList.size();
    }

    // ViewHolder class
    public static class TourGuideViewHolder extends RecyclerView.ViewHolder {
        // Initialize variables
        // TextView, ImageView, etc.
        TextView tourGuideName, tourGuideDescription;
        ImageView tourGuideImage;

        public TourGuideViewHolder(View itemView) {
            super(itemView);

            // Assign variables
            tourGuideName = itemView.findViewById(R.id.tourguide_title);
            tourGuideDescription = itemView.findViewById(R.id.tourguide_description);
            tourGuideImage = itemView.findViewById(R.id.tourguide_image);
        }
    }
}
