package com.example.covidwatch.AdminView.EscalatedRecord;

public class ResourceModel {

    private String idResource;
    private String nameResource;
    private String emailResource;
    private  String uuid ;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIdResource() {
        return idResource;
    }

    public void setIdResource(String idResource) {
        this.idResource = idResource;
    }

    public String getNameResource() {
        return nameResource;
    }

    public void setNameResource(String nameResource) {
        this.nameResource = nameResource;
    }

    public String getEmailResource() {
        return emailResource;
    }

    public void setEmailResource(String emailResource) {
        this.emailResource = emailResource;
    }
}
