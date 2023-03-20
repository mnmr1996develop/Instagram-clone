package com.michaelrichards.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class SignUp extends AppCompatActivity {

    private EditText firstName, lastName, username, password, email;
    private static final String EMAIL_FIELD = "email";
    private static final String FIRST_NAME_FIELD = "first_name";
    private static final String LAST_NAME_FIELD = "last_name";
    private static final String PASSWORD_FIELD = "password";
    private static final String USERNAME_FIELD = "username";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        email = findViewById(R.id.editTextEmail);


//        firstName.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    if (s.length() >= 1)
//                        firstName.setBackgroundResource(R.drawable.incorrect_border_bg);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
        Button loginButton = findViewById(R.id.signUpLoginButton);
        loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        Button signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(view -> {
            ParseUser newUser = new ParseUser();
            newUser.setUsername(username.getText().toString());
            newUser.setPassword(password.getText().toString());

            //newUser.setEmail(email.getText().toString());

            newUser.signUpInBackground(e -> {
               if (e == null){
                    Toast.makeText(SignUp.this, username.getText().toString() + "was created", Toast.LENGTH_LONG).show();
               }
               else {
                   e.printStackTrace();
               }
            });
        });
    }

    public void helloWorldTapped(View view){

        ParseObject users = new ParseObject("testUsers");
        String firstName = this.firstName.getText().toString();
        String lastName = this.lastName.getText().toString();
        String email = this.email.getText().toString();
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();

        users.put(FIRST_NAME_FIELD, firstName);
        users.put(LAST_NAME_FIELD, lastName);
        users.put(EMAIL_FIELD, email);
        users.put(USERNAME_FIELD, username);
        users.put(PASSWORD_FIELD, password);


       // renter.put("Income", next);
        users.saveInBackground(e -> {
            if (e == null){
                Toast.makeText(SignUp.this, "User: " + username + "is saved", Toast.LENGTH_LONG).show();
            }
        });
    }
}