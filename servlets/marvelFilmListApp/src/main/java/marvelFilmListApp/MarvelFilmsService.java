package marvelFilmListApp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("MarvelFilmsService")
public class MarvelFilmsService {
	
	@Context
    private UriInfo context;
	
	@GET
    @Produces("application/json")
    public String getHtml() {
        return "{ \"test\" : \"test1\"}";
    }
}
