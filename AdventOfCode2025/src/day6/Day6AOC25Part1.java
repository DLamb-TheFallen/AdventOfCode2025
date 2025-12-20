package day6;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day6AOC25Part1 {

    public static void main(String[] args) {



        String inputFileName = "AdventOfCode2025/src/day6/day6input";


        try {
            File inputFile = new File(inputFileName);
            Scanner input = new Scanner(inputFile);


            ArrayList<String[]> lines = new ArrayList<>();
            while (input.hasNextLine()) {
                String line = input.nextLine();
                lines.add(line.split("\\s+"));
            }



            // Last line is the operators line
            String[] operators = lines.getLast();
            lines.removeLast();



            
            // All values saved in an array with the other operands
            int[][] values = new int[lines.getFirst().length][lines.size()];
            for (int i = 0; i < lines.size(); i++){
                for (int j = 0; j < lines.get(i).length; j++){
                    values [j][i] = Integer.parseInt(lines.get(i)[j]);
                }
            }



            long totalSum = 0;
            long lineSum;
            for (int i = 0; i < operators.length; i++){
                String operator = operators[i];
                if (operator.equals("*")){
                    lineSum = 1;

                    for (int j = 0; j < values[i].length; j++){
                        lineSum *= values[i][j];
                    }
                    System.out.printf("The values being operated on: %s%nThe operator: %s%nThe result: %d%n", Arrays.toString(values[i]), operator, lineSum);
                    
                }else { // Operation is addition
                    lineSum = 0;
                    System.out.println(Arrays.toString(values[i]));
                    for (int j = 0; j < values[i].length; j++){
                        lineSum += values[i][j];
                    }
                    System.out.printf("The values being operated on: %s%nThe operator: %s%nThe result: %d%n", Arrays.toString(values[i]), operator, lineSum);
                }
                
                totalSum += lineSum;
            }

            System.out.printf("The total sum of all operations is: %d%n", totalSum);
            
            
            input.close();
        } catch (Exception e){
            System.err.printf("There was an unexpected error: %s%n", e.getMessage());
        }



    }
}
