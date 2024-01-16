package com.example.testproject1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.testproject1.HelperClasses.FeaturedAdapter;
import com.example.testproject1.HelperClasses.FeaturedHelperClass;
import com.example.testproject1.HelperClasses.MostVisitedAdapter;
import com.example.testproject1.HelperClasses.MostVisitedHelperClass;
import com.example.testproject1.HelperClasses.WishListAdapter;
import com.example.testproject1.HelperClasses.WishListHelperClass;
import com.google.android.gms.common.Feature;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class HomeFragment extends Fragment {



    RecyclerView featuredRecyclerView, mostViewedRecyclerView, wishListRecyclerView;
    FeaturedAdapter adapter;
    MostVisitedAdapter popularAdapter;
    WishListAdapter wishListAdapter;

    Random r;
    ShapeableImageView imageView;
    Integer[] images = {
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_3,
            R.drawable.img_4,
            R.drawable.img_5,
            R.drawable.img_6,
            R.drawable.img_7,
            R.drawable.img_8,
            R.drawable.img_9,
            R.drawable.img_10,
            R.drawable.img_11,
            R.drawable.img_12,
            R.drawable.img_13,
            R.drawable.img_14,
            R.drawable.img_15,
    };


    private Context fragmentContext; // Add this variable

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentContext = context; // Store the context when the fragment is attached
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentContext = null; // Clear the stored context when the fragment is detached
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Randomly select an image from the array of images
        imageView = view.findViewById(R.id.homeToolbarImage);
        imageView.setImageResource(images[new Random().nextInt(images.length)]);
        //end of random image selection




        //RecyclerViews hookings
        featuredRecyclerView = view.findViewById(R.id.featured_recycler);
        mostViewedRecyclerView = view.findViewById(R.id.popular_recycler);
        wishListRecyclerView = view.findViewById(R.id.your_wishlist_recycler);

        //functions to populate the recycler views


        //featuredRecycler()
        featuredReCycler();



        //wishListRecycler()

        wishListRecycler();



        return view;


    }




    // In your HomeFragment.java
    private void featuredReCycler() {
        featuredRecyclerView.setHasFixedSize(true);
        featuredRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
        DatabaseReference placesReference = FirebaseDatabase.getInstance().getReference("Places");

        placesReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot placeSnapshot : snapshot.getChildren()) {
                    String placeName = placeSnapshot.child("placeNameValue").getValue(String.class);
                    String placeDescription = placeSnapshot.child("placeDescriptionValue").getValue(String.class);
                    String placeImage = placeSnapshot.child("placeImageUrl").getValue(String.class);

                    featuredLocations.add(new FeaturedHelperClass(placeImage, placeName, placeDescription));
                }

                adapter = new FeaturedAdapter(featuredLocations);
                featuredRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void wishListRecycler() {
        wishListRecyclerView.setHasFixedSize(true);
        wishListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        ArrayList<WishListHelperClass> wishListedLocations = new ArrayList<>();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid();
            DatabaseReference wishListReference = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("wishList");

            wishListReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot placeSnapshot : snapshot.getChildren()) {
                        String placeName = placeSnapshot.child("placeNameValue").getValue(String.class);
                        String placeDescription = placeSnapshot.child("placeDescriptionValue").getValue(String.class);
                        String placeImage = placeSnapshot.child("placeImageUrl").getValue(String.class);

                        wishListedLocations.add(new WishListHelperClass(placeImage, placeName, placeDescription));
                    }

                    wishListAdapter = new WishListAdapter(wishListedLocations);
                    wishListRecyclerView.setAdapter(wishListAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Handle the case where the user is not logged in
            // You might want to show a message or redirect the user to the login screen
            Toast.makeText(getActivity(), "Please log in to see your wishlist", Toast.LENGTH_SHORT).show();
        }
    }

}
