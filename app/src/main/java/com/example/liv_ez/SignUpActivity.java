package com.example.liv_ez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etPwd;
    private EditText etGroup;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etName = findViewById(R.id.etName);
        etPwd = findViewById(R.id.etPwd);
   //     etGroup = findViewById(R.id.etGroup);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
                goMainActivity();
                //TODO: Go to main afterwards
            }
        });


    }

    private void createUser() {
        ParseUser user = new ParseUser();

        user.setUsername(etName.getText().toString());
        user.setPassword(etPwd.getText().toString());
        //user.setGroup();

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e("SignUpActivity", "something went wrong", e);
                    Toast.makeText(SignUpActivity.this, "Uh oh, something went wrong, ensure username and password are unique", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.i("SignUpActivity", "Sign up successful!");
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();


    }
}
