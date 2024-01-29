package cwk;

 

   
/**
 * A Card has an id number, name, a luxury rating, number of credits and points.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Card 
{
    private int cardIdNum;
    private String guestName;
    private int luxuryRating;
    private int NumOfCred;
    private int NumOfPoints;
    
    public Card (int IdNum, String gn, int Lr, int cred)
    {
        cardIdNum = IdNum;
        guestName = gn;
        luxuryRating = Lr;
        NumOfCred = cred;
        NumOfPoints = 0;
    }
    
    
    public int getCardIdNum()
    {
        return cardIdNum;
    }
    
    
    public int getLuxuryRating()
    {
        return luxuryRating;
    }
    
    public int getNumOfCredits()
    {
        return NumOfCred;
    }
    
    public void setLuxuryRating(int Lr)
    {
        if(luxuryRating >= 1 && luxuryRating <= 10) {
        luxuryRating = Lr;
    }
    } 
    
    public boolean cardAllowedToVisit(int areaRating) 
    {
        return luxuryRating >= areaRating;
    }
    public void addCredits(int creds)
    {
        NumOfCred = NumOfCred + creds;
    }
    
    public void deductCredits(int credDeduct)
    {
        if (credDeduct > 0 && credDeduct <= NumOfCred ) {
           NumOfCred -= credDeduct;
        }
    }
    
    public boolean CredBridgeCheck(int credRequired)
    {
        return NumOfCred >= credRequired;
    }
    // update credits and points for using the bridge
    public void updateCredPointBridge()
    {
        int credRequired = 4;
        int updateNumPoints = 1;
        if(CredBridgeCheck(credRequired)) {
            deductCredits(credRequired);
            NumOfPoints += updateNumPoints;
        
        }
    }
    
    // convert points to credits
    public void convertPointToCred()
    {
        int pointsConvert = 3;
        if (NumOfPoints >= pointsConvert) {
            int convertedCredits = NumOfPoints / pointsConvert;
            NumOfCred += convertedCredits;
            NumOfPoints -= convertedCredits * pointsConvert;
            
        } 
            
            
        }
    
    
    public String toString()
    {
        String gn ="\n " + guestName + " Card ID Number : " + cardIdNum + " Number of Credits : " + NumOfCred +
        " Number Of Points : " + NumOfPoints;
        return gn;
    }
}

