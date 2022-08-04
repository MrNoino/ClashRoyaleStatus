package com.example.clashroyalestatus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private final String ApiKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi" +
            "03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWF" +
            "waSIsImp0aSI6ImVlOGU4Yjg1LTg4NGEtNDZjZi1hNTY1LTU0ZTM0ZTY5NGNlYSIsImlhdCI6MTYzMjQxNzgyOSw" +
            "ic3ViIjoiZGV2ZWxvcGVyLzhkYTY4Y2IxLTFkZWUtMmJhMC03MTcwLTJmMDkyOTA5N2RmYyIsInNjb3BlcyI6WyJyb3lhb" +
            "GUiXSwibGltaXRzIjpbeyJ0aWVyIjoiZGV2ZWxvcGVyL3NpbHZlciIsInR5cGUiOiJ0aHJvdHRsaW5nIn0seyJjaWRycyI6W" +
            "yIxMjguMTI4LjEyOC4xMjgiXSwidHlwZSI6ImNsaWVudCJ9XX0.4UB172oOkX92tX8JIviGaHFl13LyCH9Pa5hwtHy141fYKFg" +
            "SmL7OMuWTyS5FKrep18bEC1X9QEsIPfSzRnyHLg";

    TextView et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.txtview);

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    JsonUpComingChestsRequest(getApplicationContext(), ApiKey);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        });

        thread.start();

    }

    private void JsonUpComingChestsRequest(Context context, String token) {

        try {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://proxy.royaleapi.dev/v1/players/%232CUQYY9YU/upcomingchests")
                    .get()
                    .addHeader("auth", ApiKey)
                    .build();

            Response response = client.newCall(request).execute();

            et.setText(response.toString());

        }catch (IOException e){

            e.printStackTrace();

        }


    }

    private void handleResponse(Context context, String json){

        Toast.makeText(context, json, Toast.LENGTH_LONG).show();

    }


}