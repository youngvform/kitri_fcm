package com.example.kitri.fcm_test;

import android.content.Intent;
import android.util.Log;
import android.webkit.WebView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 토큰 생성 클래스
 * Created by kitri on 2016-08-16.
 */
public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        Log.d(TAG, "Refreshing GCM Registration Token");

        String token = FirebaseInstanceId.getInstance().getToken();

        Log.i(TAG, "token: " + token);
        System.out.println("token !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+token);

    }

}
