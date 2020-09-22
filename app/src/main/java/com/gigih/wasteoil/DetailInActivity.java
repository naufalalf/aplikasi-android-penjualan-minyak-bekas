package com.gigih.wasteoil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailInActivity extends AppCompatActivity {
    ImageView ivminyakDetailIn;
    TextView tvJudulDetailIn,tvhargaDetailIn, tvliterDetailIn,tvstatusDetailIn;
    WebView webViewDetailIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_in);

        setGambar();
        setJudul();
        setHarga();
//        setLiter();
//        setStatus();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.setCancelable(false);
        webViewDetailIn = (WebView) findViewById(R.id.web_mapsDetailIn);
        webViewDetailIn.requestFocus();
        webViewDetailIn.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webViewDetailIn.getSettings().setBuiltInZoomControls(true);
        webViewDetailIn.getSettings().setJavaScriptEnabled(true);
        webViewDetailIn.getSettings().setLightTouchEnabled(true);
        webViewDetailIn.getSettings().setGeolocationEnabled(true);
        webViewDetailIn.loadUrl("https://www.google.com/maps/place/Surabaya,+Kota+SBY,+Jawa+Timur/@-7.2754438,112.6426427,12z/data=!3m1!4b1!4m5!3m4!1s0x2dd7fbf8381ac47f:0x3027a76e352be40!8m2!3d-7.2574719!4d112.7520883");
        webViewDetailIn.setWebViewClient(new WebViewClient(){
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100) {
                    progressDialog.show();
                }
                if (progress == 100) {
                    progressDialog.dismiss();
                }
            }
        });

        ImageView ivBack = (ImageView) findViewById(R.id.iv_backdetilIn);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailInActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void setGambar(){
        ivminyakDetailIn =findViewById(R.id.iv_gambarDetailIn);
        ivminyakDetailIn.setImageResource(getIntent().getIntExtra("gambar",R.drawable.gambar1));
    }
    public void setJudul(){
        tvJudulDetailIn = findViewById(R.id.tv_tokoDetailIn);
        tvJudulDetailIn.setText(getIntent().getStringExtra("judul"));
    }
    public void setHarga(){
        tvhargaDetailIn = findViewById(R.id.tv_hargaDetailIn);
        tvhargaDetailIn.setText(getIntent().getStringExtra("harga"));
    }
//    public void setLiter(){
//        tvliterDetailIn = findViewById(R.id.tv_literDetailIn);
//        tvliterDetailIn.setText(getIntent().getStringExtra("liter"));
//    }
//    public void setStatus(){
//        tvstatusDetailIn = findViewById(R.id.tv_statusDetailIn);
//        tvstatusDetailIn.setText(getIntent().getStringExtra("status"));
//    }
}
