package ru.joolsoul.service;

import ru.joolsoul.model.cardModel.Card;

import java.util.Comparator;

public class CardComparatorService implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        if (o1.getRank().equals(o2.getRank())) {
            return 0;
        }

        return Integer.compare(o1.getRank().ordinal(), o2.getRank().ordinal());
    }
}
