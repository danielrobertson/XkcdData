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
    @Path("relevant/{search-term}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRelevantXkcd(@PathParam("search-term") final String searchTerm) {
        Object response = "answer: " + searchTerm;
        return Response.status(200).entity(response).type("text/plain").build(); // todo serialize MediaType.APPLICATION_JSON
    }
}
