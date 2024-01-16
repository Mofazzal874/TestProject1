package com.example.testproject1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class EditProfileActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 123;
    private EditText NameEditText, passwordEditText, phoneEditText, birthDateEditText,
            bloodGroupEditText, addressEditText;
    private Button updateProfileBtn;
    private ImageView profileImage;
    private FirebaseAuth mAuth;
    private DatabaseReference userReference;
    private StorageReference storageReference;
    private Uri imageUri;
    String userName ;
    String img ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mAuth = FirebaseAuth.getInstance();
        userReference = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(mAuth.getCurrentUser().getUid());
        storageReference = FirebaseStorage.getInstance().getReference().child("profile_images");

        NameEditText = findViewById(R.id.userNameValue);
        passwordEditText = findViewById(R.id.passwordValue);
        phoneEditText = findViewById(R.id.phoneValue);
        birthDateEditText = findViewById(R.id.birthDateValue);
        bloodGroupEditText = findViewById(R.id.blood_group_value);
        addressEditText = findViewById(R.id.address_value);
        profileImage = findViewById(R.id.profile_image);
        updateProfileBtn = findViewById(R.id.edit_profile);
        profileImage.setOnClickListener(v -> openGallery());
        updateProfileBtn.setOnClickListener(v -> updateProfile());

        userReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().getValue() != null) {
                UserHelperClass user = task.getResult().getValue(UserHelperClass.class);
                if (user != null) {
                    NameEditText.setText(user.getRegName());
                    passwordEditText.setText(user.getRegPassword());
                    userName = user.getRegUsername();
                    phoneEditText.setText(user.getRegPhone());
                    birthDateEditText.setText(user.getRegDateOfBirth());
                    bloodGroupEditText.setText(user.getRegBloodGroup());
                    addressEditText.setText(user.getRegAddress());
                    // Load and display the profile image using Glide
                    String imageUrl = user.getRegProfileImageUrl();
                    img = imageUrl;
                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        Glide.with(this).load(imageUrl).into(profileImage);
                    }
                }
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            profileImage.setImageURI(imageUri);
        }
    }

    private void updateProfile() {
        String Name = NameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String birthDate = birthDateEditText.getText().toString().trim();
        String bloodGroup = bloodGroupEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(password) || TextUtils.isEmpty(phone) ||
                TextUtils.isEmpty(birthDate) || TextUtils.isEmpty(bloodGroup) || TextUtils.isEmpty(address)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (imageUri != null) {
            uploadImageAndProfileData(Name, password, phone, birthDate, bloodGroup, address);
        } else {
            updateProfileData(Name, password, phone, birthDate, bloodGroup, address, img);
        }
    }

    private void uploadImageAndProfileData(String Name, String password, String phone,
                                           String birthDate, String bloodGroup, String address) {
        StorageReference imageRef = storageReference.child(mAuth.getCurrentUser().getUid() + ".jpg");

        imageRef.putFile(imageUri).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    String imageUrl = uri.toString();
                    updateProfileData(Name, password, phone, birthDate, bloodGroup, address, imageUrl);
                });
            } else {
                Toast.makeText(EditProfileActivity.this, "Image upload failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateProfileData(String Name, String password, String phone, String birthDate,
                                   String bloodGroup, String address, String imageUrl) {
        UserHelperClass updatedUser = new UserHelperClass(Name,mAuth.getCurrentUser().getEmail() ,password,userName, phone, birthDate, bloodGroup, imageUrl, address);
        userReference.setValue(updatedUser).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(EditProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(EditProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
