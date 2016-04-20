package search;

import java.util.List;

/**
 * Determines the relevance of an Xkcd comic using the term frequency and inverse-document frequency algorithm
 */
public class RelevanceCalculator {


    /**
     * Calculates and returns the term frequency inverse document frequency score
     *
     * @param workingSet - the set of terms for the current xkcd
     * @param allSets all sets of xkcd words
     * @param term the search term
     * @return the tf-idf score of term
     */
    public double getXkcdRelevance(List<String> workingSet, List<List<String>> allSets, String term) {
        return getTermFrequency(workingSet, term) * getInverseDocumentFrequency(allSets, term);

    }

    /**
     * Calculates the term frequency
     *
     * @param workingSet  list of strings
     * @param term String represents a term
     * @return term frequency of term in working set
     */
    private double getTermFrequency(List<String> workingSet, String term) {
        double result = 0;
        for (String word : workingSet) {
            if (term.equalsIgnoreCase(word))
                result++;
        }
        return result / workingSet.size();
    }

    /**
     * Calculates inverse document frequency
     *
     * @param allSets list of list of strings represents the dataset
     * @param term String represents a term
     * @return the inverse term frequency of term
     */
    private double getInverseDocumentFrequency(List<List<String>> allSets, String term) {
        double n = 0;
        for (List<String> doc : allSets) {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }
        return Math.log(allSets.size() / n);
    }

}
