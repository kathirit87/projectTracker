package com.tracker.app.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Document
public class TeamMember {
	
	@JsonIgnore
	@Transient
	public static final String SEQUENCE_NAME="member_sequence";

	@Id
	private Long memberId;
	@NotEmpty(message = "Team Member Name is Mandatory")
	private String teamMemberName;
	@Min(value = 4, message = "No of year of experience should be greater than 4 years!")
	@NotNull(message = "No of year of experience is Mandatory")
	private Integer noOfYearExp;
	@NotEmpty(message = "Profile Description is Mandatory.")
	private String profileDesc;
	@FutureOrPresent(message = "Project Start Date is Mandatory and should be equal or greater than current date.")
	private Date projectStartDt;
	@FutureOrPresent(message = "Project End Date is Mandatory and should be greater than project end date.")
	private Date projectEndDt;
	@NotNull(message = "Allocation percentage is Mandatory.")
	private double allocPercent;
	
	public TeamMember() {
		super();
	}

	public TeamMember(Long memberId, String teamMemberName, Integer noOfYearExp, String profileDesc,
			Date projectStartDt, Date projectEndDt, double allocPercent) {
		super();
		this.memberId = memberId;
		this.teamMemberName = teamMemberName;
		this.noOfYearExp = noOfYearExp;
		this.profileDesc = profileDesc;
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

	public String getTeamMemberName() {
		return teamMemberName;
	}

	public void setTeamMemberName(String teamMemberName) {
		this.teamMemberName = teamMemberName;
	}

	public Integer getNoOfYearExp() {
		return noOfYearExp;
	}

	public void setNoOfYearExp(Integer noOfYearExp) {
		this.noOfYearExp = noOfYearExp;
	}

	public String getProfileDesc() {
		return profileDesc;
	}

	public void setProfileDesc(String profileDesc) {
		this.profileDesc = profileDesc;
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
		return "TeamMember [memberId=" + memberId + ", teamMemberName=" + teamMemberName + ", noOfYearExp="
				+ noOfYearExp + ", profileDesc=" + profileDesc + ", projectStartDt=" + projectStartDt
				+ ", projectEndDt=" + projectEndDt + ", allocPercent=" + allocPercent + "]";
	}
}
