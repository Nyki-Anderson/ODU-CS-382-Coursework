/**
 * 
 */
package Crazy8;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import PlayingCards.Card;

/**
 * @author zeil
 *
 */
public class HandTest {

    final Card c2 = new Card(Card.Suit.Clubs, Card.Rank.Two);
    final Card c10 = new Card(Card.Suit.Clubs, Card.Rank.Ten);
    final Card da = new Card(Card.Suit.Diamonds, Card.Rank.Ace);
    final Card d8 = new Card(Card.Suit.Diamonds, Card.Rank.Eight);
    final Card h9 = new Card(Card.Suit.Hearts, Card.Rank.Nine);
    final Card hk = new Card(Card.Suit.Hearts, Card.Rank.King);
    final Card sj = new Card(Card.Suit.Spades, Card.Rank.Jack);
    final Card sq = new Card(Card.Suit.Spades, Card.Rank.Queen);
    
    

    /**
     * Test method for {@link Crazy8.Hand#Hand()}.
     */
    @Test
    public void testHand() {
        Hand h = new Hand();
        assertEquals(0, h.size());
        assertEquals(0, h.score());
        assertEquals("", h.toString());
    }

    /**
     * Test method for {@link Crazy8.Hand#add(PlayingCards.Card)}.
     */
    @Test
    public void testAdd() {
        Hand h = new Hand();
        h.add(sq);
        h.add(c2);
        h.add(da);
        h.add(d8);
        h.add(hk);
        assertEquals(5, h.size());
        assertEquals(73, h.score());
        assertEquals(c2, h.get(0));
        assertEquals(d8, h.get(1));
        assertEquals(da, h.get(2));
        assertEquals(hk, h.get(3));
        assertEquals(sq, h.get(4));
        String s = h.toString();
        assertEquals("2C 8D AD KH QS", s);
    }

    /**
     * Test method for {@link Crazy8.Hand#remove(int)}.
     */
    @Test
    public void testRemove() {
        Hand h = new Hand();
        h.add(sj);
        h.add(c10);
        h.add(d8);
        h.add(sj);
        h.add(hk);
        assertEquals(5, h.size());
        assertEquals(90, h.score());
        h.remove(1);
        assertEquals(4, h.size());
        assertEquals(40, h.score());
        assertEquals(c10, h.get(0));
        assertEquals(hk, h.get(1));
        assertEquals(sj, h.get(2));
        assertEquals(sj, h.get(3));
        
        h.remove(0);
        assertEquals(3, h.size());
        assertEquals(30, h.score());
        assertEquals(hk, h.get(0));
        assertEquals(sj, h.get(1));
        assertEquals(sj, h.get(2));
    }

    /**
     * Test method for {@link Crazy8.Hand#clear()}.
     */
    @Test
    public void testClear() {
        Hand h = new Hand();
        h.add(sj);
        h.add(c10);
        h.add(d8);
        h.add(sj);
        h.add(hk);
        h.clear();
        assertEquals(0, h.size());
        assertEquals(0, h.score());
    }

}
