package ru.joolsoul.model.fieldModel;

import ru.joolsoul.model.cardModel.Card;

import java.util.ArrayList;
import java.util.List;

public class GameDeck {

    private List<Card> cards = new ArrayList<>();

    public GameDeck(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return '(' + cards.toString() + ')';
    }
}
