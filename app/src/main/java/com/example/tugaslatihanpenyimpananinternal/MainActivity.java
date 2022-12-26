package com.example.tugaslatihanpenyimpananinternal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button tombolDaftar, tombolLogin;
    private EditText inputNim, inputPassword;
    private AppDatabase db;

    //Menjalankan method Insert Data
    @SuppressLint("StaticFieldLeak")
    private void prosesLogin(final String nim, final String password){
        new AsyncTask<Void, Void, User>() {
            @Override
            protected User doInBackground(Void... voids) {
                //Menjalankan proses insert data
                return db.userDao().findByNim(nim);

            }

            @Override
            protected void onPostExecute(User user) {
                //Menandakan bahwa data berhasil disimpan
                if (user == null) {
                    Toast.makeText(MainActivity.this, "Akun " + nim +
                            " belum terdaftar", Toast.LENGTH_LONG).show();
                }

                else if (user.nim.equals(nim) && !user.password.equals(password)) {
                    Toast.makeText(MainActivity.this, "Password yang anda masukkan salah",
                            Toast.LENGTH_LONG).show();
                }

                else {
                    Context context = getApplicationContext();
                    SharedPreferences sharedPref = context.getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("nim", nim);
                    editor.putString("password", password);
                    editor.putBoolean("login", true);
                    editor.commit();
                    Intent intentHome = new Intent(MainActivity.this, HomeActivity.class);
                    intentHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intentHome);
                }

            }
        }.execute();
    }

    private void cekLogin() {
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        boolean login = sharedPref.getBoolean("login", false);

        if (login) {
            Intent intentHome = new Intent(MainActivity.this, HomeActivity.class);
            intentHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentHome);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        tombolLogin = findViewById(R.id.tombolLogin);
        tombolDaftar = findViewById(R.id.tombolDaftar);

        inputNim = findViewById(R.id.inputNim);
        inputPassword = findViewById(R.id.inputPassword);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "latihan").build();

        tombolLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nim = inputNim.getText().toString();
                String password = inputPassword.getText().toString();
                prosesLogin(nim, password);
            }
        });

        tombolDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentDaftar = new Intent(MainActivity.this, DaftarActivity.class);
                startActivity(intentDaftar);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cekLogin();
    }
}