package com.example.covidwatch.AdminView.EscalatedRecord;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidwatch.R;
import com.example.covidwatch.UsersView.UserDashboardActivity;

import java.util.ArrayList;

public class resourceAdapter extends RecyclerView.Adapter<resourceViewHolder> {

    ArrayList<ResourceModel> data;
    Context context;

    public resourceAdapter(ArrayList<ResourceModel> data, Context context) {
        this.data = data;
        this.context =context;
    }

    @NonNull
    @Override
    public resourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.resource_request_singlerow,parent,false);
        return new resourceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull resourceViewHolder holder, int position) {

        final ResourceModel temp = data.get(position);

        holder.t1.setText(temp.getIdResource());
        holder.t2.setText(temp.getNameResource());
        holder.t3.setText(temp.getEmailResource());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserDashboardActivity.class);
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
