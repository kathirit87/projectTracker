package com.tracker.app.model;

import java.util.Date;

public class TeamMemberTaskDetails {
	
	private Long memberId;
	private String memberName;
	private Long taskId;
	private String taskName;
	private Date taskStartDt;
	private Date taskEndDt;
	private Date projectStartDt;
	private Date projectEndDt;
	private double allocPercent;
	
	public TeamMemberTaskDetails() {
	}

	public TeamMemberTaskDetails(Long memberId, String memberName, Long taskId, String taskName, Date taskStartDt,
			Date taskEndDt, Date projectStartDt, Date projectEndDt, double allocPercent) {
		this.memberId = memberId;
		this.memberName = memberName;
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskStartDt = taskStartDt;
		this.taskEndDt = taskEndDt;
		this.projectStartDt = projectStartDt;
		this.projectEndDt = projectEndDt;
		this.allocPercent = allocPercent;
	}
	
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Date getTaskStartDt() {
		return taskStartDt;
	}
	public void setTaskStartDt(Date taskStartDt) {
		this.taskStartDt = taskStartDt;
	}
	public Date getTaskEndDt() {
		return taskEndDt;
	}
	public void setTaskEndDt(Date taskEndDt) {
		this.taskEndDt = taskEndDt;
	}
	public Date getProjectStartDt() {
		return projectStartDt;
	}

	public void setProjectStartDt(Date projectStartDt) {
		this.projectStartDt = projectStartDt;
	}

	public Date getProjectEndDt() {
		return projectEndDt;
	}

	public void setProjectEndDt(Date projectEndDt) {
		this.projectEndDt = projectEndDt;
	}
	public double getAllocPercent() {
		return allocPercent;
	}

	public void setAllocPercent(double allocPercent) {
		this.allocPercent = allocPercent;
	}

	@Override
	public String toString() {
		return "TeamMemberTaskDetails [memberId=" + memberId + ", memberName=" + memberName + ", taskId=" + taskId
				+ ", taskName=" + taskName + ", taskStartDt=" + taskStartDt + ", taskEndDt=" + taskEndDt
				+ ", projectStartDt=" + projectStartDt + ", projectEndDt=" + projectEndDt + ", allocPercent="
				+ allocPercent + "]";
	}
	

}
