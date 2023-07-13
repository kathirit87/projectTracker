package com.tracker.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tracker.app.model.TeamMember;

@Repository
public interface TeamMemberRepository extends MongoRepository<TeamMember, Long>, PagingAndSortingRepository<TeamMember, Long> {

}
