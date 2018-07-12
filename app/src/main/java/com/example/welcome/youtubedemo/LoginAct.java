package com.example.welcome.youtubedemo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.perfectlib.ToastGenerator;

public class LoginAct extends AppCompatActivity {

    Button button;
    EditText t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button=findViewById(R.id.button_login);
        t1=findViewById(R.id.uname);
        t2=findViewById(R.id.password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected()) {
                    if (t1.getText().toString().equals("Demo@test.com")) {
                        Intent intent = new Intent(getApplicationContext(), YoutubeData.class);
                        ToastGenerator.successToast(getApplicationContext(), "Login Success");
                        startActivity(intent);
                        finish();
                    } else {
                        ToastGenerator.infoToast(LoginAct.this, "Default login set");
                        t1.setText("Demo@test.com");
                        t2.setText("123456");
                    }

                }
                else{
                    ToastGenerator.errorToast(getApplicationContext(),"Connection Lost !");

                }
            }

        });


    }
    boolean isConnected(){
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;
        return connected;
    }
}
