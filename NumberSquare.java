/**Liam Hackett
   CS 110
   This class extends the square class and generates a square that does not contain a mine
*/
public class NumberSquare extends Square
{
   //Establish instance variables
   private int neighborMines;
   private int myRow;
   private int myCol;
   private String e = "x"; //default element
   private boolean flagged = false;
   private boolean uncovered = false;
   
   /**This constructor creates a square and then adds identifiers to make it a NumberSquare
      @param neighborMines how many mines are nex to it
      @param row what row the square is in
      @param col what column the square is in
   */
   public NumberSquare(int neighborMines, int row, int col)
   {
      super();
      this.neighborMines = neighborMines;
      myRow = row;
      myCol = col;
   }
   
   //getNeighborMines() @return the amount of mines next to the square
   public int getNeighborMines()
   {
      return neighborMines;
   }
   
   //isMine @return false that this square is not a mine
   public boolean isMine()
   {
      return false;
   }
   
   /**uncover() checks if the square is flagged and if it is not it changes the element of the square to be either a blank
      _ or the number of mines neighboring it
   */
   public boolean uncover()
   {
      if (super.isFlagged() == false)
      {
         if (neighborMines == 0)
            super.setElement("_");
         else
            super.setElement(Integer.toString(neighborMines));  
         super.setUncovered();
         return true;
      }
      else
         return false;
      
   }
}