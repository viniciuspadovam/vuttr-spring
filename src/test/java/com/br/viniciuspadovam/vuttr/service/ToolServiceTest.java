package com.br.viniciuspadovam.vuttr.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.br.viniciuspadovam.vuttr.domain.tools.Tools;
import com.br.viniciuspadovam.vuttr.domain.tools.ToolsRespository;

@ExtendWith(MockitoExtension.class)
public class ToolServiceTest {

    @InjectMocks
    private ToolsService service;
    
    @Mock
    private ToolsRespository repository;
	
	private Tools dto;

    @Captor
    private ArgumentCaptor<Tools> captor;

    @Test
    void saveTool200Test() throws Exception {
        // ARRANGE
        List<String> tags = new ArrayList<>();
        tags.add("produtividade");
        this.dto = new Tools("665a1583a0bbb", "GitHub", "https://github.com", "", tags, true);
        
        given(repository.existsByTitleAndActiveTrue(dto.getTitle())).willReturn(false);

        // ACT
        service.saveTool(dto);

        // ASSERT
        then(repository).should().save(captor.capture());
        Tools savedTool = captor.getValue();

        Assertions.assertEquals(dto.getId(), savedTool.getId());
        Assertions.assertEquals(dto.getTitle(), savedTool.getTitle());
    }

}
