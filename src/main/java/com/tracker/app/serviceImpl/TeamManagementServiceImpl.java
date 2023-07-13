package com.tracker.app.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tracker.app.exception.TrackerException;
import com.tracker.app.model.TaskDetails;
import com.tracker.app.model.TaskDetailsRepository;
import com.tracker.app.model.TeamMember;
import com.tracker.app.model.TeamMemberTaskDetails;
import com.tracker.app.repository.TeamMemberRepository;
import com.tracker.app.service.MongoDBSequenceService;
import com.tracker.app.service.TeamManagementService;

@Service
public class TeamManagementServiceImpl implements TeamManagementService {

	@Autowired
	private MongoDBSequenceService seqService;
	
	@Autowired
	private TeamMemberRepository repo;
	
	@Autowired
	private TaskDetailsRepository taskrepo;
	
	@Override
	public TeamMember saveTeamMember(TeamMember teamMember) throws TrackerException{
		if(teamMember.getProjectEndDt().after(teamMember.getProjectStartDt())) {
			teamMember.setMemberId(seqService.generateSequence(TeamMember.SEQUENCE_NAME));
			return repo.save(teamMember);
		}
		throw new TrackerException("DATE_EXCEP");
	}


	@Override
	public List<TeamMember> fetchAllTeamMembers() {
		return repo.findAll(Sort.by("noOfYearExp").descending());
	}

	@Override
	public TaskDetails assignTask(TaskDetails taskDetails) throws TrackerException {
		Optional<TeamMember> savedData = repo.findById(taskDetails.getMemberId());
		TeamMember teamMember = savedData.orElse(null);
		if(teamMember!= null) {
			if(taskDetails.getTaskEndDt().after(taskDetails.getTaskStartDt())
					&& (teamMember.getProjectEndDt().after(taskDetails.getTaskEndDt())
					|| teamMember.getProjectEndDt().equals(taskDetails.getTaskEndDt()))) {
				taskDetails.setTaskId(seqService.generateSequence(TaskDetails.SEQUENCE_NAME));
				return taskrepo.save(taskDetails);
			}
			throw new TrackerException("DATE_EXCEP");
		}
		throw new TrackerException("Member id is not avilable to assign the new task!!");
	}


	@Override
	public TeamMember updateAllocationPercent(TeamMember member, Double allocationPercentage) throws TrackerException {
		
		Optional<TeamMember> savedData = repo.findById(member.getMemberId());
		TeamMember teamMember = savedData.orElse(null);
		if(teamMember!= null) {
			if(new Date().after(teamMember.getProjectEndDt())) {
				teamMember.setAllocPercent(0);
				return repo.save(teamMember);
			} else if(new Date().after(teamMember.getProjectEndDt())
					&& allocationPercentage!= null 
							&& allocationPercentage.doubleValue()>0){
				teamMember.setAllocPercent(allocationPercentage);
				return repo.save(teamMember);
			} else {
				teamMember.setAllocPercent(100);
				return repo.save(teamMember);
			}
		}
		
		throw new TrackerException("Member id is not avilable to update!!");
	}


	@Override
	public List<TeamMemberTaskDetails> fetchTaskDetails(Long memberId) throws TrackerException {
		Optional<TeamMember> savedData = repo.findById(memberId);
		TeamMember teamMember = savedData.orElse(null);
		if(teamMember!= null) {
			
			List<TaskDetails> details = taskrepo.findByMemberId(memberId);
			if(details!= null && !details.isEmpty()) {
				List<TeamMemberTaskDetails> taskDetails = new ArrayList<>();
				for (TaskDetails taskDetails2 : details) {
					TeamMemberTaskDetails memberTaskDetails = new TeamMemberTaskDetails();
					memberTaskDetails.setMemberId(teamMember.getMemberId());
					memberTaskDetails.setMemberName(teamMember.getTeamMemberName());
					memberTaskDetails.setProjectStartDt(teamMember.getProjectStartDt());
					memberTaskDetails.setProjectEndDt(teamMember.getProjectEndDt());
					memberTaskDetails.setAllocPercent(teamMember.getAllocPercent());
					memberTaskDetails.setTaskId(taskDetails2.getTaskId());
					memberTaskDetails.setTaskName(taskDetails2.getTaskName());
					memberTaskDetails.setTaskStartDt(taskDetails2.getTaskStartDt());
					memberTaskDetails.setTaskEndDt(taskDetails2.getTaskEndDt());
					taskDetails.add(memberTaskDetails);
				}
				return taskDetails;
			}
			throw new TrackerException("TaskDetails not avilable for the given Member id :"+memberId);
		}
		throw new TrackerException("Member id is not avilable!!");
	}

}
