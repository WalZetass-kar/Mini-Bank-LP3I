package model;

public class User {
    private int id;
    private String nama;
    private String nik;
    private String tanggalLahir;
    private String alamat;
    private String noHp;
    private String createdAt;

    public User() {}

    public User(int id, String nama, String nik, String tanggalLahir, String alamat, String noHp, String createdAt) {
        this.id = id;
        this.nama = nama;
        this.nik = nik;
        this.tanggalLahir = tanggalLahir;
        this.alamat = alamat;
        this.noHp = noHp;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
