package ru.joolsoul.controller.combinationController;

import ru.joolsoul.model.cardModel.Card;
import ru.joolsoul.model.cardModel.CardRank;
import ru.joolsoul.model.combinationModel.Combination;
import ru.joolsoul.model.combinationModel.CombinationType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class StraightCombinationController implements CombinationControllerInterface {

    private final List<CardRank> RANKS_TO_YOUNGEST_STRAIGHT = new ArrayList<>(
            Arrays.asList(CardRank.ACE, CardRank.TWO, CardRank.THREE,
                    CardRank.FOUR, CardRank.FIVE));

    @Override
    public Combination checkCombination(List<Card> playerCommonCards) {
        if (playerCommonCards.size() < 5) {
            return null;
        }

        sort(playerCommonCards);

        List<Card> combinationCards = new ArrayList<>();
        List<Card> playerCommonCardsCopy = new ArrayList<>(playerCommonCards);

        List<CardRank> cardsRank = removeDuplicateRanks(playerCommonCardsCopy); //без дубликатов карт


        if (playerCommonCardsCopy.size() < 5) {
            return null;
        }

        if (new HashSet<>(cardsRank).containsAll(RANKS_TO_YOUNGEST_STRAIGHT) &&
                !cardsRank.contains(CardRank.SIX)) {
            for (Card card : playerCommonCardsCopy) {
                if (RANKS_TO_YOUNGEST_STRAIGHT.contains(card.getRank())) {
                    combinationCards.add(card);
                }
            }
            combinationCards.add(combinationCards.get(0));
            combinationCards.remove(0);
            return new Combination(CombinationType.STRAIGHT, combinationCards);
        }

        for (int i = 0; i < playerCommonCardsCopy.size() - 4; i++) {
            if (combinationCards.size() == 5) {
                break;
            }
            combinationCards.clear();
            combinationCards.add(playerCommonCardsCopy.get(i));
            for (int j = i + 1; j < i + 5; j++) {
                Card currentCard = playerCommonCardsCopy.get(j);
                Card previousCard = playerCommonCardsCopy.get(j - 1);
                if (previousCard.getRank().ordinal() -
                        currentCard.getRank().ordinal() == 1) {
                    combinationCards.add(currentCard);
                } else {
                    break;
                }
            }
        }
        if (combinationCards.size() == 5) {
            return new Combination(CombinationType.STRAIGHT, combinationCards);
        }
        return null;
    }
}
