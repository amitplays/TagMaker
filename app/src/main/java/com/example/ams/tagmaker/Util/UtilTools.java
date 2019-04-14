package com.example.ams.tagmaker.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilTools {

    public String getDateTime(){


        Date currentTime = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM");
        String formattedDate = df.format(currentTime);

        return formattedDate;

    }

    public void Dialog(final Context xz,String Title, String Message ){

        new AlertDialog.Builder(xz)
                .setTitle(Title)
                .setMessage(Message)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }


}
