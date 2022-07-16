package ru.joolsoul.controller.combinationController;

import ru.joolsoul.model.cardModel.Card;
import ru.joolsoul.model.combinationModel.Combination;
import ru.joolsoul.model.combinationModel.CombinationType;

import java.util.ArrayList;
import java.util.List;

public class HighCardCombinationController implements CombinationControllerInterface {
    @Override
    public Combination checkCombination(List<Card> playerCommonCards) {
        sort(playerCommonCards);
        List<Card> combinationCard = new ArrayList<>();
        combinationCard.add(playerCommonCards.get(0));

        return new Combination(CombinationType.HIGH_CARD, combinationCard);
    }
}
