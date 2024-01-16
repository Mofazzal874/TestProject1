package com.example.testproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.testproject1.HelperClasses.TourGuideAdapter;
import com.example.testproject1.HelperClasses.TourGuideHelperClass;
import com.example.testproject1.HelperClasses.WishListHelperClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TourGuideListActivity extends AppCompatActivity {

    RecyclerView tourGuideRecyclerView;
    TourGuideAdapter tourGuideAdapter;
    String title;
    TourGuideHelperClass tourGuideHelperClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_guide_list);

        tourGuideRecyclerView = findViewById(R.id.tourguide_recycler);

        tourGuideRecycler(); // Calling the method
    }

    private void tourGuideRecycler() {
        tourGuideRecyclerView.setHasFixedSize(true);
        tourGuideRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<TourGuideHelperClass> tourGuides = new ArrayList<>();
        DatabaseReference placesReference = FirebaseDatabase.getInstance().getReference("Places");

        // Fetch the title from the intent
        title = getIntent().getStringExtra("title");

        placesReference.orderByChild("placeNameValue").equalTo(title).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot placeSnapshot : snapshot.getChildren()) {
                    // Check if the place has the specified title
                    String placeNameValue = placeSnapshot.child("placeNameValue").getValue(String.class);
                    if (placeNameValue != null && placeNameValue.equals(title)) {
                        // If the titles match, fetch the tour guides
                        DataSnapshot tourGuidesSnapshot = placeSnapshot.child("tourGuides");
                        for (DataSnapshot tourGuideSnapshot : tourGuidesSnapshot.getChildren()) {
                            String tourGuideName = tourGuideSnapshot.child("name").getValue(String.class);
                            String tourGuideImage = tourGuideSnapshot.child("tourGuideImageUrl").getValue(String.class);
                            String tourGuideDescription = tourGuideSnapshot.child("experience").getValue(String.class);
                            String tourGuideDivision = tourGuideSnapshot.child("division").getValue(String.class);
                            String tourGuideRegion = tourGuideSnapshot.child("region").getValue(String.class);
                            String tourGuidePhone = tourGuideSnapshot.child("phone").getValue(String.class);
                            String tourGuidePaymentNumber = tourGuideSnapshot.child("paymentNumber").getValue(String.class);
                            String Email = tourGuideSnapshot.child("email").getValue(String.class);
                            String NIDno = tourGuideSnapshot.child("nidno").getValue(String.class);
                            String tourGuideAge = tourGuideSnapshot.child("age").getValue(String.class);
                            String tourGuidebloodGroup = tourGuideSnapshot.child("bloodGroup").getValue(String.class);
                            String tourGuideAddress = tourGuideSnapshot.child("address").getValue(String.class);
                            String tourGuidesLanguageSkill = tourGuideSnapshot.child("language").getValue(String.class);
                            String tourGuideSocialMediaLink = tourGuideSnapshot.child("socialMediaLink").getValue(String.class);
                            String tourGuideEducation = tourGuideSnapshot.child("education").getValue(String.class);
                            tourGuideHelperClass = new TourGuideHelperClass(tourGuideName, tourGuideImage, tourGuideDescription, tourGuideDivision,
                                    tourGuideRegion, tourGuidePhone, tourGuidePaymentNumber, Email, NIDno, tourGuideAge, tourGuidebloodGroup, tourGuideAddress,
                                    tourGuidesLanguageSkill, tourGuideSocialMediaLink, tourGuideEducation);

                            // Add the tour guide to the list
                            tourGuides.add(tourGuideHelperClass);
                        }
                    }
                }

                // Check if the tourGuides list is not empty before setting the adapter
                if (!tourGuides.isEmpty()) {
                    // Create and set the adapter
                    tourGuideAdapter = new TourGuideAdapter(tourGuides);
                    tourGuideRecyclerView.setAdapter(tourGuideAdapter);
                } else {
                    // Handle the case where there are no tour guides
                    // You can display a message or take appropriate action
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }
}
