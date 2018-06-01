package com.track.dastar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Malik Muhamad Qirtas on 5/16/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>
{

    private List<Item> itemsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, pickuphigh, pickuplow , address;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            pickuphigh = (TextView) view.findViewById(R.id.pickuphigh);
            pickuplow = (TextView) view.findViewById(R.id.pickuplow);
            address = (TextView) view.findViewById(R.id.address);

        }
    }


    public ItemAdapter(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item movie = itemsList.get(position);
        holder.name.setText(movie.getName());
        holder.address.setText(movie.getAddress());
        holder.pickuphigh.setText(movie.getPickUpHigh());
        holder.pickuplow.setText(movie.getPickUpLow());

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
