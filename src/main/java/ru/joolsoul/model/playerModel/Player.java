package ru.joolsoul.model.playerModel;

import ru.joolsoul.model.cardModel.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String playerName;
    private int playerBalance;
    private List<Card> playerCards = new ArrayList<>();
    private PlayerRole playerRole;


    public Player(String playerName, int playerBalance) {
        this.playerName = playerName;
        this.playerBalance = playerBalance;
    }

    public String getName() {
        return playerName;
    }

    public void setName(String playerName) {
        this.playerName = playerName;
    }

    public int getBalance() {
        return playerBalance;
    }

    public void setBalance(int playerBalance) {
        this.playerBalance = playerBalance;
    }

    public List<Card> getCards() {
        return playerCards;
    }

    public void setCards(List<Card> playerCards) {
        this.playerCards = playerCards;
    }

    public PlayerRole getRole() {
        return playerRole;
    }

    public void setRole(PlayerRole playerRole) {
        this.playerRole = playerRole;
    }

    @Override
    public String toString() {
        String playerInfo = "Player " + playerName + '\n' +
                "(balance: " + playerBalance + '$';
        if (playerCards != null) {
            playerInfo += '\n' + "cards: " + playerCards.toString();
        }
        if (playerRole != null) {
            playerInfo += '\n' + "role: " + playerRole;
        }
        playerInfo += ')' + "\n";

        return playerInfo;
    }
}
