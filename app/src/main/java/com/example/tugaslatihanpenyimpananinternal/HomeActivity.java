package com.example.tugaslatihanpenyimpananinternal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button tombolLogout;

    private void cekLogin() {
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        boolean login = sharedPref.getBoolean("login", false);

        if (!login) {
            Intent intentMain = new Intent(HomeActivity.this, MainActivity.class);
            intentMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentMain);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tombolLogout = findViewById(R.id.tombolLogout);

        tombolLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                SharedPreferences sharedPref = context.getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.clear();
                editor.commit(); // commit changes
                Intent intentMain = new Intent(HomeActivity.this, MainActivity.class);
                intentMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentMain);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        cekLogin();
    }
}