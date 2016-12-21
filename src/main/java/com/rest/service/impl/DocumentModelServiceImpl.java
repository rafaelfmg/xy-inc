package com.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.rest.service.DocumentModelService;
import com.rest.util.IFilters;

@Service
public class DocumentModelServiceImpl implements DocumentModelService{
	
	@Autowired
	private MongoDatabase mongoDatabase;

	@Autowired
	private IFilters filters;
	
		
	private MongoCollection<Document> collection;
	
	public List<Document> findAll() {
		List<Document> documents = new ArrayList<>();
		collection =  mongoDatabase.getCollection("/");
				
		for(Document d : collection.find()){
			documents.add(d);
		}
		
		
		return documents;
	}
	
	public List<Document> findAllByModel(String ModelName) {
		List<Document> documents = new ArrayList<>();

		collection =  mongoDatabase.getCollection("/");
				
		for(Document d : collection.find(filters.filterByModel(ModelName))){
			documents.add(d);
		}
		
		
		return documents;
	}
	
	
	public Document findOne(String documentID) {
		collection = mongoDatabase.getCollection("/");
		return collection.find(filters.filterByID(documentID)).first();
	}

	public boolean delete(String documentID) {
		collection = mongoDatabase.getCollection("/");
		return collection.deleteOne(filters.filterByID(documentID)).wasAcknowledged();
	}

	public Document save(Document doc) {
		
		collection = mongoDatabase.getCollection("/");
		collection.insertOne(doc);
		return doc;
	}

	public boolean update(String documentID, Document doc) {
		collection = mongoDatabase.getCollection("/");
		return collection.replaceOne(filters.filterByID(documentID), doc).wasAcknowledged();
	}

		
}
