package com.h2woah;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.h2woah.model.User;

public class EditProfileActivity extends AppCompatActivity {

    private EditText passwordField;
    private EditText confirmPasswordField;
    private EditText emailField;
    private EditText address;
    private TextView userTypeLbl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(myToolbar);
        passwordField = (EditText) findViewById(R.id.passwordField);;
        emailField = (EditText) findViewById(R.id.emailField);
        address = (EditText) findViewById(R.id.address);
        userTypeLbl = (TextView) findViewById(R.id.userTypeLbl);
        setDefaultInfo(LoginActivity.currentUser);
        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(address.getText().toString().equals(LoginActivity.currentUser.getAddress()))) {
                    changeAddress(address.getText().toString());

                }
                if (passwordField.getText() != null && !passwordField.getText().toString().
                        equals(LoginActivity.currentUser.getPassword())) {
                    changePassword(passwordField.getText().toString());
                }
                Toast toast = Toast.makeText(getApplicationContext(), "Changes Saved", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout_action:
            {
                LoginActivity.logout();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_back:
            {
                NavUtils.navigateUpFromSameTask(this);
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Sets default placeholders for required fields
     * @param user  User holding the default info
     */
    private void setDefaultInfo(User user) {
        emailField.setText(user.getEmail(), TextView.BufferType.SPANNABLE);
        address.setText(user.getAddress(), TextView.BufferType.EDITABLE);
        userTypeLbl.setText("User Type: " + user.getUserLevel().toString());
    }

    private void changeAddress(String address) {
        LoginActivity.currentUser = new User(LoginActivity.currentUser.getEmail(),
                LoginActivity.currentUser.getPassword(), LoginActivity.currentUser.getUserLevel(),
                address);
    }

    public void changePassword(String password) {
        LoginActivity.currentUser = new User(LoginActivity.currentUser.getEmail(),
                password, LoginActivity.currentUser.getUserLevel(),
                LoginActivity.currentUser.getAddress());
    }
}
