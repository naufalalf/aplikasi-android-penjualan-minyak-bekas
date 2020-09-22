package com.gigih.wasteoil;

public class data_minyak {
    private String jumlahstock;
    private String harga;
    private String lokasi;
    private String key;

    public data_minyak(){
    }

    public String getJumlahstock() {
        return jumlahstock;
    }

    public void setJumlahstock(String jumlahstock) {
        this.jumlahstock = jumlahstock;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public data_minyak(String jumlahstock, String harga, String lokasi) {
        this.jumlahstock = jumlahstock;
        this.harga = harga;
        this.lokasi = lokasi;

    }







}
