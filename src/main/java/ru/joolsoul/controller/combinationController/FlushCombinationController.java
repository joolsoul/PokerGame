package ru.joolsoul.controller.combinationController;

import ru.joolsoul.model.cardModel.Card;
import ru.joolsoul.model.cardModel.CardSuit;
import ru.joolsoul.model.combinationModel.Combination;
import ru.joolsoul.model.combinationModel.CombinationType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlushCombinationController implements CombinationControllerInterface {
    @Override
    public Combination checkCombination(List<Card> playerCommonCards) {
        if (playerCommonCards.size() < 5) {
            return null;
        }

        sort(playerCommonCards);

        Map<CardSuit, Integer> suitsMap = fillSuitsMap(playerCommonCards);
        List<Card> combinationCards = new ArrayList<>();

        CardSuit combinationSuit = null;

        for (Map.Entry<CardSuit, Integer> entry : suitsMap.entrySet()) {
            if (entry.getValue() > 4) {
                combinationSuit = entry.getKey();
            }
        }

        if (combinationSuit == null) {
            return null;
        }

        for (Card card : playerCommonCards) {
            if (combinationCards.size() == 5) {
                break;
            }
            if (card.getSuit().equals(combinationSuit)) {
                combinationCards.add(card);
            }
        }
        return new Combination(CombinationType.FLUSH, combinationCards);
    }
}
