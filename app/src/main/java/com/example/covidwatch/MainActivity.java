package com.example.covidwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;


public class MainActivity extends AppCompatActivity {
    private Button button;
    EditText edtUsername, edtPassword;
    boolean isAllFieldsChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();


        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        button = (Button)findViewById(R.id.btnLogin);

        myEdit.putString("name", edtUsername.getText().toString());
        System.out.println(edtUsername.getText().toString());
        myEdit.commit();


        // Method for opening security question page

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isAllFieldsChecked = CheckAllFields();

                if (isAllFieldsChecked) {
                    Intent i = new Intent(MainActivity.this, SecurityQuestionActivity.class);
                    startActivity(i);
                }
            }

            });
    }

    private boolean CheckAllFields() {
        if (edtUsername.length() == 0) {
            edtUsername.setError("Username should not be empty");
            return false;
        }

        if (edtPassword.length() == 0) {
            edtPassword.setError("Password should not be empty");
            return false;
        } else if (edtPassword.length() != 6) {
            edtPassword.setError("Password must be of 6 characters");
            return false;
        }

        // after all validation return true.
        return true;
    }
    public void resetPsw(View v){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.forgetpassword);
        bottomSheetDialog.show();
    }
}
