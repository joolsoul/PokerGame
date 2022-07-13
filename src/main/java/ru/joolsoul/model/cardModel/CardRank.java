package ru.joolsoul.model.cardModel;

public enum CardRank {

    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");


    private final String RANK;

    CardRank(String RANK) {
        this.RANK = RANK;
    }

    public String getRank() {
        return RANK;
    }

}
