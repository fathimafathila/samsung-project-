package com.example.covidwatch.UsersView.InitialInterview.Demographic.VaccineRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidwatch.R;

import java.util.ArrayList;

public class vaccineAdapter extends RecyclerView.Adapter<vaccineViewHolder> {

    ArrayList<VaccineModel> data;
    Context context;

    public vaccineAdapter(ArrayList<VaccineModel> data, Context context) {
        this.data = data;
        this.context =context;
    }

    @NonNull
    @Override
    public vaccineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.vaccinedose_singlerow,parent,false);
        return new vaccineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vaccineViewHolder holder, int position) {

        final VaccineModel temp = data.get(position);

        holder.t1.setText(temp.getDose());
        holder.t2.setText(temp.getName());
        holder.t3.setText(temp.getDate());
        holder.t4.setText(temp.getLot());
        holder.t5.setText(temp.getProvided());



    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
