package ru.joolsoul.model;

import ru.joolsoul.model.actionModel.Action;
import ru.joolsoul.model.cardModel.Card;
import ru.joolsoul.model.combinationModel.Combination;
import ru.joolsoul.model.fieldModel.Board;
import ru.joolsoul.model.fieldModel.GameDeck;
import ru.joolsoul.model.playerModel.Player;
import ru.joolsoul.model.stepModel.Step;
import ru.joolsoul.model.stepModel.StepType;

import java.util.*;

public class Game {

    private Queue<Player> players;

    private GameDeck gameDeck;

    private Board boardCards;

    private Map<Player, List<Card>> playerCommonCards = new LinkedHashMap<>(); // карты игрока + карты на столе

    private Map<Player, Combination> playerCombination = new LinkedHashMap<>();

    private List<Step> gameSteps = new LinkedList<>();

    private ArrayList<Player> playersInStep = new ArrayList<>();

    private Map<Player, Action> playersStepAction = new LinkedHashMap<>();

    private StepType currentStep;

    private boolean inGame;

    private Double ante;

    private Double smallBlind;

    private Double bigBlind;

    public Game(Queue<Player> players, Double bigBlind) {
        this.players = players;
        this.bigBlind = bigBlind;
        this.smallBlind = bigBlind / 2;
        this.ante = bigBlind / 10;

    }

    public Queue<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Queue<Player> players) {
        this.players = players;
    }

    public GameDeck getGameDeck() {
        return gameDeck;
    }

    public void setGameDeck(GameDeck gameDeck) {
        this.gameDeck = gameDeck;
    }

    public Board getBoardCards() {
        return boardCards;
    }

    public void setBoardCards(Board boardCards) {
        this.boardCards = boardCards;
    }

    public Map<Player, List<Card>> getPlayerCommonCards() {
        return playerCommonCards;
    }

    public void setPlayerCommonCards(Map<Player, List<Card>> playerCommonCards) {
        this.playerCommonCards = playerCommonCards;
    }

    public Map<Player, Combination> getPlayerCombination() {
        return playerCombination;
    }

    public void setPlayerCombination(Map<Player, Combination> playerCombination) {
        this.playerCombination = playerCombination;
    }

    public List<Step> getGameSteps() {
        return gameSteps;
    }

    public void setGameSteps(List<Step> gameSteps) {
        this.gameSteps = gameSteps;
    }

    public ArrayList<Player> getPlayersInStep() {
        return playersInStep;
    }

    public void setPlayersInStep(ArrayList<Player> playersInStep) {
        this.playersInStep = playersInStep;
    }

    public Map<Player, Action> getPlayersStepAction() {
        return playersStepAction;
    }

    public void setPlayersStepAction(Map<Player, Action> playersStepAction) {
        this.playersStepAction = playersStepAction;
    }

    public StepType getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(StepType currentStep) {
        this.currentStep = currentStep;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public Double getAnte() {
        return ante;
    }

    public void setAnte(Double ante) {
        this.ante = ante;
    }

    public Double getSmallBlind() {
        return smallBlind;
    }

    public void setSmallBlind(Double smallBlind) {
        this.smallBlind = smallBlind;
    }

    public Double getBigBlind() {
        return bigBlind;
    }

    public void setBigBlind(Double bigBlind) {
        this.bigBlind = bigBlind;
    }
}
