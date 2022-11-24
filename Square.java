/**Liam Hackett
   CS 110
   This abstract class describes each square of the grid. This class has methods to change the value of the square
*/
public abstract class Square
{
   //establish instance variables
   private String element;
   private boolean flagged;
   private boolean uncovered;
   
   /**Constructor to build a square using the element, if its flagged, and if its uncovered
      @param element what the square displays
      @param flagged whether or not it is flagged already
      @param uncovered whether or not it is uncovered
   */
   public Square(String element, boolean flagged, boolean uncovered)
   {
      this.element = element;
      this.flagged = flagged;
      this.uncovered = uncovered;
   }
   
   //Default constuctor to build a square as if the game had just started
   public Square()
   {
      element = "x";
      flagged = false;
      uncovered = false;
   }
   
   //isFlagged returns whether or not the square is flagged @return true or false if its flagged
   public boolean isFlagged()
   {
      return flagged;
   }
   
   //isUncovered returns whether or not the square is uncovered @return true or false if its uncovered
   public boolean isUncovered()
   {
      return uncovered;
   }
   
   /**flagSquare checks if the square is already flagged and if it is it unflags the square
      and if its not it flags the square
   */
   public void flagSquare()
   {
      if (flagged == true)
      {
         flagged = false;
         uncovered = false;
      }
      else if(uncovered == true)
         flagged = false;
      else
      {
         uncovered = false;
         flagged = true;
      }
   }  
   
   //setUncovered changes the value of uncovered so the square is uncovered
   public void setUncovered()
   {
      uncovered = true;
   }
   
   //setElement sets the face of the square to the given element @param element face of the square
   public void setElement(String element)
   {
      this.element = element;
   }
   
   //toString converts the squares properties into string values to be printed easily
   //@return element String
   @Override
   public String toString()
   {
      if (flagged == true)
         return "f";
      else if (uncovered == true)
         return element;
      else
         return element;
   }
   
   //Abstract methods to be defined in subclasses
   public abstract boolean uncover();
   public abstract boolean isMine();

 
}