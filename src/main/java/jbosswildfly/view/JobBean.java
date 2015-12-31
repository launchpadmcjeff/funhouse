package jbosswildfly.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import jbosswildfly.LoggingInterceptor;
import jbosswildfly.model.Job;
import jbosswildfly.repository.JobRepo;

//@Model
//@Interceptors({ LoggingInterceptor.class })
 @Named
// @SessionScoped
 @ViewScoped
public class JobBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Job job;

	private List<Job> jobs;

	@Inject
	private JobRepo jobRepo;

	@PostConstruct
	private void init() {
		jobs = jobRepo.listAll();
		System.out.println(this);
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public void save() {
		Job saveJob = jobRepo.saveJob(job);
		if (saveJob.getId().equals(job.getId())) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage("Job successfully updated"));
		} else {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage("Job successfully added"));
		}
		job = new Job();
		jobs = jobRepo.listAll();
	}

	public void delete() {
		Job deletedJob = jobRepo.deleteById(job.getId());
		if (deletedJob == null) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage("Job not deleted"));
		} else {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage("Job successfully deleted"));
		}
		job = new Job();
		jobs = jobRepo.listAll();
	}

	public void reset() {
		job = new Job();
		System.out.println("JobBean#reset()");
	}

	public JobBean() {
		// TODO Auto-generated constructor stub
	}

}
