package day2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// Only working with integers baby, discrete math is awesome
public class Day2AOC25Part2 {


    public static ArrayList<Integer> splitIntoPieces(long num, int divisions){

        // Assertion: the number can be evenly divided into the number of divisions

        ArrayList<Integer> result = new ArrayList<>();


        int slice;
        while (num > 0){
            slice = (int) (num % Math.pow(10, divisions));
            result.add(slice);
            num /= (long) Math.pow(10, divisions);
        }


        return result;
    }

    public static boolean isInvalid(long num){

        // Find how many decimal places there are
        long placeFinder = 1 ;
        int digits = 0 ;

        while ( placeFinder <= num )
        {
            placeFinder = placeFinder * 10 ;
            digits++ ;
        }

        Set<Integer> divisionSet;
        ArrayList<Integer> divisions;
        for (int i = 1; i <= digits / 2; i++){
            if (digits % i != 0) continue; // Can't be made up of divisions if it would not be evenly divided



            // Could be pretty easily sped up, but it is late, and I am tired
            divisions = splitIntoPieces(num, i);
            divisionSet = new HashSet<>(divisions);

            if (divisionSet.size() == 1){
                return true;
            }


        }



        return false;
    }



    public static void main( String[] args )
    {

        String inputFileName = "AdventOfCode2025/src/day2/day2input" ;

        try
        {
            File inputFile = new File( inputFileName ) ;
            Scanner input = new Scanner( inputFile ) ;


            // Get all the ranges
            String[] ranges = input.next().split( "," ) ;


            long invalidIds = 0 ;
            long startingVal ;
            long endingVal ;
            String[] stringVals ;

            // Go through each range
            for ( String range : ranges )
            {
                System.out.printf( "Testing range: %s%n", range ) ;
                stringVals = range.split( "-" ) ;
                startingVal = Long.parseLong( stringVals[ 0 ] ) ;
                endingVal = Long.parseLong( stringVals[ 1 ] ) ;

                // Go through the range itself
                for ( long i = startingVal ; i < endingVal + 1 ; i++ )
                {
                    if ( isInvalid( i ) )
                        invalidIds += i ;
                }

            }

            System.out.printf( "Sum of invalid IDs: %d", invalidIds ) ;


            input.close() ;
        }
        catch ( Exception e )
        {
            System.err.printf( "There was an unexpected error: %s",
                    e.getMessage() ) ;
        }

    }

}
// end class Day2AOC25Part1

