package commands;

import beans.Xkcd;
import data.XkcdDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import search.RelevanceCalculator;

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
    public Xkcd getRelevantXkcd(String searchTerm) {
        Map<Xkcd, Double> confidenceMap = new HashMap<>();
        RelevanceCalculator relevanceCalculator = new RelevanceCalculator();

        XkcdDao dao = new XkcdDao();
        List<Xkcd> xkcdList = dao.retrieveAllComics();
        log.debug("xkcd list count - " + xkcdList.size());

        // all transcripts necessary for the inverse document frequency calculation
        List<List<String>> allTranscripts = new ArrayList<>();
        for (Xkcd x : xkcdList) {
            List<String> words = Arrays.asList(x.getTranscript().replaceAll("[\\W&&[^\\s]]", "").split("\\W+"));
            allTranscripts.add(words);
        }
        log.debug("words count - " + allTranscripts.size());

        for (Xkcd x : xkcdList) {
            List<String> words = Arrays.asList(x.getTranscript().replaceAll("[\\W&&[^\\s]]", "").split("\\W+"));
            double confidence = relevanceCalculator.getXkcdRelevance(words, allTranscripts, searchTerm);
            confidenceMap.put(x, confidence);
        }

        log.info("Confidence map - \n" + confidenceMap.toString());

        // xkcd with the highest confidence is our winner
        Xkcd mostRelevant = new Xkcd();
        Map.Entry<Xkcd, Double> maxEntry = null;
        for (Map.Entry<Xkcd, Double> entry : confidenceMap.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }

        if (maxEntry != null) {
            mostRelevant = maxEntry.getKey();
        }
        return mostRelevant;
    }
}
