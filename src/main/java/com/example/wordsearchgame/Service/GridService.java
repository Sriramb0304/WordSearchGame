package com.example.wordsearchgame.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GridService {

    Logger logger= LoggerFactory.getLogger(GridService.class);

    private class Coordinate {
        int row;
        int col;
        Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        DOWN_LEFT,
        DOWN_RIGHT,
        UP_LEFT,
        UP_RIGHT
    }


    public char[][] createGrid(List<String> words, int gridSize){
        logger.info("Invoked the createGrid method in GridService");

        List<Coordinate> coordinates=new ArrayList<>();

        char[][] grid=new char[gridSize][gridSize];

        initialGridFill(grid);

        fillCoordinates(gridSize,coordinates);

        for(String word:words) {

            Collections.shuffle(coordinates);

            for (Coordinate coordinate : coordinates) {

                Direction availableDirection = getDirection(grid, word, coordinate);

                if(availableDirection!=null) {

                    fillGridOnDirection(availableDirection, grid, coordinate, word);
                    break;
                }
            }
        }

        finalGridFill(grid);

        //displayGrid(grid);

        return grid;
    }

    //Below method fills the grid with '*' to highlight empty cells if needed
    private void initialGridFill(char[][] grid){

        logger.info("Invoking initialGridFill method to initialize the grid with * ");

        for(int i=0;i<grid.length;i++){

            for(int j=0;j<grid.length;j++){

                grid[i][j]='*';

            }
        }
    }

    private void finalGridFill(char[][] grid){

        logger.info("Invoking finalGridFill method to fill the empty cells in the  grid with alphabets");

        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for(int i=0;i<grid.length;i++){

            for(int j=0;j<grid.length;j++){

                Random random = new Random();

                int index = random.nextInt(str.length());

                if(grid[i][j]=='*')

                    grid[i][j]=str.charAt(index);
            }
        }
    }

    private void fillCoordinates(int gridSize,List<Coordinate> coordinates){

        logger.info("Invoking fillCoordinates method to generate all possible coordinates");

        for(int i=0;i<gridSize;i++) {

            for (int j = 0; j < gridSize; j++) {

                Coordinate coordinate= new Coordinate(i, j);

                coordinates.add(coordinate);
            }
        }
    }

//    Method to display the grid on the console if needed
    private void displayGrid(char[][] grid){

        for (char[] chars : grid) {

            for (int j = 0; j < grid.length; j++) {

                System.out.print(chars[j] + " ");

            }

            System.out.println();
        }
    }

    private void fillGridOnDirection(Direction availableDirection,char[][] grid,Coordinate coordinate, String word) {

        logger.info("Invoking fillGridOnDirection method to fill the grid in the valid direction provided by the getDirection method");

        int row = coordinate.row;

        int col = coordinate.col;

        switch (availableDirection) {

            case UP:
                for (char c : word.toCharArray()) {
                    grid[row--][col] = c;
                }
                break;

            case DOWN:
                for (char c : word.toCharArray()) {
                    grid[row++][col] = c;
                }
                break;

            case LEFT:
                for (char c : word.toCharArray()) {
                    grid[row][col--] = c;
                }
                break;

            case RIGHT:
                for (char c : word.toCharArray()) {
                    grid[row][col++] = c;
                }
                break;

            case DOWN_LEFT:
                for (char c : word.toCharArray()) {
                    grid[row++][col--] = c;
                }
                break;

            case UP_LEFT:
                for (char c : word.toCharArray()) {
                    grid[row--][col--] = c;
                }
                break;

            case UP_RIGHT:
                for (char c : word.toCharArray()) {
                    grid[row--][col++] = c;
                }
                break;

            case DOWN_RIGHT:
                for (char c : word.toCharArray()) {
                    grid[row++][col++] = c;
                }
                break;

        }
    }

    private Direction getDirection(char[][] contents, String word, Coordinate coordinate) {

        logger.info("Invoking the getDirection method to determine a valid direction for adding the word to the grid");

        List<Direction> directions = Arrays.asList(Direction.values());

        Collections.shuffle(directions);

        int gridSize = contents[0].length;

        int wordLength = word.length();

        for (Direction direction : directions) {

            boolean validDirection = true;

            switch (direction) {

                case UP:
                    if (coordinate.row < wordLength) {
                        validDirection = false;
                    } else {
                        for (int i = 0; i < wordLength; i++) {
                            char letter = contents[coordinate.row - i][coordinate.col];
                            if (letter != '*' && letter != word.charAt(i)) {
                                validDirection = false;
                                break;
                            }
                        }
                    }
                    break;

                case DOWN:
                    if (coordinate.row + wordLength > gridSize) {
                        validDirection = false;
                    } else {
                        for (int i = 0; i < wordLength; i++) {
                            char letter = contents[coordinate.row + i][coordinate.col];
                            if (letter != '*' && letter != word.charAt(i)) {
                                validDirection = false;
                                break;
                            }
                        }
                    }
                    break;

                case LEFT:
                    if (coordinate.col < wordLength) {
                        validDirection = false;
                    } else {
                        for (int i = 0; i < wordLength; i++) {
                            char letter = contents[coordinate.row][coordinate.col - i];
                            if (letter != '*' && letter != word.charAt(i)) {
                                validDirection = false;
                                break;
                            }
                        }
                    }
                    break;

                case RIGHT:
                    if (coordinate.col + wordLength > gridSize) {
                        validDirection = false;
                    } else {
                        for (int i = 0; i < wordLength; i++) {
                            char letter = contents[coordinate.row][coordinate.col + i];
                            if (letter != '*' && letter != word.charAt(i)) {
                                validDirection = false;
                                break;
                            }
                        }
                    }
                    break;

                case DOWN_RIGHT:
                    if (coordinate.row + wordLength > gridSize || coordinate.col + wordLength > gridSize) {
                        validDirection = false;
                    } else {
                        for (int i = 0; i < wordLength; i++) {
                            char letter = contents[coordinate.row + i][coordinate.col + i];
                            if (letter != '*' && letter != word.charAt(i)) {
                                validDirection = false;
                                break;
                            }
                        }
                    }
                    break;

                case DOWN_LEFT:
                    if (coordinate.row + wordLength > gridSize || coordinate.col < wordLength) {
                        validDirection = false;
                    } else {
                        for (int i = 0; i < wordLength; i++) {
                            char letter = contents[coordinate.row + i][coordinate.col - i];
                            if (letter != '*' && letter != word.charAt(i)) {
                                validDirection = false;
                                break;
                            }
                        }
                    }
                    break;

                case UP_LEFT:
                    if (coordinate.row < wordLength || coordinate.col < wordLength) {
                        validDirection = false;
                    } else {
                        for (int i = 0; i < wordLength; i++) {
                            char letter = contents[coordinate.row - i][coordinate.col - i];
                            if (letter != '*' && letter != word.charAt(i)) {
                                validDirection = false;
                                break;
                            }
                        }
                    }
                    break;

                case UP_RIGHT:
                    if (coordinate.row < wordLength || coordinate.col + wordLength > gridSize) {
                        validDirection = false;
                    } else {
                        for (int i = 0; i < wordLength; i++) {
                            char letter = contents[coordinate.row - i][coordinate.col + i];
                            if (letter != '*' && letter != word.charAt(i)) {
                                validDirection = false;
                                break;
                            }
                        }
                    }
                    break;

            }

            if (validDirection) {
                return direction;
            }
        }

        return null;
    }

}
