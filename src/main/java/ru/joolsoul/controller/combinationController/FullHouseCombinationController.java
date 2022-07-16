package ru.joolsoul.controller.combinationController;

import ru.joolsoul.model.cardModel.Card;
import ru.joolsoul.model.cardModel.CardRank;
import ru.joolsoul.model.combinationModel.Combination;
import ru.joolsoul.model.combinationModel.CombinationType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FullHouseCombinationController implements CombinationControllerInterface {
    @Override
    public Combination checkCombination(List<Card> playerCommonCards) {
        if (playerCommonCards.size() < 5) {
            return null;
        }

        sort(playerCommonCards);

        Map<CardRank, Integer> ranksMap = fillRanksMap(playerCommonCards);

        if (!ranksMap.containsValue(3) && !ranksMap.containsValue(4)) {
            return null;
        }

        List<Card> combinationCards = new ArrayList<>();

        CardRank maxPairRank = CardRank.TWO;
        List<CardRank> thirdRanks = new ArrayList<>();
        CardRank fourRank = null;

        List<CardRank> combinationRanks = new ArrayList<>();

        for (Map.Entry<CardRank, Integer> entry : ranksMap.entrySet()) {
            if (entry.getValue() == 2 && entry.getKey().ordinal() > maxPairRank.ordinal()) {
                maxPairRank = entry.getKey();
            }
            if (entry.getValue() == 3) {
                thirdRanks.add(entry.getKey());
            }
            if (entry.getValue() == 4) {
                fourRank = entry.getKey();
            }
        }

        if (ranksMap.containsValue(3)) {
            combinationRanks.addAll(thirdRanks);
            if (ranksMap.containsValue(2)) {
                combinationRanks.add(maxPairRank);
            }
            if (ranksMap.containsValue(4)) {
                combinationRanks.add(fourRank);
            }
        }

        if (ranksMap.containsValue(4) && ranksMap.containsValue(2)) {
            combinationRanks.add(fourRank);
            combinationRanks.add(maxPairRank);
        }

        if (combinationRanks.size() < 2) {
            return null;
        }

        Iterator<Card> cardIterator = playerCommonCards.iterator();
        while (cardIterator.hasNext()) {

            Card nextCard = cardIterator.next();
            if (nextCard.getRank().equals(fourRank)) {
                cardIterator.remove();
                break;
            }
        }

        for (Card card : playerCommonCards) {
            if (combinationRanks.contains(card.getRank()) &&
                    combinationCards.size() < 5) {
                combinationCards.add(card);
            }
        }
        return new Combination(CombinationType.FULL_HOUSE, combinationCards);
    }
}
