/**Liam Hackett
   CS 110
   This class extends the square class and generates a square that contains a mine
*/
public class MineSquare extends Square
{  
   //default constructor to establish square
   public MineSquare()
   {
      super();
   }
   //uncover() uncovers the square and sets the element to be a mine
   //@return true or false if it uncovered
   public boolean uncover()
   {
      if (super.isUncovered() == false)
      {
         super.setUncovered();
         super.setElement("*");
         return true;
      }
      else
         return false;
   }
   //isMine() @return true because this square is always a mine
   public boolean isMine()
   {
      return true;
   }

}