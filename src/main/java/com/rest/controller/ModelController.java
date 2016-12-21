package com.rest.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rest.service.DocumentModelService;


@Controller
@ResponseBody
public class ModelController {

	
	@Autowired
	private DocumentModelService documentModelService;

	@RequestMapping(value = "/", method = RequestMethod.GET, params = "id")
	public Document model(@RequestParam("id") String documentID) {
		return documentModelService.findOne(documentID);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Document> models() {
		return documentModelService.findAll();
	}

	@RequestMapping(value = "/newModel", method = RequestMethod.POST)
	public Document modelSave(@RequestBody Document doc) {
		return documentModelService.save(doc);
	}

	@RequestMapping(value = "/{model}", method = RequestMethod.GET)
	public List<Document> getAllByModel(@PathVariable("model") String model) {
		return documentModelService.findAllByModel(model);
	}

	@RequestMapping(value = "/{model}/{documentid}", method = RequestMethod.GET)
	public Document getOne(@PathVariable("model") String model, @PathVariable("documentid") String documentID) {
		return documentModelService.findOne(documentID);
	}

	@RequestMapping(value = "/{model}", method = RequestMethod.POST)
	public Document save(@PathVariable("model") String model, @RequestBody Document doc) {
		return documentModelService.save(doc);
	}

	@RequestMapping(value = "/{model}/{documentid}", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> update(@PathVariable("model") String model,
			@PathVariable("documentid") String documentID, @RequestBody Document doc) {
		boolean update = documentModelService.update(documentID, doc);
		return update ? ResponseEntity.ok(true) : ResponseEntity.badRequest().body(false);
	}

	@RequestMapping(value = "/{model}/{documentid}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("model") String model,
			@PathVariable("documentid") String documentID) {
		boolean delete = documentModelService.delete(documentID);
		return delete ? ResponseEntity.ok(true) : ResponseEntity.badRequest().body(false);
	}
}
	
