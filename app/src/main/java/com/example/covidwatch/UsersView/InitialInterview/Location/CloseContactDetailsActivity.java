package com.example.covidwatch.UsersView.InitialInterview.Location;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covidwatch.R;

import java.util.ArrayList;
import java.util.Calendar;

public class CloseContactDetailsActivity extends AppCompatActivity {

    boolean isAllFieldsChecked = false;
    private Calendar calendar;
    private EditText edtDob;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_contact_details);

        edtDob = findViewById(R.id.edtDob);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        initUI();
    }

    // Setting date of birth
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(997);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 997) {
            return new DatePickerDialog(this,
                    myDateListener1, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener1 = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate1(arg1, arg2+1, arg3);
                }
            };


    // Showing date of birth
    private void showDate1(int year, int month, int day) {
        edtDob.setText(new StringBuilder().append(month).append("/")
                .append(day).append("/").append(year));

    }

    private void initUI()
    {
        //UI reference of textView
        final AutoCompleteTextView customerAutoTV1 = findViewById(R.id.edtAddressType);

        // create list of customer
        ArrayList<String> List1 = getAddressType();

        //Create adapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(CloseContactDetailsActivity.this, android.R.layout.simple_spinner_item, List1);

        //Set adapter
        customerAutoTV1.setAdapter(adapter1);

    }

    private ArrayList<String> getAddressType()
    {
        ArrayList<String> genders = new ArrayList<>();
        genders.add("Residence");
        genders.add("Work");
        return genders;
    }
}