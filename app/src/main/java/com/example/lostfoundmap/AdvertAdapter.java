package com.example.lostfoundmap;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lostfoundmap.sqlitehelper.Advert;

import java.util.ArrayList;

public class AdvertAdapter extends RecyclerView.Adapter<AdvertAdapter.MyViewHolder>{
    Context context;
    ArrayList<Advert> adverts;
    ItemClickListener clickListener;

    public AdvertAdapter(Context context, ArrayList<Advert> adverts, ItemClickListener clickListener) {
        this.context = context;
        this.adverts = adverts;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Making the layout appear
        View view = LayoutInflater.from(context).inflate(R.layout.advert_layout, parent, false);
        // Calling the constructor
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Advert advert = adverts.get(position);
        if (advert.getType() == "Lost") holder.itemView.setBackgroundColor(Color.rgb(233, 237, 201));
        holder.advertName.setText(advert.getType() + " " + advert.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(advert);
            }
        });
    }

    @Override
    public int getItemCount() {
        return adverts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView advertName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            advertName = itemView.findViewById(R.id.textViewAdvertName);
        }
    }

    public interface ItemClickListener {
        public void onItemClick(Advert advert);
    }
}
