package com.example.covidwatch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecurityQuestionActivity extends AppCompatActivity {
    private Button button;
    EditText edtSecurity;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_question);


        // Method for admin dashboard
        button = (Button)findViewById(R.id.btnProceed);
        edtSecurity = findViewById(R.id.edtSecurity);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isAllFieldsChecked = CheckAllFields();

                if (isAllFieldsChecked) {
                    openSecurityPage();
                }

            }
        });
    }

    public void openSecurityPage(){
        Intent intent = new Intent(this, AdminDashboard.class);
        startActivity(intent);
    }

    private boolean CheckAllFields() {
        if (edtSecurity.length() == 0) {
            edtSecurity.setError("Security answer should not be empty");
            return false;
        }

        // after all validation return true.
        return true;
    }
}