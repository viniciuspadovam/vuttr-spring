package com.br.viniciuspadovam.vuttr.domain.tools;

import java.util.List;

public record RegisterToolData(
	String title,
	String link,
	String description,
	List<String> tags
) {}
