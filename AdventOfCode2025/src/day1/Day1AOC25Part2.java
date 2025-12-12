package day1;

import java.io.File ;
import java.util.Scanner ;


/**
 * @author Dylan Lamb
 *
 * @version 1.0 2025-12-08 Initial implementation I did this first and realized the question was
 *     asking something much simpler but thought it might be used for a part two and I was right
 *
 * @since 1.0
 */
public class Day1AOC25Part2
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
            int passedZero = 0 ;

            // Loop for the input
            while ( input.hasNext() )
                {
                String currentLine = input.next() ;
                System.out.printf( "Following Direction: %s%n", currentLine ) ;
                System.out.printf( "Current Position Before Direction: %s%n",
                                   currentPosition ) ;


                // Get the direction being moved
                char direction = currentLine.charAt( 0 ) ;

                // Get the number of clicks being moved
                int clicks = Integer.parseInt( currentLine.substring( 1 ) ) ;

                // 100 clicks leaves you on same position but you see a zero
                passedZero += clicks / 100 ;
                clicks %= 100 ;

                // Clicks is now a number less than 100

                if ( direction == 'R' )
                    {

                    // check to see if there will be wrap around
                    if ( currentPosition == 0 )
                        {
                        currentPosition = ( 100 - clicks ) % 100 ;
                        }
                    else if ( clicks >= currentPosition )
                        {
                        passedZero++ ;
                        currentPosition = ( 100 + currentPosition - clicks ) %
                                          100 ;
                        }
                    else
                        {
                        currentPosition -= clicks ;
                        }

                    }
                else // direction is to the left
                    {

                    if ( ( clicks + currentPosition ) >= 100 )
                        {
                        passedZero++ ;
                        currentPosition = ( clicks + currentPosition ) % 100 ;
                        }
                    else
                        {
                        currentPosition += clicks ;
                        }

                    }

                System.out.printf( "Current Position After Direction: %d%n",
                                   currentPosition ) ;
                System.out.printf( "Times zero has been seen: %d%n",
                                   passedZero ) ;
                System.out.println() ;
                }

            System.out.printf( "The number of times zero was passed: %d",
                               passedZero ) ;
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
// end class day1AOC25