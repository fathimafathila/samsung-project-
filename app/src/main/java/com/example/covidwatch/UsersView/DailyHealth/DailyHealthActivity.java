package com.example.covidwatch.UsersView.DailyHealth ;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.covidwatch.R;

import android.app.ProgressDialog;
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class DailyHealthActivity extends AppCompatActivity {
    Button fab;
    WebView feedbackformlink;
    public static String openURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_daily_health );

        setTitle("Your Feedback");

        feedbackformlink = (WebView)findViewById(R.id.link_webview);
        WebSettings webSettings = feedbackformlink.getSettings();

        feedbackformlink.setInitialScale(200);
        feedbackformlink.getSettings().setSupportZoom(true);
        feedbackformlink.getSettings().setLoadWithOverviewMode(true);
        feedbackformlink.getSettings().setBuiltInZoomControls(true);

        webSettings.setJavaScriptEnabled(true);
        feedbackformlink.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLScuT4uiQhV8Hdy5ksSY0mG0ROmT0n3F9COI6w86aaFauuzAAA/viewform?usp=sf_link");
        feedbackformlink.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {
        if (feedbackformlink.canGoBack()){
            feedbackformlink.goBack();
        } else
            super.onBackPressed();
    }






//        fab = (Button) findViewById( R.id.button );
//
//        fab.setOnClickListener( new View.OnClickListener() {
//
//                @Override
//                public void onClick(View view) {
//                     openURL = Intent( android.content.Intent.ACTION_VIEW );
//                    openURL.data = Uri.parse( "https://www.google.com/" );
//                    startActivity( openURL );
//                }
//            });
//
//    }

//    ProgressDialog progressDialog;
//Button fab;
//    EditText edtName, edtPhone;
//    RequestQueue queue;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_daily_health);
////        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
////        setSupportActionBar(toolbar);
//
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Loading...");
//
//        fab = (Button) findViewById(R.id.button);
//        edtName = (EditText) findViewById(R.id.edtName);
//        edtPhone = (EditText) findViewById(R.id.edtPhone);
//
//        // Initializing Queue for Volley
//        queue = Volley.newRequestQueue(getApplicationContext());
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (edtName.getText().toString().trim().length() > 0 && edtPhone.getText().toString().trim().length() > 0) {
//                    postData(edtName.getText().toString().trim(), edtPhone.getText().toString().trim());
//                } else {
//                    Snackbar.make(view, "Required Fields Missing", Snackbar.LENGTH_LONG).show();
//                }
//            }
//        });
//
//    }
//
//
//    public void postData(final String name, final String phone) {
//
//        progressDialog.show();
//        StringRequest request = new StringRequest(
//                Request.Method.POST,
//                Constants.url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("TAG", "Response: " + response);
//                        if (response.length() > 0) {
//                            Snackbar.make(fab, "Successfully Posted", Snackbar.LENGTH_LONG).show();
//                            edtName.setText(null);
//                            edtPhone.setText(null);
//                        } else {
//                            Snackbar.make(fab, "Try Again", Snackbar.LENGTH_LONG).show();
//                        }
//                        progressDialog.dismiss();
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressDialog.dismiss();
//                Snackbar.make(fab, "Error while Posting Data", Snackbar.LENGTH_LONG).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//                params.put(Constants.nameField, name);
//                params.put(Constants.phoneField, phone);
//                return params;
//            }
//        };
//        request.setRetryPolicy(new DefaultRetryPolicy(
//                50000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        queue.add(request);
//    }

}