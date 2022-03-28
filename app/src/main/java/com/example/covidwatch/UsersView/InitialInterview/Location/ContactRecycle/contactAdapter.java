package com.example.covidwatch.UsersView.InitialInterview.Location.ContactRecycle;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidwatch.R;
import com.example.covidwatch.UsersView.InitialInterview.Location.CloseContactDetailsActivity;
import com.example.covidwatch.UsersView.UserDashboardActivity;

import java.util.ArrayList;

public class contactAdapter extends RecyclerView.Adapter<contactViewHolder> {

    ArrayList<ContactModel> data;
    Context context;

    public contactAdapter(ArrayList<ContactModel> data, Context context) {
        this.data = data;
        this.context =context;
    }

    @NonNull
    @Override
    public contactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.closecontact_singlerow,parent,false);
        return new contactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull contactViewHolder holder, int position) {

        final ContactModel temp = data.get(position);

        holder.t1.setText(temp.getContactName());
        holder.t2.setText(temp.getContactDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CloseContactDetailsActivity.class);
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
