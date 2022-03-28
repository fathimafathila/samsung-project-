package com.example.covidwatch.AdminView.ViewRecord.RecyclerView;

public class viewModel {

   private String fName;
   private String lname;
   private  String id;
   private  String number;
   private  String uuid ;

   public viewModel() { }

    public viewModel(String fName, String lname, String id, String number, String uuid) {
        this.fName = fName;
        this.lname = lname;
        this.id = id;
        this.number = number;
        this.uuid = uuid;
    }
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFullName(){
       return getfName() + " " + getLname();
    }
}
