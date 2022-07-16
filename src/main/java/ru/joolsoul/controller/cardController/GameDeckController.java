package ru.joolsoul.controller.cardController;

import ru.joolsoul.model.Game;
import ru.joolsoul.model.cardModel.Card;
import ru.joolsoul.model.cardModel.CardRank;
import ru.joolsoul.model.cardModel.CardSuit;
import ru.joolsoul.model.fieldModel.GameDeck;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameDeckController {

    public void initDeck(Game game) {
        createDeck(game);
        shuffleOfDeck(game);
    }

    private void createDeck(Game game) {
        List<Card> gameDeckCards = new ArrayList<>();
        for (CardSuit suit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                Card card = new Card(suit, rank);
                //loadCardImages(card); TODO:  включить загрузку изображений картам
                gameDeckCards.add(card);
            }
        }
        GameDeck gameDeck = new GameDeck(gameDeckCards);
        game.setGameDeck(gameDeck);
    }

    private void loadCardImages(Card card) {
        int suitCount = card.getSuit().ordinal();
        int startX = card.getRank().ordinal() * 71 + 1;

        try {
            BufferedImage bigImg = ImageIO.read(new File("resources/cards_" + suitCount + ".bmp"));
            BufferedImage image = bigImg.getSubimage(startX, 1, 69, 95);
            card.setImage(image);
        } catch (IOException e) {
            System.out.println("No image load from card" + card);
            ;
        }
    }

    private void shuffleOfDeck(Game game) {
        Collections.shuffle(game.getGameDeckCards());
        Collections.shuffle(game.getGameDeckCards());
        Collections.shuffle(game.getGameDeckCards());
    }
}
