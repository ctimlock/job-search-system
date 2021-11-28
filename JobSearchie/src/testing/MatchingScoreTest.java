package testing;

import utilities.RelevanceScorer;
import utilities.UserIO;

public class MatchingScoreTest
{
    public static void main(String[] args)
    {

        String textA = "zero match test";

        String textB = "zippy mono turkey";

        UserIO.displayBody("Test A comparison texts:");
        UserIO.displayBody("\"" + textA + "\"");
        UserIO.displayBody("\"" + textB + "\"");

        String Result = Integer.toString(RelevanceScorer.getCosineScore(textA, textB));

        UserIO.displayBody("Test A result: " + Result);

        System.out.println("");


        textA = "partial match test";

        textB = "partial match trombone";

        UserIO.displayBody("Test B comparison texts:");
        UserIO.displayBody("\"" + textA + "\"");
        UserIO.displayBody("\"" + textB + "\"");

        Result = Integer.toString(RelevanceScorer.getCosineScore(textA, textB));

        UserIO.displayBody("Test B result: " + Result);

        System.out.println("");

        textA = "full match test";
        textB = "full match test";

        Result = Integer.toString(RelevanceScorer.getCosineScore(textA, textB));

        UserIO.displayBody("Test C comparison texts:");
        UserIO.displayBody("\"" + textA + "\"");
        UserIO.displayBody("\"" + textB + "\"");

        UserIO.displayBody("Test C result: " + Result);
    }
}
