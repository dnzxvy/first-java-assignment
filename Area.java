package cwk;

import java.util.*;
/**
 * A Area is part of a ZAPP resort.Each area has a name,  a luxury rating
 * and a capacity which represents the maximum number of people(cards) who can be on the  
 * area at any one time. Each area must maintain a list of all people (cards)
 * currently on the area. These lists are updated whenever cards enter or leave 
 * an area,so that it is always possible to say how many people (cards) are in the area 
 * and who they are.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Area 
{
  private int referNumber;
  private String name;
  private int rating;
  private int capacityofArea;
  private ArrayList<Card> thecards;
  
  public Area (int refNumber, String nm, int rat, int capacity)
  {
      referNumber = refNumber;
      name = nm;
      rating = rat;
      capacityofArea = capacity;
      thecards = new ArrayList<>();
  }
  public int getrefNumber()
  {
      return referNumber;
  }
  
  public String getName()
  {
      return name;
  }
  
  public int getluxuryRating()
  {
      return rating;
  }
  
  public void setLuxuryRating(int rate)
    {
        if(rating >= 1 && rating <= 10) {
        rating = rate;
    }
    } 
  //enter mutator which adds cards to the arraylist
  public void enter(Card card)
  {
      thecards.add(card);
  }
  //leave mutator to find position in arraylist
  public void leave(Card card)
  {
      thecards.remove(card);
  }
  //the area has reached capacity
  public boolean areaIsFull()
  {
      return thecards.size() >= capacityofArea;
  }
  //the area still has capacity
  public boolean areaWithinCap()
  {
      return thecards.size() < capacityofArea;
  }
  
  //accessor which returns a boolean saying whether a Card 
  //is in the area (in the ArrayList) 
  public boolean cardAreaCheck(Card card)
  {
      return thecards.contains(card);
  }
  //list all the cards in the area
  public List<Card> listCards()
  {
      return new ArrayList<>(thecards);
  }
  
  
  public String toString()
  {
      String nm = "\n " + name + " Reference Number: " + referNumber + " luxury Rating: " +
      rating + " Capacity of the Area: " + capacityofArea + " Cards Currently in: "
      + thecards.size() + " Cards in Area: " + thecards;
      return nm;
      
  }
}
