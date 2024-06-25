package PlayingCards;

import java.util.Random;

public class Deck {

    private int numCards;
    private Card[] cards;

    /**
     * Create a new deck of 52 cards, one of each suit and rank
     * in the order clubs, diamonds, hearts, spades, starting
     * within each suit with the 2 and then ascending in rank to
     * the ace.
     * 
     * E.g.:
     *    Deck d = new Deck();
     *    Card c1 = d.draw(); // produces 2 of clubs
     *    Card c2 = d.draw(); // produces 3 of clubs
     */
    public Deck()
    {
        numCards = 0;
        cards = new Card[52];
        for (Card.Suit s: Card.Suit.values()) {
            for (Card.Rank r: Card.Rank.values()) {
                cards[51-numCards] = new Card(s, r);
                ++numCards;
            }
        }
    }

    /**
     * Remove all cards from a deck.
     * 
     */
    public void clear()
    {
        numCards = 0;
    }

    /**
     * How many cards are in this deck?
     */
    public int size() {return numCards;}

    /**
     * Look at the top card on the deck
     */
    public Card topCard() {return cards[numCards-1];}

    /**
     * Draw a card from the top of the deck
     */
    public Card draw()
    {
        Card top = topCard();
        --numCards;
        return top;
    }

    /**
     * Add a card to the top of the deck
     */
    public void add (Card c)
    {
        cards[numCards] = c;
        ++numCards;
    }

    /*
     * Re-arrange the cards in this deck into a random order
     */
    public static Random rand = new Random();
    public void shuffle()
    {
        for (int i = 1; i < numCards; ++i) {
            int k = rand.nextInt(i+1);
            Card temp = cards[i];
            cards[i] = cards[k];
            cards[k] = temp;
        }
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < numCards; ++i) {
            if (i > 0)
                buf.append(" ");
            buf.append (cards[numCards-i-1].toString());
        }
        return buf.toString();
    }

}
