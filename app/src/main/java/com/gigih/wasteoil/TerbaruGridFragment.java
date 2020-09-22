package com.gigih.wasteoil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TerbaruGridFragment extends Fragment {

    private RecyclerView rvTerbaruGrid;
    private RecyclerView.Adapter adapterGrid;
    private RecyclerView.LayoutManager layoutManagerGrid;
    private ArrayList<String> tokoG;
    private ArrayList<String> hargaG;
    private ArrayList<Integer> gambarG;
    private ArrayList<String> btnG;

    private String[] TokoG = {"Soto Cak Khoiri", "Warung Cak Man", "Rumah Makan Padang", "Kuluk Resto","Warung Bakso",
            "Warung Bebek Goreng", "Rumah Makan Bambang", "Warung Cak Min", "Masakan Padang", "Rumah Makan Pakde", "Warung Joni", "warung chu"};
    private String[] HargaG = {"Rp 3000,-", "Rp 4000,-", "Rp 5000,-", "Rp 3000,-", "Rp 4000,-", "Rp 5000,-", "Rp 8000,-", "Rp 6000,-", "Rp 10000,-", "Rp 7000,-", "Rp 9000,-","Rp 5000,-"};
    private Integer[] GambarG = {R.drawable.gambar1, R.drawable.gambar2, R.drawable.gambar3, R.drawable.gambar4, R.drawable.gambar5,R.drawable.gambar12,
                                R.drawable.gambar6, R.drawable.gambar3, R.drawable.gambar4, R.drawable.gambar1, R.drawable.gambar2, R.drawable.gambar6};
    private String[] BtnG = {"Ambil", "Ambil", "Ambil", "Ambil", "Ambil", "Ambil","Ambil", "Ambil", "Ambil", "Ambil", "Ambil","Ambil"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_terbaru, container, false);

        tokoG = new ArrayList<>();
        hargaG = new ArrayList<>();
        gambarG = new ArrayList<>();
        btnG = new ArrayList<>();

        rvTerbaruGrid =  view.findViewById(R.id.rv_terbaru_grid);
        rvTerbaruGrid.setHasFixedSize(true);
        initDatasetG();
        layoutManagerGrid = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,true);
        rvTerbaruGrid.setLayoutManager(layoutManagerGrid);

        adapterGrid = new AdapterGridTerbaru(tokoG,gambarG,hargaG,btnG,getActivity());
        rvTerbaruGrid.setAdapter(adapterGrid);

        return view;
    }
    private void initDatasetG(){
        for (int w=0; w<TokoG.length; w++){
            gambarG.add(GambarG[w]);
            tokoG.add(TokoG[w]);
            hargaG.add(HargaG[w]);
            btnG.add(BtnG[w]);
        }
    }
}