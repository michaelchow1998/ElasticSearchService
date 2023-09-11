package com.michow.elasticsearchservice.controller;

import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.michow.elasticsearchservice.dto.Document;
import com.michow.elasticsearchservice.dto.RequestQueryDto;
import com.michow.elasticsearchservice.service.ESService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/document")
@RequiredArgsConstructor
@Slf4j
public class ESController {

    private ESService esService;

    @Autowired
    public ESController(ESService testService) {
        this.esService = testService;
    }

    @PostMapping()
    public ResponseEntity<IndexResponse> addDocument (@RequestBody Document document) throws IOException {
        var indexResponse = esService.addDocument(document);

        return ResponseEntity.status(HttpStatus.OK).body(indexResponse);
    }

    @GetMapping("/{index}/{id}")
    public ResponseEntity<Document> readDocument (@PathVariable String index, @PathVariable String id) throws IOException {
        var document = esService.getDocumentById(index, id);

        return ResponseEntity.status(HttpStatus.OK).body(document);

    }

    @PostMapping("/search")
    public ResponseEntity<ArrayList<Document>> queryDocument (@RequestBody RequestQueryDto request) throws IOException {
        var searchResponse = esService.queryDocument(request);

        return ResponseEntity.status(HttpStatus.OK).body(searchResponse);

    }
}
