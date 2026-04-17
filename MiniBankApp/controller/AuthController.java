package controller;

import model.User;
import model.Account;
import model.Card;
import repository.UserRepository;
import repository.AccountRepository;
import repository.CardRepository;
import utils.GeneratorUtil;
import utils.ValidationUtil;

public class AuthController {
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private CardRepository cardRepository;

    public AuthController() {
        this.userRepository = new UserRepository();
        this.accountRepository = new AccountRepository();
        this.cardRepository = new CardRepository();
    }

    public String register(String nama, String nik, String tanggalLahir, String alamat, String noHp) {
        // Validation
        if (!ValidationUtil.isNotEmpty(nama)) {
            return "Nama tidak boleh kosong";
        }
        if (!ValidationUtil.isValidNIK(nik)) {
            return "NIK harus 16 digit";
        }
        if (!ValidationUtil.isNotEmpty(tanggalLahir)) {
            return "Tanggal lahir tidak boleh kosong";
        }
        if (!ValidationUtil.isNotEmpty(alamat)) {
            return "Alamat tidak boleh kosong";
        }
        if (!ValidationUtil.isValidPhoneNumber(noHp)) {
            return "Nomor HP harus 10-13 digit";
        }

        // Check if NIK already exists
        if (userRepository.isNIKExists(nik)) {
            return "NIK sudah terdaftar";
        }

        // Create user
        User user = new User();
        user.setNama(nama);
        user.setNik(nik);
        user.setTanggalLahir(tanggalLahir);
        user.setAlamat(alamat);
        user.setNoHp(noHp);
        user.setCreatedAt(GeneratorUtil.getCurrentDateTime());

        int userId = userRepository.createUser(user);
        if (userId == -1) {
            return "Gagal membuat user";
        }

        // Create account with initial balance 50000
        Account account = new Account();
        account.setUserId(userId);
        account.setNoRekening(GeneratorUtil.generateAccountNumber());
        account.setSaldo(50000);
        account.setCreatedAt(GeneratorUtil.getCurrentDateTime());

        int accountId = accountRepository.createAccount(account);
        if (accountId == -1) {
            return "Gagal membuat rekening";
        }

        return "SUCCESS";
    }

    public Card login(String noKartu, String pin) {
        if (!ValidationUtil.isNotEmpty(noKartu)) {
            return null;
        }
        if (!ValidationUtil.isValidPIN(pin)) {
            return null;
        }

        Card card = cardRepository.getCardByNoKartu(noKartu);
        if (card == null) {
            return null;
        }

        // Check if card is blocked
        if ("BLOKIR".equals(card.getStatus())) {
            return null;
        }

        // Check if card is expired
        if ("EXPIRED".equals(card.getStatus())) {
            return null;
        }

        // Verify PIN
        if (!card.getPin().equals(pin)) {
            int wrongCounter = card.getWrongPinCounter() + 1;
            cardRepository.updateWrongPinCounter(card.getId(), wrongCounter);
            
            if (wrongCounter >= 3) {
                cardRepository.updateCardStatus(card.getId(), "BLOKIR");
            }
            return null;
        }

        // Reset wrong pin counter on successful login
        if (card.getWrongPinCounter() > 0) {
            cardRepository.updateWrongPinCounter(card.getId(), 0);
        }

        return card;
    }
}
