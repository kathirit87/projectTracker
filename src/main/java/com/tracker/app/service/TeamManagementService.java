package com.tracker.app.service;

import java.util.List;

import com.tracker.app.exception.TrackerException;
import com.tracker.app.model.TaskDetails;
import com.tracker.app.model.TeamMember;
import com.tracker.app.model.TeamMemberTaskDetails;

public interface TeamManagementService {
	
	TeamMember saveTeamMember(TeamMember teamMember) throws TrackerException;

	List<TeamMember> fetchAllTeamMembers();

	TaskDetails assignTask(TaskDetails taskDetails) throws TrackerException;

	TeamMember updateAllocationPercent(TeamMember member, Double allocationPercentage) throws TrackerException;

	List<TeamMemberTaskDetails> fetchTaskDetails(Long memberId) throws TrackerException;

}
