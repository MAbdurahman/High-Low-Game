
package src;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * The Deck Class simulates a ordinary deck of 52 playing cards. The objects of
 * this class uses two methods: shuffle and deal.  Also, a count of the cards used
 * is maintained.
 * @author:  MAbdurrahman
 * @date:  8 June 2017
 * @version:  1.0.0
 */
public class Deck {
	//Instance Variables
	private final Card[] deck;//An array of 52 cards, representing the deck
	private int cardsUsed;//Number of cards that have been dealt from the deck
        
	/**
	 * Class Deck Constructor - Creates an instance of the Deck
	 */
        public Deck() {
            deck = new Card[52];//Create a not shuffled deck of cards.
            int cardCount = 0; //This variable holds the number of cards created
    	
            for(int suit = 0; suit <= 3; suit++) {//suit of Card
                for(int rank = 1; rank <= 13; rank++) {//rank of Card
                    deck[cardCount] = new Card(suit, rank);
                    cardCount++;
    			
                    }
            }
            cardsUsed = 0;  
        
    }//end of the Deck Constructor
    /**
     * shuffle Method - All the used cards are put back into the deck, and shuffled
     * into a random order
     * @param Void
     */
    public void shuffle() {
    	for(int i = 51; i > 0; i--) {
            int newIndex = (int)(Math.random()*(i+1));
            //Swap the Card at index i with the Card at index newIndex
            Card temp = deck[i];
            deck[i] = deck[newIndex];
            deck[newIndex] = temp;
    	}
    	cardsUsed = 0;
    }//end of the shuffle Method
    /**
     * deal Method - This method deals one top card from the deck
     * @param Void
     * @return Card - Returns a Card object on top card from the deck
     * @throws Exception - If the deck is empty, throw an Exception
     */ 
    public Card deal() {
    	try {
                if (cardsUsed == deck.length) {
                   throw new Exception("There are no cards left in the deck!"); 
                }
    			
    	} catch (Exception e) {
            JDialog.setDefaultLookAndFeelDecorated(false);
            String message = e.getMessage();
            JOptionPane.showMessageDialog(null, message);
            message = "...shuffling the deck";
            JOptionPane.showMessageDialog(null, message);
            shuffle();
    	}	
    	cardsUsed++;
    	return deck[cardsUsed - 1];  
        
    }//end of the deal Method
    /**
     * cardsLeft Method - This method returns the number of cards that are 
     * remaining in the deck.
     * @param Void
     * @return Int - Returns the number of cardsLeft remaining in the deck.
     */
    public int cardsLeft() {
    	return 52 - cardsUsed;
        
    }//end of the cardsLeft Method
} //end of class Deck