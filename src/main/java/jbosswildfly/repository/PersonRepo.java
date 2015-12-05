package jbosswildfly.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import jbosswildfly.model.Person;

@Stateless
public class PersonRepo {

	@PersistenceContext
	private EntityManager em;

	public Person savePerson(Person p) {
		return em.merge(p);
	}

	public List<Person> listAll() {
		return em.createQuery("select p from Person p", Person.class)
				.getResultList();
	}

	public void create(Person entity) {
		em.persist(entity);
	}

	public Person deleteById(Long id) {
		Person entity = em.find(Person.class, id);
		if (entity != null) {
			em.remove(entity);
		}
		return entity;
	}

	public Person update(Person entity) {
		entity = em.merge(entity);
		return entity;
	}

	public Person findById(Long id) {
		TypedQuery<Person> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT p FROM Person p WHERE p.id = :entityId ORDER BY p.id",
						Person.class);
		findByIdQuery.setParameter("entityId", id);
		Person entity;
		try {
			entity = findByIdQuery.getSingleResult();
		} catch (NoResultException nre) {
			entity = null;
		}
		return entity;

	}

	public PersonRepo() {
		// TODO Auto-generated constructor stub
	}
}
