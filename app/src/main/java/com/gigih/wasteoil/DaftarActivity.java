package com.gigih.wasteoil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DaftarActivity extends AppCompatActivity {

    private ImageView buttonBack;
    public FirebaseAuth auth;
    public TextView inputNama;
    private TextView inputNotlp;
    private TextView inputEmail;
    private TextView inputPass ;
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        buttonBack = findViewById(R.id.btn_back_daftar);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaftarActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        auth = FirebaseAuth.getInstance();

         inputNama = findViewById(R.id.reg_nama);
         inputNotlp = findViewById(R.id.reg_notelp);
        inputEmail = findViewById(R.id.tv_regEmail);
        inputPass = findViewById(R.id.tv_regPass);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Button btnDaftar = findViewById(R.id.btn_signUp);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inputNama.getText().toString().trim();
                String telp = inputNotlp.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String password = inputPass.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Masukan Nama Anda!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(telp)) {
                    Toast.makeText(getApplicationContext(), "Masukan No Telepon!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Masukan Email anda!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Masukan Password anda!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password terlalu pendek, minimum 6 karakter!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(DaftarActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(DaftarActivity.this, "anda berhasil login" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
//

                                if (!task.isSuccessful()) {
                                    Toast.makeText(DaftarActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(DaftarActivity.this, MainActivity.class));
                                    Toast.makeText(DaftarActivity.this, "anda berhasil login" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        });
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
