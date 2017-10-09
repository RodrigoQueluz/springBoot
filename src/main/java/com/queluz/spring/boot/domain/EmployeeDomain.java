package com.queluz.spring.boot.domain;

public class EmployeeDomain {

	private Long cpf;
	private Long employer;
	private String jobTitle;
	private String seed;

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Long getEmployer() {
		return employer;
	}

	public void setEmployer(Long employer) {
		this.employer = employer;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getSeed() {
		return seed;
	}

	public void setSeed(String seed) {
		this.seed = seed;
	}

} // class User