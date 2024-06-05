package com.br.viniciuspadovam.vuttr.domain.tools;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "tools")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tools {
	
	public Tools(RequestToolData tool) {
		this.title = tool.title();
		this.link = tool.link();
		this.description = tool.description();
		this.tags = tool.tags();
		this.active = true;
	}

	public Tools(String title, String link, String description, List<String> tags) {
		this.title = title;
		this.link = link;
		this.description = description;
		this.tags = tags;
	}
	
	@Id
	private String id;
	private String title;
	private String link;
	private String description;
	private List<String> tags;
	private Boolean active;
	
}
