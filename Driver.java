/**Liam Hackett
   This program is the driver for all of the other minesweeper classes 
*/
import java.util.Scanner;

public class Driver
{
   public static void main(String[] args)
   {
      Scanner keyboard = new Scanner(System.in);
      
      String replay = "Y";
      
      while (replay.equals("Y") || replay.equals("y"))//this while loop allows the game to be replayable
      {
         Minesweeper game = new Minesweeper();//generate game
         
         game.input(); //start the game and get the users moves
         
         System.out.println("Do you want to play again?(Y or N)");//ask the user if they want to play again
         
         replay = keyboard.next();
         
      }
   }
}