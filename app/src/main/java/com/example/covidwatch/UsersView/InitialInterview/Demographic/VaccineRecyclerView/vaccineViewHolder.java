package com.example.covidwatch.UsersView.InitialInterview.Demographic.VaccineRecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidwatch.R;

import org.w3c.dom.Text;

public class vaccineViewHolder extends RecyclerView.ViewHolder {
    ImageView img ;
    TextView t1;
    TextView t2 ;
    TextView t3;
    TextView t4;
    public vaccineViewHolder(@NonNull View itemView) {
        super(itemView);
        t1 = (TextView) itemView.findViewById(R.id.txtDoseNameV);
        t2 = (TextView) itemView.findViewById(R.id.txtDoseDateV);
        t3 = (TextView) itemView.findViewById(R.id.txtLotNumberV);
        t4 = (TextView) itemView.findViewById(R.id.txtDoseNumber);

    }
}
