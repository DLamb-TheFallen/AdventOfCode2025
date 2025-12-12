
package day2;

import java.io.File ;
import java.util.Scanner ;


/**
 * @author dcl10
 *
 * @version 1.0 2025-12-09 Initial implementation
 *
 * @since 1.0
 */
public class Day2AOC25Part1
    {

    public static boolean isInvalid( long num )
        {

        // Assertion: numbers have atleast 2 digits

        System.out.printf( "Testing if %d is valid.%n", num ) ;
        // Find how many decimal places there are
        long placeFinder = 1 ;
        int digits = 0 ;

        while ( placeFinder <= num )
            {
            placeFinder = placeFinder * 10 ;
            digits++ ;
            }

        if ( digits % 2 != 0 )
            {
            // System.out.println("There are an uneven number of digits");
            return false ; // number can not be split in 2 if there is an uneven number of digits
            }

        long firstHalf = (long) ( num / Math.pow( 10, digits / 2 ) ) ;
        System.out.printf( "The first half of the number is: %d%n",
                           firstHalf ) ;
        long secondHalf = (long) ( num % Math.pow( 10, digits / 2 ) ) ;
        System.out.printf( "The first half of the number is: %d%n",
                           secondHalf ) ;

        // Assertion: first half and second half have the same number of digits

        while ( firstHalf > 0 )
            {
            if ( ( firstHalf % 10 ) != secondHalf % 10 )
                return false ;

            firstHalf /= 10 ;
            secondHalf /= 10 ;
            }

        return true ;

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