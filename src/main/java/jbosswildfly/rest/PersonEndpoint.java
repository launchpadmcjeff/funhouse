package jbosswildfly.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
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

import jbosswildfly.model.Person;
import jbosswildfly.repository.PersonRepo;

@Path("/persons")
public class PersonEndpoint
{
   @Inject
   private PersonRepo repo;

   @POST
   @Consumes("application/json")
   public Response create(Person entity)
   {
      repo.create(entity);
      return Response.created(UriBuilder.fromResource(PersonEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      Person entity = repo.findById(id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      repo.deleteById(id);
      return Response.noContent().build();
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Response findById(@PathParam("id") Long id)
   {
      Person entity = repo.findById(id);
      return Response.ok(entity).build();
   }

   @GET
   @Produces("application/json")
   public List<Person> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
   {
      List<Person> results = repo.listAll();
      return results;
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes("application/json")
   public Response update(@PathParam("id") Long id, Person entity)
   {
      if (entity == null)
      {
         return Response.status(Status.BAD_REQUEST).build();
      }
      if (!id.equals(entity.getId()))
      {
         return Response.status(Status.CONFLICT).entity(entity).build();
      }
      if (repo.findById(id) == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      try
      {
         entity = repo.update(entity);
      }
      catch (OptimisticLockException e)
      {
         return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
      }

      return Response.noContent().build();
   }
}
