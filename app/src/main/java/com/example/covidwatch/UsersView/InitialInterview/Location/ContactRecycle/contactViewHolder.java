package com.example.covidwatch.UsersView.InitialInterview.Location.ContactRecycle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidwatch.R;

public class contactViewHolder extends RecyclerView.ViewHolder {
    TextView t1;
    TextView t2 ;
    public contactViewHolder(@NonNull View itemView) {
        super(itemView);
        t1 = (TextView) itemView.findViewById(R.id.txtContactName);
        t2 = (TextView) itemView.findViewById(R.id.txtContactDate);

    }
}
