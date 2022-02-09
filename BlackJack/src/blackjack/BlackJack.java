package blackjack;

import java.util.Scanner;
/**
 *
 * @author Mena
 */
public class BlackJack {

       static Game myGame = new Game();
    
    public static void main(String[] args) 
    {
        GUI gui = new GUI();
        myGame.generateCardDeck();
        myGame.setPlayers();
        gui.runGUI( myGame.CardDeck, myGame.Players[0].getCards(), myGame.Players[1].getCards(), myGame.Players[2].getCards(), myGame.Players[3].getCards() );
        myGame.playersTurn(gui);
        myGame.dealerTurn(gui);
        myGame.whoWon();
    }
    
    
  
    
}
