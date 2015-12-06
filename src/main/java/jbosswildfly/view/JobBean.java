package jbosswildfly.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import jbosswildfly.model.Job;
import jbosswildfly.repository.JobRepo;

@Model
public class JobBean {

	@Inject
	private Job job;

	private List<Job> jobs;

	@Inject
	private JobRepo jobRepo;

	@PostConstruct
	private void init() {
		jobs = jobRepo.listAll();
		System.out.println(job);
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
		jobRepo.saveJob(job);
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
