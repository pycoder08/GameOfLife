/**
 * Represents <what is this class?>
 * 
 * @author <name>
 *
 **/

/* UML CLASS DIAGRAM:
-----------------------------------------
Cell
-----------------------------------------
- x: int
- y: int
- state: int
-----------------------------------------
+ Cell()
+ Cell(x: int, y: int, state: int)
+ Cell(original: Cell)
+ getX(): int
+ getY(): int
+ getState(): int
+ setAll(x: int, y: int, state: int): boolean
+ setX(x: int): boolean
+ setY(y: int): boolean
+ setState(state: int): boolean
+ toString(): String
+ equals(other: Cell): boolean
+ isAlive(): boolean
-----------------------------------------
*/

public class Cell // don't forget to rename here and rename the file too!
{
	/***** STATIC VARIABLES *****/
	private static final int DEFAULT_STATE = 0; // constant for default alive state (dead)
	private static final int DEFAULT_X = 0; // Constant for default x coordinate
	private static final int DEFAULT_Y = 0;; // Constant for default y coordinate

	/***** INSTANCE VARIABLES *****/
	int x; // x coordinate of the cell
	int y; // y coordinate of the cell
	int state; // state of the cell (0 = dead, 1 = alive)
	/***** CONSTRUCTORS *****/
	
	/**	
	 * Default constructor for Cell class
	 */
	public Cell()
	{
		this.setAll(DEFAULT_X, DEFAULT_Y, DEFAULT_STATE); // set default values for x, y, and state
	}

	/**
	 * Full constructor for Cell class
	 * @param x x coordinate of the cell
	 * @param y y coordinate of the cell
	 * @param state state of the cell (0 = dead, 1 = alive)
	 */
	public Cell(int x, int y, int state)
	{
		this.setAll(x, y, state); // set values for x, y, and state
	}

	/**
	 * Copy constructor for Cell class
	 * @param original the original Cell object to copy from
	 */
	public Cell(Cell original)
	{	// If the original object is null, exit the program
		if(original == null)
		{
			System.err.println("CRITICAL ERROR: copy constructor given 'null' value. Shutting down...");
			System.exit(0);
		}	

		// Copy the values of the original object to this object if error check passes
		boolean setSuccessful = this.setAll(original.x, original.y, original.state);
		// Check to make sure the values were set correctly
		if (!setSuccessful)
		{
			System.err.println("CRITICAL ERROR: full constructor given invalid data. Shutting down...");
			System.exit(0);
		}

	}
	
	/***** ACCESSORS *****/
	
	/**
	 * Returns the x coordinate of the cell
	 * @return x coordinate of the cell
	 */
	public int getX()
	{
		return this.x; // return x coordinate
	}

	/**
	 * Returns the y coordinate of the cell
	 * @return y coordinate of the cell
	 */
	public int getY()
	{
		return this.y; // return y coordinate
	}

	/**
	 * Returns the state of the cell
	 * @return state of the cell (0 = dead, 1 = alive)
	 */
	public int getState()
	{
		return this.state; // return state
	}

	/***** MUTATORS *****/
	/**	
	 * Sets all the instance variables for a cell
	 * @param x x coordinate of the cell
	 * @param y y coordinate of the cell
	 * @param state state of the cell (0 = dead, 1 = alive)
	 * @return true if all three values are valid, false otherwise
	 */
	public boolean setAll(int x, int y, int state)
	{
		this.setState(state);
		this.setX(x);
		this.setY(y);
	
		boolean combinedValid = (this.setState(state) && this.setX(x) && this.setY(y));
		return combinedValid; // returns true if all three values are valid, false otherwise
	}
	
	/**
	 * Sets the x coordinate of the cell
	 * @param x x coordinate of the cell
	 * @return true if the x coordinate is valid, false otherwise
	 */
	public boolean setX(int x)
	{
		if (x < 0)
		{
			return false; // invalid x coordinate
		}
		else
		{
			this.x = x;
			return true; // valid x coordinate
		}
	}

	/**
	 * Sets the y coordinate of the cell
	 * @param y y coordinate of the cell
	 * @return true if the y coordinate is valid, false otherwise
	 */
	public boolean  setY(int y)
	{
		if (y < 0)
		{
			return false; // invalid y coordinate
		}
		else
		{
			this.y = y;
			return true; // valid y coordinate
		}
	}

	/**
	 * Sets the state of the cell
	 * @param state state of the cell (0 = dead, 1 = alive)
	 * @return true if the state is valid, false otherwise
	 */
	public boolean setState(int state)
	{
		if (state == 0 || state == 1)
		{
			this.state = state;
			return true; // valid state
		}
		else
		{
			return false; // invalid state
		}
	}
	/***** OTHER REQUIRED METHODS *****/

	/**
	 * Convert a Cell into string representation of all data
	 * @return string containing all of the Cell object's data separated by newlines,
no newline at the end
	 */
	public String toString()
	{
		// Return string detailing the attributes of an object
		return "x-value: " + this.x +
		"\ny-value: "+ this.y +
		"\nstate (0 = dead, 1 = alive): " + this.state;
	}

	/**
	 * Compare a Cell object to another Cell object
	 * @param other the other Cell object to compare to
	 * @return true if the two Cell objects are equal, false otherwise
	 */
	public boolean equals(Cell other)
	{
		return this.x == other.x && this.y == other.y && this.state == other.state;
	}

	/***** HELPER METHODS *****/

	/**
	 * Check if the cell is alive
	 * @return true if the cell is alive, false otherwise
	 */
	public boolean isAlive()
	{
		return this.state == 1;
	}
}