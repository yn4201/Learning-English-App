package com.example.englishapp;

public class write {
    private String ten;
    private String chedo;
    private String socau;
    private String dungsai;
    private String kq;
    private String time;

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getChedo() {
        return chedo;
    }

    public void setChedo(String chedo) {
        this.chedo = chedo;
    }

    public String getSocau() {
        return socau;
    }

    public void setSocau(String socau) {
        this.socau = socau;
    }

    public String getDungsai() {
        return dungsai;
    }

    public void setDungsai(String dungsai) {
        this.dungsai = dungsai;
    }

    public String getKq() {
        return kq;
    }

    public void setKq(String kq) {
        this.kq = kq;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public write(String ten, String chedo, String socau ,String kq) {
        this.ten = ten;
        this.chedo = chedo;
        this.socau = socau;
      //  this.dungsai = dungsai;
        this.kq = kq;
      //  this.time = time;
    }
    @Override
    public String toString() {
        return "Player"+" - "+this.socau+"/"+this.kq;
    }
}
