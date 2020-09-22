package com.gigih.wasteoil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;

import static android.text.TextUtils.isEmpty;

public class JualMinyakActivity extends AppCompatActivity
        implements View.OnClickListener {

    private EditText Jumlahstock, Harga, Lokasi;
    private FirebaseAuth auth;
    private Button Simpan,Batal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jual_minyak);
        //Inisialisasi ID (Button)

        Batal = findViewById(R.id.btn_batal);
        Batal.setOnClickListener(this);
        Simpan = findViewById(R.id.btn_simpan);
        Simpan.setOnClickListener(this);

        auth = FirebaseAuth.getInstance(); //Mendapakan Instance Firebase Autentifikasi

        //Inisialisasi ID (EditText)
        Jumlahstock = findViewById(R.id.new_stock);
        Harga = findViewById(R.id.new_harga);
        Lokasi = findViewById(R.id.new_lokasi);

    }
    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_simpan:
                //Mendapatkan UserID dari pengguna yang Terautentikasi
                String getUserID = auth.getCurrentUser().getUid();

                //Mendapatkan Instance dari Database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference getReference;

                //Menyimpan Data yang diinputkan User kedalam Variable
                String getJumlahstock = Jumlahstock.getText().toString();
                String getHarga = Harga.getText().toString();
                String getLokasi = Lokasi.getText().toString();

                getReference = database.getReference(); // Mendapatkan Referensi dari Database

                // Mengecek apakah ada data yang kosong
                if (isEmpty(getJumlahstock) || isEmpty(getHarga) || isEmpty(getLokasi)) {
                    //Jika Ada, maka akan menampilkan pesan singkan seperti berikut ini.
                    Toast.makeText(JualMinyakActivity.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                } else {
                    /*
                    Jika Tidak, maka data dapat diproses dan meyimpannya pada Database
                    Menyimpan data referensi pada Database berdasarkan User ID dari masing-masing Akun
                    */
                    getReference.child("Admin").child(getUserID).child("Minyak").push()
                            .setValue(new data_minyak(getJumlahstock,getHarga,getLokasi))
                            .addOnSuccessListener(JualMinyakActivity.this, new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    //Peristiwa ini terjadi saat user berhasil menyimpan datanya kedalam Database
                                    Jumlahstock.setText("");
                                    Harga.setText("");
                                    Lokasi.setText("");
                                    Toast.makeText(JualMinyakActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                break;
            case R.id.btn_batal:
               Intent intent = new Intent(this,MainActivity.class);
               startActivity(intent);
                break;
        }
    }
}
