package controller;

import model.Card;
import model.Account;
import repository.CardRepository;
import repository.AccountRepository;
import utils.GeneratorUtil;

public class CardController {
    private CardRepository cardRepository;
    private AccountRepository accountRepository;

    public CardController() {
        this.cardRepository = new CardRepository();
        this.accountRepository = new AccountRepository();
    }

    public Card createCard(int accountId) {
        // Check if card already exists
        Card existingCard = cardRepository.getCardByAccountId(accountId);
        if (existingCard != null) {
            return existingCard;
        }

        // Create new card
        Card card = new Card();
        card.setAccountId(accountId);
        card.setNoKartu(GeneratorUtil.generateCardNumber());
        card.setPin(GeneratorUtil.generatePIN());
        card.setStatus("AKTIF");
        card.setExpiredDate(GeneratorUtil.getExpiredDate());
        card.setWrongPinCounter(0);

        int cardId = cardRepository.createCard(card);
        if (cardId != -1) {
            card.setId(cardId);
            return card;
        }
        return null;
    }

    public Card getCardByAccountId(int accountId) {
        return cardRepository.getCardByAccountId(accountId);
    }

    public boolean hasCard(int accountId) {
        return cardRepository.getCardByAccountId(accountId) != null;
    }
}
