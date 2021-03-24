package com.example.kevinyue.wineselector.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kevinyue.wineselector.Element.ElementsActivity;
import com.example.kevinyue.wineselector.R;

public class LoginActivity extends AppCompatActivity {

    EditText loginUsername;
    EditText loginPassword;

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Login");

        // Get the id from EditText
        loginUsername = (EditText) findViewById(R.id.emailusername);
        loginPassword = (EditText) findViewById(R.id.password);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(loginUsername, InputMethodManager.SHOW_IMPLICIT);
    }


    public void onClickBtnLogin(View view) {
        // Test the login information
        switch (view.getId()) {
            case R.id.loginButton:
                loginUsername = (EditText) findViewById(R.id.emailusername);
                String username = loginUsername.getText().toString();
                loginPassword = (EditText) findViewById(R.id.password);
                String password = loginPassword.getText().toString();

                String pwd = helper.findPassword(username);

                // Check the username and password is not matching
                if(password.equals(pwd)) {
                    Intent intent = new Intent(this, ElementsActivity.class);
                    intent.putExtra("Username", username);
                    startActivity(intent);
                } else if(password.isEmpty() || username.isEmpty()) {
                    Toast.makeText(this, "Username and password cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "Username and password isn't correct", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.register:
                // The register link is clicked, go to the register page
                Intent intentRegister = new Intent(this, RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }

    }

}
