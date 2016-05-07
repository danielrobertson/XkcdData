package resources;

import commands.RelevantXkcdCommand;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Restful resources for Xkcd data
 */
@Path("/")
public class XkcdDataResource {
    // logger
    private static Log log = LogFactory.getLog(XkcdDataResource.class);

    /**
     * Finds and returns a relevant Xkcd comic for the given seach term
     *
     * @param searchTerm String - a search term
     * @return Response - serialized Xkcd bean json
     */
    @Path("relevant/query/{query}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRelevantXkcd(@PathParam("query") final String searchTerm) {
        log.debug("Search query - " + searchTerm);
        RelevantXkcdCommand cmd = new RelevantXkcdCommand();
        Object resultingXkcd = cmd.getRelevantXkcd(searchTerm);

        log.debug("Resulting Xkcd - " + resultingXkcd);
        return Response.status(200).entity(resultingXkcd).type(MediaType.APPLICATION_JSON).build();
    }

    /**
     * Loads Xkcd comics into the database in given range, inclusive
     *
     * @param begin Integer - Xkcd number to begin loading, inclusive
     * @param end   Integer - Xkcd number to end loading, inclusive
     * @return Response - Confirmation message
     */
    @Path("load/begin/{begin}/end/{end}")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loadXkcdComicsInRange(@PathParam("begin") final Integer begin, @PathParam("begin") final Integer end) {
        Object response = "Loading comics in range " + begin + " : " + end;
        return Response.status(200).entity(response).type("text/plain").build();
    }
}
