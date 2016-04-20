package data;

import beans.Xkcd;

import java.util.ArrayList;
import java.util.List;

/**
 * Database access object for querying the xkcd data database
 */
public class XkcdDao {

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

}
