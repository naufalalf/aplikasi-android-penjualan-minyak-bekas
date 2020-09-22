package com.gigih.wasteoil;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterGridTerbaru extends RecyclerView.Adapter<AdapterGridTerbaru.ViewHolder> {

    private ArrayList<String> rvTokoG;
    private ArrayList<String> rvHargaG;
    private ArrayList<Integer> rvGambarG;
    private ArrayList<String> rvButtonG;
    Activity activity;

    public AdapterGridTerbaru(ArrayList<String> rvTokoG, ArrayList<Integer> rvGambarG, ArrayList<String> rvHargaG,
                              ArrayList<String> rvButtonG, Activity activity){

        this.rvGambarG = rvGambarG;
        this.rvHargaG = rvHargaG;
        this.rvTokoG = rvTokoG;
        this.rvButtonG = rvButtonG;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvWarungG, tvHargaG,btnAmbilG;
        public ImageView ivProdukG;
        public CardView cvItemG;


        public ViewHolder(View v){
            super(v);
            ivProdukG = (ImageView) v.findViewById(R.id.iv_itemG);
            tvWarungG = (TextView) v.findViewById(R.id.tv_tokoG);
            tvHargaG = (TextView) v.findViewById(R.id.tv_hargaG);
            btnAmbilG = (TextView) v.findViewById(R.id.btn_ambil);
            cvItemG = (CardView) v.findViewById(R.id.cv_itemDet);
        }
    }

    @NonNull
    @Override
    public AdapterGridTerbaru.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item3, parent, false);
        AdapterGridTerbaru.ViewHolder vh = new AdapterGridTerbaru.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterGridTerbaru.ViewHolder holder, final int position) {
        final  String name = rvTokoG.get(position);
        holder.ivProdukG.setImageResource(rvGambarG.get(position));
        holder.tvWarungG.setText(name);
        holder.tvHargaG.setText(rvHargaG.get(position));
        holder.btnAmbilG.setText(rvButtonG.get(position));
        holder.cvItemG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyItem(activity).ItemTap(holder.cvItemG);
                Intent intent = new Intent(activity,DetailInActivity.class);
                intent.putExtra("judul", name);
                intent.putExtra("gambar", rvGambarG.get(position));
//                intent.putExtra("liter", rvLiter.get(position));
                intent.putExtra("harga",rvHargaG.get(position));
//                intent.putExtra("status",rvStatus.get(position));
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rvTokoG.size();
    }
}
