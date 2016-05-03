package etl;

import beans.Xkcd;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import data.XkcdDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import java.io.IOException;

/**
 * A class for extracting, transforming, and loading Xkcd data into the database from the Xkcd JSON interface
 */
public class XkcdLoader {

    // logger
    private static Log log = LogFactory.getLog(XkcdLoader.class);
    /**
     * Accepts user input and then loads the specific range of Xkcd comics
     *
     * @param args String[] - expecting two numbers for beginning and end Xkcd number
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java XkcdLoader beginInt endInt");
            return;
        }

        int begin = Integer.parseInt(args[0]);
        int end = Integer.parseInt(args[1]);
        loadComicsNtoM(begin, end);
    }

    /**
     * Fetches Xkcd comics number N to M and writes them to the database
     *
     * @param begin int - Xkcd comic number inclusive to begin pulling and loading
     * @param end int - the last Xkcd number inclusive to pull and load
     */
    public static void loadComicsNtoM(int begin, int end) {
        log.info("Loading comics " + begin + " to " + end);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Gson gson;
        HttpGet httpGet;
        CloseableHttpResponse response;
        HttpEntity entity;

        for (int num = begin; num <= end; num++) {
            String url = "http://xkcd.com/" + num + "/info.0.json";
            log.info("Xkcd url to get - " + url);

            httpGet = new HttpGet(url);
            try {
                response = httpclient.execute(httpGet);
                entity = response.getEntity();
                String xkcdJson = EntityUtils.toString(entity);
                log.info("Json - \n" + xkcdJson);

                gson = new Gson();
                Xkcd xkcd = gson.fromJson(xkcdJson, Xkcd.class);

                XkcdDao dao = new XkcdDao();
                dao.insertXkcd(xkcd);

                response.close();
            }
            catch (IOException e) {
                log.error("Unable to fetch Xkcd comic json ");
                e.printStackTrace();
            }
            catch (JsonSyntaxException j) {
                log.error("Gson could not parse json");
                j.printStackTrace();
            }
        }

    }

}
