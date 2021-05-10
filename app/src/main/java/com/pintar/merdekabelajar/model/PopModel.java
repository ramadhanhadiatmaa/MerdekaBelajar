package com.pintar.merdekabelajar.model;

public class PopModel {

    private String deskripsi;
    private String diskon;
    private String harga;
    private String imageurl;
    private String judul;
    private String kode;
    private String linkurl;
    private String postid;
    private String provider;
    private String rating;
    private String siswa;
    private String tag;
    private String waktu;
    private long waktupost;

    public PopModel(String deskripsi, String diskon, String harga, String imageurl, String judul, String kode, String linkurl, String postid, String provider, String rating, String siswa, String tag, String waktu, long waktupost) {
        this.deskripsi = deskripsi;
        this.diskon = diskon;
        this.harga = harga;
        this.imageurl = imageurl;
        this.judul = judul;
        this.kode = kode;
        this.linkurl = linkurl;
        this.postid = postid;
        this.provider = provider;
        this.rating = rating;
        this.siswa = siswa;
        this.tag = tag;
        this.waktu = waktu;
        this.waktupost = waktupost;
    }

    public PopModel() {
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getDiskon() {
        return diskon;
    }

    public void setDiskon(String diskon) {
        this.diskon = diskon;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSiswa() {
        return siswa;
    }

    public void setSiswa(String siswa) {
        this.siswa = siswa;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public long getWaktupost() {
        return waktupost;
    }

    public void setWaktupost(long waktupost) {
        this.waktupost = waktupost;
    }
}
