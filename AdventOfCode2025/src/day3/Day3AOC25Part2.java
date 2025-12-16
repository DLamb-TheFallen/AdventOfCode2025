package day3;

import com.sun.source.tree.Tree;

import java.util.*;
import java.io.File;


public class Day3AOC25Part2 {


    final static int DIGITS_TO_BE_FOUND  = 12;

    public static long getJoltage(String bank){

        long maxJoltage = 0;
        HashMap<Integer, TreeSet<Integer>> map = new HashMap<>();

        for (int i = 0; i < bank.length(); i++){
            // Get the digit we are at
            int digit = bank.charAt(i) - '0';


            map.computeIfAbsent(digit,k -> new TreeSet<>()).add(i);
        }



        // Digits are mapped to a tree set of the indexes where that digit is found

        // Look for two highest digits that can be added;
        int lastUsedIndex = -1; // Used to make sure the next digit used is not before a digit used previously
        for (int digitsAdded = 0; digitsAdded < DIGITS_TO_BE_FOUND; digitsAdded++){

            int digitToBeAdded = 0;
            boolean highestDigitFound = false;

            // Find the highest digit to be added
            for (int digit = 9; digit > 0; digit--){
                if (highestDigitFound){
                    highestDigitFound = false;
                    break; // this break takes us out to find the next highest digit
                }


                if (!map.containsKey(digit)) continue;

                // Get the set of indexes where the digit is that are still valid
                TreeSet<Integer> indexes = map.get(digit);

                //Loop to check each index in the set until a valid one is found or all are seen
                int indexCurrentSize = indexes.size();
                for (int i  = 0; i < indexCurrentSize; i++){
                    // Get the index of the earliest occurrence
                    Integer index = indexes.first();

                    // Check to see if the index is too far to the right
                    if (index > (bank.length() - 1) - (DIGITS_TO_BE_FOUND - 1 - digitsAdded)){
                        // If the digit is too far to the right then it could still be used in the future
                        // We want to leave the number there and just go to the next digit;
                        break; // This break takes us to check the next highest digits indexes
                    }

                    // Check to see if the index is too far to the left
                    if (index < lastUsedIndex){
                        // If the digit is too far to the left then it will never be usable in the future so we will remove it
                        indexes.remove(index);
                        if (indexes.isEmpty()) map.remove(digit);
                        // Then we will just want to get to the next index that that vertex has
                        continue;
                    }



                    // If the index is not too far to the right or left, then it will be added
                    digitToBeAdded = digit;
                    lastUsedIndex = index;
                    highestDigitFound = true;

                    // Since it will be added we remove it and break
                    indexes.remove(index);
                    if (indexes.isEmpty()) map.remove(digit);
                    break;
                }



            }




            //Add that digit to the max Voltage in the right place
            maxJoltage += (long) (digitToBeAdded * Math.pow(10, DIGITS_TO_BE_FOUND - digitsAdded - 1));
        }


        return maxJoltage;
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
