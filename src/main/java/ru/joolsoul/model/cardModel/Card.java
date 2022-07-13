package ru.joolsoul.model.cardModel;

import java.awt.*;

public class Card {

    private CardSuit suit;
    private CardRank rank;
    private Image image;
    private boolean isFaceUp;

    public Card(CardSuit suit, CardRank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Card(CardSuit suit, CardRank rank, Image image) {
        this.suit = suit;
        this.rank = rank;
        this.image = image;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public CardRank getRank() {
        return rank;
    }

    public void setSuit(CardSuit suit) {
        this.suit = suit;
    }

    public void setRank(CardRank rank) {
        this.rank = rank;
    }

    public Image getImage() {
        if (isFaceUp) {
            return image;
        }
        return null; //TODO вернуть рубашку карты

    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isFaceUp() {
        return this.isFaceUp;
    }

    public void setFaceUp() {
        this.isFaceUp = true;
    }

    @Override
    public String toString() {
        return "(" + suit.getSuit() + rank.getRank() + ")";
    }
}
