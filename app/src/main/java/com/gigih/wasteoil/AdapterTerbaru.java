package com.gigih.wasteoil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterTerbaru extends RecyclerView.Adapter<AdapterTerbaru.ViewHolder> {

     ArrayList<String> rvToko;
     ArrayList<String> rvHarga;
     ArrayList<Integer> rvGambar;
    ArrayList<String> rvLiter;
    ArrayList<String> rvStatus;
    Activity activity;



    public AdapterTerbaru(ArrayList<String> rvToko, ArrayList<Integer> rvGambar, ArrayList<String> rvHarga,
                          ArrayList<String> rvLiter,ArrayList<String> rvStatus, Activity activity){

        this.rvGambar = rvGambar;
        this.rvHarga = rvHarga;
        this.rvToko = rvToko;
        this.rvLiter = rvLiter;
        this.rvStatus = rvStatus;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvWarung, tvHarga, tvLiter, tvStatus;
        public ImageView ivProduk;
        public CardView cvItem;


        public ViewHolder(View v){
            super(v);
            ivProduk = (ImageView) v.findViewById(R.id.iv_item);
            tvWarung = (TextView) v.findViewById(R.id.tv_toko);
            tvHarga = (TextView) v.findViewById(R.id.tv_harga);
            tvLiter = v.findViewById(R.id.tv_literDetail);
            tvStatus = v.findViewById(R.id.tv_statusDetail);
            cvItem = v.findViewById(R.id.cvItem);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
       ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final  String name = rvToko.get(position);
        holder.ivProduk.setImageResource(rvGambar.get(position));
        holder.tvWarung.setText(name);
        holder.tvHarga.setText(rvHarga.get(position));
//        holder.tvLiter.setText(rvLiter.get(position));
//        holder.tvStatus.setText(rvStatus.get(position));
        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyItem(activity).ItemTap(holder.cvItem);
                Intent intent = new Intent(activity,DetailActivity.class);
                intent.putExtra("judul", name);
                intent.putExtra("gambar", rvGambar.get(position));
//                intent.putExtra("liter", rvLiter.get(position));
                intent.putExtra("harga",rvHarga.get(position));
//                intent.putExtra("status",rvStatus.get(position));
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rvToko.size();
    }
}
