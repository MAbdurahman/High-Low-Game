
package src;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * The Card Class represents one of the 52 cards in a standard deck of playing
 * cards. Each card object has a suit and a rank.
 * @author:  MAbdurrahman
 * @date: 8 June 2017
 * @version: 1.0.0
 */
public class Card implements Comparable {

    /**Instance Variables */
    //The four suits
    protected final int CLUBS = 0, DIAMONDS = 1, HEARTS = 2, SPADES = 3;
    //The codes for the non-numeric values
    protected final int JACK = 11, QUEEN = 12, KING = 13, ACE = 1;
    //The suit of this card, one of the constants: SPADES, HEARTS, CLUBS, DIAMONDS.
    protected final int suit;
    /*The rank of the cards, 1 to 13 (the cards from 2 to 10 are assigned that number
      and the non-numeric cards are assigned 11 to 13)*/
    protected final int rank;

    /**
     * Class Default Card Constructor - Creates an instance of Card with the rank and a suit
     */
    public Card() {
        suit = getSuit();
        rank = getRank();
        
    }//end of the Card Constructor
    /**
     * Class Card Constructor - Creates an instance of Card with the rank (from 1 to 13) and
     * and a suit (from 0 to 3)
     * @param Int - the card suit
     * @param Int - the card rank
     */
    public Card(int suit, int rank) {
        this.suit = suit;
        this.rank = rank;
        
    }//end of class Card Constructor
    /**
     * getSuit Method - Get the suit of the card
     * @param Void
     * @return Int - Return the numeric value for the suit of the card
     */
    public int getSuit() {
        return suit;
        
    }//end of getSuit Method
    /**
     * getRank Method - Gets the rank of the card
     * @param Void
     * @return Int - Return the numeric rank of the card
     */
    public int getRank() {
        return rank;
    }//end of getRank Method
    /**
     * getSuitName Method - Gets the suit name of the card
     * @param Void
     * @return String - Return String representing the suit of the card
     */
    public String getSuitName() {
        switch (suit) {
            case SPADES:
                return "Spades";
            case HEARTS:
                return "Hearts";
            case CLUBS:
                return "Clubs";
            case DIAMONDS:
                return "Diamonds";
            default:
                return "????";
        }
    }//end of getSuitName Method
    /**
     * getCardRank Method - Gets the card rank
     * @param Void
     * @return String - Return String representing the value/rank of the card
     */
    public String getCardRank() {
        switch (rank) {
            case 1:
                return "Ace";
            case 2:
                return "Deuce";
            case 3:
                return "Trey";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
            case 10:
                return "Ten";
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
            default:
                return "?????";
        }
    }//end of getCardRank Method 
    /**
     * toString Method - Returns a string representing the Card
     * @param Void
     * @return String - Returns a string representing the rank of the card and the suit of the
     * card.
     */
    @Override
    public String toString() {
        return getCardRank() + " of " + getSuitName();
        
    }//end of toString Method
    /**
     * compareTo Method - Defines the compareTo Method for Comparable Interface and 
     * compares two Card Object cards
     * @param Object - the Card Object
     * @return Int - Returns a positive integer, if a 'greater than' b; returns
     * a negative integer, if a 'less than' b; returns 0, if a 'equals' b.
     */
    @Override
    public int compareTo(Object object) {
        if (!(object instanceof Card)) {//object must Object to Card
            JDialog.setDefaultLookAndFeelDecorated(false);
            String message = "Error: object does not belong to a Object of class Card!";
            JOptionPane.showMessageDialog(null, message);
        }
        Card otherCard = (Card) object;
        if ((this.getRank()) < (otherCard.getRank())) {
            return -1;
        }
        if ((this.getRank()) > (otherCard.getRank())) {
            return 1;
        }
        return 0;
        
    }//end of compareTo Method
}//end of class Card