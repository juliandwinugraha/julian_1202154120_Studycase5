package com.example.android.julian_1202154120_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class ListAktivitas extends AppCompatActivity {
    //mendeklarasi variabel
    Database database;
    RecyclerView recyclerview;
    Adapter adapter;
    ArrayList<MenambahData> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_to_do);
        //set title menjadi ToDoList
        setTitle("To Do List");

        //mengakses recyclerview yang ada di layout
        recyclerview = findViewById(R.id.recview);
        //membuat araylist baru
        datalist = new ArrayList<>();
        //membuat database baru
        database = new Database(this);
        //memanggil method readdata
        database.readdata(datalist);

        //menginisialisasi shared preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        //membuat adapter baru
        adapter = new Adapter(this, datalist, color);
        //menghindari perubahan ukuran yang tidak perlu ketika menambahkan atau menghapus item pada recycler view
        recyclerview.setHasFixedSize(true);
        //menampilkan layoutnya linier
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        //inisiasi adapter untuk recycler view
        recyclerview.setAdapter(adapter);

        //menjalankan method hapus data pada list to do
        deleteswipe();
    }

    //membuat method untuk menghapus item pada to do list
    public void deleteswipe() {
        //membuat touch helper baru untuk recycler view
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            //Disebut ketika onMove (RecyclerView, ViewHolder, ViewHolder) mengembalikan nilai false.
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            //method ketika ViewHolder diswipe oleh pengguna
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                MenambahData current = adapter.getData(position);
                //apabila item diswipe ke arah kanan
                if (direction == ItemTouchHelper.RIGHT) {
                    //remove item yang dipilih dengan mengenali todonya sebagai primary key
                    if (database.removedata(current.getTodo())) {
                        //menghapus data
                        adapter.deleteData(position);
                        //membuat snackbar dan pemberitahuan bahwa item berhasil terhapus dengan duration 1 sekon
                        Snackbar.make(findViewById(R.id.coor), "Data Deleted", 1000).show();
                    }
                }
            }
        };
        //menentukan itemtouchhelper untuk recycler view
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(recyclerview);
    }

    //ketika menu pada activity di buat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    //method yang dijalankan ketika item di pilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //mendapatkan id dari item
        int id = item.getItemId();
        //apabila item yang dipilih adalah setting
        if (id == R.id.action_settings) {
            //membuat intent baru dari listtodo ke pengaturan
            Intent intent = new Intent(ListAktivitas.this, Setting.class);
            //memulai intent
            startActivity(intent);
            //menutup aktivitas setelah intent dijalankan
            finish();
        }
        return true;
    }

    //method yang dijalankan ketika tombol add di klik
    public void add(View view) {
        //intent dari listtodo ke addtodo
        Intent intent = new Intent(ListAktivitas.this, MenambahAktivitas.class);
        //memulai intent
        startActivity(intent);
    }
}