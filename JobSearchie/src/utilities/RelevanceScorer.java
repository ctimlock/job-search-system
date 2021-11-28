package utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class can be used to test for the similarity between two texts, and will provide a score.
 * @author Charlie Timlock
 * @version 1.0
 */
public abstract class RelevanceScorer
{
    /**
     * This method will return a similarity score for two given texts, based off of the Cosine Similarity score.
     * The two strings are checked for the frequency of common words on a word-by-word basis, and a rounded score is returned.
     * See below for more information on Cosine Similarity.
     * <a href="https://en.wikipedia.org/wiki/Cosine_similarity>Cosine Similarity (Wikipedia)</a>
     * @param textA The first text to be compared.
     * @param textB The second text to be compared.
     * @return The similarity rating, from 0-100.
     */
    public static int getCosineScore(String textA, String textB)
    {
        String[] text1Words = textA.split(" ");
        String[] text2Words = textB.split(" ");
        Map<String, Values> wordFreqVector = new HashMap<>();
        List<String> distinctWords = new ArrayList<>();

        for (String text : text1Words)
        {
            String word = text.trim();
            if (!word.isEmpty())
            {
                if (wordFreqVector.containsKey(word))
                {
                    Values vals1 = wordFreqVector.get(word);
                    int freq1 = vals1.val1 + 1;
                    int freq2 = vals1.val2;
                    vals1.updateValues(freq1, freq2);
                    wordFreqVector.put(word, vals1);
                } else
                {
                    Values vals1 = new Values(1, 0);
                    wordFreqVector.put(word, vals1);
                    distinctWords.add(word);
                }
            }
        }

        for (String text : text2Words)
        {
            String word = text.trim();
            if (!word.isEmpty())
            {
                if (wordFreqVector.containsKey(word))
                {
                    Values vals1 = wordFreqVector.get(word);
                    int freq1 = vals1.val1;
                    int freq2 = vals1.val2 + 1;
                    vals1.updateValues(freq1, freq2);
                    wordFreqVector.put(word, vals1);
                } else
                {
                    Values vals1 = new Values(0, 1);
                    wordFreqVector.put(word, vals1);
                    distinctWords.add(word);
                }
            }
        }

        double vectorAB = 0.0000000;
        double vectorA = 0.0000000;
        double vectorB = 0.0000000;
        for (String distinctWord : distinctWords)
        {
            Values vals12 = wordFreqVector.get(distinctWord);
            double freq1 = vals12.val1;
            double freq2 = vals12.val2;
            vectorAB = vectorAB + freq1 * freq2;
            vectorA = vectorA + freq1 * freq1;
            vectorB = vectorB + freq2 * freq2;
        }
        return (int) Math.round((vectorAB)/(Math.sqrt(vectorA)*Math.sqrt(vectorB))*100);
    }

    /**
     * This class holds the frequency scores for each word in the texts.
     */
    private static class Values
    {

        private int val1;
        private int val2;

        private Values(int v1, int v2)
        {
            this.val1 = v1;
            this.val2 = v2;
        }

        public void updateValues(int v1, int v2)
        {
            this.val1 = v1;
            this.val2 = v2;
        }
    }
}
