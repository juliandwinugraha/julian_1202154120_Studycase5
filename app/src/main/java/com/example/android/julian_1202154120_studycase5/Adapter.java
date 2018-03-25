package com.example.android.julian_1202154120_studycase5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.holder> {
    //mendeklarasi variabel
    private Context context;
    private List<MenambahData> list;
    int color;

    //konstruktor
    public Adapter(Context context, ArrayList<MenambahData> list, int color){
        this.context=context;
        this.list=list;
        this.color=color;
    }

    //menentukan viewholder untuk recyclerview
    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //membuat view baru
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_list, parent, false);
        holder holder = new holder(view);
        return holder;
    }

    //menyetting nilai yang didapatkan pada viewholder
    @Override
    public void onBindViewHolder(holder holder, int position) {
        MenambahData data = list.get(position);
        holder.ToDo.setText(data.getTodo());
        holder.Description.setText(data.getDesc());
        holder.Priority.setText(data.getPrior());
        holder.cardview.setCardBackgroundColor(context.getResources().getColor(this.color));
    }

    //mendapatkan jumlah list
    @Override
    public int getItemCount() {
        return list.size();
    }

    //mendapatkan list dari adapter
    public MenambahData getData(int position){
        return list.get(position);
    }

    //untuk menghapus list pada todolist
    public void deleteData(int i){
        //remove item yang terpilih
        list.remove(i);
        //memberi notifikasi item yang di remove
        notifyItemRemoved(i);
        //Memberitahu setiap pengamat terdaftar bahwa item itemCount mulai dari posisi posisi mulai telah berubah
        notifyItemRangeChanged(i, list.size());
    }

    class holder extends RecyclerView.ViewHolder{
        //mendeklarasi variabel
        public TextView ToDo, Description, Priority;
        public CardView cardview;
        public holder(View itemView){
            super(itemView);

            //mengakses id text view pada layout dan juga cardview
            ToDo = itemView.findViewById(R.id.headline);
            Description = itemView.findViewById(R.id.explanation);
            Priority = itemView.findViewById(R.id.number);
            cardview = itemView.findViewById(R.id.cardlist);
        }
    }
}
