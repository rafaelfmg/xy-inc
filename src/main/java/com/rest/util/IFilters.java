package com.rest.util;

import org.bson.conversions.Bson;

public interface IFilters {

	Bson filterByID(String documentID);
	
	Bson filterByModel(String collectionName);

}