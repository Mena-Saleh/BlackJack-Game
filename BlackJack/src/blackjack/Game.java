package blackjack;

import java.awt.Choice;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author Mena
 */
public class Game {
    //Fields
    public Player[] Players = new Player[4];
    public Card[] CardDeck = new Card[52];
    public static int HighScore = 0;
   
    
      public void generateCardDeck() //Function that generates a deck of 52 unique cards, each having a different suit, rank and value combination.
    {   
        
        for (int i = 0; i < 4; i++) 
        {
            for (int j = 0; j < 13; j++) 
            {
                if (j+1 > 10) 
                {
                    CardDeck[j+(13*i)] = new Card(i,j,10);
                }
                else{
                CardDeck[j+(13*i)] = new Card(i,j,j+1);
                }
                
            }
            
        }

    }
    
    
    
    
    public Card drawCard() //Function that draws a random card using the Random Library.
    {
    Random myRandom = new Random();
    int randomChoice;
        do 
        {
           randomChoice = myRandom.nextInt(52); //Generate a random number from 0 to 51.
        } 
        while (CardDeck[randomChoice] == null); //To make sure that the card that is randomly picked wasn't already picked before.
        
    Card PickedCard = new Card(CardDeck[randomChoice]); //Saving the randomly picked card in a temporal variable.
    CardDeck[randomChoice] = null; //Removing the picked card from the deck.
    return PickedCard;
    
    }
    public void setPlayers() //Set players' names
    {
        for (int i = 0; i < 4; i++) 
        {
        Scanner MyScanner = new Scanner(System.in);
        System.out.println("Enter Player " + (i+1) + " Name: ");
        Players[i] = new Player();
        Players[i].setName(MyScanner.next());
        Card[] RandomCards = new Card[2]; //An array of cards to initialize each player with two random cards.
        RandomCards[0] = drawCard(); //Draw first random card.
        RandomCards[1] = drawCard(); //Draw second random card.
        Players[i].setCards(RandomCards); //Assigning the cards to the player.
        Players[i].setScore(RandomCards[0].getValue()+ RandomCards[1].getValue()); //Calculating initial score.
            if (i==3) 
            {
                 System.out.println(Players[i].getName() + ", You're the dealer"); //Printing dealer info (score is unknown)
                System.out.println("---------------------------------------------------------");
            }
            else 
            {
                System.out.println(Players[i].getName() + ", Your score is currently: " + Players[i].getScore()); //Printing player info.
                System.out.println("---------------------------------------------------------");
            }
       
        }
        updateMaximumScore(); //Calling the max score function to make sure the current max score is saved.
        
       
    }
    public void updateMaximumScore() //Function that loops through all four players' scores to store the maximum one.
    {
        HighScore = 0;
        for (int i = 0; i < 4; i++)  
        {
            if (Players[i].getScore() > HighScore && Players[i].getScore() <=21) 
            {
                HighScore = Players[i].getScore();
            }
   
        }
    }
    
    public void playersTurn(GUI gui) //To prompt user to Hit or Stand.
    {
        Scanner MyScanner = new Scanner(System.in);
        Card Temp;
        for (int i = 0; i < 3; i++) 
        {
            char Choice;
            do 
            {
                System.out.println("Player " + (i+1) + ": " + Players[i].getName() + ", it's your turn!");
                System.out.println("[1] HIT");
                System.out.println("[2] STAND"); //or any other button.
                Choice = MyScanner.next().charAt(0);
                if (Choice == '1')
                {
                    Temp = drawCard(); //Draw card.
                    Players[i].setScore(Players[i].getScore() + Temp.getValue()); //Updating score.
                    gui.updatePlayerHand(Temp,i);
                    if (Players[i].getScore()> 21) //The player has lost (BUSTED).
                    {
                     updateMaximumScore();
                     System.out.println(Players[i].getName() + ", Your score is currently: " + Players[i].getScore());
                     System.out.println("BUSTED");
                     System.out.println("---------------------------------------------------------");
                     break;
                    }
                    
                    else if (Players[i].getScore() == 21)  //BlackJack
                    {
                       System.out.println(Players[i].getName() + ", Your score is currently: " + Players[i].getScore());
                       updateMaximumScore();
                       System.out.println("BLACKJACK");
                       System.out.println("---------------------------------------------------------");
                       break;
                    }
                    
                }
                else //The player doesn't draw a card.
                {
                    updateMaximumScore();
                    System.out.println("STAND");
                    System.out.println("---------------------------------------------------------");
                }
                
                System.out.println(Players[i].getName() + ", Your score is currently: " + Players[i].getScore());
                updateMaximumScore();
                System.out.println("---------------------------------------------------------");
             
            }
            while (Choice =='1');
           
        }
       
    
    }
    public void dealerTurn(GUI gui) //The dealer can't stand, they have to hit untill either they lose or win (BlackJack or highest score).
    {
    Scanner MyScanner = new Scanner(System.in);
    char Choice;
    Card Temp;
        System.out.println("Player 4: " + Players[3].getName() + " you're the dealer and its your turn!");
        do 
        {
            System.out.println("[1] HIT");
            Choice = MyScanner.next().charAt(0);
            Temp = drawCard();
            Players[3].setScore(Players[3].getScore() + Temp.getValue());
            gui.updateDealerHand(Temp,CardDeck);
            if (Players[3].getScore() > HighScore && Players[3].getScore() <= 21) //The dealer has the highest score and he has not exceeded 21 (didn't lose).
            {
                updateMaximumScore();
                System.out.println(Players[3].getName() + ", Your score is currently: " + Players[3].getScore());
                System.out.println("---------------------------------------------------------");
                break;
                
            }
            else if (Players[3].getScore() == 21) //BlackJack!
            {
                updateMaximumScore();
                System.out.println(Players[3].getName() + ", Your score is currently: " + Players[3].getScore());
                System.out.println("BLACKJACK");
                System.out.println("---------------------------------------------------------");
                break;
                
            }
            else if (Players[3].getScore() > 21) //The dealer lost because his score is higher than 21.
            {     
                updateMaximumScore();
                System.out.println(Players[3].getName() + ", Your score is currently: " + Players[3].getScore());
                System.out.println("BUSTED");
                System.out.println("---------------------------------------------------------");
                break;
                
            }
            
             updateMaximumScore(); 
             System.out.println("Hit again!");
            
            
        } while (true);
        
    }
    
    public void whoWon() // Function that determines the winner based on different criteria to satisfy game logic.
    
    {
        
        int WinnerCount = 4;   //Suppose all 4 are winners.
        for (int i = 0; i < 4; i++) //Go through all 4 players to check if they meet winning conditions.
        {
            if (Players[i].getScore() < HighScore || Players[i].getScore() >21) //If a player doesn't meet the high score requirements or has more than 21 cards they're ommitted.
            {
                Players[i].setHasLost(true); //Flag the player as lost because they have not met the winning criteria.
                WinnerCount--; //Reduce winners' count.
   
            }
        }
 
        if (WinnerCount ==1) //If there is only one winner, then it is a "WIN".
        {
            System.out.println("WIN");   
            
            for (int i = 0; i < 4; i++) 
            {
              if (Players[i].isHasLost() == false) //If the player is a winner 
              {
                System.out.println(Players[i].getName() + " has won!");   //Print their name.
              }
            }
            
        }
        else
        {
            System.out.println("PUSH"); //If there is more than one winner, it is a "PUSH".
            for (int i = 0; i < 4; i++) 
            {
                if (Players[i].isHasLost() == false) //If the player is a winner 
                {
                System.out.println(Players[i].getName() + " has tied");   //Print their name.
                }
            }
        }
          
        
    }
    
    
}
