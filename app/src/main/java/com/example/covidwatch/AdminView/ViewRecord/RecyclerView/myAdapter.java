package com.example.covidwatch.AdminView.ViewRecord.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidwatch.R;
import com.example.covidwatch.UsersView.UserDashboardActivity;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myViewHolder> {

    ArrayList<viewModel> data;
    Context context;

    public myAdapter(ArrayList<viewModel> data, Context context) {
        this.data = data;
        this.context =context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.viewrecord_singlerow,parent,false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        final viewModel temp = data.get(position);

        holder.t1.setText(temp.getId());
        holder.t2.setText(temp.getFullName());
        holder.t3.setText(temp.getNumber());
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

class myViewHolder extends RecyclerView.ViewHolder {

    TextView t1;
    TextView t2 ;
    TextView t3;
    public myViewHolder(@NonNull View itemView) {
        super(itemView);

        t1 = (TextView) itemView.findViewById(R.id.t1);
        t2 = (TextView) itemView.findViewById(R.id.t2);
        t3 = (TextView) itemView.findViewById(R.id.t3);


    }
}

