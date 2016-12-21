package com.rest.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rest.service.DocumentModelService;


@RunWith(MockitoJUnitRunner.class)
public class ModelControllerTest {

	
	private static final String MODEL_NAME = "produto";	
	private static final String DOCUMENT_ID = "5859230190519d4488463f123";

	private Document modelDocument;
	
	@Mock
	private DocumentModelService documentModelService;
	@InjectMocks
	private ModelController modelController = new ModelController();

	@Before
	public void setUp() throws Exception {
		String json = "	{\n"  + "	    \"_id\" : ObjectId(\"5859230190519d4488463fb8\"),\n"
	            + "             \"model\" : \"produto\",\n" + "	    \"fields\" : [ \n" + "	        {\n"
				+ "	            \"name\" : \"nome\",\n" + "	            \"type\" : \"String\",\n"
				+ "	            \"required\" : true\n" + "	        }, \n" + "	        {\n"
				+ "	            \"name\" : \"preco\",\n" + "	            \"type\" : \"Double\",\n"
				+ "	            \"required\" : true\n" + "	        }, \n" + "	        {\n"
				+ "	            \"name\" : \"codigo\",\n" + "	            \"type\" : \"Integer\",\n"
				+ "	            \"required\" : false\n" + "	        }\n" + "	    ]\n" + "	}";
		
		modelDocument = Document.parse(json);
		
		//documentModelService.save(modelDocument);
		
	}
	
	@Test
	public void modelSaveTest() {
		Document doc = new Document();
		when(modelController.modelSave(modelDocument)).thenReturn(doc);
		doc = modelController.modelSave(modelDocument);
		assertNotNull(doc);
	}
	
	@Test
	public void modelSaveTestError() {
		Document doc = new Document();
		when(modelController.modelSave(modelDocument)).thenReturn(doc);
		doc = modelController.modelSave(modelDocument);
		assertNotNull(doc);
	}
	
	@Test
	public void getAllByModelTest() {
		List<Document> list = new ArrayList<>();
		when(modelController.getAllByModel(MODEL_NAME)).thenReturn(list);
		assertNotNull(modelController.getAllByModel(MODEL_NAME));
		list = modelController.getAllByModel(MODEL_NAME);
		assertNotNull(list);
		
	}
	
	@Test
	public void getAllByModelTestError() {
		List<Document> list = new ArrayList<>();
		when(modelController.getAllByModel(MODEL_NAME)).thenReturn(null);
		list = modelController.getAllByModel(MODEL_NAME);
		assertNull(list);
		
	}
		
	@Test
	public void modelTest() {
		Document doc = new Document();
		when(modelController.model(MODEL_NAME)).thenReturn(doc);	
		doc = modelController.model(MODEL_NAME);
		assertNotNull(doc);
		
	}
	
	@Test
	public void modelTestError() {		
		Document doc = new Document();
		when(modelController.model(MODEL_NAME)).thenReturn(null);	
		doc = modelController.model(MODEL_NAME);
		assertNull(doc);	
	}
	
	@Test
	public void modelsTest() {
		List<Document> list = new ArrayList<>();
		when(modelController.models()).thenReturn(list);	
		list = modelController.models();
		assertNotNull(list);
	}
	
	@Test
	public void modelsTestError() {
		List<Document> list = new ArrayList<>();
		when(modelController.models()).thenReturn(null);	
		list = modelController.models();
		assertNull(list);
	}
	
	
	
	@Test
	public void updateTest() {
		Document doc = new Document();
		when(documentModelService.update(DOCUMENT_ID, doc)).thenReturn(true);
		ResponseEntity<Boolean> update = modelController.update(MODEL_NAME, DOCUMENT_ID, doc);
		assertThat(update.getBody(), is(true));
		assertThat(update.getStatusCode(), is(HttpStatus.OK));
		
	}
	
	@Test
	public void updateTestError() {
		Document doc = new Document();
		when(documentModelService.update(DOCUMENT_ID, doc)).thenReturn(false);
		ResponseEntity<Boolean> update = modelController.update(MODEL_NAME, DOCUMENT_ID, doc);
		assertThat(update.getBody(), is(false));
		assertThat(update.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	}
	

	@Test
	public void deleteTest() {
		when(documentModelService.delete(DOCUMENT_ID)).thenReturn(true);
		ResponseEntity<Boolean> delete = modelController.delete(MODEL_NAME, DOCUMENT_ID);
		verify(documentModelService).delete(DOCUMENT_ID);
		assertThat(delete.getBody(), is(true));
		assertThat(delete.getStatusCode(), is(HttpStatus.OK));
		
	}
	
	@Test
	public void deleteTestError() {
		when(documentModelService.delete(DOCUMENT_ID)).thenReturn(false);
		ResponseEntity<Boolean> delete = modelController.delete(MODEL_NAME, DOCUMENT_ID);
		verify(documentModelService).delete(DOCUMENT_ID);
		assertThat(delete.getBody(), is(false));
		assertThat(delete.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	}


}
