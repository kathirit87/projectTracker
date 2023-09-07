package com.tracker.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tracker.app.exception.TrackerException;
import com.tracker.app.model.TaskDetails;
import com.tracker.app.model.TeamMember;
import com.tracker.app.service.TeamManagementService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
public class TeamManagementController {

	@Autowired
	private TeamManagementService teamMgmtService;

	@PostMapping("/v1/manager/add-member")
	public ResponseEntity<String> addTeamMember(@Valid @RequestBody TeamMember teamMember) {
		ObjectWriter ow = new ObjectMapper().setSerializationInclusion(Include.NON_NULL).writer().withDefaultPrettyPrinter();
		try {
			return new ResponseEntity<>(ow.writeValueAsString(teamMgmtService.saveTeamMember(teamMember)), HttpStatus.CREATED);
		} catch (TrackerException | JsonProcessingException e) {
			String errmsg= null;
			if("DATE_EXCEP".equalsIgnoreCase(e.getMessage())) {
				errmsg = "Project end date should be greater than project start date.";
			}
			return new ResponseEntity<>(errmsg, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/v1/manager/list")
	public ResponseEntity<List<TeamMember>> fetchAllTeamMembers() {

		return new ResponseEntity<>(teamMgmtService.fetchAllTeamMembers(), HttpStatus.OK);
	}

	@PostMapping("/v1/manager/assign-task")
	public ResponseEntity<String> assignTask(@Valid @RequestBody TaskDetails taskDetails) {
		ObjectWriter ow = new ObjectMapper().setSerializationInclusion(Include.NON_NULL).writer().withDefaultPrettyPrinter();
		try {
			return new ResponseEntity<>(ow.writeValueAsString(teamMgmtService.assignTask(taskDetails)), HttpStatus.CREATED);
		} catch (TrackerException | JsonProcessingException e) {
			if("DATE_EXCEP".equalsIgnoreCase(e.getMessage())) {
				String errmsg = "Task end date should be greater than task end date OR "
						+ "Task end date should be less than or equal to project end date.";
				return new ResponseEntity<>(errmsg, HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/v1/manager/update")
	public ResponseEntity<String> updateAllocationPercent(@RequestBody TeamMember member,
			@PathVariable(required = false) Double allocationPercentage) {
		ObjectWriter ow = new ObjectMapper().setSerializationInclusion(Include.NON_NULL).writer().withDefaultPrettyPrinter();
		try {
			return new ResponseEntity<>(ow.writeValueAsString(teamMgmtService.updateAllocationPercent(member, allocationPercentage)),
					HttpStatus.CREATED);
		} catch (TrackerException | JsonProcessingException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@CrossOrigin
	@GetMapping("/v1/member/list/{memberId}")
	public ResponseEntity<String> fetchTaskDetails(@PathVariable Long memberId) {
		ObjectWriter ow = new ObjectMapper().setSerializationInclusion(Include.NON_NULL).writer().withDefaultPrettyPrinter();
		try {
			return new ResponseEntity<>(ow.writeValueAsString(teamMgmtService.fetchTaskDetails(memberId)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
