package day5;


import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Day5AOC25Part1 {


    public static boolean idIsFresh (ArrayList<long[]> ranges, long id){

        for (long[] range: ranges){
            if (id >= range[0] && id <= range[1]){
                return true;
            }
        }

        return false;
    }


    public static ArrayList<long[]> mergeRanges (ArrayList<long[]> ranges){

        ArrayList<long[]> result = new ArrayList<>();

        // Sort the ranges by their starting time
        ranges.sort(Comparator.comparingLong(a -> a[0]));

        // Add the first one
        result.add(ranges.getFirst());

        for (int i = 1; i < ranges.size(); i++){
            long[] last = result.getLast();
            long[] curr = ranges.get(i);


            if (curr[0] <= last[1]){
                last[1] = Math.max(last[1], curr[1]);
            }else {
                result.add(curr);
            }
        }

        return result;
    }


    public static void main(String[] args) {

        String inputFileName = "AdventOfCode2025/src/day5/day5input";


        try {

            File inputFile = new File(inputFileName);
            Scanner input = new Scanner(inputFile);



            // Get all the ranges
            ArrayList<String> ranges = new ArrayList<>();
            while (input.hasNext()){
                String range = input.nextLine();
                if (range.isBlank()) break;

                ranges.add(range);
            }

            // Get the ranges into integer arrays where the first value is the start and the second value is the end
            ArrayList<long[]> intRanges = new ArrayList<>();
            for (String range : ranges) {
                String[] splitRange = range.split("-");
                long[] intRange = new long[2];
                intRange[0] = Long.parseLong(splitRange[0]);
                intRange[1] = Long.parseLong(splitRange[1]);

                intRanges.add(intRange);
            }


            // Merge the ranges together so they don't overlap
            ArrayList<long[]> mergedRanges = mergeRanges(intRanges);


            // Get all values to be checked
            ArrayList<Long> IDs = new ArrayList<>();
            while(input.hasNext()){
                IDs.add(input.nextLong());
            }

            int freshIDs = 0;

            // Check each id if it is in the ranges
            for (Long id : IDs) {
                if (idIsFresh(mergedRanges, id)) {
                    freshIDs++;
                }
            }


            System.out.printf("The number of fresh IDs is: %d%n", freshIDs);

        }catch (Exception e){
            System.err.printf("There was an unexpected error: %s%n", e.getMessage());
        }
    }



}
