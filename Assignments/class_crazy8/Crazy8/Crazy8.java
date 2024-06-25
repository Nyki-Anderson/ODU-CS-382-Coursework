/**
 * 
 */
package Crazy8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import PlayingCards.Card;
import PlayingCards.Card.Suit;
import PlayingCards.Deck;

/**
 * @author zeil
 *
 */
public class Crazy8 {

    private ComputerPlayer[] cPlayers;
    private Deck drawPile;
    private Deck discards;
    private Card.Suit currentSuit;

    private String humanName;
    private int humanScore;
    private Hand humanHand;

    private BufferedReader input;

    public Crazy8 ()
    {
        cPlayers = new ComputerPlayer[3];
        cPlayers[0] = new ComputerPlayer("Wild Bill");
        cPlayers[1] = new ComputerPlayer("Maverick");
        cPlayers[2] = new ComputerPlayer("Doc Holliday");
        drawPile = null;
        discards = null;

        input = new BufferedReader(new InputStreamReader(System.in));
        humanName = "";
        while (humanName.length() == 0) {
            System.out.print("What is your name? ");
            System.out.flush();
            try {
                humanName = input.readLine();
            } catch (IOException e) {
            }
            if (humanName == null)
                System.exit(0);
        }
        humanScore = 0;
        humanHand = new Hand();
    }


    public void play()
    {
        for (int handNum = 0; handNum < 4; ++handNum) {
            playOneRound (handNum);
        }
        int lowestCScore = cPlayers[0].getScore() + 1;
        String winner = "";
        for (int i = 0; i < 3; ++i) {
            if (cPlayers[i].getScore() < lowestCScore) {
                lowestCScore = cPlayers[i].getScore();
                winner = cPlayers[i].getName();
            }
        }
        if (humanScore < lowestCScore) {
            System.out.println("\n" + humanName + " wins!");
        } else {
            System.out.println("\n" + winner + " wins!");			
        }	
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
        for (int i = 0; i < 3; ++i) {
            cPlayers[i].getHand().clear();
            for (int j = 0; j < 5; ++j) {
                Card c = drawPile.draw();
                cPlayers[i].getHand().add(c);
            }
        }
        humanHand.clear();
        for (int j = 0; j < 5; ++j) {
            Card c = drawPile.draw();
            humanHand.add(c);
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
            if (nextPlayer < 3) {
                currentSuit = cPlayers[nextPlayer].makeAPlay(this);
                int handSize = cPlayers[nextPlayer].getHand().size();
                roundCompleted =  handSize == 0;
                System.out.println(cPlayers[nextPlayer].getName() + " has " + handSize + " cards.");
            } else {
                System.out.println ("\nYour hand: " + humanHand + "\n");
                humanPlay();
                roundCompleted = humanHand.size() == 0;
                System.out.println(humanName + " has " + humanHand.size() + " cards.");
            }
            nextPlayer = (nextPlayer + 1) % 4;
        }
    }



    private void humanPlay() {
        boolean OK = false;
        String selection= "";
        int selected = -1;
        while (!OK) {
            System.out.print("What card would you like to play? (hit return to draw) ");
            System.out.flush();
            try {
                selection = input.readLine();
                if (selection == null)
                    System.exit(0);
            } catch (IOException e) {
            }
            selection = selection.trim();
            if (selection.length() == 0)
                OK = true; // draw
            else {
                for (int i = 0; i < humanHand.size(); ++i) {
                    Card c = humanHand.get(i); 
                    if (c.toString().toLowerCase().equals(selection.toLowerCase())) {
                        if (isALegalPlay(c)) {
                            selected = i;
                            OK = true;
                            break;
                        } else {
                            System.out.println ("You can't play the " + c + " right now.");
                            break;
                        }
                    }
                }
            }
        }
        if (selected < 0) {
            Card c = drawACard();
            if (c != null)
                humanHand.add(c);
        } else {
            Card c = humanHand.get(selected);
            humanHand.remove(selected);
            discards.add(c);
            if (c.getRank().equals(Card.Rank.Eight)) {
                currentSuit = null;
                while (currentSuit == null) {
                    System.out.print("What suit would you like to select? (CDHS) ");
                    System.out.flush();
                    String response = "";
                    try {
                        response = input.readLine();
                    } catch (IOException e) {
                    }
                    if (response == null)
                        System.exit(0);
                    response = response.trim();
                    if (response.length() > 0) {
                        char ch = response.toLowerCase().charAt(0);
                        switch (ch) {
                        case 'c':
                            currentSuit = Card.Suit.Clubs;
                            break;
                        case 'd':
                            currentSuit = Card.Suit.Diamonds;
                            break;
                        case 'h':
                            currentSuit = Card.Suit.Hearts;
                            break;
                        case 's':
                            currentSuit = Card.Suit.Spades;
                            break;
                        }
                    }
                }
            } else {
                currentSuit = c.getSuit();
            }
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


    public boolean isALegalPlay(Card c) {
        if (c.getRank().equals(Card.Rank.Eight))
            return true;
        else {
            Card topCard = discards.topCard();
            return c.getRank().equals(topCard.getRank()) || c.getSuit().equals(currentSuit);
        }
    }


    private void displayGameState() {
        System.out.print ("\n# cards: ");
        for (int i = 0; i < 3; i++) {
            System.out.print (cPlayers[i].getName() + ":" + cPlayers[i].getHand().size() + "  ");
        }
        System.out.println ("\nTop card is " + discards.topCard() + "   Current suit is " + currentSuit);
    }


    private void scoreTheRound() {
        for (int i = 0; i < 3; ++i) {
            int oldScore = cPlayers[i].getScore();
            cPlayers[i].scoreHand();
            System.out.println(cPlayers[i].getName() + " scores " + (cPlayers[i].getScore() - oldScore) 
                    + " points for a total of " + cPlayers[i].getScore());
        }
        int oldScore = humanScore;
        humanScore += humanHand.score();
        System.out.print(humanName + " scores " + (humanScore - oldScore) 
                + " points for a total of " + humanScore);
        System.out.println("\n\n");
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

}
