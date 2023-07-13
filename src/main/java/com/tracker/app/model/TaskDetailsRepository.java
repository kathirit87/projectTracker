package com.tracker.app.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskDetailsRepository extends MongoRepository<TaskDetails, Long>, PagingAndSortingRepository<TaskDetails, Long>{

	@Query("{'memberId': ?0}")
	List<TaskDetails> findByMemberId(Long memberId);

}
