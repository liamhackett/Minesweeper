/**Liam Hackett
   CS 110
   This program generates a grid of number and mine squares in order to play the game of minesweeper
*/
import java.util.Random;
public class Grid
{
   //establish instance variables
   private int width;
   private int height;
   private int numMines;
   private int numSquaresUncovered;
   private Square[][] grid;

   /**This constructor generates the grid and randomly places bombs within the numbersquares
      @param columns number of columns, @param rows number of rows, @param numMines number of mines in the game
   */
   public Grid(int columns, int rows, int numMines) throws IndexOutOfBoundsException
   {
      Random generator = new Random();
      width = columns;
      height = rows;
      this.numMines = numMines;
      numSquaresUncovered = 0;
      grid = new Square[10][12];
      for(int i=0; i < numMines; i++)//generates 10 random places for the mines and generates the mine square
      {
         int ranHeight = generator.nextInt(11)+1;
         int ranWidth  = generator.nextInt(9)+1;
         while(grid[ranWidth][ranHeight] !=null)
         {
            ranHeight = generator.nextInt(11)+1;
            ranWidth  = generator.nextInt(9)+1;
         }
         grid[ranWidth][ranHeight] = new MineSquare();
      }
      
      //These nested for loops run generate each number square and calculates the nearby neighbors
      //The try catch statements prevent errors from going outside the grid
      for(int r = 0; r < 10; r++)
      {
         for(int c = 0; c < 12; c++)
         {
            try
            {
               if (grid[r][c] == null)
               {
                  int neighbors = 0;
                  for(int i=-1; i<=1; i++)
                  {
                     for(int j=-1; j<=1;j++)
                     {
                        try
                        {
                           if (grid[r+i][c+j] == null)
                              neighbors+=0;
                           else if (grid[r+i][c+j].isMine())
                              neighbors+=1;
                        }
                        catch(ArrayIndexOutOfBoundsException e)
                        {
                        } 
                     } 
                  } 
                  grid[r][c] = new NumberSquare(neighbors, r, c);              
               }
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
               System.out.print(e);
            }
         }
      }    
   }
   
   /**getNeighbors returns the number of neighboring mines their are using if else statements
      @return number of mines neighboring the square
   */
   public int getNeighbors(int r, int c) throws ArrayIndexOutOfBoundsException
   {
      int neighbors = 0;
      if (grid[r][c].isMine() == false)
      {
         if (grid[r-1][c].isMine())
            neighbors+=1;
         else if (grid[r+1][c].isMine())
            neighbors+=1;
         else if (grid[r][c+1].isMine())
            neighbors+=1;
         else if (grid[r][c-1].isMine())
            neighbors+=1;
         else if (grid[r-1][c-1].isMine())
            neighbors+=1;
         else if (grid[r-1][c+1].isMine())
            neighbors+=1;
         else if (grid[r+1][c-1].isMine())
            neighbors+=1;
         else if (grid[r+1][c+1].isMine())
            neighbors+=1;
         return neighbors;
      }
      return 0;
   }
   
   /**uncover checks if the square is flagged and if it is not it uncovers the square and reveals the number of neighbors or if it is 
      a mine
      @param r row of the square, @param c column of the square
   */
   public Status uncover(int r, int c)
   {
      try
      {
         if (grid[r][c].isFlagged())
         {
            System.out.print("Cannot uncover a flagged square\n");
            return Status.OK;
         }
         else if (grid[r][c].isMine())//if its a mine it returns MINE status and the user will lose the game
            return Status.MINE;

         else if (((NumberSquare)(grid[r][c])).getNeighborMines() > 1)//if the neighbors is >1 than only that square is revealed
         {
            grid[r][c].uncover();
            return Status.OK;
         }
         else if (((NumberSquare)(grid[r][c])).getNeighborMines() == 1)//if the neighbors = 1 then it uncovers a 3x3
         {
            for(int i=-1; i<=1; i++)
            {
               for(int j=-1; j<=1;j++)
               {
                  try
                  {
                     if (grid[r+i][c+j].isMine() == false)
                        grid[r+i][c+j].uncover();
                     else
                        numSquaresUncovered+=1;
                        
                  }
                  catch(ArrayIndexOutOfBoundsException e)
                  {
                  
                  }
               }
            }
            if (numSquaresUncovered == numMines)//if you have uncovered all of the mines then the status is set to WIN and the game ends
               return Status.WIN;
            else
               return Status.OK;
         }
         else //if the neighbors = 0 then it reveals a 5x5 area around it
         {
            for(int i=-2; i<=2; i++)
            {
               for(int j=-2; j <=2; j++)
               {
                  try
                  {
                     if (grid[r+i][c+j].isMine() == false)
                        grid[r+i][c+j].uncover();
                     else
                        numSquaresUncovered+=1;
                  }
                  
                  catch(ArrayIndexOutOfBoundsException e)
                  {
                  }
               }
            }
            if (numSquaresUncovered == numMines)//sets status to win if the user has uncovered all of the mines
               return Status.WIN;
            else
               return Status.OK;
         }
      }
      catch(ArrayIndexOutOfBoundsException e)
      {
         System.out.println("Out of range");
         return Status.OK;
      }

   }
   
   //expose mines uncovers each mine even if it is flagged
   public void exposeMines()
   {
      for(int j = 0; j < height; j++)
      {
         for(int k = 0; k < width; k++)
         {
            if(grid[j][k].isMine())
            {
               if(grid[j][k].isFlagged())
               {
                  grid[j][k].flagSquare();
                  grid[j][k].uncover();
               }
               else
                  grid[j][k].uncover();
            }
         }
      }
   }
   
   //flagSquare flags the desired square the Square class's flagSquare() method
   public void flagSquare(int r, int c)
   {
      grid[r][c].flagSquare();
   }
   
   //toString converts the grid into string values by their element to be printed easily
   //@return element String
   @Override
   public String toString()
   {
      String g = "   ";
      for(int i=0; i < 12; i++)
      {
         if (i <= 9)
            g+=Integer.toString(i)+"  ";
         else
            g+=Integer.toString(i)+ " ";
      }
      for(int j = 0; j < 10; j++)
      {
         g+="\n" + Integer.toString(j) + "  ";
         for(int k=0; k < 12; k++)
         {
            g+=grid[j][k] + "  ";
         }
      }
      return g+"\n";
   }
}