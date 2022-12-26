package com.example.tugaslatihanpenyimpananinternal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class DaftarActivity extends AppCompatActivity {

    ImageView tombolKembali;
    Button tombolDaftar;
    EditText inputNim, inputNama, inputPassword;
    AppDatabase db;

    //Menjalankan method Insert Data
    @SuppressLint("StaticFieldLeak")
    private void insertData(final User user){
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                //Menjalankan proses insert data
                db.userDao().insertUser(user);
                return "berhasil";
            }

            @Override
            protected void onPostExecute(String status) {
                //Menandakan bahwa data berhasil disimpan
                Toast.makeText(DaftarActivity.this,
                        "Akun dengan NIM " + user.nim + " berhasil dibuat silahkan login menggunakan " +
                                "Akun yang telah dibuat",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        }.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "latihan").build();
        tombolKembali = findViewById(R.id.tombolKembali);
        tombolDaftar = findViewById(R.id.tombolDaftar);
        inputNim = findViewById(R.id.inputNim);
        inputNama = findViewById(R.id.inputNama);
        inputPassword = findViewById(R.id.inputPassword);

        tombolKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tombolDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = new User();
                user.nim = inputNim.getText().toString();
                user.nama = inputNama.getText().toString();
                user.password = inputPassword.getText().toString();

                if (user.nim.trim().equals("") || user.nama.trim().equals("") ||
                        user.password.trim().equals("")) {
                    Toast.makeText(DaftarActivity.this, "datanya diisi dulu", Toast.LENGTH_SHORT).show();
                    return;
                }

                insertData(user);
            }
        });
    }
}