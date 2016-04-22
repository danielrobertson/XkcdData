package data;

import beans.Xkcd;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Database access object for querying the xkcd data database
 */
public class XkcdDao {

    private static Log log = LogFactory.getLog(XkcdDao.class);

    /**
     * Retrieve all Xkcd comics in the database
     *
     * @return List<Xkcd> all comics
     */
    public List<Xkcd> retrieveAllComics() {
        List<Xkcd> xkcdList = new ArrayList<>();

        // TODO access database to pull all xkcd's.. for now two hard coded xkcd's
        Xkcd a = new Xkcd();
        a.setTitle("Planespotting");
        a.setTranscript("No, a hydroplane doesn't land on water--that's an aquaplane. A hydroplane is a plane that gets electric power from an onboard water reservoir with a tiny dam and turbines. No, a hydroplane doesn't land on water--that's an aquaplane. A hydroplane is a plane that gets electric power from an onboard water reservoir with a tiny dam and turbines.");

        Xkcd b = new Xkcd();
        b.setTitle("Dating Service");
        b.setTranscript("Hi, my name is Randall. I like candlelight dinners and long walks on the beach. When I say long walks on teh beach, I mean LONG walks on the beach. I've met people through these services who claim to like long walks on the beach. But we'll be out there barely an hour before they start in with 'I'm tired' and 'don't you think it's time we head back?' Bring a TENT.");

        xkcdList.add(a);
        xkcdList.add(b);

        return xkcdList;
    }

    /**
     * Inserts Xkcd data into the database
     *
     * @param xkcd Xkcd - to be inserted
     */
    public void insertXkcd(Xkcd xkcd) {
        log.info("Inserting Xkcd into database - comic number " + xkcd.getNum());

        Connection connection;
        try {
            Map<String, String> environment = System.getenv();
            connection = DriverManager.getConnection(environment.get("DB_PATH"), environment.get("DB_USER"), environment.get("DB_PASSWORD"));
            String sql = "insert into xkcd values(?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, xkcd.getNum());
            preparedStatement.setString(2, xkcd.getTitle());
            preparedStatement.setString(3, xkcd.getImg());
            preparedStatement.setString(4, xkcd.getTranscript());
            preparedStatement.setString(5, xkcd.getAlt());
            log.info("Prepared statement - " + preparedStatement.toString());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            log.error("Failed to insert Xkcd - comic number " + xkcd.getNum());
            e.printStackTrace();
        }

    }

}
