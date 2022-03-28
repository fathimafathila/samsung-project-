package com.example.covidwatch.UsersView.InitialInterview.Demographic.VaccineRecyclerView;

public class VaccineModel {

    private String dose;
    private String name;
    private String date;
    private String lot;
    private String provided;

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getProvided() {
        return provided;
    }

    public void setProvided(String provided) {
        this.provided = provided;
    }
}
