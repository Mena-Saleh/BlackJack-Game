package blackjack;

/**
 *
 * @author Mena
 */
public class Player {
    //Fields
    private String Name;
    private int Score = 0;
    private Card[] Cards = new Card[11];
    private boolean gotBlackJack = false;
    private boolean hasLost = false;
    //Setters and Getters.
    public void setName(String Name) {
        this.Name = Name;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    public void setCards(Card[] Cards) {
        this.Cards = Cards;
    }

    public void setGotBlackJack(boolean gotBlackJack) {
        this.gotBlackJack = gotBlackJack;
    }

    public void setHasLost(boolean hasLost) {
        this.hasLost = hasLost;
    }

    public String getName() {
        return Name;
    }

    public int getScore() {
        return Score;
    }

    public Card[] getCards() {
        return Cards;
    }

    public boolean isGotBlackJack() {
        return gotBlackJack;
    }

    public boolean isHasLost() {
        return hasLost;
    }
 
    
    
    
    
}
