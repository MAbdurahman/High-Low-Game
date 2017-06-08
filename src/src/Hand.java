
package src;

/**
 * This object represents a hand of cards.  The maximum number of card in the hand 
 * can be specified in the constructor, but by default is 5.  A utility method is
 * provided for computing the value of the hand in the game of Blackjack.
 *
 * @author:  MAbdurrahman 
 * @date:  8 June 2017
 * @version:  1.0.0
 */
import java.util.ArrayList;

public class Hand {
	//Instance Variable
	protected ArrayList<Card> hand;
    /**
     * Class Hand Constructor - Creates an instance of the Hand Class
     */
    public Hand() {
    	
        hand = new ArrayList<>(5);
    }
    /**
     * clear Method - Discards all the cards from the hand
     * @param void
     */
    public void clear() {
   
        hand.removeAll(hand);
        
    }
    /**
     * addCard Method - Adds one top card to the hand
     * @param Card - the card
     */
    public void addCard(Card c) {
    	if (c != null) {
            hand.add(c);
            
        }
    }
    /**
     * removeCard Method - If the specified card is in the hand, remove it
     * @param Card - the card to be remove
     */
    public void removeCard(Card c) {
        hand.remove(c);
        
    }
    /**
     * removeCard Method - If the specified position is a valid card position in
     * in the hand, remove the card in that position
     * @param Int - the position
     */
    public void removeCard(int position) {
    	if(position >= 0 && position < hand.size()) {
            hand.remove(position);
            
        }
    }
    /**
     * getCardCount Method - Get the number of cards in the hand
     * @param Void
     * @return Int - Return the number of cards in the hand
     */
    public int getCardCount() {
    	return hand.size();
        
    }
    /**
     * getCard Method - Get the card from the hand in the specified position.  If the given 
     * position is not a specified numbered position of a card in the hand, return null.
     * @param Int - the position
     * @return Card - the card at the integer position
     */
     public Card getCard(int position) {
     	if(position >= 0 && position < hand.size()) {
            return (Card) hand.get(position);
            
        } else {
           return null; 
           
        }	
     }//end of the getCard Method
     /**
      * sortBySuit Method - Sorts the cards in the according to the same suit.  And
      * within the same suit, cards are sorted according to rank.
      * @param  Void
      */
     public void sortBySuit() {
        ArrayList<Card> newHand = new ArrayList<>();
     	while(hand.size() > 0) {
     		int pos = 0; //the position of the minimal card.
                Card card1 = (Card) hand.get(pos);
     		for(int i = 1; i < hand.size(); i++) {
                        Card card2 = (Card) hand.get(i);
     			if(card2.getSuit() < card1.getSuit() ||
     				(card2.getSuit() == card1.getSuit() && card2.getRank() <
     				card1.getRank())) {
     					pos = i;
     					card1 = card2;
     				}
     		}
                hand.remove(pos);
                newHand.add(card1);
     	}
     	hand = newHand;	
        
     }//end of sortBySuit Method
     /**
      * sortByRank Method - Sorts the cards in the hand according to the same rank.  Cards
      * with the same rank value are sorted according to suit.
      * @param Void
      */
     public void sortByRank() {
        ArrayList<Card> newHand = new ArrayList<>();
     	while(hand.size() > 0) {
            int pos = 0; //the position of the minimal card.
            Card card1 = (Card) hand.get(pos);
            for(int i = 1; i < hand.size(); i++) {
                    Card card2 = (Card) hand.get(i);
                    if(card2.getRank() < card1.getRank() ||
                            (card2.getRank() == card1.getRank() && card2.getSuit() <
                                    card1.getSuit())) {
                                            pos = i;
                                            card1 = card2;
                                    }
                    }
                    hand.remove(pos);
                    newHand.add(card1);
                    
     	}
     	hand = newHand;
        
     }//end of sortByRank Method   
}//end of class Hand
