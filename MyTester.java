package cwk;

import java.util.*;
/**
 * Write a description of class MyTester here.
 * 
 * @author 
 * @version 
 */
public class MyTester 
{   
    
    private void doTest()
    {
        ZAPP fantasia = new Park("Fantasia Pleasure Park");

        Scanner enter = new Scanner(System.in);


        // display the park details
        System.out.println("********** Park Details **********");
        System.out.println(fantasia.toString());

        // Test 1: Get the card details of any cardId
        System.out.println("\n**** Getting Card Details ****");
        int cardId = 1001;
        String cardDetails = fantasia.getCardDetails(cardId);
        System.out.println(cardDetails);

        // Test 2: Get the card location of any cardId at the Pleasure park
        System.out.println("\n**** Getting Card Location ****");
        int cardLocationId = 1007;
        String cardLocation = fantasia.getCardLocation(cardLocationId);
        System.out.println("Card Location: " + (cardLocation != null ? cardLocation : "Card not in any area"));

        // Test 3: Get the area number
        System.out.println("\n**** Getting Area Number ****");
        String areaName = "Concourse";
        int areaNumber = fantasia.getAreaNumber(areaName);
        System.out.println("Area Number: " + (areaNumber != -1 ? areaNumber : "Area does not exist"));

        // Test 4: Get all cards in one area in the Pleasure Park
        System.out.println("\n**** Getting All Cards in One Area ****");
        String areaForCards = "Lobby";
        String allCardsInOneArea = fantasia.getAllCardsInOneArea(areaForCards);
        System.out.println("All Cards in " + areaForCards + ": " + allCardsInOneArea);

        // Test 5: Get all cards in all areas in the Pleasure Park
        System.out.println("\n**** Getting All Cards in All Areas ****");
        String allCardsInAllAreas = fantasia.getAllCardsInAllAreas();
        System.out.println(allCardsInAllAreas);

        // Test 6: Can move so checking to see if a card has the right requirements
        // to move
        System.out.println("\n**** Checking If Card Can Move ****");
        int moveCardId = 1002;
        String moveBridgeCode = "ABC1";
        boolean canMove = fantasia.canMove(moveCardId, moveBridgeCode);
        System.out.println("Can Move: " + canMove);

        // Test 7: Move card so moving the card to a different location if it meets the right
        // requirements
        System.out.println("\n**** Moving Card ****");
        int moveNowCardId = 1002;
        String moveNowBridgeCode = "ABC1";
        String moveResult = fantasia.move(moveNowCardId, moveNowBridgeCode);
        System.out.println("Move Result: " + moveResult);



    }
    
     
     
        
        

     
     
     
    
    public static void main(String[] args)
    {
        MyTester xx = new MyTester();
        xx.doTest();
    }
}
