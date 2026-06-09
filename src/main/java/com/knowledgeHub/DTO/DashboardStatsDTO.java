package com.knowledgeHub.DTO;

public class DashboardStatsDTO {

    private long courses;
    private long questions;
    private long answers;
    public long getCourses() {
		return courses;
	}
	public void setCourses(long courses) {
		this.courses = courses;
	}
	public long getQuestions() {
		return questions;
	}
	public void setQuestions(long questions) {
		this.questions = questions;
	}
	public long getAnswers() {
		return answers;
	}
	public void setAnswers(long answers) {
		this.answers = answers;
	}
	public long getTechStacks() {
		return techStacks;
	}
	public void setTechStacks(long techStacks) {
		this.techStacks = techStacks;
	}
	private long techStacks;

    // getters & setters
}