package com.br.viniciuspadovam.vuttr.domain.tools;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "tools")
@Data
@NoArgsConstructor
public class Tools {
	
	public Tools(RequestToolData tool) {
		this.title = tool.title();
		this.link = tool.link();
		this.description = tool.description();
		this.tags = tool.tags();
		this.active = true;
	}
	
	@Id
	private String id;
	private String title;
	private String link;
	private String description;
	private List<String> tags;
	private Boolean active;
	
}
