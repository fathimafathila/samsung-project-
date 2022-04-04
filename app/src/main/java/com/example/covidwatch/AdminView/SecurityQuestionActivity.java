package com.example.covidwatch.AdminView ;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covidwatch.AdminView.AdminDashboard;
import com.example.covidwatch.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SecurityQuestionActivity extends AppCompatActivity {
    private Button button;
    EditText edtSecurity;
    TextView txtSecurity;
    boolean isAllFieldsChecked = false;

    FirebaseAuth fAuth ;
    FirebaseFirestore db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_question);


        // Method for admin dashboard
        button = (Button)findViewById(R.id.btnProceed);
        edtSecurity = findViewById(R.id.edtSecurity);
        txtSecurity = findViewById(R.id.txtSecurity);
        db = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();


        // Get Security Question Form Database
        DocumentReference documentReference = db.collection("users").document(fAuth.getCurrentUser().getUid());
        documentReference
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        txtSecurity.setText(documentSnapshot.getString("Security Question"));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SecurityQuestionActivity.this,"Not Working",Toast.LENGTH_SHORT).show();
                    }
                });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isAllFieldsChecked = CheckAllFields();

                if (isAllFieldsChecked) {
                    // convert string to lower case  text
                    String sec = edtSecurity.getText().toString().toLowerCase();

                    // Check Security Answer with database
                    DocumentReference documentReference = db.collection("users").document(fAuth.getCurrentUser().getUid());
                    documentReference
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if(documentSnapshot.getString("Security Answer").equals(sec)){
                                            openSecurityPage();
                                    }else{
                                        edtSecurity.setError("Invalid Answer");
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });
                }

            }
        });
    }

    public void openSecurityPage(){
        Intent intent = new Intent(this, AdminDashboard.class);
        startActivity(intent);
        finish();
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