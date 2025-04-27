/********************************************
*	AUTHORS:	<your name>
* COLLABORATORS: <name of peer, tutor, instructor>
*	LAST MODIFIED:	<date of last change>
********************************************/

/********************************************
*	<TITLE OF PROGRAM>
*********************************************
*	PROGRAM DESCRIPTION:
*	<1-2 sentences describing overall program>
*********************************************
*	ALGORITHM:
*	<Pseudocode here>
*********************************************
*	STATIC METHODS:
* <UML style list of static methods>
*********************************************
*	ALL IMPORTED PACKAGES NEEDED AND PURPOSE:
 *	Not used for UD1
*	<ex: Scanner= used for console input>
*********************************************/

// <IMPORTS GO HERE - Not used for UD1>

public class Main 
{
  
  /***** CONSTANT SECTION *****/
  public static void main(String[] args) throws InterruptedException // We need to declare this exception in case the Thread.sleep() method gives an interrupted exception
  {

    /***** DECLARATION SECTION *****/
    int rows;
    int cols;
    int sizeChoice; // Variable to store the choice of size
    double secondsChoice; // Variable to store the choice of seconds

    Cell[][] boardArray; // An array of cells
    Board gameBoard; // A Board object
    boolean contGame; // declare the LCV for the main loop
    int boardChoice; // variable to store the user's choice of board pattern
    boolean contChoice; // LCV for board creation loop
    int choiceRow; // Variable to store the row choice for custom board creation
    int choiceCol; // Variable to store the column choice for custom board creation

    /***** INITIALIZATION SECTION *****/
    
    contGame = true; // Set the loop control variable to true to start the main loop
    
    /***** INTRO SECTION *****/
    
    System.out.println("Welcome to the Game of Life!");
    System.out.println("This game was invented by John Conway in 1970.");
    System.out.println("The rules are as follows:\nEvery cell is either dead or alive.");
    System.out.println("If a cell is alive, it will stay alive in the next generation if it has 2 or 3 neighbors.");
    System.out.println("If it has less 2 or more than 3 neighbors, it will die.");
    System.out.println("If a cell is dead, it will come to life if it has exactly 3 neighbors.");
    System.out.println("Select a pattern and watch it evolve!\n\n");
    
    /***** INPUT SECTION *****/

    sizeChoice = UtilityBelt.readInt("Enter the size of the board (between 20 and 50): ", 20, 50); // Get the size choice from the user
    rows = sizeChoice; // Set the number of rows to the size choice
    cols = sizeChoice; // Set the number of columns to the size choice

    secondsChoice = UtilityBelt.readDouble("Enter the number of seconds to wait between generations (between 0.1 and 2.0): ", 0.1, 2.0); // Get the seconds choice from the user

    boardArray = new Cell[rows][cols];
    gameBoard = new Board(boardArray, rows, cols);
    
    boardChoice = UtilityBelt.readInt("Enter one of the following patterns to start the game:\n1: Glider\n2: Blinker\n3: Lightweight Spaceship\n4: Make your own board\n", 1, 4);
    contChoice = true; // Set the loop control variable to true to start the board creation loop

    gameBoard.board = gameBoard.initializeDefaultBoard(); // Initialize the blank board state
    if (boardChoice == 4) // If the user chooses to make their own board
    {
      System.out.println("Follow the instructions to create your own board. Type '0' when you are ready to start.");
      while (contChoice)
      {
        for (int i = 0; i < 10; i++)
        {
          System.out.println(); // Print a new line for better readability
        }

        gameBoard.printBoard();
        choiceRow = UtilityBelt.readInt("What row would you like to place a cell at?", 0, rows);
        if (choiceRow != 0) //
        {
          choiceCol = UtilityBelt.readInt("What column would you like to place a cell at?", 1, cols);
          gameBoard.board[choiceRow - 1][choiceCol - 1].setState(1); // Set the cell at the chosen row and column to alive
        }
        else // If the user enters 0
        {
          contChoice = false; // Set the loop control variable to false to exit the loop
        }
      }
    }
    else // If the user does not want to make their own board
    {
      gameBoard.setBoard(gameBoard.initializeBoard(boardChoice)); // Initialize the board with the chosen pattern
    }

    
    

    
    /***** PROCESSING SECTION *****/

    /***** OUTPUT SECTION *****/

    Board checkBoard = new Board(); // Create a new default Board object to check the board state
    while (contGame)
    {

      for (int i = 0; i < 10; i++)
      {
        System.out.println(); // Print a new line for better readability
      }

      gameBoard.printBoard(); // Print the updated board state
      gameBoard.updateBoard(secondsChoice); // Call the updateBoard method to update the board state

      if (gameBoard.equals(checkBoard)) // Check if the board state has changed
      {
        System.out.println("The board has stabilized. Exiting the game..."); // Print a message if the board has stabilized
        contGame = false; // Set the loop control variable to false to exit the loop
      }
      else
      {
        checkBoard = new Board(gameBoard); // Copy the current board state to checkBoard
      }
    }
    
  }
  /***** STATIC METHODS *****/
}
