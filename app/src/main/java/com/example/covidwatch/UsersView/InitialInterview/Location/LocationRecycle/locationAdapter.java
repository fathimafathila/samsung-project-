package com.example.covidwatch.UsersView.InitialInterview.Location.LocationRecycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidwatch.R;

import java.util.ArrayList;

public class locationAdapter extends RecyclerView.Adapter<locationViewHolder> {

    ArrayList<LocationModel> data;
    Context context;

    public locationAdapter(ArrayList<LocationModel> data, Context context) {
        this.data = data;
        this.context =context;
    }

    @NonNull
    @Override
    public locationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.closecontact_singlerow,parent,false);
        return new locationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull locationViewHolder holder, int position) {

        final LocationModel temp = data.get(position);

        holder.t1.setText(data.get(position).getHeader());
        holder.t2.setText(data.get(position).getDesc());



    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
