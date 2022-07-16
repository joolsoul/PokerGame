package ru.joolsoul.controller.combinationController;

import ru.joolsoul.model.cardModel.Card;
import ru.joolsoul.model.cardModel.CardRank;
import ru.joolsoul.model.cardModel.CardSuit;
import ru.joolsoul.model.combinationModel.Combination;
import ru.joolsoul.model.combinationModel.CombinationType;

import java.util.*;

public interface CombinationControllerInterface {

    Combination checkCombination(List<Card> playerCommonCards);

    default Combination getSomeRepeatOfRank(List<Card> playerCommonCards,
                                            int numberOfRepetitions, int numberOfPairs,
                                            CombinationType combinationType) {
        if (playerCommonCards.size() < numberOfPairs * numberOfRepetitions) {
            return null;
        }

        sort(playerCommonCards);

        Map<CardRank, Integer> ranksMap = fillRanksMap(playerCommonCards);

        if (!ranksMap.containsValue(numberOfRepetitions)) {
            return null;
        }

        List<Card> combinationCards = new ArrayList<>();
        List<CardRank> combinationRanks = new ArrayList<>();

        for (Map.Entry<CardRank, Integer> entry : ranksMap.entrySet()) {
            if (entry.getValue() == numberOfRepetitions) {
                combinationRanks.add(entry.getKey());
            }
        }

        combinationRanks.sort(Comparator.comparingInt(Enum::ordinal));
        Collections.reverse(combinationRanks);

        if (combinationRanks.size() < numberOfPairs) {
            return null;
        }

        for (Card card : playerCommonCards) {
            if (combinationRanks.subList(0, numberOfPairs).contains(card.getRank())) {
                combinationCards.add(card);
            }
        }
        return new Combination(combinationType, combinationCards);
    }

    default Map<CardRank, Integer> fillRanksMap(List<Card> playerHandCards) {
        Map<CardRank, Integer> ranksMap = new TreeMap<>();
        for (Card card : playerHandCards) {
            if (ranksMap.containsKey(card.getRank())) {
                ranksMap.put(card.getRank(), ranksMap.get(card.getRank()) + 1);
            } else {
                ranksMap.put(card.getRank(), 1);
            }
        }
        return ranksMap;
    }

    default Map<CardSuit, Integer> fillSuitsMap(List<Card> playerHandCards) {
        Map<CardSuit, Integer> suitsMap = new TreeMap<>();
        for (Card card : playerHandCards) {
            if (suitsMap.containsKey(card.getSuit())) {
                suitsMap.put(card.getSuit(), suitsMap.get(card.getSuit()) + 1);
            } else {
                suitsMap.put(card.getSuit(), 1);
            }
        }
        return suitsMap;
    }

    default void sort(List<Card> playerHandCards) {
        playerHandCards.sort(Comparator.comparing(Card::getRank).reversed());
    }

    default List<CardRank> removeDuplicateRanks(List<Card> inputList) {
        List<CardRank> ranksList = new ArrayList<>();
        Iterator<Card> cardIterator = inputList.iterator();

        while (cardIterator.hasNext()) {

            Card nextCard = cardIterator.next();
            if (ranksList.contains(nextCard.getRank())) {
                cardIterator.remove();
            } else {
                ranksList.add(nextCard.getRank());
            }
        }
        return ranksList;
    }
}
