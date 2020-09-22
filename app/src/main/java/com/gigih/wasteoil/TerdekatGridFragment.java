package com.gigih.wasteoil;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class TerdekatGridFragment extends Fragment {

    private RecyclerView rvTerdekatGrid;
    private RecyclerView.Adapter adapterGrid;
    private RecyclerView.LayoutManager layoutManagerGrid;
    private ArrayList<String> tokoH;
    private ArrayList<String> hargaH;
    private ArrayList<Integer> gambarH;
    private ArrayList<String> btnH;

    private String[] TokoH = {"RM Bang Udin ", "Bebek Goreng Kusnul", "Tombo Luwe", "Warung Rahayu", "Go Chicken", "Warung Makan Biru"};
    private String[] HargaH = {"Rp 3000,-", "Rp 4000,-", "Rp 5000,-", "Rp 3000,-", "Rp 4000,-", "Rp 5000,-"};
    private Integer[] GambarH = {R.drawable.gambar7, R.drawable.gambar8, R.drawable.gambar9, R.drawable.gambar10, R.drawable.gambar11, R.drawable.gambar12};
    private String[] BtnH = {"Ambil", "Ambil", "Ambil", "Ambil", "Ambil", "Ambil"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview =  inflater.inflate(R.layout.fragment_terdekat, container, false);

        tokoH = new ArrayList<>();
        hargaH = new ArrayList<>();
        gambarH = new ArrayList<>();
        btnH = new ArrayList<>();

        rvTerdekatGrid =  rootview.findViewById(R.id.rv_terdekat_grid);
        rvTerdekatGrid.setHasFixedSize(true);
        initDatasetH();
        layoutManagerGrid = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,true);
        rvTerdekatGrid.setLayoutManager(layoutManagerGrid);

        adapterGrid = new AdapterGridTerbaru(tokoH,gambarH,hargaH,btnH,getActivity());
        rvTerdekatGrid.setAdapter(adapterGrid);

        return rootview;
    }

    private void initDatasetH() {
        for (int i=0; i<TokoH.length; i++) {
            gambarH.add(GambarH[i]);
            tokoH.add(TokoH[i]);
            hargaH.add(HargaH[i]);
            btnH.add(BtnH[i]);
        }
    }
}
