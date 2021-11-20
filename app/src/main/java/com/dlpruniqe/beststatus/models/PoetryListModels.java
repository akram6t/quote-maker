package com.dlpruniqe.beststatus.models;

public class PoetryListModels {
    private int id;
    private int gradient;
    private String pname;

    public PoetryListModels(int id ,int gradient, String pname) {
        this.id = id;
        this.gradient = gradient;
        this.pname = pname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGradient() {
        return gradient;
    }

    public void setGradient(int gradient) {
        this.gradient = gradient;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

}
