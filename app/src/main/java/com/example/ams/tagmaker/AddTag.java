package com.example.ams.tagmaker;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ams.tagmaker.Db.DataBaseHelper;
import com.example.ams.tagmaker.Util.UtilTools;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import static android.content.ContentValues.TAG;
import static com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE;

public class AddTag extends Activity {

    DataBaseHelper db = new DataBaseHelper(AddTag.this);
    UtilTools ut = new UtilTools();
    private int serial = 1;
    EditText tagName ,tagDescription ,secureDescription ,numberOfTagsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_tag);
        Button addTag = findViewById(R.id.AddTag);
        tagName = findViewById(R.id.itemname);
        tagDescription = findViewById(R.id.itemdescription);
        secureDescription = findViewById(R.id.secureDescriptionET);
        numberOfTagsText = findViewById(R.id.number_of_tags);
        boolean chkPermission = checkPermission();

        if (chkPermission) {

            Toast.makeText(AddTag.this, "Already have storage permission", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AddTag.this, "dnt have storage permission", Toast.LENGTH_SHORT).show();
        }
         addTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String Name = tagName.getText().toString() ;
                String Description = tagDescription.getText().toString() ;
                String SecureDescription = secureDescription.getText().toString() ;
                int noOfTags = Integer.parseInt(numberOfTagsText.getText().toString().trim());


                if ( Name != null && Description != null  && noOfTags != 0) {

                    // Defining the Document for the pdf here :
                    Document document = new Document();
                    String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
                    try {
                        PdfWriter.getInstance(document, new FileOutputStream(path + "/"+tagName.getText().toString()+"_BarCodes"+ut.getDateTime()+".pdf")); //  Change pdf's name.
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    document.open();


                    Paragraph p1 = new Paragraph("TagMaker Application");
                    Font paraFont= new Font(Font.FontFamily.COURIER);
                    p1.setAlignment(Paragraph.ALIGN_CENTER);
                    p1.setFont(paraFont);
                    try {
                        document.add(p1);
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }


                    for (int i = 1; i < noOfTags; i++) {

                        boolean result = db.insertTAG(serial, Name, Description, SecureDescription,noOfTags);
                        if (result) {
                            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                            try {
                                BitMatrix bitMatrix = multiFormatWriter
                                        .encode(serial + " :" + Name + " : " + Description, BarcodeFormat.QR_CODE, 80, 80);
                                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bos);
                                Image generatedBarcode = Image.getInstance(bos.toByteArray());
                                float scaler = ((document.getPageSize()
                                        .getWidth() - document.leftMargin() - document.rightMargin() - 0) / generatedBarcode.getWidth()) * 40; // 0 means you have no indentation. If you have any, change it.
                                generatedBarcode.scalePercent(scaler);
                                generatedBarcode.setAlignment(Image.ALIGN_CENTER | Image.ALIGN_TOP);
                                document.add(generatedBarcode);

                                //---------------------------inserting of current barcode to the document ends here ------------------------

                            } catch (WriterException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (BadElementException e) {
                                e.printStackTrace();
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                        } else {


                            ut.Dialog(AddTag.this, "Check Fields", "Tag was not added to the database");


                        }
                        serial++;
                    }
                    document.close();
                    Intent intent = new Intent(AddTag.this, TagsList.class);
                    startActivity(intent);

                } else {

                    ut.Dialog(AddTag.this,"Check Fields", "Maybe you left some necessary fields");
                }
            }


        });
    }

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {
                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }


}
