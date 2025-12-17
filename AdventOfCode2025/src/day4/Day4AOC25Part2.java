package day4;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// I think I can just do the same thing as the last one but when I remove a box look at the neighbors that are a box and recalculate them (essentially sort of like a bfs)
public class Day4AOC25Part2 {


    public static int accessibleBoxes = 0;


    public static boolean boxIsAccessible(char[][] charArray, int indexI, int indexJ) {

        int boxCount = 0;

        for (int i = indexI - 1; i <= indexI + 1; i++) {
            for (int j = indexJ - 1; j <= indexJ + 1; j++) {

                // Skip the center cell
                if (i == indexI && j == indexJ) {
                    continue;
                }

                // Check bounds
                if (i < 0 || i >= charArray.length ||
                        j < 0 || j >= charArray[i].length) {
                    continue;
                }

                if (isABox(charArray[i][j])) {
                    boxCount++;
                    if (boxCount > 3) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static boolean isABox(char character){
        return character == '@';
    }

    public static void verifyBox(char[][] charArray, int indexI, int indexJ){
        if (isABox(charArray[indexI][indexJ]) && boxIsAccessible(charArray, indexI , indexJ)){
            accessibleBoxes++;
            charArray[indexI][indexJ] = '.';

            // Check if the neighbors are boxes and if they are verify if the box is accessible
            for (int i = indexI - 1; i <= indexI + 1; i++) {
                for (int j = indexJ - 1; j <= indexJ + 1; j++) {

                    // Skip the center cell
                    if (i == indexI && j == indexJ) {
                        continue;
                    }

                    // Check bounds
                    if (i < 0 || i >= charArray.length ||
                            j < 0 || j >= charArray[i].length) {
                        continue;
                    }

                    if (isABox(charArray[i][j])) {
                        verifyBox(charArray, i, j);
                    }
                }
            }
        }
        return;
    }



    public static void main(String[] args) {

        String inputFileName = "AdventOfCode2025/src/day4/day4input";

        try {
            File inputFile = new File(inputFileName);
            Scanner input = new Scanner(inputFile);

            // Turn the input into a 2d array of characters
            ArrayList<String> lines = new ArrayList<>();
            while (input.hasNextLine()){
                lines.add(input.nextLine());
            }

            char[][] charArray = new char[lines.size()][lines.getFirst().length()];

            for (int i = 0; i < lines.size(); i++){
                charArray[i] = lines.get(i).toCharArray();
            }




            // Iterate over the box and find all valid boxes
            for (int i = 0; i < charArray.length; i++){
                for (int j = 0; j <charArray[i].length; j++){
                    verifyBox(charArray, i , j);
                }
            }

            System.out.println(Arrays.deepToString(charArray));
            System.out.printf("The number of accessible boxes is: %d%n", accessibleBoxes);


        } catch (Exception e){
            System.err.printf("An unexpected error has occurred: %s%n", e.getMessage());
        }
    }
}
