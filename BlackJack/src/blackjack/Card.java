
package blackjack;

/**
 *
 * @author Mena
 */
public class Card {
    //Fields
    
//Suit = 0 for Clubs, Suit = 1 for Diamonds, Suit = 2 for Hearts, Suit = 3 for Spades.
//rank = 0 for Ace card
//rank = 1 for 2 card
//rank = 2 for 3 card
//rank = 3 for 4 card
//rank = 4 for 5 card
//rank = 5 for 6 card
//rank = 6 for 7 card
//rank = 7 for 8 card
//rank = 8 for 9 card
//rank = 9 for 10 card
//rank = 10 for Jack card
//rank = 11 for Queen card
//rank = 12 for King card
//-------------------------------------------------------------------------------------------------------------
//value = 1 for Ace card
//value = 2 for 2 card
//value = 3 for 3 card
//value = 4 for 4 card
//value = 5 for 5 card
//value = 6 for 6 card
//value = 7 for 7 card
//value = 8 for 8 card
//value = 9 for 9 card
//value = 10 for 10 card
//value = 10 for Jack card
//value = 10 for Queen card
//value = 10 for King card
//-------------------------------------------------------------------------------------------------------------
   private final int suit;
   private final int rank;
   private final int value;

   //Constructors
    public Card(int suit, int rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public Card(Card c) {
        this.suit = c.suit;
        this.rank = c.rank;
        this.value = c.value;
    }
   //Getters
    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }
    
    
    
}
