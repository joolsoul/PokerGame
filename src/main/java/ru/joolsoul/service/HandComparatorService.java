package ru.joolsoul.service;

import ru.joolsoul.model.cardModel.Card;
import ru.joolsoul.model.combinationModel.Combination;
import ru.joolsoul.model.combinationModel.CombinationType;
import ru.joolsoul.model.playerModel.Player;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class HandComparatorService implements Comparator<Map.Entry<Player, Combination>> {

    @Override
    public int compare(Map.Entry<Player, Combination> o1, Map.Entry<Player, Combination> o2) {
        Combination combination1 = o1.getValue();
        Combination combination2 = o2.getValue();

        if (combination1.getType().equals(CombinationType.ROYAL_FLUSH) && combination2.getType().equals(CombinationType.ROYAL_FLUSH)) {
            return 0;
        }

        if (combination1.getType().equals(combination2.getType())) {
            List<Card> cardList1 = combination1.getCombinationCards();
            List<Card> cardList2 = combination2.getCombinationCards();
            cardList1.sort(Comparator.comparing(Card::getRank).reversed());
            cardList2.sort(Comparator.comparing(Card::getRank).reversed());

            int combinationResult = new CardComparatorService().compare(cardList1.get(0), cardList2.get(0));
            if (combinationResult == 0) {
                List<Card> playerCardList1 = o1.getKey().getCards();
                List<Card> playerCardList2 = o2.getKey().getCards();
                playerCardList1.sort(Comparator.comparing(Card::getRank).reversed());
                playerCardList2.sort(Comparator.comparing(Card::getRank).reversed());

                int playerResult = new CardComparatorService().compare(playerCardList1.get(0), playerCardList2.get(0));
                if (playerResult == 0) {
                    return new CardComparatorService().compare(playerCardList1.get(1), playerCardList2.get(1));
                } else {
                    return playerResult;
                }
            } else {
                return combinationResult;
            }
        }

        return combination1.getType().compareTo(combination2.getType());
    }
}
