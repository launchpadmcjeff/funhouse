package jbosswildfly.model;

import javax.persistence.Entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

import java.lang.Override;
import java.math.BigDecimal;

import jbosswildfly.model.Person;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

//import javax.validation.constraints.;

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllJobsWithTitle", query = "SELECT DISTINCT j FROM Job j WHERE j.title = :title ORDER BY j.id"),
		@NamedQuery(name = "findAllJobsWithDescription", query = "SELECT DISTINCT j FROM Job j WHERE j.description = :description ORDER BY j.id"),
		@NamedQuery(name = "findAllJobsWithSalaryRange", query = "SELECT j FROM Job j WHERE j.salary >= :minSalary and j.salary <= :maxSalary ORDER BY j.id")
})
public class Job implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column
	@NotNull(message = "Foo Bar!")
	private String title;

	@Column
	private String description;

	@Column
	private BigDecimal salary;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Job)) {
			return false;
		}
		Job other = (Job) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (title != null && !title.trim().isEmpty())
			result += "title: " + title;
		if (description != null && !description.trim().isEmpty())
			result += ", description: " + description;
		return result;
	}
}