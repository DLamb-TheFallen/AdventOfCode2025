package day4;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day4AOC25Part1 {

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

            int accessibleBoxes = 0;


            // Iterate over the box and find all valid boxes
            for (int i = 0; i < charArray.length; i++){
                for (int j = 0; j <charArray[i].length; j++){
                    if (isABox(charArray[i][j]) && boxIsAccessible(charArray, i , j)) accessibleBoxes++;
                }
            }

            System.out.println(Arrays.deepToString(charArray));
            System.out.printf("The number of accessible boxes is: %d%n", accessibleBoxes);


        } catch (Exception e){
            System.err.printf("An unexpected error has occurred: %s%n", e.getMessage());
        }
    }

}
