package jbosswildfly.rest;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import jbosswildfly.model.Vehicle;

@Stateless
@Path("/vehicles")
public class VehicleEndpoint implements SessionSynchronization {
	@PersistenceContext(unitName = "jbosswildfly-persistence-unit")
	private EntityManager em;

	@POST
	@Consumes("application/json")
	public Response create(Vehicle entity) {
		em.persist(entity);
		return Response.created(
				UriBuilder.fromResource(VehicleEndpoint.class)
						.path(String.valueOf(entity.getId())).build()).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Long id) {
		Vehicle entity = em.find(Vehicle.class, id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		em.remove(entity);
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Long id) {
		TypedQuery<Vehicle> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT v FROM Vehicle v WHERE v.id = :entityId ORDER BY v.id",
						Vehicle.class);
		findByIdQuery.setParameter("entityId", id);
		Vehicle entity;
		try {
			entity = findByIdQuery.getSingleResult();
		} catch (NoResultException nre) {
			entity = null;
		}
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(entity).build();
	}

	@GET
	@Produces("application/json")
	public List<Vehicle> listAll(@QueryParam("start") Integer startPosition,
			@QueryParam("max") Integer maxResult) {
		TypedQuery<Vehicle> findAllQuery = em
				.createQuery("SELECT DISTINCT v FROM Vehicle v ORDER BY v.id",
						Vehicle.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		final List<Vehicle> results = findAllQuery.getResultList();
		return results;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") Long id, Vehicle entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(entity.getId())) {
			return Response.status(Status.CONFLICT).entity(entity).build();
		}
		if (em.find(Vehicle.class, id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		try {
			entity = em.merge(entity);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT)
					.entity(e.getEntity()).build();
		}

		return Response.noContent().build();
	}

	@Override
	public void afterBegin() throws EJBException, RemoteException {
		System.out
				.println("Stateful EJB SessionSynchronization: VehicleEndpoint#afterBegin()");
	}

	@Override
	public void beforeCompletion() throws EJBException, RemoteException {
		System.out
				.println("Stateful EJB SessionSynchronization: VehicleEndpoint#beforeCompletion()");
	}

	@Override
	public void afterCompletion(boolean committed) throws EJBException,
			RemoteException {
		System.out
				.println("Stateful EJB SessionSynchronization: VehicleEndpoint#afterCompletion()");
	}
}
