package com.gigih.wasteoil;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    ImageView ivminyakDetail;
    TextView tvJudulDetail,tvhargaDetail;
    TextView tvliterDetail,tvstatusDetail;
    WebView webViewDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setGambar();
        setJudul();
        setHarga();
//        setLiter();
//        setStatus();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.setCancelable(false);
        webViewDetail = (WebView) findViewById(R.id.web_mapsDetail);
        webViewDetail.requestFocus();
        webViewDetail.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webViewDetail.getSettings().setBuiltInZoomControls(true);
        webViewDetail.getSettings().setJavaScriptEnabled(true);
        webViewDetail.getSettings().setLightTouchEnabled(true);
        webViewDetail.getSettings().setGeolocationEnabled(true);
        webViewDetail.loadUrl("https://www.google.com/maps/place/Surabaya,+Kota+SBY,+Jawa+Timur/@-7.2754438,112.6426427,12z/data=!3m1!4b1!4m5!3m4!1s0x2dd7fbf8381ac47f:0x3027a76e352be40!8m2!3d-7.2574719!4d112.7520883");
        webViewDetail.setWebViewClient(new WebViewClient(){
            public void onProgressChanged(WebView view, int progress) {
            if (progress < 100) {
                progressDialog.show();
            }
            if (progress == 100) {
                progressDialog.dismiss();
            }
        }
    });



        ImageView ivBack = (ImageView) findViewById(R.id.iv_backdetil);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DetailActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }



    public void setGambar(){
        ivminyakDetail =findViewById(R.id.iv_gambarDetail);
        ivminyakDetail.setImageResource(getIntent().getIntExtra("gambar",R.drawable.gambar1));
    }
    public void setJudul(){
        tvJudulDetail = findViewById(R.id.tv_tokoDetail);
        tvJudulDetail.setText(getIntent().getStringExtra("judul"));
    }
    public void setHarga(){
        tvhargaDetail = findViewById(R.id.tv_hargaDetail);
        tvhargaDetail.setText(getIntent().getStringExtra("harga"));
    }
//    public void setLiter(){
//        tvliterDetail = findViewById(R.id.tv_literDetail);
//        tvliterDetail.setText(getIntent().getStringExtra("liter"));
//    }
//    public void setStatus(){
//        tvstatusDetail = findViewById(R.id.tv_statusDetail);
//        tvstatusDetail.setText(getIntent().getStringExtra("status"));
//    }

}
