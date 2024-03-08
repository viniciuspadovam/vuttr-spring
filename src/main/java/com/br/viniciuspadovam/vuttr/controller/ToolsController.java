package com.br.viniciuspadovam.vuttr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/tools")
public class ToolsController {
	@GetMapping()
	public ResponseEntity<String> list() {
		return ResponseEntity.ok("Hello World!");
	}
}
