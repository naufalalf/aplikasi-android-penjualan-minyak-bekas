package com.gigih.wasteoil;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterTambah extends RecyclerView.Adapter
        <RecyclerViewAdapterTambah.ViewHolder> {


    //Deklarasi Variable
    private ArrayList<data_minyak> listMinyak;
    private Context context;

    //Membuat Interfece
    public interface dataListener {
        void onDeleteData(data_minyak data, int position);
    }

    //Deklarasi objek dari Interfece
    dataListener listener;

    //Membuat Konstruktor, untuk menerima input dari Database
    public RecyclerViewAdapterTambah(ArrayList<data_minyak>
                                             listMinyak, Context context) {
        this.listMinyak = listMinyak;
        this.context = context;
        listener = (MyListData) context;
    }

    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView Jumlahstock, Harga, Lokasi;
        private LinearLayout ListItem;

        ViewHolder(View itemView) {
            super(itemView);
            //Menginisialisasi View-View yang terpasang pada layout RecyclerView kita
            Jumlahstock = itemView.findViewById(R.id.STOCK);
            Harga = itemView.findViewById(R.id.HARGA);
            Lokasi = itemView.findViewById(R.id.LOKASI);
            ListItem = itemView.findViewById(R.id.list_item);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Membuat View untuk Menyiapkan dan Memasang Layout yang Akan digunakan pada RecyclerView
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_design
                , parent, false);
        return new ViewHolder(V);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //Mengambil Nilai/Value yenag terdapat pada RecyclerView berdasarkan Posisi Tertentu
        final String Lokasi = listMinyak.get(position).getLokasi();
        final String Jumlahstock = listMinyak.get(position).getJumlahstock();
        final String Harga = listMinyak.get(position).getHarga();


        //Memasukan Nilai/Value kedalam View (TextView: NIM, Nama, Jurusan)
        holder.Jumlahstock.setText(Jumlahstock);
        holder.Harga.setText(Harga);
        holder.Lokasi.setText(Lokasi);

////        Menampilkan Menu Update dan Delete saat user melakukan long klik pada salah satu item
//        holder.ListItem.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(final View view) {
//                final String[] action = { "Delete","Batal"};
//                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
//                alert.setItems(action, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int i) {
//                        switch (i) {
//                            case 0:
//                                //Menggunakan interface untuk mengirim data mahasiswa, yang akan dihapus
//                                listener.onDeleteData(listMinyak.get(position), position);
//                                break;
//
//
//                            case 1:
////                                Intent intent = new Intent(view.getContext(), MyListData.class);
////                                context.startActivity(intent);
////                                break;
//                        }
//                    }
//                });
//                alert.create();
//                alert.show();
//                return true;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        //Menghitung Ukuran/Jumlah Data Yang Akan Ditampilkan Pada RecyclerView
        return listMinyak.size();
    }
}

