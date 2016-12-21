package com.rest.service;

import java.util.List;

import org.bson.Document;

public interface DocumentModelService {

	List<Document> findAll();
	
	List<Document> findAllByModel(String ModelName);
	
	Document findOne(String documentID);
	
	boolean delete(String documentID);
	
	Document save(Document doc);
	
	boolean update(String documentID, Document doc);
}

