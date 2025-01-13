/**
* This class provides several useful static methods and is part of the CS111 course material
* MiraCosta College 2020. This version includes a String input method but not printCentered.
*
* @author Nery Chapleton-Lamas <nery@miracosta.edu>
* @author Gabriel Siguenza <gebsiguenza@gmail.com>
* @author Catherine Walker <cwalker@miracosta.edu>
*
* @version 2.0
**/

import java.util.Scanner;

public class UtilityBelt
{
	private static Scanner keyboard = new Scanner(System.in); //used by all methods, MUST be kept clean

	/**
	* Centers and prints text value within total width (like width in printf) given for one line,
	* ends with new line
	*
	* @param width minimum width for one line, assuming width is greater than text length
	* @param text string value to be displayed in centered format
	**/
	public static void printCentered(int width, String text)
	{
		
		int leftWidth; //number of space left of text, plus text length
		String spec; //format specified for text

		leftWidth = (width + text.length() ) / 2;
		spec = "%" + leftWidth + "s"; //automatically right justified

		System.out.printf(spec + "%n", text);
	}
	
	/**
	* Reads input from user until valid integer value entered (error-checked using bounds)
	*
	* @param prompt string value containing the question to ask user for input (assuming in format "question: ")
	* @param lower an integer for the lower bounds for input, assume lower < upper
	* @param upper an integer for the upper bounds for input, assume upper > lower
	*
	* @return returns integer value between lower and upper (inclusive)
	**/
	public static int readInt(String prompt, int lower, int upper)
	{
		String temp;
		int result;
		boolean isNotValid;
		
		isNotValid = true; // initialize all variables, Compiler will complain because they are initialized in try/catch
		result = 0;
		
		do
		{
			System.out.print(prompt);
			temp = keyboard.nextLine();
			
			try
			{
				result = Integer.parseInt(temp);
				isNotValid = (result < lower) || (result > upper);
				
				if(isNotValid)
				{
					System.out.println("ERROR: please enter value between " + lower + " - " + upper);
				}
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("Error: integer input is required");
			}
			
		} while(isNotValid);


		return result;
	}
	
	
	/**
	* Reads input from user until valid double value entered (error-checked using bounds)
	*
	* @param prompt string value containing the question to ask user for input (assuming in format "question: ")
	* @param lower an double for the lower bounds for input, assume lower < upper
	* @param upper an double for the upper bounds for input, assume upper > lower
	*
	* @return returns double value between lower and upper (inclusive)
	**/
	public static double readDouble(String prompt, double lower, double upper)
	{
		String temp;
		double result;
		boolean isNotValid;
		
		isNotValid = true;
		result = 0;
		
		do
		{
			System.out.print(prompt);
			temp = keyboard.nextLine();
			
			try
			{
				result = Double.parseDouble(temp);
				isNotValid = (result < lower) || (result > upper);
				
				if(isNotValid)
				{
					System.out.println("ERROR: please enter value between " + lower + " - " + upper);
				}
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("ERROR: double input is required");
			}
		} while(isNotValid);


		return result;
	}


	/**
	* Reads input from user until valid char value entered (error-checked using validChars)
	*
	* @param prompt string value containing the question to ask user for input (assuming in format "question: ")
	* @param validChars string value containing exhaustive list of all valid chars (i.e., "YyNn")
	*
	* @return returns char value within validChars provided
	**/
	public static char readChar(String prompt, String validChars)
	{
		String temp;
		char result;
		boolean isNotValid;
		
		isNotValid = true;
		result = 0;
		
		do
		{
			System.out.print(prompt);
			temp = keyboard.nextLine();
			try
			{
				result = temp.charAt(0);
				isNotValid = validChars.indexOf(result) == -1;
			
				if(isNotValid)
				{
					System.out.println("ERROR: please enter one of the following valid characters: " + validChars);
				}
			}
			catch(StringIndexOutOfBoundsException sOutOfBounds)
			{
				System.out.println("ERROR: input type does not match");
			}
		} while(isNotValid);


		return result;
	}

	/**
	* Reads input from user until valid string entered (error-checked using length bounds)
	*
	* @param prompt string value containing the question to ask user for input (assuming in format "question: ")
	* @param lower an integer for the lower bounds for length, assume lower < upper
	* @param upper an integer for the upper bounds for length, assume upper > lower
	*
	* @return returns string value longer than lower and trimmed to length upper (inclusive)
	**/
	public static String readString(String prompt, int lower, int upper)
	{
		String temp;
		String result;
		boolean isNotValid;
		
		//isNotValid = true; // initialize all variables
		result = "";
		
		do
		{
			System.out.print(prompt);
			temp = keyboard.nextLine();
			
			isNotValid = (temp.length() < lower);
			if(isNotValid)
			{
				System.out.println("Error: string length must be at least " + lower);
			}
			else if(temp.length() > upper)
			{
				System.out.println("Warning: string length exceeds maximum characters and will be truncated.");
				result = temp.substring(0, upper);
			}
			else
			{
				result = temp;
			}
			
		} while(isNotValid);

		return result;
	}
	
}
