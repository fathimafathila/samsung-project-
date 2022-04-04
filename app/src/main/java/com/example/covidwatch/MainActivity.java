package com.example.covidwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covidwatch.AdminView.AdminDashboard;
import com.example.covidwatch.AdminView.SecurityQuestionActivity;
import com.example.covidwatch.UsersView.UserDashboardActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private Button button;
    EditText edtUsername, edtPassword;
    Switch saveCredential ;
    boolean isAllFieldsChecked = false;

    FirebaseAuth fAuth ;
    FirebaseFirestore db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        button = (Button)findViewById(R.id.btnLogin);
        saveCredential = findViewById(R.id.switch1);

        boolean s = sharedPreferences.getBoolean("save",false);
        saveCredential.setChecked(s);

        if(saveCredential.isChecked()){
             edtUsername.setText(sharedPreferences.getString("username",""));
             edtPassword.setText(sharedPreferences.getString("password",""));
        }

        myEdit.putString("name", edtUsername.getText().toString());
        System.out.println(edtUsername.getText().toString());
        myEdit.commit();

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Method for opening security question page





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(saveCredential.isChecked()){
                    myEdit.putBoolean("save",true);
                    myEdit.putString("username",edtUsername.getText().toString());
                    myEdit.putString("password",edtPassword.getText().toString());
                }else{
                    myEdit.putBoolean("save",false);
                    myEdit.putString("username","");
                    myEdit.putString("password","");
                }
                myEdit.commit();

                isAllFieldsChecked = CheckAllFields();
                String txt_userName = edtUsername.getText().toString();
                String txt_password = edtPassword.getText().toString();

                if (isAllFieldsChecked) {
                    fAuth.signInWithEmailAndPassword(txt_userName,txt_password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            String id = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = db.collection("users").document(id);
                            documentReference
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            String name = documentSnapshot.getString("Admin").toString();
                                            System.out.println("");
                                            if( name.equals("0")){
                                                Intent i = new Intent(MainActivity.this, SecurityQuestionActivity.class);
                                                startActivity(i);
                                                finish();
                                                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                                myEdit.putString("AdminId", fAuth.getCurrentUser().getUid());
                                                myEdit.commit();

                                            }else{
                                                Intent intent = new Intent(MainActivity.this, UserDashboardActivity.class);
                                                intent.putExtra("uuid","");
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(MainActivity.this,"About Page",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this,"Incorrect email id or password",Toast.LENGTH_SHORT).show();
                            TextView credentialError = findViewById(R.id.errormsg);
                            credentialError.setVisibility(View.VISIBLE);
                        }
                    });
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
        Button btn_reset = bottomSheetDialog.findViewById(R.id.button3);

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = bottomSheetDialog.findViewById(R.id.editTextForgetEmail);
                TextView error = bottomSheetDialog.findViewById(R.id.errormsg);

                if(email.length() != 0 ) {
                    fAuth.sendPasswordResetEmail(email.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    bottomSheetDialog.hide();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    error.setVisibility(View.VISIBLE);
                                }
                            });
                }else{
                    email.setError("Enter Email Id");
                }
            }
        });
        bottomSheetDialog.show();
    }
}
