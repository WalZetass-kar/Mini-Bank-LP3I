package controller;

import model.Account;
import repository.AccountRepository;

public class AccountController {
    private AccountRepository accountRepository;

    public AccountController() {
        this.accountRepository = new AccountRepository();
    }

    public Account getAccountById(int accountId) {
        return accountRepository.getAccountById(accountId);
    }

    public Account getAccountByUserId(int userId) {
        return accountRepository.getAccountByUserId(userId);
    }

    public long getSaldo(int accountId) {
        Account account = accountRepository.getAccountById(accountId);
        return account != null ? account.getSaldo() : 0;
    }

    public boolean updateSaldo(int accountId, long newSaldo) {
        return accountRepository.updateSaldo(accountId, newSaldo);
    }
}
