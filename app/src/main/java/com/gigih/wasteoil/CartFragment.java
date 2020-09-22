package com.gigih.wasteoil;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

public class CartFragment extends Fragment  {



    private ViewPager viewPager;
    TabLayout tabLayout;

    private String[] tabs = {"Tabihan", "Pembelian", "Penjualan"};
    public CartFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_cart, container, false);



//       actionBar = getActivity().getActionBar();
//       mAdapter = new ViewPagerAdapter(getFragmentManager());
//
//       viewPager.setAdapter(mAdapter);
//       actionBar.setHomeButtonEnabled(false);
//       getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//
//       actionBar.setDisplayShowHomeEnabled(false);
//       actionBar.setDisplayShowTitleEnabled(false);
//
//
//       for (String tab_name : tabs){
//           actionBar.addTab(actionBar.newTab().setText(tab_name)
//           .setTabListener(this));
//       }

//       viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//           @Override
//           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//           }
//
//           @Override
//           public void onPageSelected(int position) {
//                actionBar.setSelectedNavigationItem(position);
//           }
//
//           @Override
//           public void onPageScrollStateChanged(int state) {
//
//           }
//       });

       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.viewpager);
        tabLayout = view.findViewById(R.id.tablayout);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        adapter.addFrag(new TagihanFragment(),"tagihan");
        adapter.addFrag(new PenjualanFragment(),"penjualan");
        adapter.addFrag(new PembelianFragment(),"pembelian");

        viewPager.setAdapter(adapter);
    }
}
