package cwk;

 

  
/**
 * Write a description of class CardTester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CardTester
{
    public static void main(String[] args)
    {
       
        // test 1 - Create two card objects
        Card card1 = new Card(1384567890, "Elliot Colby ", 2,15);

        Card card2 = new Card(1876543210, "Danny Granger", 3, 50);
        // Display initial card information
        displayCardInfo("Initial Card Info - Card 1", card1);
        displayCardInfo("Initial Card Info - Card 2", card2);

        // Test 2 - update the credits using the bridge for card 1
        card1.updateCredPointBridge();
        // display card information after using the bridge for card 1
        displayCardInfo("Info after using the bridge - Card 1", card1);

        // Top up credits on Card 2
        card2.addCredits(20);
        displayCardInfo("Info after topping up credits - Card 2", card2);

        // Convert points to credits on Card 1
        card1.convertPointToCred();
        displayCardInfo("Info after converting points to credits - Card 1", card1);
    }

    //  method to display card information
    private static void displayCardInfo(String message, Card card) {
        System.out.println(message);
        System.out.println("Card ID Number: " + card.getCardIdNum());
        System.out.println("Luxury Rating: " + card.getLuxuryRating());
        System.out.println("Number of Credits: " + card.getNumOfCredits());
        System.out.println();
    }
}
 
       
    
        
    

