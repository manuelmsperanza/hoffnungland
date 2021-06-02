package com.hoffnungland.planetDrivingApp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.hoffnungland.planetDrivingApp.entity.DrivingSchool;

@Stateless
@Path("PlanetDrivingService")
public class PlanetDrivingService {
	private static final Logger logger = Logger.getLogger("com.hoffnungland.planetDrivingApp.PlanetDrivingService");

	@Context
	private UriInfo context;

	@PersistenceContext
	private EntityManager em;

	@Path("drivingSchools")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<DrivingSchool> getDrivingSchools(){
		logger.info("Calling get all driving schools");

		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<DrivingSchool> dvCritQuery = cb.createQuery(DrivingSchool.class);
		Root<DrivingSchool> rootDv = dvCritQuery.from(DrivingSchool.class);
		dvCritQuery.select(rootDv);
		TypedQuery<DrivingSchool> dvQuery = this.em.createQuery(dvCritQuery);

		return dvQuery.getResultList();
	}

	@Path("drivingSchool/{entityId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public DrivingSchool getDrivingSchool(@PathParam("entityId") Long entityId){		
		return this.em.find(DrivingSchool.class, entityId);
	}

	@Path("drivingSchool")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DrivingSchool doDrivingPost(DrivingSchool drivingSchool) {
		this.em.persist(drivingSchool);
		return drivingSchool;
	}

	@Path("drivingSchool")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DrivingSchool doDrivingPut(DrivingSchool drivingSchool) {
		drivingSchool = this.em.merge(drivingSchool);
		return drivingSchool;
	}

	@Path("drivingSchool/{entityId}")
	@DELETE
	public void doDrivingDelete(@PathParam("entityId") Long entityId){		
		DrivingSchool drivingSchool = this.em.find(DrivingSchool.class, entityId);
		this.em.remove(drivingSchool);
	}

}
