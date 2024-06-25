/**
 * 
 */
package PlayingCards;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;

/**
 * @author zeil
 *
 */
public class TestDeck {

    /**
     * Test method for {@link PlayingCards.Deck#Deck()}.
     */
    @Test
    public void testDeck() {
        Deck d = new Deck();
        assertEquals(52, d.size());
        for (Card.Suit s: Card.Suit.values()) {
            for (Card.Rank r: Card.Rank.values()) {
                assertEquals(d.topCard(), new Card(s, r));
                assertEquals(d.draw(), new Card(s, r));
            }
        }
        assertEquals(0, d.size());        
    }

    /**
     * Test method for {@link PlayingCards.Deck#clear()}.
     */
    @Test
    public void testClear() {
        Deck d = new Deck();
        d.clear();
        assertEquals(0, d.size());
        Card c = new Card(Card.Suit.Diamonds, Card.Rank.Eight);
        d.add(c);
        assertEquals(1, d.size());
        assertEquals(c, d.topCard());
    }


    /**
     * Test method for {@link PlayingCards.Deck#draw()}.
     */
    @Test
    public void testDraw() {
        Deck d = new Deck();
        assertEquals(52, d.size());
        Card c = d.draw();
        assertEquals(51, d.size());
        assertEquals (new Card(Card.Suit.Clubs, Card.Rank.Two), c);
        for (int i = 0; i < 12; ++i)
            c = d.draw();
        assertEquals(52-13, d.size());
        assertEquals (new Card(Card.Suit.Clubs, Card.Rank.Ace), c);
        c = d.draw();
        assertEquals(52-14, d.size());
        assertEquals (new Card(Card.Suit.Diamonds, Card.Rank.Two), c);
    }

    /**
     * Test method for {@link PlayingCards.Deck#add(PlayingCards.Card)}.
     */
    @Test
    public void testAdd() {
        Deck d = new Deck();
        d.draw();
        d.draw();
        d.draw();
        assertEquals(49, d.size());
        Card c = new Card(Card.Suit.Diamonds, Card.Rank.Two);
        d.add(c);
        assertEquals(50, d.size());
        assertEquals(c, d.topCard());
    }

    /**
     * Test method for {@link PlayingCards.Deck#shuffle()}.
     */
    @Test
    public void testShuffle() {
        Deck d = new Deck();
        d.shuffle();
        assertEquals(52, d.size());
        boolean changed = false;
        
        boolean[][] covered = new boolean[Card.Suit.values().length][Card.Rank.values().length];
        for (Card.Suit s: Card.Suit.values()) {
            for (Card.Rank r: Card.Rank.values()) {
                covered[s.ordinal()][r.ordinal()] = false;
            }
        }
        for (Card.Suit s: Card.Suit.values()) {
            for (Card.Rank r: Card.Rank.values()) {
                Card c = new Card(s,r);
                assertEquals(false, covered[s.ordinal()][r.ordinal()]);
                covered[s.ordinal()][r.ordinal()] = true;
                if (!c.equals(d.topCard()))
                    changed = true;
            }
        }
        assertEquals(true, changed); // This test fails once every 52! runs, but I can live with that.
    }

    
    public static void main(String[] args)
    {
    	if (args.length > 0) {
    		System.out.println ("Test: " + args[0]);
    		Result r = (new JUnitCore()).run(Request.method(TestDeck.class,
    				args[0]));
    		System.out.println(r.getFailureCount() + " failures\n" + r.getFailures());
    	} else {
    		System.out.println ("All tests...");
    		Result r = (new JUnitCore()).run(TestDeck.class);
    		System.out.println(r.getFailureCount() + " failures\n" + r.getFailures());
    	}
    }

}
