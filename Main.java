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
  static final int ROWS = 20;
  static final int COLS = 20;
  public static void main(String[] args) throws InterruptedException // We need to declare this exception in case the Thread.sleep() method gives an interrupted exception
  {

    /***** DECLARATION SECTION *****/
    
    Cell[][] boardArray = new Cell[ROWS][COLS]; // Create a 2D array of Cell objects with 10 rows and 10 columns
    Board gameBoard = new Board(boardArray, ROWS, COLS); // Create a Board object with the 2D array and its dimensions
    boolean contGame; // declare the LCV for the main loop
    /***** INITIALIZATION SECTION *****/


    // "▣  "
    // "▢  "
   
    gameBoard.setBoard(gameBoard.initializeBoard(1)); // Initialize the board with a glider pattern
    contGame = true; // Set the loop control variable to true to start the main loop


    /***** INTRO SECTION *****/

    /***** INPUT SECTION *****/

    /***** PROCESSING SECTION *****/

    /***** OUTPUT SECTION *****/

    while (contGame)
    {
      gameBoard.printBoard(); // Print the updated board state
      gameBoard.updateBoard(); // Call the updateBoard method to update the board state
      
      for (int i = 0; i < 5; i++)
      {
        System.out.println(); // Print a new line for better readability
      }
    }
    
  }
  /***** STATIC METHODS *****/
}
