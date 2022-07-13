package ru.joolsoul.model.combinationModel;

import ru.joolsoul.model.cardModel.Card;

import java.util.List;

public class Combination {

    private CombinationType type;
    private List<Card> combinationCards;

    public Combination(CombinationType type, List<Card> combinationCards) {
        this.type = type;
        this.combinationCards = combinationCards;
    }

    public CombinationType getType() {
        return type;
    }

    public void setType(CombinationType type) {
        this.type = type;
    }

    public List<Card> getCombinationCards() {
        return combinationCards;
    }

    public void setCombinationCards(List<Card> combinationCards) {
        this.combinationCards = combinationCards;
    }

    @Override
    public String toString() {
        return type + combinationCards.toString();
    }
}
