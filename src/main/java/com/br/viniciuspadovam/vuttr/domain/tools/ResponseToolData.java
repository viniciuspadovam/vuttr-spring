package com.br.viniciuspadovam.vuttr.domain.tools;

import java.util.List;

public record ResponseToolData(
	String id,
	String title,
	String link,
	String description,
	List<String> tags
) {
	public ResponseToolData(Tools tools) {
		this(tools.getId(), tools.getTitle(), tools.getLink(), tools.getDescription(), tools.getTags());
	}
}
