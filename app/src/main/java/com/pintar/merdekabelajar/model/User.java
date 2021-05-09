package com.pintar.merdekabelajar.model;

public class User {

    private String id;
    private String username;
    private String gender;
    private String asal;
    private String lahir;
    private long join;
    private String reputasi;
    private String phone;
    private String email;
    private String verified;
    private String imageurl;
    private String rekening;
    private String alengkap;
    private String status;

    public User(String id, String username, String gender, String asal, String lahir, long join, String reputasi, String phone, String email, String verified, String imageurl, String rekening, String alengkap, String status) {
        this.id = id;
        this.username = username;
        this.gender = gender;
        this.asal = asal;
        this.lahir = lahir;
        this.join = join;
        this.reputasi = reputasi;
        this.phone = phone;
        this.email = email;
        this.verified = verified;
        this.imageurl = imageurl;
        this.rekening = rekening;
        this.alengkap = alengkap;
        this.status = status;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public String getLahir() {
        return lahir;
    }

    public void setLahir(String lahir) {
        this.lahir = lahir;
    }

    public long getJoin() {
        return join;
    }

    public void setJoin(long join) {
        this.join = join;
    }

    public String getReputasi() {
        return reputasi;
    }

    public void setReputasi(String reputasi) {
        this.reputasi = reputasi;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getRekening() {
        return rekening;
    }

    public void setRekening(String rekening) {
        this.rekening = rekening;
    }

    public String getAlengkap() {
        return alengkap;
    }

    public void setAlengkap(String alengkap) {
        this.alengkap = alengkap;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
