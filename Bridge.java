package cwk;

 

  
/**
 * A bridge provides a one-way connection between two areas. It
 has a bridge code and information about both the source and
 the destination area
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bridge
{
    private String code;
    private Area fromArea;
    private Area toArea;
    
    public Bridge(String cde, Area fa, Area ta)
    {
        code = cde;
        fromArea = fa;
        toArea = ta;
    }
    
    public String getCode()
    {
        return code;
    }
    
    public Area getFromArea()
    {
        return fromArea;
    }
    
    public Area getToArea()
    {
        return toArea;
    }
    
    public void setcode (String cde)
    {
        code = cde;
    }
    
    public void setFromArea (Area fa)
    {
        fromArea = fa;
    }
    
    public void setToArea (Area ta)
    {
        toArea = ta;
    }
    
    

    public boolean canEnterBridge (Card card, int credRequired)
    {
        return card.CredBridgeCheck(credRequired);
    }
    
    public String processMove (Card card, int credRequired)
    {
        if (canEnterBridge(card, credRequired)) {
            
            fromArea.leave(card);
            toArea.enter(card);
            card.updateCredPointBridge();
            return "Card has moved to the destination area.";
        } else {
          return "Card cannot move to the destination area due to insufficient luxury rating or credits.";
            
        }
            
    }
    
    public String toString()
    {
        return "Bridge" + code + "From Area" + fromArea.getrefNumber() + "," + fromArea.getName() + "\nTo Area: "
        + toArea.getrefNumber() + "," + toArea.getName();
    }
    
}
