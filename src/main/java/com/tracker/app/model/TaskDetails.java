package com.tracker.app.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Document
public class TaskDetails {
	
	@JsonIgnore
	@Transient
	public static final String SEQUENCE_NAME="task_sequence";
	
	@Id
	private Long taskId;
	@NotEmpty(message = "Task Name is Mandatory")
	private String taskName;
	@NotNull(message = "Member ID is Mandatory")
	private Long memberId;
	@NotEmpty(message = "Member Name is Mandatory")
	private String memberName;
	@NotEmpty(message = "Deliverables is Mandatory")
	private String deliverables;
	@FutureOrPresent(message = "Task Start Date is Mandatory and should be equal or greater than current date.")
	private Date taskStartDt;
	@FutureOrPresent(message = "Task End Date is Mandatory and should be greater than task end date.")
	private Date taskEndDt;
	
	public TaskDetails() {
		super();
	}
	public TaskDetails(Long taskId, String taskName, Long memberId, String memberName, String deliverables,
			Date taskStartDt, Date taskEndDt) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.memberId = memberId;
		this.memberName = memberName;
		this.deliverables = deliverables;
		this.taskStartDt = taskStartDt;
		this.taskEndDt = taskEndDt;
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
	public String getDeliverables() {
		return deliverables;
	}
	public void setDeliverables(String deliverables) {
		this.deliverables = deliverables;
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
	@Override
	public String toString() {
		return "TaskDetails [taskId=" + taskId + ", taskName=" + taskName + ", memberId=" + memberId + ", memberName="
				+ memberName + ", deliverables=" + deliverables + ", taskStartDt=" + taskStartDt + ", taskEndDt="
				+ taskEndDt + "]";
	}

}
