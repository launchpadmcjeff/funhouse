package jbosswildfly.model;

import jbosswildfly.model.Job;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
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

	@Deployment
	public static WebArchive createDeployment() {
		WebArchive archive = ShrinkWrap
				.create(WebArchive.class)
				.addClass(Job.class)
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		archive.toString(true);
		return archive;
	}

	@Test
	public void should_be_deployed() {
		Assert.assertNotNull(job);
	}
}
