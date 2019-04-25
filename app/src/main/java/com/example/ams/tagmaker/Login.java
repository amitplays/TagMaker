package com.example.ams.tagmaker;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ams.tagmaker.Db.DataBaseHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class Login extends Activity {
    private FirebaseAuth mAuth;

    Button SignUp, Login;
    ImageView logo;
    EditText username, password;
    static String currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Initialize Firebase Auth
       final DataBaseHelper db = new DataBaseHelper(Login.this);
        mAuth = FirebaseAuth.getInstance();
        SignUp = findViewById(R.id.signUpBtn);
        logo = findViewById(R.id.logo);
        Login = findViewById(R.id.login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.pass);

        Animation leftToRight = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        Animation rightToLeft = AnimationUtils.loadAnimation(this, R.anim.right_to_left);
        Animation up = AnimationUtils.loadAnimation(this, R.anim.slide_up_info);
        username.startAnimation(leftToRight);
        password.startAnimation(rightToLeft);
        SignUp.startAnimation(up);
        Login.startAnimation(up);



        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);


            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(username.getText().toString().equals("")|| password.getText().toString().equals("")){

                    Toast.makeText(Login.this,"Please fill in all the details",Toast.LENGTH_LONG).show();


                }else{

                    Cursor x =  db.login(username.getText().toString());
                    while (x.moveToFirst()){

                        if ( password.getText().toString().equals(x.getString(x.getColumnIndexOrThrow("UserPassword")))){
                            Toast.makeText(Login.this,"Login Successful !",Toast.LENGTH_LONG).show();

                                Intent start = new Intent(Login.this, MainActivity.class);
                                currentUser = username.getText().toString();
                                startActivity(start);
                                username.setText("");
                                password.setText("");
                                break;

                        }else {

                            Toast.makeText(Login.this,"Invalid Login Details PLease Try Again Later !",Toast.LENGTH_LONG).show();
                                break;

                        }

                    }

                }






            }
        });





    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //    updateUI(currentUser);

    }


}
