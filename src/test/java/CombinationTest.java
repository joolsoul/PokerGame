import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.joolsoul.controller.combinationController.*;
import ru.joolsoul.model.cardModel.Card;
import ru.joolsoul.model.cardModel.CardRank;
import ru.joolsoul.model.cardModel.CardSuit;
import ru.joolsoul.model.combinationModel.Combination;
import ru.joolsoul.model.combinationModel.CombinationType;

import java.util.ArrayList;
import java.util.List;

public class CombinationTest {

    private CombinationControllerInterface combinationController;
    List<Card> combinationCards = new ArrayList<>();
    List<Card> expected = new ArrayList<>();

    @Test
    public void testHighCardCombination() {
        Card card0 = new Card(CardSuit.CLUBS, CardRank.TWO);
        Card card1 = new Card(CardSuit.DIAMONDS, CardRank.FIVE);
        Card card2 = new Card(CardSuit.DIAMONDS, CardRank.TEN);
        Card card3 = new Card(CardSuit.CLUBS, CardRank.THREE);
        Card card4 = new Card(CardSuit.DIAMONDS, CardRank.FOUR);
        Card card5 = new Card(CardSuit.HEARTS, CardRank.ACE);
        Card card6 = new Card(CardSuit.HEARTS, CardRank.SIX);

        combinationCards.add(card0);
        combinationCards.add(card1);
        combinationCards.add(card2);
        combinationCards.add(card3);
        combinationCards.add(card4);
        combinationCards.add(card5);
        combinationCards.add(card6);

        expected.add(card5);

        combinationController = new HighCardCombinationController();
        Combination combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.HIGH_CARD, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.remove(6);
        Card newCard = new Card(CardSuit.SPADES, CardRank.ACE);
        combinationCards.add(6, newCard);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.HIGH_CARD, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());
    }

    @Test
    public void testPairCombination() {
        Card card0 = new Card(CardSuit.CLUBS, CardRank.TWO);
        Card card1 = new Card(CardSuit.DIAMONDS, CardRank.TWO);
        Card card2 = new Card(CardSuit.DIAMONDS, CardRank.TEN);
        Card card3 = new Card(CardSuit.CLUBS, CardRank.THREE);
        Card card4 = new Card(CardSuit.DIAMONDS, CardRank.FOUR);
        Card card5 = new Card(CardSuit.HEARTS, CardRank.ACE);
        Card card6 = new Card(CardSuit.HEARTS, CardRank.SIX);

        combinationCards.add(card0);
        combinationCards.add(card1);
        combinationCards.add(card2);
        combinationCards.add(card3);
        combinationCards.add(card4);
        combinationCards.add(card5);
        combinationCards.add(card6);

        expected.add(card0);
        expected.add(card1);

        combinationController = new PairCombinationController();
        Combination combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.PAIR, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.remove(3);
        combinationCards.remove(4);
        Card newCard1_1 = new Card(CardSuit.SPADES, CardRank.FIVE);
        Card newCard1_2 = new Card(CardSuit.HEARTS, CardRank.FIVE);
        combinationCards.add(3, newCard1_1);
        combinationCards.add(5, newCard1_2);

        expected.clear();
        expected.add(newCard1_1);
        expected.add(newCard1_2);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.PAIR, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.remove(0);
        combinationCards.remove(4);
        Card newCard2_1 = new Card(CardSuit.SPADES, CardRank.THREE);
        Card newCard2_2 = new Card(CardSuit.HEARTS, CardRank.SEVEN);
        combinationCards.add(newCard2_1);
        combinationCards.add(newCard2_2);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertNull(combination);
    }

    @Test
    public void testTwoPairCombination() {
        Card card0 = new Card(CardSuit.CLUBS, CardRank.TWO);
        Card card1 = new Card(CardSuit.DIAMONDS, CardRank.TWO);
        Card card2 = new Card(CardSuit.DIAMONDS, CardRank.TEN);
        Card card3 = new Card(CardSuit.CLUBS, CardRank.FOUR);
        Card card4 = new Card(CardSuit.DIAMONDS, CardRank.FOUR);
        Card card5 = new Card(CardSuit.HEARTS, CardRank.ACE);
        Card card6 = new Card(CardSuit.HEARTS, CardRank.SIX);

        combinationCards.add(card0);
        combinationCards.add(card1);
        combinationCards.add(card2);
        combinationCards.add(card3);
        combinationCards.add(card4);
        combinationCards.add(card5);
        combinationCards.add(card6);

        expected.add(card3);
        expected.add(card4);
        expected.add(card0);
        expected.add(card1);

        combinationController = new TwoPairsCombinationController();
        Combination combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.TWO_PAIRS, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.remove(5);
        combinationCards.remove(5);
        Card newCard1_1 = new Card(CardSuit.SPADES, CardRank.FIVE);
        Card newCard1_2 = new Card(CardSuit.HEARTS, CardRank.FIVE);
        combinationCards.add(newCard1_1);
        combinationCards.add(newCard1_2);

        expected.clear();
        expected.add(newCard1_1);
        expected.add(newCard1_2);
        expected.add(card3);
        expected.add(card4);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.TWO_PAIRS, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.remove(0);
        combinationCards.remove(3);
        combinationCards.remove(4);
        Card newCard2_1 = new Card(CardSuit.SPADES, CardRank.THREE);
        Card newCard2_2 = new Card(CardSuit.HEARTS, CardRank.SEVEN);
        Card newCard2_3 = new Card(CardSuit.HEARTS, CardRank.QUEEN);
        combinationCards.add(newCard2_1);
        combinationCards.add(newCard2_2);
        combinationCards.add(newCard2_3);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertNull(combination);
    }

    @Test
    public void testThreeOfAKindCombination() {
        Card card0 = new Card(CardSuit.CLUBS, CardRank.THREE);
        Card card1 = new Card(CardSuit.DIAMONDS, CardRank.THREE);
        Card card2 = new Card(CardSuit.DIAMONDS, CardRank.TEN);
        Card card3 = new Card(CardSuit.CLUBS, CardRank.FOUR);
        Card card4 = new Card(CardSuit.DIAMONDS, CardRank.FOUR);
        Card card5 = new Card(CardSuit.HEARTS, CardRank.THREE);
        Card card6 = new Card(CardSuit.HEARTS, CardRank.SIX);

        combinationCards.add(card0);
        combinationCards.add(card1);
        combinationCards.add(card2);
        combinationCards.add(card3);
        combinationCards.add(card4);
        combinationCards.add(card5);
        combinationCards.add(card6);

        expected.add(card0);
        expected.add(card1);
        expected.add(card5);

        combinationController = new ThreeOfAKindCombinationController();
        Combination combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.THREE_OF_A_KIND, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.remove(6);
        Card newCard1_1 = new Card(CardSuit.HEARTS, CardRank.FOUR);
        combinationCards.add(newCard1_1);

        expected.clear();
        expected.add(card3);
        expected.add(card4);
        expected.add(newCard1_1);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.THREE_OF_A_KIND, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.remove(0);
        combinationCards.remove(5);
        Card newCard2_1 = new Card(CardSuit.SPADES, CardRank.QUEEN);
        Card newCard2_2 = new Card(CardSuit.HEARTS, CardRank.ACE);
        combinationCards.add(newCard2_1);
        combinationCards.add(newCard2_2);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertNull(combination);
    }

    @Test
    public void testStraightCombination() {
        Card card0 = new Card(CardSuit.CLUBS, CardRank.THREE);
        Card card1 = new Card(CardSuit.DIAMONDS, CardRank.FIVE);
        Card card2 = new Card(CardSuit.SPADES, CardRank.FOUR);
        Card card3 = new Card(CardSuit.HEARTS, CardRank.ACE);
        Card card4 = new Card(CardSuit.CLUBS, CardRank.SIX);
        Card card5 = new Card(CardSuit.DIAMONDS, CardRank.THREE);
        Card card6 = new Card(CardSuit.SPADES, CardRank.TWO);

        combinationCards.add(card0);
        combinationCards.add(card1);
        combinationCards.add(card2);
        combinationCards.add(card3);
        combinationCards.add(card4);
        combinationCards.add(card5);
        combinationCards.add(card6);

        expected.add(card4);
        expected.add(card1);
        expected.add(card2);
        expected.add(card0);
        expected.add(card6);

        combinationController = new StraightCombinationController();
        Combination combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.STRAIGHT, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.remove(4);
        Card newCard1_1 = new Card(CardSuit.SPADES, CardRank.ACE);
        combinationCards.add(newCard1_1);

        expected.clear();
        expected.add(card1);
        expected.add(card2);
        expected.add(card0);
        expected.add(card6);
        expected.add(card3);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.STRAIGHT, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.clear();
        expected.clear();

        Card card2_0 = new Card(CardSuit.CLUBS, CardRank.THREE);
        Card card2_1 = new Card(CardSuit.DIAMONDS, CardRank.FIVE);
        Card card2_2 = new Card(CardSuit.SPADES, CardRank.FOUR);
        Card card2_3 = new Card(CardSuit.HEARTS, CardRank.SEVEN);
        Card card2_4 = new Card(CardSuit.CLUBS, CardRank.SIX);
        Card card2_5 = new Card(CardSuit.DIAMONDS, CardRank.EIGHT);
        Card card2_6 = new Card(CardSuit.SPADES, CardRank.TWO);

        combinationCards.add(card2_0);
        combinationCards.add(card2_1);
        combinationCards.add(card2_2);
        combinationCards.add(card2_3);
        combinationCards.add(card2_4);
        combinationCards.add(card2_5);
        combinationCards.add(card2_6);

        expected.add(card2_5);
        expected.add(card2_3);
        expected.add(card2_4);
        expected.add(card2_1);
        expected.add(card2_2);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.STRAIGHT, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.remove(1);
        Card newCard3_1 = new Card(CardSuit.SPADES, CardRank.ACE);
        combinationCards.add(newCard3_1);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertNull(combination);
    }

    @Test
    public void testFlushCombination() {
        Card card0 = new Card(CardSuit.CLUBS, CardRank.THREE);
        Card card1 = new Card(CardSuit.DIAMONDS, CardRank.THREE);
        Card card2 = new Card(CardSuit.CLUBS, CardRank.TEN);
        Card card3 = new Card(CardSuit.CLUBS, CardRank.FOUR);
        Card card4 = new Card(CardSuit.DIAMONDS, CardRank.FOUR);
        Card card5 = new Card(CardSuit.CLUBS, CardRank.ACE);
        Card card6 = new Card(CardSuit.CLUBS, CardRank.SIX);

        combinationCards.add(card0);
        combinationCards.add(card1);
        combinationCards.add(card2);
        combinationCards.add(card3);
        combinationCards.add(card4);
        combinationCards.add(card5);
        combinationCards.add(card6);

        expected.add(card5);
        expected.add(card2);
        expected.add(card6);
        expected.add(card3);
        expected.add(card0);

        combinationController = new FlushCombinationController();
        Combination combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.FLUSH, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.remove(1);
        Card newCard1_1 = new Card(CardSuit.CLUBS, CardRank.QUEEN);
        combinationCards.add(newCard1_1);

        expected.clear();
        expected.add(card5);
        expected.add(newCard1_1);
        expected.add(card2);
        expected.add(card6);
        expected.add(card3);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.FLUSH, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.remove(0);
        combinationCards.remove(5);
        Card newCard2_1 = new Card(CardSuit.SPADES, CardRank.QUEEN);
        Card newCard2_2 = new Card(CardSuit.HEARTS, CardRank.ACE);
        combinationCards.add(newCard2_1);
        combinationCards.add(newCard2_2);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertNull(combination);
    }

    @Test
    public void testFullHouseCombination() {
        Card card0 = new Card(CardSuit.CLUBS, CardRank.THREE);
        Card card1 = new Card(CardSuit.DIAMONDS, CardRank.THREE);
        Card card2 = new Card(CardSuit.HEARTS, CardRank.TWO);
        Card card3 = new Card(CardSuit.SPADES, CardRank.FOUR);
        Card card4 = new Card(CardSuit.DIAMONDS, CardRank.FOUR);
        Card card5 = new Card(CardSuit.HEARTS, CardRank.THREE);
        Card card6 = new Card(CardSuit.HEARTS, CardRank.FOUR);

        combinationCards.add(card0);
        combinationCards.add(card1);
        combinationCards.add(card2);
        combinationCards.add(card3);
        combinationCards.add(card4);
        combinationCards.add(card5);
        combinationCards.add(card6);

        expected.add(card3);
        expected.add(card4);
        expected.add(card6);
        expected.add(card0);
        expected.add(card1);

        combinationController = new FullHouseCombinationController();
        Combination combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.FULL_HOUSE, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.clear();
        expected.clear();

        Card card1_0 = new Card(CardSuit.CLUBS, CardRank.THREE);
        Card card1_1 = new Card(CardSuit.DIAMONDS, CardRank.THREE);
        Card card1_2 = new Card(CardSuit.HEARTS, CardRank.FIVE);
        Card card1_3 = new Card(CardSuit.SPADES, CardRank.FOUR);
        Card card1_4 = new Card(CardSuit.DIAMONDS, CardRank.FIVE);
        Card card1_5 = new Card(CardSuit.HEARTS, CardRank.THREE);
        Card card1_6 = new Card(CardSuit.HEARTS, CardRank.FOUR);

        combinationCards.add(card1_0);
        combinationCards.add(card1_1);
        combinationCards.add(card1_2);
        combinationCards.add(card1_3);
        combinationCards.add(card1_4);
        combinationCards.add(card1_5);
        combinationCards.add(card1_6);

        expected.add(card1_2);
        expected.add(card1_4);
        expected.add(card1_0);
        expected.add(card1_1);
        expected.add(card1_5);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.FULL_HOUSE, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.clear();
        expected.clear();

        Card card2_0 = new Card(CardSuit.CLUBS, CardRank.FOUR);
        Card card2_1 = new Card(CardSuit.DIAMONDS, CardRank.THREE);
        Card card2_2 = new Card(CardSuit.DIAMONDS, CardRank.FOUR);
        Card card2_3 = new Card(CardSuit.SPADES, CardRank.FOUR);
        Card card2_4 = new Card(CardSuit.SPADES, CardRank.THREE);
        Card card2_5 = new Card(CardSuit.HEARTS, CardRank.THREE);
        Card card2_6 = new Card(CardSuit.HEARTS, CardRank.FOUR);

        combinationCards.add(card2_0);
        combinationCards.add(card2_1);
        combinationCards.add(card2_2);
        combinationCards.add(card2_3);
        combinationCards.add(card2_4);
        combinationCards.add(card2_5);
        combinationCards.add(card2_6);

        expected.add(card2_2);
        expected.add(card2_3);
        expected.add(card2_6);
        expected.add(card2_1);
        expected.add(card2_4);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.FULL_HOUSE, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.clear();
        expected.clear();

        Card card3_0 = new Card(CardSuit.CLUBS, CardRank.FOUR);
        Card card3_1 = new Card(CardSuit.DIAMONDS, CardRank.THREE);
        Card card3_2 = new Card(CardSuit.DIAMONDS, CardRank.FOUR);
        Card card3_3 = new Card(CardSuit.SPADES, CardRank.FOUR);
        Card card3_4 = new Card(CardSuit.SPADES, CardRank.THREE);
        Card card3_5 = new Card(CardSuit.HEARTS, CardRank.FIVE);
        Card card3_6 = new Card(CardSuit.HEARTS, CardRank.FOUR);

        combinationCards.add(card3_0);
        combinationCards.add(card3_1);
        combinationCards.add(card3_2);
        combinationCards.add(card3_3);
        combinationCards.add(card3_4);
        combinationCards.add(card3_5);
        combinationCards.add(card3_6);

        expected.add(card3_2);
        expected.add(card3_3);
        expected.add(card3_6);
        expected.add(card3_1);
        expected.add(card3_4);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.FULL_HOUSE, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.clear();
        expected.clear();

        Card card4_0 = new Card(CardSuit.CLUBS, CardRank.FOUR);
        Card card4_1 = new Card(CardSuit.DIAMONDS, CardRank.SEVEN);
        Card card4_2 = new Card(CardSuit.DIAMONDS, CardRank.FOUR);
        Card card4_3 = new Card(CardSuit.SPADES, CardRank.FOUR);
        Card card4_4 = new Card(CardSuit.SPADES, CardRank.THREE);
        Card card4_5 = new Card(CardSuit.HEARTS, CardRank.FIVE);
        Card card4_6 = new Card(CardSuit.HEARTS, CardRank.FOUR);

        combinationCards.add(card4_0);
        combinationCards.add(card4_1);
        combinationCards.add(card4_2);
        combinationCards.add(card4_3);
        combinationCards.add(card4_4);
        combinationCards.add(card4_5);
        combinationCards.add(card4_6);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertNull(combination);
    }

    @Test
    public void testFourOfAKindCombination() {
        Card card0 = new Card(CardSuit.CLUBS, CardRank.THREE);
        Card card1 = new Card(CardSuit.DIAMONDS, CardRank.THREE);
        Card card2 = new Card(CardSuit.DIAMONDS, CardRank.TEN);
        Card card3 = new Card(CardSuit.CLUBS, CardRank.FOUR);
        Card card4 = new Card(CardSuit.DIAMONDS, CardRank.FOUR);
        Card card5 = new Card(CardSuit.HEARTS, CardRank.THREE);
        Card card6 = new Card(CardSuit.SPADES, CardRank.THREE);

        combinationCards.add(card0);
        combinationCards.add(card1);
        combinationCards.add(card2);
        combinationCards.add(card3);
        combinationCards.add(card4);
        combinationCards.add(card5);
        combinationCards.add(card6);

        expected.add(card0);
        expected.add(card1);
        expected.add(card5);
        expected.add(card6);

        combinationController = new FourOfAKindCombinationController();
        Combination combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.FOUR_OF_A_KIND, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.remove(2);
        Card newCard1_1 = new Card(CardSuit.HEARTS, CardRank.FOUR);
        combinationCards.add(newCard1_1);

        expected.clear();
        expected.add(card0);
        expected.add(card1);
        expected.add(card5);
        expected.add(card6);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.FOUR_OF_A_KIND, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.remove(0);
        Card newCard2_1 = new Card(CardSuit.SPADES, CardRank.QUEEN);
        combinationCards.add(newCard2_1);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertNull(combination);
    }

    @Test
    public void testStraightFlushCombination() {
        Card card0 = new Card(CardSuit.DIAMONDS, CardRank.TWO);
        Card card1 = new Card(CardSuit.DIAMONDS, CardRank.THREE);
        Card card2 = new Card(CardSuit.DIAMONDS, CardRank.FOUR);
        Card card3 = new Card(CardSuit.DIAMONDS, CardRank.FIVE);
        Card card4 = new Card(CardSuit.DIAMONDS, CardRank.SIX);
        Card card5 = new Card(CardSuit.DIAMONDS, CardRank.SEVEN);
        Card card6 = new Card(CardSuit.DIAMONDS, CardRank.ACE);

        combinationCards.add(card0);
        combinationCards.add(card1);
        combinationCards.add(card2);
        combinationCards.add(card3);
        combinationCards.add(card4);
        combinationCards.add(card5);
        combinationCards.add(card6);

        expected.add(card5);
        expected.add(card4);
        expected.add(card3);
        expected.add(card2);
        expected.add(card1);

        combinationController = new StraightFlushCombinationController();
        Combination combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.STRAIGHT_FLUSH, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.clear();
        expected.clear();

        Card card1_0 = new Card(CardSuit.DIAMONDS, CardRank.TWO);
        Card card1_1 = new Card(CardSuit.DIAMONDS, CardRank.THREE);
        Card card1_2 = new Card(CardSuit.DIAMONDS, CardRank.FOUR);
        Card card1_3 = new Card(CardSuit.DIAMONDS, CardRank.FIVE);
        Card card1_4 = new Card(CardSuit.DIAMONDS, CardRank.EIGHT);
        Card card1_5 = new Card(CardSuit.DIAMONDS, CardRank.SEVEN);
        Card card1_6 = new Card(CardSuit.DIAMONDS, CardRank.ACE);

        combinationCards.add(card1_0);
        combinationCards.add(card1_1);
        combinationCards.add(card1_2);
        combinationCards.add(card1_3);
        combinationCards.add(card1_4);
        combinationCards.add(card1_5);
        combinationCards.add(card1_6);

        expected.add(card1_3);
        expected.add(card1_2);
        expected.add(card1_1);
        expected.add(card1_0);
        expected.add(card1_6);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.STRAIGHT_FLUSH, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.clear();
        expected.clear();

        Card card2_0 = new Card(CardSuit.DIAMONDS, CardRank.TWO);
        Card card2_1 = new Card(CardSuit.DIAMONDS, CardRank.THREE);
        Card card2_2 = new Card(CardSuit.DIAMONDS, CardRank.FOUR);
        Card card2_3 = new Card(CardSuit.DIAMONDS, CardRank.FIVE);
        Card card2_4 = new Card(CardSuit.DIAMONDS, CardRank.SIX);
        Card card2_5 = new Card(CardSuit.HEARTS, CardRank.SEVEN);
        Card card2_6 = new Card(CardSuit.DIAMONDS, CardRank.EIGHT);

        combinationCards.add(card2_0);
        combinationCards.add(card2_1);
        combinationCards.add(card2_2);
        combinationCards.add(card2_3);
        combinationCards.add(card2_4);
        combinationCards.add(card2_5);
        combinationCards.add(card2_6);

        expected.add(card2_4);
        expected.add(card2_3);
        expected.add(card2_2);
        expected.add(card2_1);
        expected.add(card2_0);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.STRAIGHT_FLUSH, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.get(2).setSuit(CardSuit.CLUBS);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertNull(combination);
    }

    @Test
    public void testRoyalFlushCombination() {
        Card card0 = new Card(CardSuit.DIAMONDS, CardRank.JACK);
        Card card1 = new Card(CardSuit.DIAMONDS, CardRank.KING);
        Card card2 = new Card(CardSuit.DIAMONDS, CardRank.TEN);
        Card card3 = new Card(CardSuit.CLUBS, CardRank.FOUR);
        Card card4 = new Card(CardSuit.DIAMONDS, CardRank.FOUR);
        Card card5 = new Card(CardSuit.DIAMONDS, CardRank.QUEEN);
        Card card6 = new Card(CardSuit.DIAMONDS, CardRank.ACE);

        combinationCards.add(card0);
        combinationCards.add(card1);
        combinationCards.add(card2);
        combinationCards.add(card3);
        combinationCards.add(card4);
        combinationCards.add(card5);
        combinationCards.add(card6);

        expected.add(card6);
        expected.add(card1);
        expected.add(card5);
        expected.add(card0);
        expected.add(card2);

        combinationController = new RoyalFlushCombinationController();
        Combination combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.ROYAL_FLUSH, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.clear();
        expected.clear();

        Card card1_0 = new Card(CardSuit.DIAMONDS, CardRank.JACK);
        Card card1_1 = new Card(CardSuit.DIAMONDS, CardRank.KING);
        Card card1_2 = new Card(CardSuit.DIAMONDS, CardRank.TEN);
        Card card1_3 = new Card(CardSuit.CLUBS, CardRank.FOUR);
        Card card1_4 = new Card(CardSuit.DIAMONDS, CardRank.NINE);
        Card card1_5 = new Card(CardSuit.DIAMONDS, CardRank.QUEEN);
        Card card1_6 = new Card(CardSuit.DIAMONDS, CardRank.ACE);

        combinationCards.add(card1_0);
        combinationCards.add(card1_1);
        combinationCards.add(card1_2);
        combinationCards.add(card1_3);
        combinationCards.add(card1_4);
        combinationCards.add(card1_5);
        combinationCards.add(card1_6);

        expected.add(card1_6);
        expected.add(card1_1);
        expected.add(card1_5);
        expected.add(card1_0);
        expected.add(card1_2);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertEquals(CombinationType.ROYAL_FLUSH, combination.getType());
        Assertions.assertIterableEquals(expected, combination.getCombinationCards());

        //----------------------------------------------------------------------------//

        combinationCards.remove(2);
        Card newCard2_1 = new Card(CardSuit.SPADES, CardRank.QUEEN);
        combinationCards.add(newCard2_1);

        combination = combinationController.checkCombination(new ArrayList<>(combinationCards));

        Assertions.assertNull(combination);
    }
}
