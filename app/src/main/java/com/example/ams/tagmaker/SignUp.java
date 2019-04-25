package com.example.ams.tagmaker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ams.tagmaker.Db.DataBaseHelper;

public class SignUp extends Activity {


    private EditText name, email , password;
    private Button AddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

       final DataBaseHelper db = new DataBaseHelper(SignUp.this);

        name = findViewById(R.id.username);
        email = findViewById(R.id.emailET);
        password = findViewById(R.id.passwordET);
        AddUser = findViewById(R.id.AddUser);


        AddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("") || email.getText().toString().equals("") ||   password.getText().toString().equals("")) {

                    Toast.makeText(SignUp.this,"Please enter all the information",Toast.LENGTH_LONG).show();

                }else{

                db.insertUser(name.getText().toString(),email.getText().toString(),password.getText().toString());
                    Toast.makeText(SignUp.this,"User Added ! Use email to Login",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }
}
