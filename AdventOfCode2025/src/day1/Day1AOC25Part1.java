package day1;

import java.io.File ;
import java.util.Scanner ;

/**
 * @author Dylan Lamb Hey don't over complicate the problem like i did
 *
 * @version 1.0 2025-12-08 Initial implementation
 *
 * @since 1.0
 */
public class Day1AOC25Part1
    {

    public static void main( String[] args )
        {

        String inputFileName = "AdventOfCode2025/src/day1/day1input" ;

        try
            {
            // Get the input file
            File inputFile = new File( inputFileName ) ;
            Scanner input = new Scanner( inputFile ) ;


            // Set up starting variables
            int currentPosition = 50 ;
            int pointedAtZero = 0 ;

            // Loop for the input
            while ( input.hasNext() )
                {
                String currentLine = input.next() ;
                // System.out.printf( "Following Direction: %s%n", currentLine ) ;
                // System.out.printf( "Current Position Before Direction: %s%n", currentPosition ) ;


                // Get the direction being moved
                char direction = currentLine.charAt( 0 ) ;

                // Get the number of clicks being moved
                int clicks = Integer.parseInt( currentLine.substring( 1 ) ) ;


                clicks %= 100 ;

                // Clicks is now a number less than 100

                if ( direction == 'R' )
                    {
                    currentPosition = ( 100 - clicks + currentPosition ) % 100 ;
                    }
                else // direction is to the left
                    {
                    currentPosition = ( clicks + currentPosition ) % 100 ;
                    }

                if ( currentPosition == 0 )
                    {
                    pointedAtZero++ ;
                    System.out.printf( "Current Position After Direction: %s%n",
                                       currentPosition ) ;
                    System.out.println() ;
                    }

                }

            System.out.printf( "Zero was pointed at %d times%n",
                               pointedAtZero ) ;
            input.close() ;
            }
        catch ( Exception e )
            {
            System.err.printf( "There was an unexpected error: %s",
                               e.getMessage() ) ;
            return ;
            }

        }

    }

// end class day1Uncomplicated