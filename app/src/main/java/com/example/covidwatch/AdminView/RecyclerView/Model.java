package com.example.covidwatch.AdminView.RecyclerView;

public class Model {

   private String fName;
   private String lname;
   private  String id;
   private  String number;

   public Model() { }

    public Model(String fName, String lname, String id, String number) {
        this.fName = fName;
        this.lname = lname;
        this.id = id;
        this.number = number;
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
