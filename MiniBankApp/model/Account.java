package model;

public class Account {
    private int id;
    private int userId;
    private String noRekening;
    private long saldo;
    private String createdAt;

    public Account() {}

    public Account(int id, int userId, String noRekening, long saldo, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.noRekening = noRekening;
        this.saldo = saldo;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
