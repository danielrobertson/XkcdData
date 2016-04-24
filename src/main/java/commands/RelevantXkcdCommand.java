package main.java.commands;

import main.java.beans.Xkcd;
import main.java.data.XkcdDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import main.java.search.RelevanceCalculator;

import java.util.*;

/**
 * Searches all Xkcd comics for the most relevant to a search term
 */
public class RelevantXkcdCommand {

    // logger
    private static Log log = LogFactory.getLog(RelevantXkcdCommand.class);

    /**
     * Determines and returns the most relevant Xkcd comic for the given search term
     *
     * @param searchTerm String - a search term
     * @return Xkcd bean representing the most relevant Xkcd
     */
    public static Xkcd getRelevantXkcd(String searchTerm) {
        Map<Xkcd, Double> confidenceMap = new HashMap<>();
        RelevanceCalculator relevanceCalculator = new RelevanceCalculator();

        XkcdDao dao = new XkcdDao();
        List<Xkcd> xkcdList = dao.retrieveAllComics();

        // all transcripts necessary for the inverse document frequency calculation
        List<List<String>> allTranscripts = new ArrayList<>();
        for (Xkcd x : xkcdList) {
            List<String> words = Arrays.asList(x.getTranscript().replaceAll("[\\W&&[^\\s]]", "").split("\\W+"));
            allTranscripts.add(words);
        }

        for (Xkcd x : xkcdList) {
            List<String> words = Arrays.asList(x.getTranscript().replaceAll("[\\W&&[^\\s]]", "").split("\\W+"));
            double confidence = relevanceCalculator.getXkcdRelevance(words, allTranscripts, searchTerm);
            log.info("Confidence scores - " + x.getTitle() + " : " + confidence);
            confidenceMap.put(x, confidence);
        }

        // xkcd with the highest confidence is our winner
        Xkcd mostRelevant = Collections.max(confidenceMap.entrySet(), (x1, x2) -> x1.getValue().compareTo(x2.getValue())).getKey();
        return mostRelevant;
    }

    // testing only - TODO remove main after creating the API
    public static void main(String[] args) {
        System.out.println("Most relevant is \n" + getRelevantXkcd("earth"));
    }

}
