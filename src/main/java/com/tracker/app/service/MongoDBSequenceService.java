package com.tracker.app.service;

public interface MongoDBSequenceService {
	
	public long generateSequence(String sequenceName);

}
