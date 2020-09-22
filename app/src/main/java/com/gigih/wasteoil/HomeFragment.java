package com.gigih.wasteoil;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Spinner spKota;
    private RecyclerView rvTerbaru;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public ArrayList<String> toko;
    public ArrayList<String> harga;
    public ArrayList<Integer> gambar;
    public ArrayList<String> liter;
    public ArrayList<String> status;

    private RecyclerView rvTerdekat;
    private RecyclerView.Adapter adapter1;
    private RecyclerView.LayoutManager layoutManager1;
    private ArrayList<String> toko1;
    private ArrayList<String> harga1;
    private ArrayList<Integer> gambar1;
    public ArrayList<String> liter1;
    public ArrayList<String> status1;

    private String [] Toko = {"RM Cak Khoiri", "Warung Cak Man", "Rumah Makan Padang", "Kuluk Resto", "Pedagang Gorengan", "Warung Bebek Goreng"};
    private String [] Harga ={"Rp 3000,-","Rp 4000,-","Rp 5000,-","Rp 3000,-","Rp 4000,-","Rp 5000,-"};
    private Integer [] Gambar = {R.drawable.gambar1,R.drawable.gambar2,R.drawable.gambar3,R.drawable.gambar4,R.drawable.gambar5,R.drawable.gambar6};
    public String [] Liter = {"2 Liter","4 Liter", "5 Liter", "3 Liter","3.5 Liter","6 Liter"};
    public String [] Status = {"Tersedia","Tersedia","Tersedia","Tersedia","Tersedia","Tersedia"};


    private String [] Toko1 = {"RM Bang Udin ", "Bebek Goreng Kusnul", "Tombo Luwe", "Warung Rahayu", "Go Chicken", "Warung Makan Biru"};
    private String [] Harga1 ={"Rp 3000,-","Rp 4000,-","Rp 5000,-","Rp 3000,-","Rp 4000,-","Rp 5000,-"};
    private Integer [] Gambar1 = {R.drawable.gambar7,R.drawable.gambar8,R.drawable.gambar9,R.drawable.gambar10,R.drawable.gambar11,R.drawable.gambar12};


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         final View view = inflater.inflate(R.layout.fragment_home, container, false);

        toko = new ArrayList<>();
        harga = new ArrayList<>();
        gambar = new ArrayList<>();
        liter = new ArrayList<>();
        status = new ArrayList<>();

        rvTerbaru = (RecyclerView) view.findViewById(R.id.rv_terbaru);
        rvTerbaru.setHasFixedSize(true);
        initDataset();
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        rvTerbaru.setLayoutManager(layoutManager);

        adapter = new AdapterTerbaru(toko,gambar,harga,liter,status,getActivity());
        rvTerbaru.setAdapter(adapter);

        //terdekat
        toko1 = new ArrayList<>();
        harga1 = new ArrayList<>();
        gambar1 = new ArrayList<>();
        liter1 = new ArrayList<>();
        status1 = new ArrayList<>();

        initDataset1();
        rvTerdekat = (RecyclerView) view.findViewById(R.id.rv_terdekat);
        rvTerdekat.setHasFixedSize(true);
        layoutManager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        rvTerdekat.setLayoutManager(layoutManager1);

        adapter1 = new AdapterTerdekat(toko1,harga1,gambar1,liter1,status1,getActivity());
        rvTerdekat.setAdapter(adapter1);


        spKota = (Spinner) view.findViewById(R.id.spinner_kota);

        TextView tvall = view.findViewById(R.id.tv_all);
        tvall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    TerbaruGridFragment fragment2 = new TerbaruGridFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        TextView tvall2 = view.findViewById(R.id.tv_all2);
        tvall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TerdekatGridFragment fragment3 = new TerdekatGridFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment3);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

     return view;
    }

    private void initDataset(){
        for (int w=0; w<Toko.length; w++){
            gambar.add(Gambar[w]);
            toko.add(Toko[w]);
            harga.add(Harga[w]);
//            liter.add(Liter[w]);
//            status.add(Status[w]);
        }
    }

    private void initDataset1(){
        for (int y=0; y<Toko1.length; y++){
            gambar1.add(Gambar1[y]);
            toko1.add(Toko1[y]);
            harga1.add(Harga1[y]);
        }

    }
}
