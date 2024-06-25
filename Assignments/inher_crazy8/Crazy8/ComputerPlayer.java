/**
 * 
 */
package Crazy8;

import PlayingCards.Card;
import PlayingCards.Card.Suit;

/**
 * @author zeil
 *
 */
public class ComputerPlayer extends Player {

    public ComputerPlayer(String nm) {
        super(nm);
    }


    public Suit makeAPlay(Crazy8 game) {
        Hand hand = getHand();
        for (int i = 0; i < hand.size(); ++i) {
            Card c = hand.get(hand.size() - i - 1);
            if ((!c.getRank().equals(Card.Rank.Eight)) && game.isALegalPlay(c)) {
                hand.remove(hand.size() - i - 1);
                game.getDiscardPile().add(c);
                System.out.println (getName() + " plays the " + c);
                return c.getSuit();
            }
        }
        for (int i = 0; i < hand.size(); ++i) {
            Card c = hand.get(i);
            if (c.getRank().equals(Card.Rank.Eight)) {
                hand.remove(i);
                game.getDiscardPile().add(c);
                Suit newSuit = (hand.size() > 0) ? hand.get(hand.size()-1).getSuit() : Card.Suit.Diamonds;
                System.out.println (getName() + " plays the " + c + " and calls " + newSuit);
                return newSuit;
            }
        }
        // No legal plays...
        Card c = game.drawACard();
        if (c != null) {
            System.out.println (getName() + " draws a card.");
            hand.add(c);
        } else {
            System.out.println (getName() + " would like to draw, but must pass.");
        }
        return game.getCurrentSuit();
    }



}
