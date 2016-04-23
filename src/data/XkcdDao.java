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

    // log
    private static Log log = LogFactory.getLog(XkcdDao.class);

    /**
     * Retrieve all Xkcd comics in the database
     *
     * @return List<Xkcd> all comics
     */
    public List<Xkcd> retrieveAllComics() {
        List<Xkcd> xkcdList = new ArrayList<>();

        Connection connection;
        try {
            Map<String, String> environment = System.getenv();
            connection = DriverManager.getConnection(environment.get("DB_PATH"), environment.get("DB_USER"), environment.get("DB_PASSWORD"));

            String sql = "select number, title, image_url, transcript, alt from xkcd";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Xkcd xkcd = new Xkcd();
                xkcd.setNum(resultSet.getInt("number"));
                xkcd.setTitle(resultSet.getString("title"));
                xkcd.setImg(resultSet.getString("image_url"));
                xkcd.setTranscript(resultSet.getString("transcript"));
                xkcd.setAlt(resultSet.getString("alt"));

                xkcdList.add(xkcd);
            }
        }
        catch (SQLException e) {
            log.error("Failed to retrieve comics ");
            e.printStackTrace();
        }

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
