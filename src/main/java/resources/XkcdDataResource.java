package resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Restful resources for Xkcd data
 */
@Path("/")
public class XkcdDataResource {

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
        Object response = "You searched for " + searchTerm;
        return Response.status(200).entity(response).type("text/plain").build(); // todo serialize MediaType.APPLICATION_JSON
    }

    /**
     * Loads Xkcd comics into the database in given range, inclusive
     * @param begin Integer - Xkcd number to begin loading, inclusive
     * @param end Integer - Xkcd number to end loading, inclusive
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
