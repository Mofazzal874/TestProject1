package com.example.testproject1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TourGuideProfile extends AppCompatActivity implements View.OnClickListener {

    private static final int CALL_PHONE_REQUEST_CODE = 1;

    ImageView tourGuideImage;
    TextView name, division, region, phone, paymentNumber,
            email, NID, age, bloodGroup, address, languageSkill, socialMediaLink, education, details;

    Button callBtn, bookBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_guide_profile);

        tourGuideImage = findViewById(R.id.profile_image);
        name = findViewById(R.id.fullname_value);
        division = findViewById(R.id.division_value);
        region = findViewById(R.id.area_value);
        phone = findViewById(R.id.phone_value);
        paymentNumber = findViewById(R.id.payment_number_value);
        email = findViewById(R.id.email_value);
        NID = findViewById(R.id.NID_value);
        age = findViewById(R.id.age_value);
        bloodGroup = findViewById(R.id.blood_group_value);
        address = findViewById(R.id.address_value);
        languageSkill = findViewById(R.id.language_skill_value);
        socialMediaLink = findViewById(R.id.social_link_value);
        education = findViewById(R.id.education_value);
        details = findViewById(R.id.details_value);

        callBtn = findViewById(R.id.callNow);
        bookBtn = findViewById(R.id.bookNow);

        // Fetch the title from the intent
        callBtn.setOnClickListener(this);
        bookBtn.setOnClickListener(this);

        showAllData();
    }

    private void showAllData() {
        // Fetch the title from the intent
        String tourGuideName = getIntent().getStringExtra("name");
        String tourGuideDivision = getIntent().getStringExtra("division");
        String tourGuideRegion = getIntent().getStringExtra("region");
        String tourGuidePhone = getIntent().getStringExtra("phone");
        String tourGuidePaymentNumber = getIntent().getStringExtra("paymentNumber");
        String Email = getIntent().getStringExtra("email");
        String NIDno = getIntent().getStringExtra("NID");
        String tourGuideAge = getIntent().getStringExtra("age");
        String bg = getIntent().getStringExtra("bloodGroup");
        String tourGuideAddress = getIntent().getStringExtra("address");
        String tourGuidesLanguageSkill = getIntent().getStringExtra("languageSkill");
        String tourGuideSocialMediaLink = getIntent().getStringExtra("socialMediaLink");
        String tourGuideEducation = getIntent().getStringExtra("education");
        String tourGuideDescription = getIntent().getStringExtra("details");
        String tourGuideImage = getIntent().getStringExtra("image");

        // Set the fetched data to the views
        name.setText(tourGuideName);
        division.setText(tourGuideDivision);
        region.setText(tourGuideRegion);
        phone.setText(tourGuidePhone);
        paymentNumber.setText(tourGuidePaymentNumber);
        email.setText(Email);
        NID.setText(NIDno);
        age.setText(tourGuideAge);
        bloodGroup.setText(bg);
        address.setText(tourGuideAddress);
        languageSkill.setText(tourGuidesLanguageSkill);
        socialMediaLink.setText(tourGuideSocialMediaLink);
        education.setText(tourGuideEducation);
        details.setText(tourGuideDescription);

        // Set the image using Glide for image loading
        Glide.with(this)
                .load(tourGuideImage) // Load the image from the URL
                .placeholder(R.drawable.img_13) // Placeholder image while loading
                .error(R.drawable.__icon__image_) // Error image if Glide fails to load
                .into(this.tourGuideImage);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.callNow) {
            // Check if the CALL_PHONE permission is granted
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // Request the CALL_PHONE permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_REQUEST_CODE);
            } else {
                // Permission already granted, proceed with making the call
                makePhoneCall();
            }
        } else if (v.getId() == R.id.bookNow) {
            // Book the tour guide
            initiatePayment();

        }
    }



    private void initiatePayment() {

        Toast.makeText(this, "Payment initiated", Toast.LENGTH_SHORT).show();

        String paymentUrl = "https://sandbox.sslcommerz.com/gwprocess/v4/api.php";

        // Open the SSLCOMMERZ payment page in a web browser
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(paymentUrl));
        startActivity(browserIntent);
    }

    // ...

    private void makePhoneCall() {
        // Retrieve the phone number
        String rawPhoneNumber = phone.getText().toString().trim();

        // Check if the phone number is not empty
        if (!TextUtils.isEmpty(rawPhoneNumber)) {
            // Add +88 to the phone number
            String formattedPhoneNumber = "+88" + rawPhoneNumber;

            // Create an Intent with the ACTION_CALL action and the formatted phone number URI
            Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + formattedPhoneNumber));

            // Check if there's an app that can handle this intent
            if (callIntent.resolveActivity(getPackageManager()) != null) {
                // Start the phone call
                startActivity(callIntent);
            } else {
                // No app to handle the call intent
                Toast.makeText(this, "No app to handle the phone call.", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Phone number is empty
            Toast.makeText(this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
        }
    }

// ...


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PHONE_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with making the call
                makePhoneCall();
            } else {
                // Permission denied, show a message to the user
                Toast.makeText(this, "Permission denied. Cannot make a phone call.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
