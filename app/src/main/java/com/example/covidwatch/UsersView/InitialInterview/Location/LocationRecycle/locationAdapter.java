package com.example.covidwatch.UsersView.InitialInterview.Location.LocationRecycle;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidwatch.R;
import com.example.covidwatch.UsersView.InitialInterview.Location.AddIsolationLocationactivity;
import com.example.covidwatch.UsersView.InitialInterview.Location.CloseContactDetailsActivity;

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
        View view = inflater.inflate(R.layout.addlocation_singlerow,parent,false);
        return new locationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull locationViewHolder holder, int position) {

        final LocationModel temp = data.get(position);

        holder.t1.setText(data.get(position).getLocationName());
        holder.t2.setText(data.get(position).getVisitDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddIsolationLocationactivity.class);
                intent.putExtra("uuid", temp.getUuid());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
