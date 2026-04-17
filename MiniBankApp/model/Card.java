package model;

public class Card {
    private int id;
    private int accountId;
    private String noKartu;
    private String pin;
    private String status;
    private String expiredDate;
    private int wrongPinCounter;

    public Card() {}

    public Card(int id, int accountId, String noKartu, String pin, String status, String expiredDate, int wrongPinCounter) {
        this.id = id;
        this.accountId = accountId;
        this.noKartu = noKartu;
        this.pin = pin;
        this.status = status;
        this.expiredDate = expiredDate;
        this.wrongPinCounter = wrongPinCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getNoKartu() {
        return noKartu;
    }

    public void setNoKartu(String noKartu) {
        this.noKartu = noKartu;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public int getWrongPinCounter() {
        return wrongPinCounter;
    }

    public void setWrongPinCounter(int wrongPinCounter) {
        this.wrongPinCounter = wrongPinCounter;
    }
}
