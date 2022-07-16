package ru.joolsoul.controller.cardController;

import ru.joolsoul.model.Game;
import ru.joolsoul.model.cardModel.Card;
import ru.joolsoul.model.playerModel.Player;
import ru.joolsoul.model.stepModel.StepType;

import java.util.ArrayList;
import java.util.List;

public class CardsDistributorController {

    public void distributeCards(Game game) {
        if (game.getCurrentStep() == StepType.PRE_FLOP) {
            distributePlayersStartCards(game);
        } else if (game.getCurrentStep() != StepType.START && game.getCurrentStep() != StepType.SHOWDOWN) {
            distributeBoardCards(game);
        }
    }

    private void distributePlayersStartCards(Game game) {
        for (int i = 0; i < game.getPlayers().size(); i++) {
            Player currentPlayer = game.getPlayers().poll();
            List<Card> startPlayerCards = new ArrayList<>(game.getGameDeckCards().subList(0, 2));
            game.getGameDeckCards().remove(0);
            game.getGameDeckCards().remove(0);

            if (currentPlayer != null) {
                currentPlayer.setCards(startPlayerCards);
            }

            game.getPlayers().add(currentPlayer);
        }
        game.getGameSteps().put(StepType.PRE_FLOP, new ArrayList<>());
        game.setBoardCards(new ArrayList<>());
    }

    private void distributeBoardCards(Game game) {
        StepType previousStepType = StepType.values()[game.getCurrentStep().ordinal() - 1];
        List<Card> previousStepCards = game.getGameSteps().get(previousStepType);
        List<Card> boardCards = new ArrayList<>(previousStepCards);

        int numberOfCards = 1;
        if (game.getCurrentStep() == StepType.FLOP) {
            numberOfCards = 3;
        }

        for (int i = 0; i < numberOfCards; i++) {
            game.getGameDeckCards().remove(0);
            boardCards.add(game.getGameDeckCards().get(0));
            game.addBoardCard(game.getGameDeckCards().get(0));
            game.getGameDeckCards().remove(0);
        }

        StepType currentStepType = game.getCurrentStep();

        game.getGameSteps().put(currentStepType, boardCards);
    }
}
