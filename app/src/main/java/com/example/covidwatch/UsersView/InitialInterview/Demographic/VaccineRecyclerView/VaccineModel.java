package com.example.covidwatch.UsersView.InitialInterview.Demographic.VaccineRecyclerView;

public class VaccineModel {

    private String header;
    private  String desc;
    private String lot;
    private String dose;


    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLot() { return lot; }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }
}
