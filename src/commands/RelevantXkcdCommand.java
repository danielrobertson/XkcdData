package commands;

import beans.Xkcd;
import data.XkcdDao;
import search.RelevanceCalculator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Searches all Xkcd comics for the most relevant to a search term
 */
public class RelevantXkcdCommand {

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
            System.out.println(x.getTitle() + " confidence - " + confidence);
            confidenceMap.put(x, confidence);
        }

        // xkcd with the highest confidence is our winner
        Xkcd mostRelevant = Collections.max(confidenceMap.entrySet(), (x1, x2) -> x1.getValue().compareTo(x2.getValue())).getKey();
        return mostRelevant;
    }

    // testing only - TODO remove main after creating the API
    // compile from top level directory with javac -cp "src/" src/commands/RelevantXkcdCommand.java
    // run from top level directory with java -cp src commands.RelevantXkcdCommand
    public static void main(String[] args) {
        System.out.println("Most relevant is \n" + getRelevantXkcd("beach"));
    }

}
