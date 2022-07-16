package ru.joolsoul.controller.cardController;

import ru.joolsoul.model.Game;
import ru.joolsoul.model.cardModel.Card;
import ru.joolsoul.model.playerModel.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CardController {

    public void getPlayersCommonCard(Game game) {
        List<Card> boardCards = game.getBoardCards();
        Map<Player, List<Card>> playerCommonCards = new LinkedHashMap<>();
        for (Player player : game.getPlayers()) {
            List<Card> currentPlayerCommonCards = new ArrayList<>(boardCards);
            currentPlayerCommonCards.addAll(player.getCards());
            playerCommonCards.put(player, currentPlayerCommonCards);
        }
        game.setPlayerCommonCards(playerCommonCards);
    }
}
