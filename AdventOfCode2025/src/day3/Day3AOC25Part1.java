package day3;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;


public class Day3AOC25Part1 {


    public static long getJoltage(String bank){
        return 0;
    }


    public static void main(String[] args){

        String inputFileName = "AdventOfCode2025/src/day3/day3input";

        try{
            File inputFile = new File(inputFileName);
            Scanner input = new Scanner(inputFile);

            ArrayList<String> ranks = new ArrayList<>();
            while (input.hasNextLine()){
                ranks.add(input.nextLine());
            }



            long result = 0;
            for (String rank: ranks){
                result += getJoltage(rank);
            }

            System.out.printf("The total output joltage is: %d%n", result);


            input.close();
        }catch (Exception e){
            System.err.printf("Unexpected error occured: %s%n", e.getMessage());
        }
    }
}
