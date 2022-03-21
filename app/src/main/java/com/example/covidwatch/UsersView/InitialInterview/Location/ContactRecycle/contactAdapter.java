package com.example.covidwatch.UsersView.InitialInterview.Location.ContactRecycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidwatch.R;

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

        holder.t1.setText(data.get(position).getHeader());
        holder.t2.setText(data.get(position).getDesc());



    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
