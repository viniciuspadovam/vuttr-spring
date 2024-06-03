package com.br.viniciuspadovam.vuttr.domain.tools;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record RequestToolData(
	@NotNull
	String title,
	@NotNull
	String link,
	String description,
	@NotNull
	List<String> tags
) {}
