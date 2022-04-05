package com.example.covidwatch.AdminView.ResourceRequest;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidwatch.R;

public class resourceViewHolder extends RecyclerView.ViewHolder {
    TextView t1;
    TextView t2 ;
    TextView t3;
    public resourceViewHolder(@NonNull View itemView) {
        super(itemView);
        t1 = (TextView) itemView.findViewById(R.id.t1);
        t2 = (TextView) itemView.findViewById(R.id.t2);
        t3 = (TextView) itemView.findViewById(R.id.t3);
    }
}
