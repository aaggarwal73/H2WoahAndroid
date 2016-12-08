package com.h2woah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.h2woah.model.User;
import com.h2woah.model.UserLevel;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText confirmEmail;
    private EditText password;
    private EditText confirmPassword;
    private EditText address;
    private Spinner userLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userLevel = (Spinner) findViewById(R.id.userLevel);
        email = (EditText) findViewById(R.id.email);
        confirmEmail = (EditText) findViewById(R.id.confirmEmail);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        address = (EditText) findViewById(R.id.address);
        userLevel.setAdapter(new ArrayAdapter<UserLevel>(this, android.R.layout.simple_spinner_item, UserLevel.values()));
        Button registerButton = (Button) findViewById(R.id.rButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        // Store values at the time of the login attempt.
        String uname = email.getText().toString();
        String pass = password.getText().toString();
        String uname2 = confirmEmail.getText().toString();
        String pass2 = confirmPassword.getText().toString();
        String location = address.getText().toString();
        UserLevel level = (UserLevel) userLevel.getSelectedItem();
        //String userType = UserLevel.toString(level);
        if (uname.isEmpty() || pass.isEmpty() || location.isEmpty()) {
            Toast toast = Toast.makeText(this, "Fill in all fields", Toast.LENGTH_SHORT);
            toast.show();
        } else if (!(uname.equals(uname2))) {
            Toast toast = Toast.makeText(this, "Usernames are different", Toast.LENGTH_SHORT);
            toast.show();
        } else if (!(pass.equals(pass2))) {
            Toast toast = Toast.makeText(this, "Passwords are different", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            try {
                User person = new User(uname, pass, level, location);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("Users.txt", this.MODE_PRIVATE));
                outputStreamWriter.write(person.toString());
                outputStreamWriter.close();
                LoginActivity.currentUser = person;
                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
            } catch (IOException ex) {

            }
        }

    }
}
