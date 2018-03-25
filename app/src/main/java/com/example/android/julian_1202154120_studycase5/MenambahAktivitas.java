package com.example.android.julian_1202154120_studycase5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MenambahAktivitas extends AppCompatActivity {
    //mendeklarasi variable yang akan digunakan
    EditText ToDo, Description, Priority;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);
        //set title menjadi add to do
        setTitle("Add To Do");

        //mengakses id edittext pada layout
        ToDo = (EditText) findViewById(R.id.editTodo);
        Description = (EditText) findViewById(R.id.editDesc);
        Priority = (EditText) findViewById(R.id.editPriority);
        database = new Database(this);
    }

    @Override
    public void onBackPressed() {
        //intent dari add to do menuju list to do
        Intent intent = new Intent(MenambahAktivitas.this, ListAktivitas.class);
        //memulai intent
        startActivity(intent);
        //menutup aktivitas setelah intent dijalankan
        this.finish();
    }


    public void tambah(View view) {
        //ketika to do name,description dan priority diisi
        if (database.inputdata(new MenambahData(ToDo.getText().toString(), Description.getText().toString(), Priority.getText().toString()))){
            //maka ketika kita klik button tambah to do maka akan menampilkan toast
            Toast.makeText(this, "To Do List Added", Toast.LENGTH_SHORT).show();
            //pindah dari add to do ke list to do
            startActivity(new Intent(MenambahAktivitas.this, ListAktivitas.class));
            //menutup activity agar tidak kembali ke activity yang dijalankan setelah intent
            this.finish();
        }else {
            //meng-set edittext menjadi null
            ToDo.setText(null);
            Description.setText(null);
            Priority.setText(null);
            //ketika edittext tidak diisi maka akan muncul toast seperti dibawah ini
            Toast.makeText(this, "Cannot add the list, fill the To Do", Toast.LENGTH_SHORT).show();

        }
    }
}
