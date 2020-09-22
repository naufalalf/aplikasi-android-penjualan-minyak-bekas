package com.gigih.wasteoil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MasukActivity extends AppCompatActivity {

    ImageView btnMasuk;
    public EditText inputEmail;
    private EditText inputPass;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button buttonLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(MasukActivity.this, MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_masuk);

        btnMasuk =  findViewById(R.id.btn_back_masuk);
        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MasukActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        inputEmail = (EditText) findViewById(R.id.tv_logEmail);
        inputPass = (EditText) findViewById(R.id.tv_logPass);
        progressBar = (ProgressBar) findViewById(R.id.progressBarLog);
        buttonLogin = (Button) findViewById(R.id.btn_login);

        auth = FirebaseAuth.getInstance();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();

                final String password = inputPass.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Masukan Email Anda!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Masukan Password Anda!", Toast.LENGTH_SHORT).show();
                    return;
                }



                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MasukActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPass.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(MasukActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(MasukActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });


    }
}
