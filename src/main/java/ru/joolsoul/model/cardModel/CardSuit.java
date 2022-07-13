package ru.joolsoul.model.cardModel;

public enum CardSuit {

    HEARTS('♥'),
    CLUBS('♣'),
    SPADES('♠'),
    DIAMONDS('♦');

    private final char SUIT;

    CardSuit(char SUIT) {
        this.SUIT = SUIT;
    }

    public char getSuit() {
        return SUIT;
    }
}
