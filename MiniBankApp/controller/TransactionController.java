package controller;

import model.Transaction;
import model.Account;
import repository.TransactionRepository;
import repository.AccountRepository;
import utils.GeneratorUtil;
import utils.ValidationUtil;
import java.util.List;

public class TransactionController {
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    public TransactionController() {
        this.transactionRepository = new TransactionRepository();
        this.accountRepository = new AccountRepository();
    }

    public String withdraw(int accountId, long amount, String pin) {
        // Validate amount
        if (amount <= 0) {
            return "Jumlah harus lebih dari 0";
        }

        if (!ValidationUtil.isMultipleOf50000(amount)) {
            return "Jumlah harus kelipatan 50.000";
        }

        // Get account
        Account account = accountRepository.getAccountById(accountId);
        if (account == null) {
            return "Rekening tidak ditemukan";
        }

        // Check balance
        if (account.getSaldo() < amount) {
            return "Saldo tidak mencukupi";
        }

        // Update balance
        long newSaldo = account.getSaldo() - amount;
        if (!accountRepository.updateSaldo(accountId, newSaldo)) {
            return "Gagal memperbarui saldo";
        }

        // Create transaction record
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setType("WITHDRAW");
        transaction.setAmount(amount);
        transaction.setDescription("Penarikan tunai");
        transaction.setDate(GeneratorUtil.getCurrentDateTime());

        if (!transactionRepository.createTransaction(transaction)) {
            return "Gagal mencatat transaksi";
        }

        return "SUCCESS";
    }

    public String deposit(int accountId, long amount) {
        // Validate amount
        if (amount <= 0) {
            return "Jumlah harus lebih dari 0";
        }

        // Get account
        Account account = accountRepository.getAccountById(accountId);
        if (account == null) {
            return "Rekening tidak ditemukan";
        }

        // Update balance
        long newSaldo = account.getSaldo() + amount;
        if (!accountRepository.updateSaldo(accountId, newSaldo)) {
            return "Gagal memperbarui saldo";
        }

        // Create transaction record
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setType("DEPOSIT");
        transaction.setAmount(amount);
        transaction.setDescription("Setor tunai");
        transaction.setDate(GeneratorUtil.getCurrentDateTime());

        if (!transactionRepository.createTransaction(transaction)) {
            return "Gagal mencatat transaksi";
        }

        return "SUCCESS";
    }

    public String transfer(int fromAccountId, String toNoRekening, long amount) {
        // Validate amount
        if (amount <= 0) {
            return "Jumlah harus lebih dari 0";
        }

        // Get sender account
        Account fromAccount = accountRepository.getAccountById(fromAccountId);
        if (fromAccount == null) {
            return "Rekening pengirim tidak ditemukan";
        }

        // Get receiver account
        Account toAccount = accountRepository.getAccountByNoRekening(toNoRekening);
        if (toAccount == null) {
            return "Rekening tujuan tidak ditemukan";
        }

        // Check if same account
        if (fromAccount.getId() == toAccount.getId()) {
            return "Tidak dapat transfer ke rekening sendiri";
        }

        // Check balance
        if (fromAccount.getSaldo() < amount) {
            return "Saldo tidak mencukupi";
        }

        // Update sender balance
        long newSaldoFrom = fromAccount.getSaldo() - amount;
        if (!accountRepository.updateSaldo(fromAccountId, newSaldoFrom)) {
            return "Gagal memperbarui saldo pengirim";
        }

        // Update receiver balance
        long newSaldoTo = toAccount.getSaldo() + amount;
        if (!accountRepository.updateSaldo(toAccount.getId(), newSaldoTo)) {
            // Rollback sender balance
            accountRepository.updateSaldo(fromAccountId, fromAccount.getSaldo());
            return "Gagal memperbarui saldo penerima";
        }

        // Create transaction record for sender
        Transaction transactionFrom = new Transaction();
        transactionFrom.setAccountId(fromAccountId);
        transactionFrom.setType("TRANSFER");
        transactionFrom.setAmount(amount);
        transactionFrom.setDescription("Transfer ke " + toNoRekening);
        transactionFrom.setDate(GeneratorUtil.getCurrentDateTime());
        transactionRepository.createTransaction(transactionFrom);

        // Create transaction record for receiver
        Transaction transactionTo = new Transaction();
        transactionTo.setAccountId(toAccount.getId());
        transactionTo.setType("TRANSFER");
        transactionTo.setAmount(amount);
        transactionTo.setDescription("Transfer dari " + fromAccount.getNoRekening());
        transactionTo.setDate(GeneratorUtil.getCurrentDateTime());
        transactionRepository.createTransaction(transactionTo);

        return "SUCCESS";
    }

    public List<Transaction> getTransactionHistory(int accountId) {
        return transactionRepository.getTransactionsByAccountId(accountId);
    }
}
