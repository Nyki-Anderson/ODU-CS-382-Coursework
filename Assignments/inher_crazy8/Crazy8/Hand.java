/**
 * 
 */
package Crazy8;

import PlayingCards.Card;

/**
 * @author zeil
 *
 */
public class Hand {
    private Card[] cards;
    private int numCards;

    public Hand()
    {
        numCards = 0;
        cards = new Card[52];
    }

    public int size() {return numCards;}

    /**
     * Add a card to a hand. Cards are kept sorted in order by suit, then rank.
     * @param c
     */
    public void add (Card c)
    {
        cards[numCards] = c;
        int k = numCards - 1;
        while (k >= 0 && !cards[k].comesBefore(cards[k+1])) {
            Card temp = cards[k];
            cards[k] = cards[k+1];
            cards[k+1] = temp;
            --k;
        }
        ++numCards;
    }

    /**
     * Get the ith card from the hand. See add(...) above, for explanation of ordering.
     * 
     * @param i
     * @return card in ith position
     */
    public Card get(int i) {
        return cards[i];
    }

    /**
     * Remove the ith card from the hand.
     * 
     * @param i
     */
    public void remove(int i) {
        for (int k = i+1; k < numCards; ++k) {
            cards[k-1] = cards[k];
        }
        --numCards;
    }


    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < numCards; ++i) {
            if (i > 0)
                buf.append(' ');
            buf.append (cards[i].toString());
        }
        return buf.toString();
    }

    public void clear() {
        numCards = 0;
    }

    public int score() {
        int s = 0;
        for (int i = 0; i < numCards; ++i) {
            Card c = cards[i];
            int rank = c.getRank().getValue();
            if (rank == 8)
                s += 50;
            else if (rank == 14)
                s += 1;
            else if (rank >= 10)
                s += 10;
            else
                s += rank;
        }
        return s;
    }


}
