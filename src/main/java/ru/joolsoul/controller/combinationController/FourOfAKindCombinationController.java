package ru.joolsoul.controller.combinationController;

import ru.joolsoul.model.cardModel.Card;
import ru.joolsoul.model.combinationModel.Combination;
import ru.joolsoul.model.combinationModel.CombinationType;

import java.util.List;

public class FourOfAKindCombinationController implements CombinationControllerInterface {
    @Override
    public Combination checkCombination(List<Card> playerCommonCards) {
        return getSomeRepeatOfRank(playerCommonCards, 4, 1,
                CombinationType.FOUR_OF_A_KIND);
    }
}
