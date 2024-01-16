package com.example.testproject1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MoreFragment extends Fragment {

    ImageView profileImage, tourguideImage, addplaceImage;
    TextView nameText, emailText, userNameValText, phoneValText,
            birthDateValText, addressValText, bloodGroupValText;

    Button editProfileBtn;
    Button logoutBtn;

    // Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference userRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        profileImage = view.findViewById(R.id.profile_image);
        tourguideImage = view.findViewById(R.id.become_guide);
        addplaceImage = view.findViewById(R.id.add_place);
        nameText = view.findViewById(R.id.profile_name);
        emailText = view.findViewById(R.id.profile_email);
        userNameValText = view.findViewById(R.id.userNameValue);
        phoneValText = view.findViewById(R.id.phoneValue);
        birthDateValText = view.findViewById(R.id.birthDateValue);
        bloodGroupValText = view.findViewById(R.id.blood_group_value);
        addressValText = view.findViewById(R.id.address_value);
        editProfileBtn = view.findViewById(R.id.edit_profile);
        logoutBtn = view.findViewById(R.id.log_out);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        // Initialize Firebase Database reference
        userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());

        // Set an OnClickListener for the Edit Profile Button
        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        // Set an OnClickListener for the Become a Tour Guide Button
        tourguideImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTourGuide.class);
                startActivity(intent);
            }
        });

        // Set an OnClickListener for the Add a Place Button
        addplaceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddPlace.class);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        // Retrieve user data from Firebase
        retrieveUserData();

        return view;
    }

    private void retrieveUserData() {
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Data exists, proceed with retrieval
                    String name = dataSnapshot.child("regName").getValue(String.class);
                    String email = dataSnapshot.child("regEmail").getValue(String.class);
                    String userName = dataSnapshot.child("regUsername").getValue(String.class);
                    String phone = dataSnapshot.child("regPhone").getValue(String.class);
                    String birthDate = dataSnapshot.child("regDateOfBirth").getValue(String.class);
                    String address = dataSnapshot.child("regAddress").getValue(String.class);
                    String bloodGroup = dataSnapshot.child("regBloodGroup").getValue(String.class);

                    // Image retrieving
                    String imageUrl = dataSnapshot.child("regProfileImageUrl").getValue(String.class);

                    // Set the image
                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        if (getActivity() != null) {
                            Glide.with(getActivity()).load(imageUrl).into(profileImage);
                        }
                    }

                    // Set the data to the views
                    nameText.setText(name);
                    emailText.setText(email);
                    userNameValText.setText(userName);
                    phoneValText.setText(phone);
                    birthDateValText.setText(birthDate);
                    addressValText.setText(address);
                    bloodGroupValText.setText(bloodGroup);
                } else {
                    // Data doesn't exist in Firebase, handle the case
                    // You can set default values or show a message to the user
                    nameText.setText("Name not available");
                    emailText.setText("Email not available");
                    userNameValText.setText("Username not available");
                    phoneValText.setText("Phone not available");
                    birthDateValText.setText("Birth Date not available");
                    addressValText.setText("Address not available");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


                Log.e("FirebaseError", "Database error: " + databaseError.getMessage());
            }
        });
    }
}
