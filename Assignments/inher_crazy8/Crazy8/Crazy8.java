/**
 * 
 */
package Crazy8;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import PlayingCards.Card;
import PlayingCards.Card.Suit;
import PlayingCards.Deck;

/**
 * @author zeil
 *
 */
public class Crazy8 {

    private Player[] players;
    private Deck drawPile;
    private Deck discards;
    private Card.Suit currentSuit;

    private BufferedReader input;

    public Crazy8 ()
    {
        input = new BufferedReader(new InputStreamReader(System.in));
        players = new Player[4];
        players[0] = new ComputerPlayer("Wild Bill");
        players[1] = new ComputerPlayer("Maverick");
        players[2] = new ComputerPlayer("Doc Holliday");
        players[3] = new HumanPlayer(input);
        drawPile = null;
        discards = null;
    }


    public void play()
    {
        for (int handNum = 0; handNum < 4; ++handNum) {
            playOneRound (handNum);
        }
        int lowestCScore = players[0].getScore() + 1;
        String winner = "";
        for (int i = 0; i < 4; ++i) {
            if (players[i].getScore() < lowestCScore) {
                lowestCScore = players[i].getScore();
                winner = players[i].getName();
            }
        }
        System.out.println("\n" + winner + " wins!");			
    }



    private void playOneRound(int handNum) {
        deal();
        playTheRound(handNum);
        scoreTheRound();
    }


    private void deal() {
        discards = new Deck();
        drawPile = new Deck();
        discards.clear();
        drawPile.shuffle();
        for (int i = 0; i < 4; ++i) {
            players[i].getHand().clear();
            for (int j = 0; j < 5; ++j) {
                Card c = drawPile.draw();
                players[i].getHand().add(c);
            }
        }

        Card c = drawPile.draw();
        discards.add(c);
        while (c.getRank().equals(Card.Rank.Eight)) {
            c = drawPile.draw();
            discards.add(c);
        }
        currentSuit = c.getSuit();
    }


    private void playTheRound(int handNum) {
        boolean roundCompleted = false;
        int nextPlayer = handNum;
        while (!roundCompleted) {
            displayGameState();

            currentSuit = players[nextPlayer].makeAPlay(this);
            int handSize = players[nextPlayer].getHand().size();
            roundCompleted =  handSize == 0;
            System.out.println(players[nextPlayer].getName() + " has " + handSize + " cards.");
            
            nextPlayer = (nextPlayer + 1) % 4;
        }
    }





    public Card drawACard() {
        if (drawPile.size() == 0) {
            if (discards.size() > 1) {
                Card top = discards.draw();
                discards.shuffle();
                drawPile = discards;
                discards = new Deck();
                discards.clear();
                discards.add (top);
            } else {
                return null;
            }
        }
        return drawPile.draw();
    }


    private void displayGameState() {
        System.out.print ("\n# cards: ");
        for (int i = 0; i < 3; i++) {
            System.out.print (players[i].getName() + ":" + players[i].getHand().size() + "  ");
        }
        System.out.println ("\nTop card is " + discards.topCard() + "   Current suit is " + currentSuit);
    }


    private void scoreTheRound() {
        for (int i = 0; i < 4; ++i) {
            int oldScore = players[i].getScore();
            players[i].scoreHand();
            System.out.println(players[i].getName() + " scores " + (players[i].getScore() - oldScore) 
                    + " points for a total of " + players[i].getScore());
        }
        System.out.println("\n");
    }


    /**
     * Plays a game (4 hands) of crazy 8s
     * 
     * @param args
     */
    public static void main(String[] args) {
        // Optional: an integer can be given for the random number seed 
        //   so that a repeatable sequence of hands can be played during testing
        //   or debugging.
        if (args.length > 0) {
            long seed = Long.parseLong(args[0]);
            Deck.rand.setSeed(seed);
        }
        new Crazy8().play();
    }


    public Deck getDiscardPile() {
        return discards;
    }


    public Suit getCurrentSuit() {
        return currentSuit;
    }

    public boolean isALegalPlay(Card c) {
        if (c.getRank().equals(Card.Rank.Eight))
            return true;
        else {
            Card topCard = discards.topCard();
            return c.getRank().equals(topCard.getRank()) || c.getSuit().equals(currentSuit);
        }
    }

}
