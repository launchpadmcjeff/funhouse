package jbosswildfly.view;

import javax.inject.Inject;

import jbosswildfly.model.Person;
import jbosswildfly.repository.PersonRepo;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class PersonBeanIT {

	@Inject
	private PersonBean personBean;

	@Deployment
	public static WebArchive createDeployment() {
		WebArchive archive = ShrinkWrap
				.create(WebArchive.class, "personBeanIT.war")
				.addClasses(PersonBean.class, PersonRepo.class, Person.class)
				.addAsWebInfResource("META-INF/persistence.xml",
						"classes/META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(archive.toString(true));
		return archive;
	}

	@Test
	public void should_be_deployed() {
		System.out.println(personBean);
		Assert.assertNotNull(personBean);
	}

	@RunAsClient
	@Test
	public void afterDeployment() {
		System.out.println("END OF TEST");
	}
}
