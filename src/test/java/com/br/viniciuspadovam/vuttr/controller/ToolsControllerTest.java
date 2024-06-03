package com.br.viniciuspadovam.vuttr.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.br.viniciuspadovam.vuttr.domain.tools.Tools;
import com.br.viniciuspadovam.vuttr.service.ToolsService;

@SpringBootTest
@AutoConfigureMockMvc
public class ToolsControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ToolsService service;

    @Test
    public void insertTool200Test() throws Exception {
        // ARRANGE
        String json = """
            {
                "title": "GitHub",
                "link": "https://github.com",
                "description": "A place to store your code.",
                "tags": [
                    "produtividade"
                ]
            }
        """;

        // ACT
        var res = mvc.perform(
            MockMvcRequestBuilders.post("/api/tools")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // ASSERT
        Assertions.assertEquals(201, res.getStatus());
    }

    @Test
    public void insertTool400InvalidRequestBodyTest() throws Exception {
        // ARRANGE
        String json = "{}";

        // ACT
        var res = mvc.perform(
            MockMvcRequestBuilders.post("/api/tools")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // ASSERT
        Assertions.assertEquals(400, res.getStatus());
        Assertions.assertEquals("Invalid request content.", res.getErrorMessage());
    }

    @Test
    public void insertTool400DataAlreadyExistsTest() throws Exception {
        // ARRANGE
        List<String> tagsMock = new ArrayList<>();
        tagsMock.add("produtividade");
        Tools mock = new Tools("GitHub", "https://github.com", "A place to store your code.", tagsMock);

        when(service.saveTool(mock)).thenThrow(new BadRequestException("This tool already exist."));

        // ACT
        var res = mvc.perform(
            MockMvcRequestBuilders.post("/api/tools")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mock.toString())
        ).andReturn().getResponse();

        // ASSERT
        Assertions.assertEquals(400, res.getStatus());
    }

}
