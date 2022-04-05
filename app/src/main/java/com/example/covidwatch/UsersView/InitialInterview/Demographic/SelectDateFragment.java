package com.example.covidwatch.UsersView.InitialInterview.Demographic;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.example.covidwatch.R;

import java.util.Calendar;

public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
{

    public EditText activity_edittext;

    @SuppressLint("ValidFragment")
    public SelectDateFragment(EditText edit_text) {
        activity_edittext = edit_text;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    }

    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
        activity_edittext.setText(String.valueOf(yy ) + "/" +   String.valueOf(mm + 1) + "/" + String.valueOf(dd));
    }
}


