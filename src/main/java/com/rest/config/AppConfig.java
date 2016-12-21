package com.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;


@Configuration
public class AppConfig {

	private static final String MONGODB_URI = "mongodb://127.0.0.1/";

	@Bean
	public MongoClient mongoClient() {
		MongoClientURI uri = new MongoClientURI(MONGODB_URI);
		return new MongoClient(uri);
	}

	@Bean
	@Autowired
	public MongoDatabase mongoDatabase(MongoClient mongoClient) {
		return mongoClient.getDatabase("modelsRest");
	}

}
