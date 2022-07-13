package ru.joolsoul.model.stepModel;

import ru.joolsoul.model.cardModel.Card;

import java.util.List;

public class Step { // текущие карты на борде и тип степа

    private List<Card> boardCards;
    private StepType stepType;

    public Step(List<Card> boardCards, StepType stepType) {
        this.boardCards = boardCards;
        this.stepType = stepType;
    }

    public List<Card> getBoardCards() {
        return boardCards;
    }

    public void setBoardCards(List<Card> boardCards) {
        this.boardCards = boardCards;
    }

    public StepType getStepType() {
        return stepType;
    }

    public void setStepType(StepType stepType) {
        this.stepType = stepType;
    }

    @Override
    public String toString() {
        return "Step type: " + stepType.toString() + "\n" +
                "Cards on board: " + boardCards.toString();
    }
}
