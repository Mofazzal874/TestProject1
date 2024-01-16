package com.example.testproject1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.testproject1.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

//    ActivityLoginBinding binding;
    private EditText email , password;
    private AppCompatButton signInButton;
    private Button callSignUp;
    private TextView forgotPassword;


    //firebase

    private FirebaseAuth mAuth ; // to create a new user
    private FirebaseDatabase fireDB; // to store user data
    private DatabaseReference reference; // to store user data



    @Override


//    public ActivityLoginBinding getBinding() {
//        if (binding != null) {
//            return binding;
//        } else {
//            throw new IllegalStateException("Binding is not initialized");
//        }
//    }
//    public void setBinding(ActivityLoginBinding activityLoginBinding) {
//        binding = activityLoginBinding;
//    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActivityLoginBinding activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
//        setBinding(activityLoginBinding);
//        setContentView(getBinding().getRoot());
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.log_email);
        password = findViewById(R.id.log_password);
        signInButton = findViewById(R.id.log_btn);
        callSignUp = findViewById(R.id.call_signup_btn);
        forgotPassword = findViewById(R.id.log_forget_password);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Initialize Firebase Database
//        fireDB = FirebaseDatabase.getInstance();

        // Check if the user is already logged in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish(); // Finish the LoginActivity so that the user can't go back to it using the back button
        }

        //all Listeners(OnClick methods
        signInButton.setOnClickListener(this);
        callSignUp.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);

//        Animation fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in);
//        Animation bottom_down = AnimationUtils.loadAnimation(this, R.anim.bottom_down);
//
//        getBinding().topLinearLayout.setAnimation(bottom_down);
//
//        Handler handler = new Handler();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                onCreateLambda();
//            }
//        };
//
//        handler.postDelayed(runnable, 1000L);
    }

//    private void onCreateLambda() {
//        Animation fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in);
//
//        getBinding().textView.setAnimation(fade_in);
//        getBinding().cardView1.setAnimation(fade_in);
//        getBinding().cardView.setAnimation(fade_in);
//        getBinding().signInWithFacebook.setAnimation(fade_in);
//        getBinding().signInWithGoogle.setAnimation(fade_in);
//        getBinding().signInWithGithub.setAnimation(fade_in);
//        getBinding().OR.setAnimation(fade_in);
//        getBinding().signUpButton.setAnimation(fade_in);
//    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.log_btn){
            //get all the values
            String emailText = email.getText().toString().trim();
            String passwordText = password.getText().toString().trim();

            //check if email and password are empty
            if(emailText.isEmpty()){
                email.setError("Email is required");
                email.requestFocus();
                return;
            }
            if(passwordText.isEmpty()){
                password.setError("Password is required");
                password.requestFocus();
                return;
            }
            //check if email is valid
            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
                email.setError("Please provide valid email");
                email.requestFocus();
                return;
            }
            //check if password is valid
            if(passwordText.length()<6){
                password.setError("Min password length should be 6 characters");
                password.requestFocus();
                return;
            }
            else {
                //sign in the user
                mAuth.signInWithEmailAndPassword(emailText,passwordText).addOnCompleteListener(LoginActivity.this , task -> {
                    if(task.isSuccessful()){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }else{
                        //if user is not registered
                        Toast.makeText(LoginActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                        finish();
                    }
                });

            }
        }

        else if(v.getId()==R.id.call_signup_btn){
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
//            finish();
        }
        else if(v.getId()==R.id.log_forget_password){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

    }
}
