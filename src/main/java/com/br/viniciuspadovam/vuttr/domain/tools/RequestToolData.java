package com.br.viniciuspadovam.vuttr.domain.tools;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record RequestToolData(
	@NotBlank
	String title,
	@NotBlank
	String link,
	@NotBlank
	String description,
	@NotBlank
	List<String> tags
) {}
