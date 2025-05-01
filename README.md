# Conway's Game of Life
This is a game I created for my college Java course to demonstrate my understanding of object-oriented programming in python. This game recreates John Conway's game of life, allowing for the user to choose a preset pattern or make their own to experience the iconic algorithm.

## Development
This project consists of two custom classes, those being Board and Cell. Each cell object has a position and state (dead or alive), and each board contains an array of cells as well as a width and height. By organizing the program this way, I was able to neatly store all of the game logic away in these classes while the actual game functionality occurs in the main method. It was a great crash course in class organization in Java.

**NOTE**: if your console is outputting question marks when trying to print the board, it is because your encoding preset doesn't recognize the characters I used for the cells. I found that you can fix this by simply typing 'chcp 65001' into the terminal to change the encoding method.
