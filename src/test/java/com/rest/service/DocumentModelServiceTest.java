package com.rest.service;

import static org.hamcrest.Matchers.emptyCollectionOf;
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
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DocumentModelServiceTest {

	private static final String MODEL_NAME = "produto";
	private static final String DOCUMENT_ID = "5859230190519d4488463f123";

	private Document modelDocument;

	@Mock
	private DocumentModelService documentModelService;

	@Before
	public void setUp() throws Exception {
		String json = "	{\n" + "	    \"_id\" : ObjectId(\"5859230190519d4488463fb8\"),\n"
				+ "             \"model\" : \"produto\",\n" + "	    \"fields\" : [ \n" + "	        {\n"
				+ "	            \"name\" : \"nome\",\n" + "	            \"type\" : \"String\",\n"
				+ "	            \"required\" : true\n" + "	        }, \n" + "	        {\n"
				+ "	            \"name\" : \"preco\",\n" + "	            \"type\" : \"Double\",\n"
				+ "	            \"required\" : true\n" + "	        }, \n" + "	        {\n"
				+ "	            \"name\" : \"codigo\",\n" + "	            \"type\" : \"Integer\",\n"
				+ "	            \"required\" : false\n" + "	        }\n" + "	    ]\n" + "	}";

		modelDocument = Document.parse(json);

	}

	@Test
	public void findAllTest() {
		List<Document> list = new ArrayList<>();
		when(documentModelService.findAll()).thenReturn(list);
		List<Document> listAll = documentModelService.findAll();
		verify(documentModelService).findAll();
		assertThat(listAll, emptyCollectionOf(Document.class));
	}

	@Test
	public void findAllTestError() {
		when(documentModelService.findAll()).thenReturn(null);
		assertNull(documentModelService.findAll());
	}

	@Test
	public void findAllByModelTest() {
		List<Document> list = new ArrayList<>();
		when(documentModelService.findAllByModel(MODEL_NAME)).thenReturn(list);
		List<Document> listModel = documentModelService.findAllByModel(MODEL_NAME);
		verify(documentModelService).findAllByModel(MODEL_NAME);
		assertThat(listModel, emptyCollectionOf(Document.class));
	}

	@Test
	public void findAllByModelTestError() {
		
		when(documentModelService.findAllByModel(MODEL_NAME)).thenReturn(null);
		List<Document> listModel = documentModelService.findAllByModel(MODEL_NAME);
		verify(documentModelService).findAllByModel(MODEL_NAME);
		assertNull(listModel);
	}

	@Test
	public void findOneTest() {
       
		Document doc = new Document();
		when(documentModelService.findOne(DOCUMENT_ID)).thenReturn(doc);
		doc = documentModelService.findOne(DOCUMENT_ID);
		verify(documentModelService).findOne(DOCUMENT_ID);
		assertNotNull(doc);
	}
	
	@Test
	public void findOneTestError() {
       
		Document doc = new Document();
		when(documentModelService.findOne(DOCUMENT_ID)).thenReturn(null);
		doc = documentModelService.findOne(DOCUMENT_ID);
		verify(documentModelService).findOne(DOCUMENT_ID);
		assertNull(doc);
	}

	@Test
	public void deleteTest() {
		
		when(documentModelService.delete(DOCUMENT_ID)).thenReturn(true);
		Boolean delete = documentModelService.delete(DOCUMENT_ID);
		verify(documentModelService).delete(DOCUMENT_ID);
		assertThat(delete, is(true));
	}
	
	@Test
	public void deleteTestError() {
		
		when(documentModelService.delete(DOCUMENT_ID)).thenReturn(false);
		Boolean delete = documentModelService.delete(DOCUMENT_ID);
		verify(documentModelService).delete(DOCUMENT_ID);
		assertThat(delete, is(false));
	}

	@Test
	public void saveTest() {
		Document doc = new Document();
		when(documentModelService.save(doc)).thenReturn(doc);
		doc = documentModelService.save(doc);
		verify(documentModelService).save(doc);
		assertNotNull(doc);
		
	}
	
	@Test
	public void saveTestError() {
		Document doc = new Document();
		when(documentModelService.save(doc)).thenReturn(null);
		doc = documentModelService.save(doc);
		assertNull(doc);
		
	}

	@Test
	public void updateTest() {
		
		when(documentModelService.update(DOCUMENT_ID, modelDocument)).thenReturn(true);
		Boolean delete = documentModelService.update(DOCUMENT_ID, modelDocument);
		verify(documentModelService).update(DOCUMENT_ID, modelDocument);
		assertThat(delete, is(true));
	}
	
	@Test
	public void updateTestError() {
		
		when(documentModelService.update(DOCUMENT_ID, modelDocument)).thenReturn(false);
		Boolean delete = documentModelService.update(DOCUMENT_ID, modelDocument);
		verify(documentModelService).update(DOCUMENT_ID, modelDocument);
		assertThat(delete, is(false));
	}

}
