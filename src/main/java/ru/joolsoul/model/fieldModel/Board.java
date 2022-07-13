package ru.joolsoul.model.fieldModel;

import ru.joolsoul.model.cardModel.Card;

import java.util.ArrayList;
import java.util.List;

public class Board { // карты на столе

    private List<Card> cards = new ArrayList<>();

    public Board(List<Card> cards) {
        this.cards = cards;
    }

    public Board() {
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCards(Card card) {
        this.cards.add(card);
    }


    @Override
    public String toString() {
        return "(" + cards.toString() + ")";
    }
}
