package com.example.testproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.testproject1.HelperClasses.WishList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DetailedActivity extends AppCompatActivity implements View.OnClickListener {





    ImageView backBtn, wishListBtn ;
    TextView locbtn , hotelsBtn , tourGuidesBtn;

    ImageView detailedImage;
    TextView detailedTitle , detailedDescription , detailedAddress;
    Button comment ;


    FirebaseAuth mAuth ;

    String title;
    String googleMapsLink;


    String wishListedTitle , wishListedDescription , wishListedImage ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        //All the buttons hooking
        backBtn = findViewById(R.id.detailed_back_image_button);
        wishListBtn = findViewById(R.id.detailed_wishlist_image_button);
        locbtn = findViewById(R.id.detailed_location);
        hotelsBtn = findViewById(R.id.detailed_hotel);
        tourGuidesBtn = findViewById(R.id.detailed_tour_guide);
        //All the buttons hooking ends
        detailedImage = findViewById(R.id.detailed_image);
        detailedTitle = findViewById(R.id.detailed_title);
        detailedDescription = findViewById(R.id.detailed_description);
        detailedAddress = findViewById(R.id.detailed_address);
        comment = findViewById(R.id.detailed_upload_review_button);




        //Buttons hooking ends


        //setting on click listeners
        backBtn.setOnClickListener(this);
        wishListBtn.setOnClickListener(this);
        locbtn.setOnClickListener(this);
        hotelsBtn.setOnClickListener(this);
        tourGuidesBtn.setOnClickListener(this);
        //setting on click listeners ends

        //firebase part
        mAuth = FirebaseAuth.getInstance();


        //Retrieving data from the intent
        String placeTitle =  getIntent().getStringExtra("title");
        title = placeTitle;
        fetchDataFromFirebase(placeTitle);



    }
    private void fetchDataFromFirebase(String placeTitle) {
        //using firebase query to get additional details based on placetitle
        //and then setting the data to the views
        DatabaseReference placesReference = FirebaseDatabase.getInstance().getReference("Places");
        Query query = placesReference.orderByChild("placeNameValue").equalTo(placeTitle);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Fetch additional details as needed
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String placeName = dataSnapshot.child("placeNameValue").getValue(String.class);
                    String placeDescription = dataSnapshot.child("placeDescriptionValue").getValue(String.class);
                    String placeLocation = dataSnapshot.child("placeLocationValue").getValue(String.class);
                    String placeImage = dataSnapshot.child("placeImageUrl").getValue(String.class);
                    String placeAddress = dataSnapshot.child("placeAddressValue").getValue(String.class);
                    String placeEmail = dataSnapshot.child("placeEmailValue").getValue(String.class);
                    String placePhone = dataSnapshot.child("placePhoneValue").getValue(String.class);
                    String placeWebsite = dataSnapshot.child("placeWebsiteValue").getValue(String.class);

                    wishListedTitle = placeName ;
                    wishListedDescription = placeDescription ;
                    wishListedImage = placeImage ;
                    googleMapsLink = placeLocation ;

                    //setting the data to the views
                    detailedTitle.setText(placeName);
                    detailedDescription.setText(placeDescription);
                    String fullAddress = "Address: " + placeAddress + "\n" + "Phone: " + placePhone + "\n" + "Email: " + placeEmail + "\n" + "Website: " + placeWebsite + "\n";
                    detailedAddress.setText(fullAddress);
                    //setting the data to the views ends

                    Log.d("ImageURL", "Place Image URL: " + placeImage); // Log the image URL

                    //setting the image using glide
                    // Setting the image using Glide
                    if (detailedImage != null) {
                        Glide.with(DetailedActivity.this)
                                .load(placeImage)
                                .placeholder(R.drawable.img_13)
                                .error(R.drawable.__icon__image_)
                                .into(detailedImage);
                    } else {
                        Log.e("ImageLoad", "detailedImage is null");
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DetailedActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.detailed_back_image_button) {
            finish(); // go back to the previous activity
        } else if (v.getId() == R.id.detailed_wishlist_image_button) {
            addPlaceToWishList();
        } else if (v.getId() == R.id.detailed_location) {
            openGoogleMapsOrBrowser(googleMapsLink);
        } else if (v.getId() == R.id.detailed_hotel) {
            startActivity(new Intent(DetailedActivity.this, HotelListActivity.class));
        } else if (v.getId() == R.id.detailed_tour_guide) {
            Intent intent = new Intent(DetailedActivity.this, TourGuideListActivity.class);
            intent.putExtra("title", title);
            startActivity(intent);
        }
    }

    private void openGoogleMapsOrBrowser(String googleMapsLink) {
        Uri gmmIntentUri = Uri.parse(googleMapsLink);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            // If the Google Maps app is not available, open the link in a web browser
            Intent webIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            startActivity(webIntent);
        }
    }
    private void addPlaceToWishList() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();

            DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("Users").child(userId);

            DatabaseReference wishListReference = userReference.child("wishList");

            // Check if the place is already in the wishlist
            Query query = wishListReference.orderByChild("placeNameValue").equalTo(wishListedTitle);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // The place is already in the wishlist, remove it
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            childSnapshot.getRef().removeValue();
                            Toast.makeText(DetailedActivity.this, "Removed from wishlist", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // The place is not in the wishlist, add it
                        DatabaseReference newWishListItemReference = wishListReference.push();
                        newWishListItemReference.child("placeNameValue").setValue(wishListedTitle);
                        newWishListItemReference.child("placeDescriptionValue").setValue(wishListedDescription);
                        newWishListItemReference.child("placeImageUrl").setValue(wishListedImage);
                        Toast.makeText(DetailedActivity.this, "Added to wishlist", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(DetailedActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show();
        }
    }

}