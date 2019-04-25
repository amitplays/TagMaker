package com.example.ams.tagmaker;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.content.ContentValues.TAG;

public class ScanTag extends Activity {

    static TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_tag);
        TextView heading = findViewById(R.id.heading);
         result = findViewById(R.id.textView);
        Button ShowDetails = findViewById(R.id.ShowDetails);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/fontBold.ttf");
        heading.setTypeface(typeface);

        Intent start = new Intent(ScanTag.this,ScanActivity.class);
        startActivity(start);



        ShowDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent start = new Intent(ScanTag.this,ScanActivity.class);
                startActivity(start);
            }

        });





    }



}
