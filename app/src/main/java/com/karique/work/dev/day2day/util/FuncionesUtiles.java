package com.karique.work.dev.day2day.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class FuncionesUtiles {

    public static void mostrarMensaje(String titulo, String mensaje, Context context){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        dialog.dismiss();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(mensaje)
                .setPositiveButton("Ok", dialogClickListener)
                //.setNegativeButton("No", dialogClickListener)
                .setTitle(titulo)
                .show();
    }

    public static Date parseFechaDate(String fechaFormatoADMS){
        try {
            Date dateADMS = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a").parse(fechaFormatoADMS);
            return dateADMS;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parseFechaApi(String fechaFormatoADMS){
        fechaFormatoADMS = fechaFormatoADMS.replace(".","").replace("am", "AM").replace("pm", "PM");
        String fechaFormatoLDS = "";

        try {
            Date dateADMS = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.US).parse(fechaFormatoADMS);
            fechaFormatoLDS = new SimpleDateFormat("yyyy-MM-dd").format(dateADMS);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fechaFormatoLDS;
    }

    public static String parseFechaADMSAApiLDSConHora(String fechaFormatoADMS){
        boolean esPM = fechaFormatoADMS.contains("p.m.");
        fechaFormatoADMS = fechaFormatoADMS.replace(".","").replace("am", "AM").replace("pm", "PM");

        String fechaFormatoLDS = "";
        Date dateADMS = null;

        try {
            dateADMS = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.US).parse(fechaFormatoADMS);
            //if (esPM)
            //    dateADMS = addHours(dateADMS, 12);
            fechaFormatoLDS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateADMS);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fechaFormatoLDS;
    }

    public static Date addHours(Date date, int hoursToAdd){
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(date); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, hoursToAdd); // adds hours
        return cal.getTime();
    }

    public static Date getCurrentDate(){
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        return localCalendar.getTime();
    }

    public static String getCurrentDateStr(){
        Date date = getCurrentDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static String formatDateForAPIWithHour(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static void hideKeyboardFromActivity(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
