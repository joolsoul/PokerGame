package ru.joolsoul.controller.combinationController;

import ru.joolsoul.model.cardModel.Card;
import ru.joolsoul.model.cardModel.CardRank;
import ru.joolsoul.model.cardModel.CardSuit;
import ru.joolsoul.model.combinationModel.Combination;
import ru.joolsoul.model.combinationModel.CombinationType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StraightFlushCombinationController implements CombinationControllerInterface {

    private final List<CardRank> RANKS_TO_YOUNGEST_STRAIGHT = new ArrayList<>(
            Arrays.asList(CardRank.ACE, CardRank.TWO, CardRank.THREE,
                    CardRank.FOUR, CardRank.FIVE));

    @Override
    public Combination checkCombination(List<Card> playerCommonCards) {
        if (playerCommonCards.size() < 5) {
            return null;
        }

        sort(playerCommonCards);

        Map<CardSuit, Integer> suitsMap = fillSuitsMap(playerCommonCards);

        CardSuit probablyCombinationSuit = null;

        for (Map.Entry<CardSuit, Integer> entry : suitsMap.entrySet()) {
            if (entry.getValue() > 4) {
                probablyCombinationSuit = entry.getKey();
            }
        }

        List<Card> probablyCombinationCards = new ArrayList<>();

        for (Card card : playerCommonCards) {
            if (card.getSuit().equals(probablyCombinationSuit)) {
                probablyCombinationCards.add(card);
            }
        }

        CombinationControllerInterface straightCombinationController = new StraightCombinationController();

        Combination combination = straightCombinationController.checkCombination(probablyCombinationCards);

        if (combination == null) {
            return null;
        }

        return new Combination(CombinationType.STRAIGHT_FLUSH, combination.getCombinationCards());
    }
}
