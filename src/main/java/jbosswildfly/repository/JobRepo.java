package jbosswildfly.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import jbosswildfly.model.Job;

@Stateless
public class JobRepo {

	@PersistenceContext
	private EntityManager em;

	public Job saveJob(Job p) {
		return em.merge(p);
	}

	public List<Job> listAll() {
		return em.createQuery("select j from Job j", Job.class).getResultList();
	}

	public void create(Job entity) {
		em.persist(entity);
	}

	public Job deleteById(Long id) {
		Job entity = em.find(Job.class, id);
		if (entity != null) {
			em.remove(entity);
		}
		return entity;
	}

	public Job update(Job entity) {
		entity = em.merge(entity);
		return entity;
	}

	public Job findById(Long id) {
		TypedQuery<Job> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT j FROM Job j WHERE j.id = :entityId ORDER BY j.id",
						Job.class);
		findByIdQuery.setParameter("entityId", id);
		Job entity;
		try {
			entity = findByIdQuery.getSingleResult();
		} catch (NoResultException nre) {
			entity = null;
		}
		return entity;

	}

	public JobRepo() {
		// TODO Auto-generated constructor stub
	}
}
