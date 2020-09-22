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

public class AdapterTerdekat extends RecyclerView.Adapter<AdapterTerdekat.ViewHolder> {

    private ArrayList<String> rvToko1;
    private ArrayList<String> rvHarga1;
    private ArrayList<Integer> rvGambar1;
    ArrayList<String> rvLiter1;
    ArrayList<String> rvStatus1;
    Activity activity;

    public AdapterTerdekat(ArrayList<String> rvToko1, ArrayList<String> rvHarga1, ArrayList<Integer> rvGambar1,
                           ArrayList<String> rvLiter1,ArrayList<String> rvStatus1, Activity activity){

        this.rvGambar1 = rvGambar1;
        this.rvHarga1 = rvHarga1;
        this.rvToko1 = rvToko1;
        this.rvLiter1 = rvLiter1;
        this.rvStatus1 = rvStatus1;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvWarung1, tvHarga1, tvLiter1, tvStatus1;
        public ImageView ivProduk1;
        public CardView cvItem1;

        public ViewHolder(View v){
            super(v);
            ivProduk1 = (ImageView) v.findViewById(R.id.iv_item1);
            tvWarung1 = (TextView) v.findViewById(R.id.tv_toko1);
            tvHarga1 = (TextView) v.findViewById(R.id.tv_harga1);
            tvLiter1 = v.findViewById(R.id.tv_literDetail);
            tvStatus1 = v.findViewById(R.id.tv_statusDetail);
            cvItem1 = v.findViewById(R.id.cvItem1);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
       ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final  String name = rvToko1.get(position);
        holder.ivProduk1.setImageResource(rvGambar1.get(position));
        holder.tvWarung1.setText(name);
        holder.tvHarga1.setText(rvHarga1.get(position));
        holder.cvItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyItem(activity).ItemTap(holder.cvItem1);
                Intent intent = new Intent(activity,DetailInActivity.class);
                intent.putExtra("judul", name);
                intent.putExtra("gambar", rvGambar1.get(position));
                intent.putExtra("harga",rvHarga1.get(position));
//                intent.putExtra("status",rvStatus.get(position));
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rvToko1.size();
    }
}
