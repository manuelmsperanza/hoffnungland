package com.hoffnungland.marvelFilmListApp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.hoffnungland.marvelFilmListApp.entity.Film;

@Stateless
@Path("MarvelFilmsService")
public class MarvelFilmsService {
	
	private static final Logger logger = Logger.getLogger("com.hoffnungland.marvelFilmListApp.MarvelFilmsService");
	
	@Context
    private UriInfo context;
	
	@PersistenceContext
    private EntityManager em;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Film> getHtml() {
		
		logger.info("Calling getAllCurrentEvents");

		TypedQuery<Film> filmsQuery = this.em.createQuery("SELECT f FROM Film f ORDER BY f.releaseDate", Film.class);
        List<Film> allFilms = filmsQuery.getResultList();
        if (allFilms == null || allFilms.size() == 0) {
            logger.warning("No film available!");
        }
        return allFilms;
    }
}
