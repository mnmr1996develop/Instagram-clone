package com.michaelrichards.instagramclone;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText username, password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Button signUpButton = findViewById(R.id.signUpRedirect);
        signUpButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
            finish();
        });

        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);

        loginButton = findViewById(R.id.signUpLoginButton);
        loginButton.setOnClickListener(v -> {
            ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), (user, e) -> {
                if (user != null && e == null){
                    Toast.makeText(LoginActivity.this, "welcome " + username.getText().toString(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "Incorrect Password ", Toast.LENGTH_SHORT).show();
                }
            });
        });

    }


}
