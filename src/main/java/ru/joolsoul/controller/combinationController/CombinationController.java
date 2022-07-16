package ru.joolsoul.controller.combinationController;

import ru.joolsoul.model.Game;
import ru.joolsoul.model.cardModel.Card;
import ru.joolsoul.model.combinationModel.Combination;
import ru.joolsoul.model.playerModel.Player;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CombinationController {

    private List<CombinationControllerInterface> combinationsControllers = new LinkedList<>();

    public CombinationController() {
        combinationsControllers.add(new RoyalFlushCombinationController());
        combinationsControllers.add(new StraightFlushCombinationController());
        combinationsControllers.add(new FourOfAKindCombinationController());
        combinationsControllers.add(new FullHouseCombinationController());
        combinationsControllers.add(new FlushCombinationController());
        combinationsControllers.add(new StraightCombinationController());
        combinationsControllers.add(new ThreeOfAKindCombinationController());
        combinationsControllers.add(new TwoPairsCombinationController());
        combinationsControllers.add(new PairCombinationController());
        combinationsControllers.add(new HighCardCombinationController());
    }

    public void getCombinations(Game game) {
        Map<Player, Combination> playerCombination = new LinkedHashMap<>();

        for (Map.Entry<Player, List<Card>> entry : game.getPlayerCommonCards().entrySet()) {
            playerCombination.put(entry.getKey(), calculateCombination(entry.getValue()));
        }
        game.setPlayerCombination(playerCombination);
    }

    private Combination calculateCombination(List<Card> playerCommonCards) {
        for (CombinationControllerInterface combinationController : combinationsControllers) {
            Combination combination = combinationController.checkCombination(playerCommonCards);
            if (combination != null) {
                return combination;
            }
        }
        return null;
    }
}
