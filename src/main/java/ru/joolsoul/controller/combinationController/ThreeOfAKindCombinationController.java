package ru.joolsoul.controller.combinationController;

import ru.joolsoul.model.cardModel.Card;
import ru.joolsoul.model.combinationModel.Combination;
import ru.joolsoul.model.combinationModel.CombinationType;

import java.util.List;

public class ThreeOfAKindCombinationController implements CombinationControllerInterface {
    @Override
    public Combination checkCombination(List<Card> playerCommonCards) {
        return getSomeRepeatOfRank(playerCommonCards, 3, 1,
                CombinationType.THREE_OF_A_KIND);
    }
}
