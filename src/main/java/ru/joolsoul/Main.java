package ru.joolsoul;

import ru.joolsoul.controller.cardController.CardsDistributorController;
import ru.joolsoul.controller.cardController.GameDeckController;
import ru.joolsoul.controller.combinationController.HighCardCombinationController;
import ru.joolsoul.model.Game;
import ru.joolsoul.model.cardModel.Card;
import ru.joolsoul.model.cardModel.CardRank;
import ru.joolsoul.model.cardModel.CardSuit;
import ru.joolsoul.model.combinationModel.Combination;
import ru.joolsoul.model.combinationModel.CombinationType;
import ru.joolsoul.model.playerModel.Bot;
import ru.joolsoul.model.playerModel.Player;
import ru.joolsoul.model.playerModel.PlayerRole;
import ru.joolsoul.model.stepModel.StepType;
import ru.joolsoul.service.HandComparatorService;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Card> combinationCards = new ArrayList<>();
        combinationCards.add(new Card(CardSuit.CLUBS, CardRank.TWO));
        combinationCards.add(new Card(CardSuit.DIAMONDS, CardRank.TWO));
        combinationCards.add(new Card(CardSuit.HEARTS, CardRank.TWO));
        combinationCards.add(new Card(CardSuit.CLUBS, CardRank.THREE));
        combinationCards.add(new Card(CardSuit.DIAMONDS, CardRank.THREE));
        combinationCards.add(new Card(CardSuit.HEARTS, CardRank.FOUR));
        combinationCards.add(new Card(CardSuit.HEARTS, CardRank.SIX));

    }

    public static void test1() {
        Bot p1 = new Bot("a", 1000);
        Player p2 = new Player("b", 1000);

        Card fc = new Card(CardSuit.CLUBS, CardRank.ACE);
        Card sc = new Card(CardSuit.DIAMONDS, CardRank.ACE);
        List<Card> cards = new ArrayList<>();
        cards.add(fc);
        cards.add(sc);

        System.out.println(p1);

        p1.setCards(cards);
        p2.setRole(PlayerRole.DEALER);

        System.out.println(p1);
        System.out.println(p2);

        p1.setRole(PlayerRole.KATOFF);

        System.out.println(p1);
    }

    public static void test2() {
        Game game = createGame();
        GameDeckController gdc = new GameDeckController();
        gdc.initDeck(game);
        System.out.println(game.getGameDeckCards().toString());

        CardsDistributorController cdc = new CardsDistributorController();

        for (StepType stepType : StepType.values()) {
            game.setCurrentStep(stepType);
            cdc.distributeCards(game);
        }
        System.out.println(game.getGameSteps().toString());
    }

    public static void test3() {
        Bot p1 = new Bot("a", 1000);
        Player p2 = new Player("b", 1000);

        Queue<Player> players = new LinkedList<>();
        players.add(p1);
        players.add(p2);

        Game game = new Game(players, 2.0);

        List<Card> combinationCards = new ArrayList<>();
        combinationCards.add(new Card(CardSuit.CLUBS, CardRank.TWO));
        combinationCards.add(new Card(CardSuit.DIAMONDS, CardRank.TWO));

        List<Card> p1Cards = new ArrayList<>();
        List<Card> p2Cards = new ArrayList<>();

        Card c1 = new Card(CardSuit.SPADES, CardRank.TWO);
        p1Cards.add(c1);
        p1Cards.add(new Card(CardSuit.SPADES, CardRank.FIVE));

        Card c2 = new Card(CardSuit.HEARTS, CardRank.TWO);
        p2Cards.add(c2);
        p2Cards.add(new Card(CardSuit.HEARTS, CardRank.THREE));

        p1.setCards(p1Cards);
        p2.setCards(p2Cards);

        List<Card> combination1 = new ArrayList<>(combinationCards);
        List<Card> combination2 = new ArrayList<>(combinationCards);

        combination1.add(c1);
        combination2.add(c2);

        game.getPlayerCombination().put(p1, new Combination(CombinationType.THREE_OF_A_KIND, combination1));
        game.getPlayerCombination().put(p2, new Combination(CombinationType.THREE_OF_A_KIND, combination2));

        List<Map.Entry<Player, Combination>> winnerList = new ArrayList<>(game.getPlayerCombination().entrySet());

        HandComparatorService comparatorService = new HandComparatorService();

        boolean isWin = false;

        if (comparatorService.compare(winnerList.get(0), winnerList.get(1)) > 0) {
            winnerList.remove(1);
            isWin = true;
        }

        if (!isWin && comparatorService.compare(winnerList.get(0), winnerList.get(1)) < 0) {
            winnerList.remove(0);
        }

        System.out.println(winnerList.toString());


    }

    public static void test4() {
        List<Card> combinationCards = new ArrayList<>();
        combinationCards.add(new Card(CardSuit.CLUBS, CardRank.TWO));
        combinationCards.add(new Card(CardSuit.DIAMONDS, CardRank.FIVE));
        combinationCards.add(new Card(CardSuit.DIAMONDS, CardRank.TEN));
        combinationCards.add(new Card(CardSuit.CLUBS, CardRank.THREE));
        combinationCards.add(new Card(CardSuit.DIAMONDS, CardRank.FOUR));
        combinationCards.add(new Card(CardSuit.HEARTS, CardRank.FOUR));
        combinationCards.add(new Card(CardSuit.HEARTS, CardRank.SIX));

        HighCardCombinationController pcc = new HighCardCombinationController();
        Combination combination = pcc.checkCombination(combinationCards);

        if (combination == null) {
            System.out.println("combination not found");
        } else {
            System.out.println(combination);
        }
    }

    public static void test5() {

    }

    public static Game createGame() {
        Bot p1 = new Bot("a", 1000);
        Player p2 = new Player("b", 1000);

        Queue<Player> players = new LinkedList<>();
        players.add(p1);
        players.add(p2);

        return new Game(players, 2.0);
    }
}