
package com.example.testproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class AddTourGuide extends AppCompatActivity {




    private ImageView tourGuideImage ;
    private EditText tourGuideName , tourGuidePhone , tourGuidePaymentNumber ,  tourGuideNID , tourGuideAge , tourGuideBloodGroup , tourGuideAddress ,
            tourGuideEmail , tourGuideLanguageSkill, tourGuideSocialLink,tourGuideEducation, tourGuideExperience,
            tourGuideDescription ;
    private Spinner tourGuideDivisionSpinner , regionSpinner, genderSpinner ;

    private Button AddTourGuideBtn ;

    private static final int PICK_PROFILE_IMAGE_REQUEST = 1;

    private Uri imageUri;

    private FirebaseAuth mAuth ;
    private DatabaseReference placeReference ;
    private DatabaseReference placeDivisionReference ;
    private StorageReference storageReference ;
    String selectedRegion ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tour_guide);

        //firebase part
        mAuth = FirebaseAuth.getInstance();
        placeReference = FirebaseDatabase.getInstance().getReference().child("Places");
        storageReference = FirebaseStorage.getInstance().getReference().child("tourGuide_images");
        placeDivisionReference = FirebaseDatabase.getInstance().getReference().child("DivisionRegionMap");

        tourGuideName = findViewById(R.id.fullname_value);
        tourGuidePhone = findViewById(R.id.phone_value);
        tourGuidePaymentNumber = findViewById(R.id.payment_number_value);
        tourGuideNID = findViewById(R.id.NID_value);
        tourGuideAge = findViewById(R.id.age_value);
        tourGuideBloodGroup = findViewById(R.id.blood_group_value);
        tourGuideAddress = findViewById(R.id.address_value);
        tourGuideEmail = findViewById(R.id.email_value);
        tourGuideLanguageSkill = findViewById(R.id.language_skill_value);
        tourGuideSocialLink = findViewById(R.id.social_link_value);
        tourGuideEducation = findViewById(R.id.education_value);
        tourGuideExperience = findViewById(R.id.experience_value);
        regionSpinner = findViewById(R.id.area_list);
        tourGuideDivisionSpinner = findViewById(R.id.division_list);
        String[] divisionList = {"Dhaka", "Chittagong", "Sylhet", "Rajshahi", "Khulna", "Barishal", "Rangpur", "Mymensingh"};
        ArrayAdapter<String> divisionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, divisionList);
        divisionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tourGuideDivisionSpinner.setAdapter(divisionAdapter);



        //setting up onItemSelected listener for division spinner
        tourGuideDivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected division
                String selectedDivision = parentView.getItemAtPosition(position).toString();
                // Update the regionSpinner based on the selected division
                updateRegionSpinner(selectedDivision);
            }

            private void updateRegionSpinner(String selectedDivision) {
                // Get the reference to the divisionRegionMap node in the database
                DatabaseReference divisionRegionReference = placeDivisionReference.child(selectedDivision);
                divisionRegionReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // Get the list of regions for the selected division
                        List<String> regionList = new ArrayList<>();
                        for (DataSnapshot regionSnapshot : snapshot.getChildren()) {
                            String region = regionSnapshot.getValue(String.class);
                            regionList.add(region);
                        }

                        // Update the regionSpinner with the list of regions
                        ArrayAdapter<String> regionAdapter = new ArrayAdapter<>(AddTourGuide.this, android.R.layout.simple_spinner_item, regionList);
                        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        regionSpinner.setAdapter(regionAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRegion = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tourGuideImage = findViewById(R.id.profile_image);
        AddTourGuideBtn = findViewById(R.id.register);

        tourGuideImage.setOnClickListener(v -> openFileChooser(PICK_PROFILE_IMAGE_REQUEST));
        AddTourGuideBtn.setOnClickListener(v -> addTourGuide());
    }




    private void openFileChooser(int i) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, i );
    }
    //for selecting image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //for selecting image
        if(resultCode == RESULT_OK && data!=null && data.getData() !=null){
            if(requestCode == PICK_PROFILE_IMAGE_REQUEST){
                imageUri = data.getData();
                tourGuideImage.setImageURI(imageUri);
            }
        }
    }

    private void addTourGuide() {
        String tourGuideNameValue = tourGuideName.getText().toString().trim();
        String tourGuidePhoneValue = tourGuidePhone.getText().toString().trim();
        String tourGuidePaymentNumberValue = tourGuidePaymentNumber.getText().toString().trim();
        String tourGuideNIDValue = tourGuideNID.getText().toString().trim();
        String tourGuideAgeValue = tourGuideAge.getText().toString().trim();
        String tourGuideBloodGroupValue = tourGuideBloodGroup.getText().toString().trim();
        String tourGuideAddressValue = tourGuideAddress.getText().toString().trim();
        String tourGuideEmailValue = tourGuideEmail.getText().toString().trim();
        String tourGuideLanguageSkillValue = tourGuideLanguageSkill.getText().toString().trim();
        String tourGuideSocialLinkValue = tourGuideSocialLink.getText().toString().trim();
        String tourGuideEducationValue = tourGuideEducation.getText().toString().trim();
        String tourGuideExperienceValue = tourGuideExperience.getText().toString().trim();
        String tourGuideDivisionValue = tourGuideDivisionSpinner.getSelectedItem().toString().trim();
        String tourGuideRegionValue = selectedRegion ;
        String tourGuideImageUrl = imageUri.toString();

        if (tourGuideNameValue.isEmpty()) {
            tourGuideName.setError("Tour Guide name is required");
            tourGuideName.requestFocus();
            return;
        }

        if (tourGuideDivisionSpinner.getSelectedItem().toString().isEmpty()) {
            Toast.makeText(this, "Please select a division", Toast.LENGTH_SHORT).show();
            tourGuideDivisionSpinner.requestFocus();
            return;
        }
        if(tourGuideRegionValue.isEmpty()){
            Toast.makeText(this, "Please select a region", Toast.LENGTH_SHORT).show();
            regionSpinner.requestFocus();
            return;
        }

        if (tourGuidePhoneValue.isEmpty()) {
            tourGuidePhone.setError("Tour Guide phone is required");
            tourGuidePhone.requestFocus();
            return;
        }

        if (tourGuidePaymentNumberValue.isEmpty()) {
            tourGuidePaymentNumber.setError("Tour Guide payment number is required");
            tourGuidePaymentNumber.requestFocus();
            return;
        }

        if (tourGuideNIDValue.isEmpty()) {
            tourGuideNID.setError("Tour Guide NID is required");
            tourGuideNID.requestFocus();
            return;
        }

        if (tourGuideAgeValue.isEmpty()) {
            tourGuideAge.setError("Tour Guide age is required");
            tourGuideAge.requestFocus();
            return;
        }
        if(tourGuideBloodGroupValue.isEmpty()){
            tourGuideBloodGroup.setError("Tour Guide blood group is required");
            tourGuideBloodGroup.requestFocus();
            return;
        }
        if(tourGuideAddressValue.isEmpty()){
            tourGuideAddress.setError("Tour Guide address is required");
            tourGuideAddress.requestFocus();
            return;
        }
        if(tourGuideLanguageSkillValue.isEmpty()){
            tourGuideLanguageSkill.setError("Tour Guide language skill is required");
            tourGuideLanguageSkill.requestFocus();
            return;
        }
        if(tourGuideExperienceValue.isEmpty()){
            tourGuideExperience.setError("Tour Guide experience is required");
            tourGuideExperience.requestFocus();
            return;
        }

        if(imageUri == null){
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        StorageReference imageRef = storageReference.child(tourGuideNameValue + ".jpg");
        //uploading image to firebase storage

        imageRef.putFile(imageUri).addOnCompleteListener(task ->{
            if(task.isSuccessful()){
                //get the download url of the image uploaded
                imageRef.getDownloadUrl().addOnSuccessListener(uri->{
                    String imageUrl = uri.toString();
                    TourGuide tourGuide = new TourGuide(
                            tourGuideNameValue,
                            tourGuideDivisionValue,
                            tourGuideRegionValue,
                            tourGuidePhoneValue,
                            tourGuidePaymentNumberValue,
                            tourGuideEmailValue,
                            tourGuideNIDValue,
                            tourGuideAgeValue,
                            tourGuideBloodGroupValue,
                            tourGuideAddressValue,
                            tourGuideLanguageSkillValue,
                            tourGuideSocialLinkValue,
                            tourGuideEducationValue,
                            tourGuideExperienceValue,
                            imageUrl);
                    saveTourGuideToDatabase(tourGuide); // Save the tourGuide object to the database
                }) ;
            }
            else {
                Toast.makeText(AddTourGuide.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
            }
        }) ;
    }

    private void saveTourGuideToDatabase(TourGuide tourGuide) {
        // Find the child with the matching placeNameValue
        placeReference.orderByChild("placeNameValue").equalTo(tourGuide.getRegion()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot placeSnapshot : dataSnapshot.getChildren()) {
                        String placeKey = placeSnapshot.getKey();
                        DatabaseReference tourGuideReference = placeReference.child(placeKey).child("tourGuides");

                        // Save the tourGuide object to the database under the "tourGuides" node
                        String tourGuideId = tourGuideReference.push().getKey();
                        tourGuideReference.child(tourGuideId).setValue(tourGuide)
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(AddTourGuide.this, "Tour Guide added successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(AddTourGuide.this, "Failed to add Tour Guide", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                } else {
                    Toast.makeText(AddTourGuide.this, "Place not found for the given region", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AddTourGuide.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}


