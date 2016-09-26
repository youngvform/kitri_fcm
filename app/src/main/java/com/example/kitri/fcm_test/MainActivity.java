package com.example.kitri.fcm_test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.webkit.JavascriptInterface;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import java.net.URL;
import java.net.URLEncoder;


public class MainActivity extends AppCompatActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";
    public static Boolean isVisible = false;


    String token="";
    String hUrl ="http://192.168.0.12:3000/";

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fcm push알림 사용 가능한 안드로이드인지 체크
        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.


            token = FirebaseInstanceId.getInstance().getToken();
            Log.i(TAG, "token: " + token);
            System.out.println("token ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + token);
        }

            mWebView = (WebView) findViewById(R.id.webview);

            //get방식으로 토큰 넘겨줌
            mWebView.loadUrl(hUrl + "endpoint?id=" + token);

    }

    //fcm 사용가능한 안드로이드 버전인지 체크하는 메소드
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported by Google Play Services.");
                ToastNotify("This device is not supported by Google Play Services.");
                finish();
            }
            return false;
        }
        return true;
    }

    public void ToastNotify(final String notificationMessage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, notificationMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    //상황별 푸시알림 허용설정
//    @Override
//    protected void onStart() {
//        super.onStart();
//        isVisible = true;
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        isVisible = false;
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        checkPlayServices();
//        isVisible = true;
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        isVisible = false;
//    }

}

