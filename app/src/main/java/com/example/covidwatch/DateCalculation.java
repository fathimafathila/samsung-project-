package com.example.covidwatch;

import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateCalculation {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String currentDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String todayDate = dateFormat.format(date);
        return todayDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int findDifference(String end_date)
    {

        String todayDate = currentDate();

        //Covert to Local Day Format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate td = LocalDate.parse(todayDate,formatter);
        LocalDate ed = LocalDate.parse(end_date,formatter);
        Period diff = Period.between(td,ed);
        return  diff.getDays();
    }



    public String findEndDate(String start_date){

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            Date date = sdf1.parse(start_date);
            String date1 = sdf.format(date) ;
            cal.setTime(sdf.parse(date1));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        cal.add(Calendar.DAY_OF_MONTH,6);

        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        return sd.format(cal.getTime());
    }

    public String findAge(int year,int month,int day){
        LocalDate today = null;
        LocalDate past = null;
        int y = 0 ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            today = LocalDate.now();
            past = LocalDate.of(year,month,day);
            y = Period.between(past,today).getYears();
        }
        return String.valueOf(y);
    }

    public String findMinor(int age){
        if( age < 18 ){
           return  "Yes";
        }else{
            return "No";
        }
    }

}
