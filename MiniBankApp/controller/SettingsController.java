package controller;

import model.Card;
import repository.CardRepository;
import utils.ValidationUtil;

public class SettingsController {
    private CardRepository cardRepository;

    public SettingsController() {
        this.cardRepository = new CardRepository();
    }

    public String changePin(int cardId, String oldPin, String newPin, String confirmPin) {
        // Get card from database
        Card card = cardRepository.getCardById(cardId);
        if (card == null) {
            return "Kartu tidak ditemukan";
        }

        // Validate old PIN
        if (!card.getPin().equals(oldPin)) {
            return "PIN lama tidak sesuai";
        }

        // Validate new PIN format
        if (!ValidationUtil.isValidPIN(newPin)) {
            return "PIN baru harus 6 digit angka";
        }

        // Validate confirmation
        if (!newPin.equals(confirmPin)) {
            return "PIN baru dan konfirmasi tidak sama";
        }

        // Check if new PIN same as old PIN
        if (oldPin.equals(newPin)) {
            return "PIN baru tidak boleh sama dengan PIN lama";
        }

        // Update PIN
        boolean updated = cardRepository.updatePin(cardId, newPin);
        if (!updated) {
            return "Gagal mengubah PIN";
        }

        // Reset wrong PIN counter
        cardRepository.updateWrongPinCounter(cardId, 0);

        return "SUCCESS";
    }

    public Card getCardById(int cardId) {
        return cardRepository.getCardById(cardId);
    }
}
