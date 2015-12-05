package jbosswildfly.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jbosswildfly.model.Person;
import jbosswildfly.repository.PersonRepo;

@Named
@RequestScoped
public class PersonBean implements Serializable {
	@Inject
	private Person person;

	List<Person> persons;
	
	@Inject
	private PersonRepo personRepo;
	
	@PostConstruct
	private void init() {
		System.out.println("PersonBean#init()");
		persons = personRepo.listAll();
	}
	
	public Person getPerson() {
		System.out.println("PersonBean#getPerson()");
		return person;
	}

	public void setPerson(Person person) {
		System.out.println("PersonBean#setPerson()");
		this.person = person;
	}

	public List<Person> getPersons() {
		System.out.println("PersonBean#getPersons()");
		return persons;
	}
	
	public void setPersons(List<Person> persons) {
		System.out.println("PersonBean#setPersons()");
		this.persons = persons;
	}

	public String ok() {
		System.out.println("PersonBean#ok() " + person.toString());
		personRepo.savePerson(person);
		persons = personRepo.listAll();
		return null;
	}

	public PersonBean() {
		// TODO Auto-generated constructor stub
	}

}
