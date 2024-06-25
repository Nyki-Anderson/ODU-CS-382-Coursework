/**
 * 
 */
package Crazy8;

import PlayingCards.Card.Suit;

/**
 * @author zeil
 *
 */
public abstract class Player {

    private String name;
    private Hand hand;
    private int score;

    public Player(String nm) {
        name = nm;
        hand = new Hand();
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setName(String nm) {
        name = nm;
    }

    public Hand getHand() {
        return hand;
    }

    public abstract Suit makeAPlay(Crazy8 game);

    public void scoreHand() {
        score += hand.score();
    }

}
