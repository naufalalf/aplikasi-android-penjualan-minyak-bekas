package com.gigih.wasteoil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyListData extends AppCompatActivity
        implements RecyclerViewAdapterTambah.dataListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private DatabaseReference reference;
    private ArrayList<data_minyak> dataMinyak;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_data);
        recyclerView =findViewById(R.id.rv_datalist);
//        getSupportxActionBar().setTitle("Data Mahasiswa");
        auth = FirebaseAuth.getInstance();
        MyRecyclerView();
        GetData();
    }


    private void GetData(){
        //Mendapatkan Referensi Database
        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Admin").child(auth.getUid()).child("Minyak")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Inisialisasi ArrayList
                        dataMinyak = new ArrayList<>();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            //Mapping data pada DataSnapshot ke dalam objek mahasiswa
                            data_minyak minyak = snapshot.getValue(data_minyak.class);

                            //Mengambil Primary Key, digunakan untuk proses Update dan Delete
                            minyak.setKey(snapshot.getKey());
                            dataMinyak.add(minyak);
                        }

                        //Inisialisasi Adapter dan data Mahasiswa dalam bentuk Array
                        adapter = new RecyclerViewAdapterTambah(dataMinyak,MyListData.this );

                        //Memasang Adapter pada RecyclerView
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
              /*
                Kode ini akan dijalankan ketika ada error dan
                pengambilan data error tersebut lalu memprint error nya
                ke LogCat
               */
                         Toast.makeText(getApplicationContext(),"Data Gagal Dimuat", Toast.LENGTH_LONG).show();
                        Log.e("MyListActivity", databaseError.getDetails()+" "+databaseError.getMessage());
                    }
                });
    }

    //Methode yang berisi kumpulan baris kode untuk mengatur RecyclerView
    private void MyRecyclerView(){
        //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //Membuat Underline pada Setiap Item Didalam List
//        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
//        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.line));
//        recyclerView.addItemDecoration(itemDecoration);
    }


    public void onDeleteData(data_minyak data, int position) {
        /*
         * Kode ini akan dipanggil ketika method onDeleteData
         * dipanggil dari adapter pada RecyclerView melalui interface.
         * kemudian akan menghapus data berdasarkan primary key dari data tersebut
         * Jika berhasil, maka akan memunculkan Toast
         */
        String userID = auth.getUid();
        if(reference != null){
            reference.child("Admin")
                    .child(userID)
                    .child("Minyak")
                    .child(data.getKey())
                    .removeValue()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(MyListData.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
