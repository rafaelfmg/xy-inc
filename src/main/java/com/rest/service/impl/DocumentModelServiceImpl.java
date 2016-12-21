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
import com.rest.util.Validation;

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
		validationModel(doc,"/");
		collection = mongoDatabase.getCollection("/");
		collection.insertOne(doc);
		return doc;
	}

	public boolean update(String documentID, Document doc) {
		validationModel(doc,"/");
		collection = mongoDatabase.getCollection("/");
		return collection.replaceOne(filters.filterByID(documentID), doc).wasAcknowledged();
	}
	
	private void validationModel(Document doc, String modelName){
		Document document = findModel(modelName);
		Validation validation = new Validation(document);
		
		validation.validate(doc);
	}
	
	private Document findModel(String collectionName) {
		if ("/".equals(collectionName)) {
			return Document.parse(
					"{\"model\":\"/\",\"fields\":[{\"name\":\"model\",\"type\":\"String\",\"required\":true},{\"name\":\"fields\",\"type\":\"ArrayList\"}]}");
		}

		Document modelo = mongoDatabase.getCollection("/").find(filters.filterByModel(collectionName)).first();
		if (modelo == null) {
			throw new IllegalArgumentException("coleção sem modelo");
		}
		return modelo;
	}

		
}
