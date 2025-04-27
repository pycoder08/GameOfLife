/**
 * A class to represent a game board.
 * 
 * @author Muhammad Conn
 *
 **/

/* UML CLASS DIAGRAM:
-----------------------------------------
Board
-----------------------------------------
- width: int
- height: int
- board: Cell[][]
-----------------------------------------
+ Board()
+ Board(width: int, height: int)
+ Board(original: Board)
+ getWidth(): int
+ getHeight(): int
+ getBoard(): Cell[][]
+ setAll(width: int, height: int): boolean
+ setWidth(width: int): boolean
+ setHeight(height: int): boolean
+ setBoard(board: Cell[][]): boolean
+ toString(): String
+ equals(other: Board): boolean
+ initializeDefaultBoard(): Cell[][]
+ initializeBoard(boardType: int): Cell[][]
+ printBoard(): void
+ deepCopyBoard(source: Cell[][]): Cell[][]
+ updateBoard(): void
-----------------------------------------
*/

public class Board
{
	/***** STATIC VARIABLES *****/
    private static final int DEFAULT_WIDTH = 20; // constant for default width of the board
    private static final int DEFAULT_HEIGHT = 20; // constant for default height of the board
    /***** INSTANCE VARIABLES *****/
    int width; // width of the board
    int height; // height of the board
    Cell[][] board; // 2D array to represent the board
	/***** CONSTRUCTORS *****/
    /** 
     * Default constructor for Board class
     */
    public Board()
    {
        this.width = DEFAULT_WIDTH; // set default width
        this.height = DEFAULT_HEIGHT; // set default height
        this.board = initializeDefaultBoard(); // initialize the board as blank
        
    }
    

    /**
     * Full constructor for Board class
     * @param width integer value of the width of the board
     * @param height integer value of the height of the board
     */
    public Board(Cell[][] board, int width, int height)
    {
        this.setAll(board, width, height); // set values for width and height
    }

    /**
     * Copy constructor for Board class
     * @param original Board object to be copied
     */
    public Board(Board original)
    {
        this.width = original.getWidth(); // set width to the original board's width
        this.height = original.getHeight(); // set height to the original board's height
        this.board = deepCopyBoard(original.getBoard()); // set board to the original board's Cell[][] array
    }

	/***** ACCESSORS *****/
    /**
     * Returns the height of the board
     * @return integer value of the height of the board
     */
	public int getWidth() 
    {
        return width;
    }

    /**
     * Returns the height of the board
     * @return integer value of the height of the board
     */
    public int getHeight() 
    {
        return height;
    }

    /**
     * Returns the board as a Cell[][] array
     * @return Cell[][] array representing the board
     */
    public Cell[][] getBoard()
    {
        return board; // return the board as a Cell[][] array
    }


	/***** MUTATORS *****/
	
    /** 
     * Sets all the values of the board
     * @param width integer value of the width of the board
     * @param height integer value of the height of the board
     * @return boolean value indicating success or failure of the operation
     */
    public boolean setAll(Cell[][] board, int width, int height)
    {
        this.setWidth(width); // set the width of the board
        this.setHeight(height); // set the height of the board
        this.setBoard(board); // set the board to the new Cell[][] array
        
        boolean combinedValid = (this.setWidth(width) && this.setHeight(height) && this.setBoard(board)); // check if both operations were successful
        return combinedValid; // return the result of the operation
    }

    /**
     * Sets the width of the board
     * @param width integer value of the width of the board
     * @return boolean value indicating success or failure of the operation
     */
    public boolean setWidth(int width)
    {
		if (width < 0)
		{
			return false; // invalid x coordinate
		}
		else
		{
			this.width = width;
			return true; // valid x coordinate
		}
	}

    /**
     * Sets the height of the board
     * @param height integer value of the height of the board
     * @return boolean value indicating success or failure of the operation
     */
    public boolean setHeight(int height)
    {
        if (height < 0)
        {
            return false; // invalid y coordinate
        }
        else
        {
            this.height = height;
            return true; // valid y coordinate
        }
    }
    
    /**
     * Sets the board to a new Cell[][] array
     * @param board Cell[][] array to set the board to
     */
    public boolean setBoard(Cell[][] board)
    {
        if (board == null || board.length == 0 || board[0].length == 0) // check if the board is null or empty
        {
            return false; // invalid board
        }
        else
        {
            this.board = board; // set the board to the new Cell[][] array
            return true; // valid board
        }
    }

	/***** OTHER REQUIRED METHODS *****/
    /**
     * Returns a string representation of the board
     * @return string value of the board
     */
    public String toString()
    {

        String outputString = "";

        for (int row = 0; row < height; row++) 
        {
            for (int col = 0; col < width; col++) 
            {
                if (board[row][col].getState() == 1) 
                {
                    outputString += "■  ";
                } 
                else 
                {
                    outputString += "▢  ";
                }
            }
            outputString += "\n"; // move to next line after each row
        }
        return "Board width:" + width + 
        "\nBoard height:" + height + 
        "\n" + outputString;

    
    }

    /** 
     * Checks if two Board objects are equal
     * @param other Board object to compare to
     */
    public boolean equals(Board other) 
    {
        if (this.width != other.width || this.height != other.height) return false; // check if the dimensions are equal before comparing the contents of the boards
        
        // Compare the states of each cell in the boards
        for (int r = 0; r < height; r++) 
        {
            for (int c = 0; c < width; c++) 
            {
                if (this.board[r][c].getState() != other.board[r][c].getState())
                    return false;
            }
        }
        return true;
    }
    
    /***** HELPER METHODS *****/

    public Cell[][] initializeDefaultBoard() 
    {
        int rows = this.height;
        int cols = this.width;
        Cell[][] tempBoard = new Cell[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                tempBoard[row][col] = new Cell(row, col, 0);
            }
        }
        return tempBoard;
    }

    public Cell[][] initializeBoard(int boardType)
    {
        Cell[][] tempBoard = this.initializeDefaultBoard(); // Create a 2D array of Cell objects with the specified number of rows and columns
        switch (boardType) // Use a switch statement to determine the type of board to initialize
        {
            case 0: // Blank
                // Do nothing and keep the board blank
                break;
            case 1: // Glider
                // Initialize a glider pattern
                tempBoard[1][0].setState(1);
                tempBoard[2][1].setState(1);
                tempBoard[0][2].setState(1);
                tempBoard[1][2].setState(1);
                tempBoard[2][2].setState(1);
                break;
            case 2: // Blinker
                // Initialize a blinker pattern
                tempBoard[1][0].setState(1);
                tempBoard[1][1].setState(1);
                tempBoard[1][2].setState(1);
                break;
            case 3: // Lightweight spaceship

                // Set the first 5 cells in the second row to alive EXCEPT the first one
                for (int col = 1; col < 5; col++) 
                {
                    tempBoard[1][col].setState(1); 
                }
                tempBoard[2][0].setState(1); // Set the cell in the third row and first column to alive
                tempBoard[2][4].setState(1); // Set the cell in the third row and fifth column to alive
                tempBoard[3][4].setState(1); // Set the cell in the fourth row and fifth column to alive
                tempBoard[4][1].setState(1); // Set the cell in the fifth row and second column to alive
                tempBoard[4][3].setState(1); // Set the cell in the fifth row and fourth column to alive
                break;
                
                
            default:
                // Print an error message for invalid board type and shut down
                System.err.println("Invalid board type: " + boardType + ", shutting down..."); 
                System.exit(0);
        }
        return tempBoard; // Return the initialized board
        }

    public void printBoard()
    {
        // We print the labels for each column at the top of the board
        // We will need to account for the fact that indexes start at 0, so we add one to the width value if necessary

        String[] colLabels = new String[this.width + 2]; // Create an array to hold the row labels
        for (int i = 0; i < 10; i++)
        {
            colLabels[i] = i + "  "; // Initialize the row labels with the row numbers
            System.out.print(colLabels[i]);
        }

        for (int i = 10; i < this.width + 1; i++)
        {
            colLabels[i] = (i) + " "; // Use one space instead of two for double digits
            System.out.print(colLabels[i]);
        }

        System.out.println(); // Print a new line after the row labels



        for (int row = 0; row < this.height; row++)
        {

            if (row < 9) // For single digit numbers, use two space
            {
                System.out.print((row + 1) + "  "); 
            }
            else // For double digit numbers, use one space
            {
                System.out.print((row + 1) + " ");
            }
        for (int col = 0; col < this.width; col++)
        {
            if (board[row][col].getState() == 1) // Check if the cell is alive (state = 1)
            {
            System.out.print("■  "); // Print a filled square for alive cells
            }
            else
            {
            System.out.print("▢  "); // Print an empty square for dead cells
            }
        }
        System.out.println(); // Print a new line after each row of cells
        }
    }


    /**
     * Deep copies a source Cell[][] array into a target Cell[][] array.
     * @param source the original array to copy from
     * @return a new deep-copied array
     */
    private Cell[][] deepCopyBoard(Cell[][] source) 
    {
        int rows = source.length;
        int cols = source[0].length;
        Cell[][] copy = new Cell[rows][cols];

        for (int row = 0; row < rows; row++) 
        {
            for (int col = 0; col < cols; col++) 
            {
                copy[row][col] = new Cell(source[row][col]); // use copy constructor
            }
        }

        return copy;
    }

    /**
     * Updates the board to the next generation based on the rules of Conway's Game of Life.
     * @paramm seconds
     * @throws InterruptedException if the thread is interrupted during sleep
     */
    public void updateBoard(double seconds) throws InterruptedException // We need to declare this exception in case the Thread.sleep() method gives an interrupted exception
    {
        Cell[][] tempBoard; // Deep copy the board to a temporary array
        tempBoard = this.deepCopyBoard(this.board); 

        int rows = this.height; // Get the number of rows from the board's height
        int cols = this.width; // Get the number of columns from the board's width
        Thread.sleep((int)(seconds * 1000)); // Pause for however many seconds the user specifies
        for (int currentRow = 0; currentRow < rows; currentRow++)
        {
          for (int currentCol = 0; currentCol < cols; currentCol++)
          {
              int liveNeighbors = 0; // We create an int to track the number of alive cells adjacent to the current one
              if (currentCol != cols - 1 && this.board[currentRow][currentCol + 1].getState() == 1) // Check the cell to the right so long as we are not on the right edge of the board
              {
                liveNeighbors++;
              }
              if (currentCol != 0 && this.board[currentRow][currentCol - 1].getState() == 1) // Check the cell to the left so long as we are not on the left edge of the board
              {
                liveNeighbors++;
              }
              if (currentRow != rows - 1 && this.board[currentRow + 1][currentCol].getState() == 1) // Check the cell below so long as we are not on the bottom edge of the board
              {
                liveNeighbors++;
              }
              if (currentRow != 0 && this.board[currentRow - 1][currentCol].getState() == 1) // Check the cell above so long as we are not on the top edge of the board
              {
                liveNeighbors++;
              }
              if (currentRow != rows - 1 && currentCol != cols - 1 && this.board[currentRow + 1][currentCol + 1].getState() == 1) // Check the cell diagonally down-right so long as we are not on the bottom or right edge of the board
              {
                liveNeighbors++;
              }
              if (currentRow != 0 && currentCol != 0 && this.board[currentRow - 1][currentCol - 1].getState() == 1) // Check the cell diagonally up-left so long as we are not on the top or left edge of the board
              {
                liveNeighbors++;
              }
              if (currentRow != rows - 1 && currentCol != 0 && this.board[currentRow + 1][currentCol - 1].getState() == 1) // Check the cell diagonally down-left so long as we are not on the bottom or left edge of the board
              {
                liveNeighbors++;
              }
              if (currentRow != 0 && currentCol != cols - 1 && this.board[currentRow - 1][currentCol + 1].getState() == 1) // Check the cell diagonally up-right so long as we are not on the top or right edge of the board
              {
                liveNeighbors++;
              }
  
            // We create conditionals to follow the rules of Conway's Game of Life
            if (liveNeighbors < 2 || liveNeighbors > 3) // If there is over or underpopulation
            {
                tempBoard[currentRow][currentCol].setState(0); // Set the cell to dead
            }
            else if (tempBoard[currentRow][currentCol].getState() == 0 && liveNeighbors == 3) // If the cell is dead and there are exactly 3 live neighbors
            {
                tempBoard[currentRow][currentCol].setState(1); // Set the cell to alive
            }
          }
        }

        this.board = deepCopyBoard(tempBoard); // Update the board with the new state after all the checks are complete
    }
}