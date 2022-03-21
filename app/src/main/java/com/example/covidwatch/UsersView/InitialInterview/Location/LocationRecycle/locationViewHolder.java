package com.example.covidwatch.UsersView.InitialInterview.Location.LocationRecycle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidwatch.R;

public class locationViewHolder extends RecyclerView.ViewHolder {
    ImageView img ;
    TextView t1;
    TextView t2 ;
    public locationViewHolder(@NonNull View itemView) {
        super(itemView);
        t1 = (TextView) itemView.findViewById(R.id.txtName);
        t2 = (TextView) itemView.findViewById(R.id.txtDate);

    }
}
