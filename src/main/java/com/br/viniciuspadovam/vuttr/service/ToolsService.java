package com.br.viniciuspadovam.vuttr.service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import com.br.viniciuspadovam.vuttr.domain.tools.ResponseToolData;
import com.br.viniciuspadovam.vuttr.domain.tools.Tools;
import com.br.viniciuspadovam.vuttr.domain.tools.ToolsRespository;

@Service
public class ToolsService {
	
	@Autowired
	private ToolsRespository repository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public Page<ResponseToolData> getTools(Pageable pageable, String tag) {
		Query query = new Query().with(pageable);
		
		Criteria criteria = Criteria.where("active").is(true);
		if(tag != null) {
			criteria = criteria.and("tags").in(tag);
		}
		query.addCriteria(criteria);
		
		// Find a list of tools based on pageable size of elements.
		List<ResponseToolData> toolsList = mongoTemplate.find(query, Tools.class).stream().map(ResponseToolData::new).toList();
		// Converts the list of tools to a Page Object.
		Page<ResponseToolData> toolsPage = PageableExecutionUtils.getPage(toolsList, pageable, () -> mongoTemplate.count(Query.of(query).limit(-1).skip(0), Tools.class));
		
		return toolsPage;
	}
	
	public Tools saveTool(Tools tool) throws BadRequestException {		
		Boolean exists = repository.existsByTitleAndActiveTrue(tool.getTitle());
		
		if(exists) {
			throw new BadRequestException("This tool already exist.");
		}
		
		return repository.save(tool);
	}
	
	public void logicDeleteTool(String id) {
		Query query = new Query().addCriteria(Criteria.where("_id").is(id));
		
		Update updateDef = new Update().set("active", false);
		
		mongoTemplate.findAndModify(query, updateDef, Tools.class);
	}
	
}
