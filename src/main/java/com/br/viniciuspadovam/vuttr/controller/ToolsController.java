package com.br.viniciuspadovam.vuttr.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.viniciuspadovam.vuttr.domain.tools.RegisterToolData;
import com.br.viniciuspadovam.vuttr.domain.tools.Tools;
import com.br.viniciuspadovam.vuttr.domain.tools.ToolsRespository;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/api/tools")
public class ToolsController {
	
	@Autowired
	private ToolsRespository repository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Operation(summary="Get all tools or filter by it's id - paginated.")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Page<Tools>> list(@RequestParam(required = false) String tag) {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("title").ascending());
		
		Query query = new Query().with(pageable);
		if(tag != null) {
			query.addCriteria(Criteria.where("tags").in(tag));
		}
		
		List<Tools> toolsList = mongoTemplate.find(query, Tools.class);
		Page<Tools> toolsPage = PageableExecutionUtils.getPage(toolsList, pageable, () -> mongoTemplate.count(Query.of(query).limit(-1).skip(0), Tools.class));
		
		return ResponseEntity.ok(toolsPage);
	}
	
	@Operation(summary = "Insert a new tool.")
	@PostMapping
	@Transactional
	public ResponseEntity<Tools> insertTool(@RequestBody RegisterToolData data, UriComponentsBuilder uriBuilder) {
		System.out.println(data);
		
		Tools tool = new Tools(data);
		
		repository.save(tool);
		
		URI uri = uriBuilder.path("/api/tools/{id}").buildAndExpand(tool.getId()).toUri();
		
		return ResponseEntity.created(uri).body(tool);
	}
	
	@Operation(summary = "Deletes a tool by it's id.")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
