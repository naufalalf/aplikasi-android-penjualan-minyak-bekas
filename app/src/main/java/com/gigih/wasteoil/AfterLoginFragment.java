package com.gigih.wasteoil;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class AfterLoginFragment extends Fragment {

    private FirebaseAuth auth;
    private Button buttonJualMinyak;
    String name;
private Button btnLogOut;
public TextView userId;
    public AfterLoginFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_after_login, container, false);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        auth = FirebaseAuth.getInstance();

//        DaftarActivity nama = new DaftarActivity();
//        name = nama.inputNama.getDisplay().getName().trim();
//        userId = view.findViewById(R.id.tv_idUser);
//        userId.setText(name);

         btnLogOut = view.findViewById(R.id.btn_signOut);
         btnLogOut.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 signOut();

                     Intent intent=  new Intent(getActivity(),LoginActivity.class);
                     startActivity(intent);
                     onStop();
             }
         });

         buttonJualMinyak = view.findViewById(R.id.btn_jualMinyak);
         buttonJualMinyak.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getActivity(),JualMinyakActivity.class);
                 startActivity(intent);
             }
         });
        return view;
    }
    //sign out method
    public void signOut() {
        auth.signOut();
    }

}
