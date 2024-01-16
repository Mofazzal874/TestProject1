package com.example.testproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AddPlace extends AppCompatActivity {



    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int GALLERY_REQUEST_CODE = 123;
    private ShapeableImageView image ;
    private TextView uploadImage ;
    private EditText placeName , placeDescription ,placeLocation, placeAddress  ,  placePhone , placeWebsite , placeEmail ;
    private Button addPlaceBtn ;
    private Spinner placeTypeSpinner , placeDivision ;
    private Uri imageUri ;


    private FirebaseAuth mAuth ;
    private DatabaseReference placeReference ;
    private DatabaseReference placeDivisionReference ;
    private StorageReference storageReference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);



        //firebase part
        mAuth = FirebaseAuth.getInstance();
        placeReference = FirebaseDatabase.getInstance().getReference().child("Places");
        storageReference = FirebaseStorage.getInstance().getReference().child("place_images");


        //for selecting image
        image = findViewById(R.id.add_place_image);
        uploadImage = findViewById(R.id.add_place_upload_image);
        placeName = findViewById(R.id.add_place_name);
        placeDescription = findViewById(R.id.add_place_description);
        placeLocation = findViewById(R.id.add_place_location);
        placeAddress = findViewById(R.id.add_place_address);
        placeDivision = findViewById(R.id.add_place_division) ;
        placeEmail = findViewById(R.id.add_place_email);
        placePhone = findViewById(R.id.add_place_phone);
        placeWebsite = findViewById(R.id.add_place_website);
        addPlaceBtn = findViewById(R.id.add_place_button);


        //for selecting place type
        placeTypeSpinner = findViewById(R.id.place_type_spinner);
        String[] placeTypeOptions = {"Tourist Attraction", "Hotel", "Restaurant"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, placeTypeOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        placeTypeSpinner.setAdapter(adapter);



        //for selecting division
        placeDivision = findViewById(R.id.add_place_division);
        String[] placeDivisionOptions = {"Dhaka", "Chittagong", "Sylhet", "Rajshahi", "Khulna", "Barishal", "Rangpur", "Mymensingh"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, placeDivisionOptions);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        placeDivision.setAdapter(adapter2);

        //setting on click listeners
        uploadImage.setOnClickListener(v -> openFileChooser());
        addPlaceBtn.setOnClickListener(v -> addPlace());



    }




    //for selecting image
    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    //for selecting image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //for selecting image
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageUri = data.getData();
            image.setImageURI(imageUri);
        }
    }

    private void addPlace() {
        String placeNameValue = placeName.getText().toString().trim();
        String placeDescriptionValue = placeDescription.getText().toString().trim();
        String placeLocationValue = placeLocation.getText().toString().trim();
        String placeAddressValue = placeAddress.getText().toString().trim();
        String placeDivisionValue = placeDivision.getSelectedItem().toString().trim();
        String placePhoneValue = placePhone.getText().toString().trim();
        String placeWebsiteValue = placeWebsite.getText().toString().trim();
        String placeEmailValue = placeEmail.getText().toString().trim();
        String placeTypeValue = placeTypeSpinner.getSelectedItem().toString().trim();
        String placeImageUrl = imageUri.toString();

        if (placeNameValue.isEmpty()) {
            placeName.setError("Place name is required");
            placeName.requestFocus();
            return;
        }

        if (placeDescriptionValue.isEmpty()) {
            placeDescription.setError("Place description is required");
            placeDescription.requestFocus();
            return;
        }

        if (placeLocationValue.isEmpty()) {
            placeLocation.setError("Place location is required");
            placeLocation.requestFocus();
            return;
        }

        if (placeAddressValue.isEmpty()) {
            placeAddress.setError("Place address is required");
            placeAddress.requestFocus();
            return;
        }
        if(placeDivisionValue.isEmpty()){
            Toast.makeText(this, "Please select a division", Toast.LENGTH_SHORT).show();
            return;
        }
        if (placeImageUrl.isEmpty()) {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }
        if(placeTypeValue.isEmpty()){
            Toast.makeText(this, "Please select a place type", Toast.LENGTH_SHORT).show();
            return;
        }

        StorageReference imageRef = storageReference.child(placeNameValue + ".jpg");

        //uploading image to firebase storage
        imageRef.putFile(imageUri).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    String imageUrl = uri.toString();
                    String id = placeReference.push().getKey();
                    Place place = new Place(placeNameValue, placeDescriptionValue, placeLocationValue, placeAddressValue,placeDivisionValue ,  placePhoneValue, placeWebsiteValue, placeEmailValue, placeTypeValue, imageUrl);
                    assert id != null;
                    placeReference.child(id).setValue(place).addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            Toast.makeText(AddPlace.this, "Place added successfully", Toast.LENGTH_SHORT).show();
                            updateDivisionRegionMap(placeNameValue , placeDivisionValue);
                            startActivity(new Intent(AddPlace.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(AddPlace.this, "Failed to add place", Toast.LENGTH_SHORT).show();
                        }
                    });
                });
            } else {
                Toast.makeText(AddPlace.this, "Image upload failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateDivisionRegionMap(String placeNameValue, String placeDivisionValue) {
        DatabaseReference divisionRegionMapRef = FirebaseDatabase.getInstance().getReference().child("DivisionRegionMap");
        divisionRegionMapRef.child(placeDivisionValue).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.exists()){
                    divisionRegionMapRef.child(placeDivisionValue).child(placeNameValue).setValue(placeNameValue);
                }
                else{
                    boolean isPlaceAlreadyAdded = false ;
                    for (DataSnapshot regionSnapshot : snapshot.getChildren()) {
                        if (regionSnapshot.getKey().equals(placeNameValue)) {
                            isPlaceAlreadyAdded = true;
                            break;
                        }
                    }

                    //if place is not already added
                    if(!isPlaceAlreadyAdded){
                        divisionRegionMapRef.child(placeDivisionValue).child(placeNameValue).setValue(placeNameValue);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Database error: " + error.getMessage());

            }
        });
    }


}