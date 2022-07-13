package ru.joolsoul;

import ru.joolsoul.model.cardModel.Card;
import ru.joolsoul.model.cardModel.CardRank;
import ru.joolsoul.model.cardModel.CardSuit;
import ru.joolsoul.model.playerModel.Bot;
import ru.joolsoul.model.playerModel.Player;
import ru.joolsoul.model.playerModel.PlayerRole;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

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
}