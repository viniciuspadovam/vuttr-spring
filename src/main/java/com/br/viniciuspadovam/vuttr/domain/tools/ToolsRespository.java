package com.br.viniciuspadovam.vuttr.domain.tools;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToolsRespository extends MongoRepository<Tools, String> {

	Boolean existsByTitleAndActiveTrue(String tool);
}
