package com.br.viniciuspadovam.vuttr.controller;

import java.net.URI;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import com.br.viniciuspadovam.vuttr.domain.tools.RequestToolData;
import com.br.viniciuspadovam.vuttr.domain.tools.ResponseToolData;
import com.br.viniciuspadovam.vuttr.domain.tools.Tools;
import com.br.viniciuspadovam.vuttr.service.ToolsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/tools")
public class ToolsController {
	
	@Autowired
	ToolsService service;
	
	@Operation(summary="List all tools or filter by tag field - paginated.")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Page<ResponseToolData>> list(@RequestParam(required = false) String tag) {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("title").ascending());
		
		return ResponseEntity.ok(service.getTools(pageable, tag));
	}
	
	@Operation(summary = "Insert a new tool if it do not previously exist.")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "201", description = "Tool created.", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = Tools.class)) }),
	  @ApiResponse(responseCode = "400", description = "Tool already exist.", 
	  	content = @Content) 
	})
	@PostMapping
	@Transactional
	public ResponseEntity<Tools> insertTool(@RequestBody @Valid RequestToolData data, UriComponentsBuilder uriBuilder) throws BadRequestException {
		Tools tool = new Tools(data);
		
		service.saveTool(tool);
		
		URI uri = uriBuilder.path("/api/tools/{id}").buildAndExpand(tool.getId()).toUri(); 
		
		return ResponseEntity.created(uri).body(tool);
	}
	
	@Operation(summary = "Do a logical deletion on a tool by it's id.")
	@ApiResponse(responseCode = "204", description = "Tool deleted.")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		service.logicDeleteTool(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
