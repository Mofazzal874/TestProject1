package com.example.testproject1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.testproject1.R;
import com.example.testproject1.databinding.ActivitySignupBinding;
import com.example.testproject1.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.FirebaseStorage;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name, email, password, username,phone,dateOfBirth ,bloodGroup;
    Button signUpButton;
    private TextView callLogin;


    //firebase

    FirebaseAuth mAuth ; // to create a new user
    FirebaseDatabase fireDB; // to store user data
    DatabaseReference reference; // to store user data


    // check if user is already logged in
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(mAuth.getCurrentUser() != null){
//            startActivity(new Intent(SignupActivity.this, MainActivity.class));
//            finish();
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        //hooks to all xml elements in activity_sign_up.xml
        name= findViewById(R.id.reg_name);
        email= findViewById(R.id.reg_email);
        password=  findViewById(R.id.reg_password);
        username= findViewById(R.id.reg_username);
        phone= findViewById(R.id.reg_phone);
        dateOfBirth= findViewById(R.id.reg_dateOfBirth);
        bloodGroup= findViewById(R.id.reg_bloodGroup);
        signUpButton= findViewById(R.id.reg_btn);
        callLogin =findViewById(R.id.ORsignIn);



        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Initialize Firebase Database
        fireDB = FirebaseDatabase.getInstance();

        //all Listeners(OnClick methods
        signUpButton.setOnClickListener(this);
        callLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() ==R.id.reg_btn) {
            //get all the values
            String regName = name.getText().toString();
            String regEmail = email.getText().toString();
            String regPassword = password.getText().toString();
            String regUsername = username.getText().toString();
            String regPhone = phone.getText().toString();
            String regDateOfBirth = dateOfBirth.getText().toString();
            String regBloodGroup = bloodGroup.getText().toString();

            //check if any field is empty
            if(regName.isEmpty()){
                name.setError("Name is required");
                name.requestFocus();
                return;
            }
            if(regEmail.isEmpty()){
                email.setError("Email is required");
                email.requestFocus();
                return;
            }
            if(regPassword.isEmpty()){
                password.setError("Password is required");
                password.requestFocus();
                return;
            }
            if(regUsername.isEmpty()){
                username.setError("Username is required");
                username.requestFocus();
                return;
            }
            if(regPhone.isEmpty()){
                phone.setError("Phone is required");
                phone.requestFocus();
                return;
            }
            if(regDateOfBirth.isEmpty()){
                dateOfBirth.setError("Date of Birth is required");
                dateOfBirth.requestFocus();
                return;
            }
            if(regBloodGroup.isEmpty()){
                bloodGroup.setError("Blood Group is required");
                bloodGroup.requestFocus();
                return;
            }

            //validity checking part

            //check if email is valid
            else if(!regEmail.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
                email.setError("Enter a valid email");
                email.requestFocus();
                return;
            }
            else if(regPassword.length()<6){
                password.setError("Password must be at least 6 characters");
                password.requestFocus();
                return;
            }
            else if(regUsername.length()<4){
                username.setError("Username must be at least 4 characters");
                username.requestFocus();
                return;
            }
            else if(regPhone.length()<10){
                phone.setError("Enter a valid phone number");
                phone.requestFocus();
                return;
            }
            else if(regUsername.contains(" ")){
                username.setError("Username cannot contain spaces");
                username.requestFocus();
                return;
            }
            else if(regDateOfBirth.length()>10){
                dateOfBirth.setError("Enter a valid date of birth");
                dateOfBirth.requestFocus();
                return;
            }
            else{
                 //all fields are valid and filled   //continue with registration

                mAuth.createUserWithEmailAndPassword(regEmail, regPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //check if task is successful
                        if(task.isSuccessful()){
                            String userId = task.getResult().getUser().getUid();
                            DatabaseReference root = fireDB.getReference().child("Users").child(userId);

                            //create a new user
                            UserHelperClass helperClass = new UserHelperClass(regName, regEmail, regPassword, regUsername, regPhone, regDateOfBirth, regBloodGroup,"" , "");
                            root.setValue(helperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    //check if task is successful
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignupActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                    }
                                    else{
                                        Toast.makeText(SignupActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(SignupActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }


    }
    else if(v.getId() ==R.id.ORsignIn){
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        }
    }
}
