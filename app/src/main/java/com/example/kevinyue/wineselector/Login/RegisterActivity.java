package com.example.kevinyue.wineselector.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kevinyue.wineselector.R;

/*
 *   https://www.youtube.com/watch?v=A6Jq7NVBVxU
 *   With this link I have got inspiration for registration from this video tutorials
 */

public class RegisterActivity extends AppCompatActivity {

    private EditText registerName, registerUsername, registerPassword, registerConfirmPassword;

    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.setTitle("Registrer");

        registerContent();
    }

    public void registerContent() {
        // Get the id from EditText
        registerName = (EditText) findViewById(R.id.name);
        registerUsername = (EditText) findViewById(R.id.username);
        registerPassword = (EditText) findViewById(R.id.password);
        registerConfirmPassword = (EditText) findViewById(R.id.confirmpassword);
    }

    public void onClickBtnRegister(View view) {
        // This is the same with if(view.getId() == R.id.registerButton)
        switch (view.getId()) {
            case R.id.registerButton:
                String name = registerName.getText().toString();
                String username = registerUsername.getText().toString();
                String password = registerPassword.getText().toString();
                String confirmPassword = registerConfirmPassword.getText().toString();

                // Password cannot be less than 8 character or empty
                if(password.isEmpty() && confirmPassword.isEmpty()) {
                    Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if(password.length() < 8 && confirmPassword.length() < 8) {
                    Toast.makeText(this, "Password must be 8 or more than characters", Toast.LENGTH_SHORT).show();
                }
                // Check the password and confirm password matches
                else if(password.equals(confirmPassword)) {
                    // Insert some information into database
                    User user = new User();
                    user.setName(name);
                    user.setUsername(username);
                    user.setPassword(password);

                    // Redirect back to login page
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);

                    databaseHelper.insertUser(user);
                }
                else {
                    // Message popup if the password don't match
                    Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
