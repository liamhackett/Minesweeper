/**Liam Hackett
   CS 110
   This class generates the game of minesweeper and gets the user input and displays everything to the console.
*/
import java.util.Scanner;
public class Minesweeper
{
   //establish instance variables
   private Grid field;
   //Constant for choices
   private final String UNCOVER = "u";
   private final String FLAG = "f";
   private final String QUIT = "q";
   private String choice;
   private String option;
   private int columns = 12;
   private int rows = 10;
   private int mines = 10;
   private Status s;
   private int r, c;

   //default constructor that generates the grid and displays the grid to start
   public Minesweeper()
   {
      field = new Grid(columns, rows, mines);
      System.out.println("Welecome to Minesweeper!");
      System.out.print(field);
   }
   
   //input gets the users move and decides what needs to be done
   public void input()
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.println("What's next?");
      System.out.println("Options: (U)ncover r c (F)lag r c, (Q)quit");
      option = keyboard.next();
      choice = option.toLowerCase();
      //this while loop loops through and asks for choices as long as the user does not want to quit
      while(!choice.equals(QUIT))
      {
         if(choice.equals(UNCOVER))
         {
            int r = keyboard.nextInt();
            int c = keyboard.nextInt(); 
            s = field.uncover(r, c);
            System.out.print(field);
            if (s == Status.MINE) //Ends the game if user selects a bomb
            {
               field.exposeMines();
               System.out.print("\n" + field);
               System.out.println("Better Luck Next Time");
               choice = "q";
            }
            else if (s == Status.OK) //asks user for next turn if they did not select a bomb
            {
               System.out.println("What's next?");
               System.out.println("Options: (U)ncover r c (F)lag r c, (Q)quit");
               option = keyboard.next();
               choice = option.toLowerCase();
            }
            
            else if (s == Status.WIN)//Ends game if the user wins the game
            {
               field.exposeMines();
               System.out.print("\n" + field + "\n");
               System.out.println("Congratulations! You Have Survived Minesweeper!");
               choice = "q";
            }
            
         }
         else if(choice.equals(FLAG)) //if the user selects flag then it flags the square and asks the user for their next turn
         {
            int r = keyboard.nextInt();
            int c = keyboard.nextInt();
            field.flagSquare(r, c);
            System.out.print(field);
            System.out.println("Options: (U)ncover r c (F)lag r c, (Q)quit");
            option = keyboard.next();
            choice = option.toLowerCase();
         }
         else if(!choice.equals(UNCOVER) || !choice.equals(FLAG))//if the user inputs an option not on the list it asks again
         {
            int r = keyboard.nextInt();
            int c = keyboard.nextInt();
            System.out.println("Invalid option");
            System.out.println("Options: (U)ncover r c (F)lag r c, (Q)quit");
            option = keyboard.next();
            choice = option.toLowerCase();

         }
      }
      
      System.out.println("Game Over");
   }
   
    
}