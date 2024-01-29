package cwk;

 
import java.util.*;

/**This class implements the ZAPP interface
 *
 * @author 
 * @version 
 **/
public class Park implements ZAPP
{
    private String parkName;
    private List<Area> allAreas;
    private List<Bridge> allBridges;
    private List<Card> allCards;

    /** constructor
     */
    public Park(String nme) 
    {
        parkName = nme;
        allAreas = new ArrayList<>();
        allBridges = new ArrayList<>();
        allCards = new ArrayList<>();
        
        loadAreas();
        setUpBridges();
        loadCards();
        
        placeCardsInLobby();
       
}
    public void addCard(Card c) 
    {
        allCards.add(c);
    }
    
    public void removeCard(Card c)
    {
        allCards.remove(c);
    }
    
    public void addArea(Area a)
    {
        allAreas.add(a);
    }
    
    public void removeArea(Area a)
    {
        allAreas.remove(a);
    }
    
    public void removeBridge(Bridge b)
    {
        allBridges.remove(b);
    }
    
    /**
     * Returns all of the details of the park: its name and its areas 
     * including lists of cards in each area.
     */
    public String toString()
    {
         StringBuilder result = new StringBuilder("Park Name: " + parkName + "\n");

    
    for (Area area : allAreas) {
        result.append("Area: ").append(area.getName()).append("\n");
        List<Card> cardsInArea = area.listCards();
        for (Card card : cardsInArea) {
            result.append("  ").append(card.toString()).append("\n");
        }
    }

    return result.toString();
    
    
    }
    
    /**Returns a String with details of a card
     * @param cardId - id number of the card
     * @return the details of the card as a String, or "No such card"
     */
    public String getCardDetails(int cardId)
    {
      for (Card card : allCards) {
          if (card.getCardIdNum() == cardId) {
              return card.toString();
          }    
      }  
      return "No such card";
    } 
    
    /**Returns the name of the area which contains the specified card or null
     * @param ccd - the specified card
     * @return the name of the Area which contains the card, or null
     **/
    public String getCardLocation(int cd)
    {
        for (Area area : allAreas) {
            if (area.cardAreaCheck(getCard(cd))) {
                return area.getName();
            }
        }
        return null;
    }
    
    /** Given the name of a area, returns the area id number
     * or -1 if area does not exist
     * @param name of area
     * @return id number of area
     */
    public int getAreaNumber(String ww)
    {
        for (Area area : allAreas) {
        if (area.getName().equals(ww)) {
            return area.getrefNumber();
        }
    }
        return -1;
    }
                
    /**Returns a String representation of all the cards on specified area
     * @param area is the name of the area 
     * @return a String representation of all cards on specified area
     **/
    public String getAllCardsInOneArea(String area)
    {
        String rs = "";
        Area specificArea = getArea(area);
        if (specificArea != null) {
            List<Card> cardsInArea = specificArea.listCards();
            
            for(Card card : cardsInArea) {
                rs += card.toString() + "\n";
            }
        } else {
            rs = "Area not found.";
        }
        return rs;
    } 

    /**Returns a String representation of all the cards in all areas including the name of each area
     * @return a String representation of all cards on specified area
     **/
    public String getAllCardsInAllAreas()
    {
        String rs = "";
        for (Area area : allAreas) {
            rs +="Area: " + area.getName() + "\n";
            List<Card> cardsInArea = area.listCards();
            for (Card card : cardsInArea) {
                rs += card.toString() + "\n";
            }
            rs += "\n";
        }
        
        return rs;
    }


    
    /**Returns true if a Card is allowed to move using the bridge, false otherwise
     * A move can be made if:  
     * the rating of the card  >= the rating of the destination area
     * AND the destination area is not full
     * AND the card has sufficient credits
     * AND the card is currently in the source area
     * AND the card id is for a card on the system
     * AND the bridge code is the code for a bridge on the system
     * @param trId is the id of the card requesting the move
     * @param brCode is the code of the bridge by which the card wants to move
     * @return true if the card is allowed on the bridge journey, false otherwise 
    **/
    public boolean canMove(int trId, String brCode)
    {   
            
        Card cardcanMove = getCard(trId);
        
        Bridge bridge = getBridge(brCode);
        
        if (cardcanMove == null || bridge == null) {
            return false;
        } else {
        return cardcanMove.getLuxuryRating() >= bridge.getToArea().getluxuryRating() &&
        !bridge.getToArea().areaIsFull() &&
        cardcanMove.getNumOfCredits() >= 4 &&
        bridge.getFromArea().cardAreaCheck(cardcanMove) &&
        cardcanMove.getCardIdNum() == trId &&
        bridge.getCode().equals(brCode);
            

    } 
}
    

    

    /**Returns the result of a card requesting to move over a bridge.
     * A move will be successful if:  
     * the luxury rating of the card  >= the luxury rating of the destination area
     * AND the destination area is not full
     * AND the card has sufficient credits
     * AND the card is currently in the source area
     * AND the card id is for a card in the system
     * AND the bridge code is the code for a bridge on the system
     * If the bridge crossing can be made, the card information is removed from 
     * the source area, added to the destination area, card details updated and a suitable message returned.
     * a suitable message returned.
     * If bridge journey cannot be made, the state of the system remains unchanged
     * and a message specifying the reason is returned.
     * @param pCardId is the id of the card requesting the move
     * @param brCode is the code of the bridge by which the card wants to move
     * @return a String giving the result of the request 
     **/
    public String move(int pCardId, String brCode )
    {
        Card cardMove = getCard(pCardId);
        Bridge bridge = getBridge(brCode);
        
        if (cardMove == null) {
            return "Card not found.";
        }
        if (bridge == null) {
            return "Bridge not found.";
        }
        
        if (cardMove.getLuxuryRating() >= bridge.getToArea().getluxuryRating() &&
                !bridge.getToArea().areaIsFull() &&
                cardMove.getNumOfCredits() >= 4 &&
                bridge.getFromArea().cardAreaCheck(cardMove) &&
                cardMove.getCardIdNum() == pCardId &&
                bridge.getCode().equals(brCode)) {
                 
            bridge.getFromArea().leave(cardMove);
            bridge.getToArea().enter(cardMove);
            cardMove.updateCredPointBridge();
            
            return "Card has successfully moved to the destination area.";
        } else {
            return "Move was unsuccessful.";
        }
    }
                
    
      
        
               
        
        
        
       
        

    /** Allows a card to top up their credits.This method is not concerned with 
     *  the cost of a credit as currency and prices may vary between resorts.
     *  @param id the id of the card toping up their credits
     *  @param creds the number of credits purchased to be added to cards information
     */
    public void topUpCredits(int id, int creds)
    {
        
         Card cardTp = null;

    for (Card card : allCards) {
        if (card.getCardIdNum() == id) {
            cardTp = card;
            
        }
    }


    if (cardTp != null) {
        cardTp.addCredits(creds);
        
    } else {
        
    }
        
    }
    
    /** Allows a card to convert points to credits
     *  @param cdId the id of the card toping up their credits
     */
    public void convertPoints(int cdId)
    {
        Card cardToConvert = null;

    for (Card card : allCards) {
        if (card.getCardIdNum() == cdId) {
            cardToConvert = card;
            
        }
    }
    if (cardToConvert != null) {
        cardToConvert.convertPointToCred();
        
    } else {
        
    }
        
    }
    
    
   
    //***************private methods**************
    private void loadAreas()
    {
        allAreas.add(new Area (0, "Lobby", 0, 1000));
        allAreas.add(new Area (1, "Concourse", 1, 100));
        allAreas.add(new Area (2, "Waterworld", 3, 10));
        allAreas.add(new Area (3, "WildWest", 5, 2));
        allAreas.add(new Area (4, "Solitare", 1, 1));
    }
        
    
    private void setUpBridges()
    {
        allBridges.add(new Bridge ("ABC1", allAreas.get(0), allAreas.get(1)));
        allBridges.add(new Bridge ("BCD2", allAreas.get(1), allAreas.get(0)));
        allBridges.add(new Bridge ("CDE3", allAreas.get(1), allAreas.get(2)));
        allBridges.add(new Bridge ("DEF4", allAreas.get(2), allAreas.get(1)));
        allBridges.add(new Bridge ("EFG5", allAreas.get(3), allAreas.get(1)));
        allBridges.add(new Bridge ("GHJ6", allAreas.get(1), allAreas.get(4)));
        allBridges.add(new Bridge ("HJK7", allAreas.get(4), allAreas.get(1)));
        allBridges.add(new Bridge ("JKL8", allAreas.get(2), allAreas.get(3)));
        
    }
    
    private void loadCards()
    {
       allCards.add(new Card(1000, "Lynn", 5, 10 ));
       allCards.add(new Card(1001, "May", 3, 20 ));
       allCards.add(new Card(1002, "Nils", 10, 20 ));
       allCards.add(new Card(1003, "Olek", 2, 12 ));
       allCards.add(new Card(1004, "Pan", 3, 4 ));
       allCards.add(new Card(1005, "Quin", 1, 5 ));
       allCards.add(new Card(1006, "Raj", 10, 6 ));
       allCards.add(new Card(1007, "Sol", 7, 20 ));
       allCards.add(new Card(1008, "Tel", 6, 24 ));
    }
 
    /** Returns the card with the card id specified by the parameter
     * @return the card with the specified id
     **/
    private Card getCard(int id)
    {
        
        for (Card card : allCards) {
            if (card.getCardIdNum() == id) {
                return card;
            }
        }
        return null;
    }
    
    
    /** Returns the area with the name specified by the parameter
     * @return the area with the specified name
     **/
    private Area getArea(String areaName)
    {
        for (Area area : allAreas) {
            if (area.getName().equals(areaName)) {
                return area;
            }
        }
        return null;
    }
    
    private void placeCardsInLobby() {
    Area lobby = getArea("Lobby");
    for (Card card : allCards) {
        lobby.enter(card);
    }
}



    /** Returns the area with the name specified by the parameter
     * @return the area with the specified name
     **/
    private Bridge getBridge(String br)
    {
        for (Bridge bridge : allBridges) {
            if (bridge.getCode().equals(br)) {
                return bridge;
            }
        }
        return null;
    }
    
    
}
