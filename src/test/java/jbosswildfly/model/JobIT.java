package jbosswildfly.model;

import java.math.BigDecimal;

import jbosswildfly.model.Job;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

@RunWith(Arquillian.class)
public class JobIT {

	private static final String WEBAPP_SRC = "src/main/webapp/io/entities";

	@Inject
	private Job job;

	@PersistenceContext
	EntityManager em;

	@Deployment
	public static WebArchive createDeployment() {
		WebArchive archive = ShrinkWrap
				.create(WebArchive.class)
				.addClass(Job.class)
				.addAsWebInfResource("META-INF/persistence.xml",
						"classes/META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource(
						new StringAsset("<faces-config version=\"2.0\"/>"),
						"faces-config.xml");
		archive.toString(true);
		return archive;
	}

	@Test
	public void should_be_deployed() {
		Assert.assertNotNull(job);
		Assert.assertNotNull(em);
	}

	@Inject
	UserTransaction utx;

	@Test
	public void testPersistJobs() {
		Job a = new Job();
		a.setDescription("Job Description");
		a.setSalary(BigDecimal.valueOf(99.09));
		a.setTitle("Extra");
		try {
			utx.begin();
			em.persist(a);
			utx.commit();
		} catch (SecurityException | IllegalStateException
				| NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			try {
				utx.rollback();
			} catch (IllegalStateException | SecurityException
					| SystemException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Test
	public void testPersistJobs2() {
		for (int i = 0; i < 8; i++) {
			Job a = Fuzr.makeJob();
			try {
				utx.begin();
				em.persist(a);
				utx.commit();
			} catch (SecurityException | IllegalStateException
					| NotSupportedException | SystemException
					| RollbackException | HeuristicMixedException
					| HeuristicRollbackException e) {
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException
						| SystemException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public static class Fuzr {
		private static String[] descriptions = { "this", "that", "the other" };
		private static String[] titles = { "Director", "Grip", "Extra" };
		private static BigDecimal[] salaries = { new BigDecimal("314000"),
				new BigDecimal("99800"), new BigDecimal("243932") };

		public static Job makeJob() {
			Job ret = new Job();
			ret.setDescription(randDescription());
			ret.setTitle(randTitle());
			ret.setSalary(randSalary());
			return ret;
		}

		public static BigDecimal randSalary() {
			return salaries[(int) (Math.random() * salaries.length)];
		}

		public static String randTitle() {
			return titles[(int) (Math.random() * titles.length)];
		}

		public static String randDescription() {
			return descriptions[(int) (Math.random() * descriptions.length)];
		}
	}
}
