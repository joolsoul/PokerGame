package ru.joolsoul.controller.combinationController;

import ru.joolsoul.model.cardModel.Card;
import ru.joolsoul.model.cardModel.CardRank;
import ru.joolsoul.model.combinationModel.Combination;
import ru.joolsoul.model.combinationModel.CombinationType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoyalFlushCombinationController implements CombinationControllerInterface {

    private final List<CardRank> COMBINATION_RANKS = new ArrayList<>(Arrays.asList(CardRank.TEN,
            CardRank.JACK, CardRank.QUEEN, CardRank.KING, CardRank.ACE));

    @Override
    public Combination checkCombination(List<Card> playerCommonCards) {

        CombinationControllerInterface flushCombinationController = new FlushCombinationController();

        Combination combination = flushCombinationController.checkCombination(playerCommonCards);

        if (combination == null) {
            return null;
        }

        for (Card card : combination.getCombinationCards()) {
            if (!COMBINATION_RANKS.contains(card.getRank())) {
                return null;
            }
        }

        return new Combination(CombinationType.ROYAL_FLUSH, combination.getCombinationCards());
    }
}
