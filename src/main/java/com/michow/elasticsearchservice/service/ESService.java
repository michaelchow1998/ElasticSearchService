package com.michow.elasticsearchservice.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
import com.michow.elasticsearchservice.dto.Document;
import com.michow.elasticsearchservice.dto.RequestQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ESService {

    private ElasticsearchClient elasticsearchClient;


    @Autowired
    public ESService(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    public IndexResponse addDocument(Document document) throws IOException {
        IndexResponse indexResponse = elasticsearchClient.index(indexRequest ->
                indexRequest
                        .index(document.getIndex())
                        .id(document.getId())
                        .document(document)
        );

        return indexResponse;
    }

    public Document getDocumentById(String index, String id) throws IOException {
        GetResponse<Document> response = elasticsearchClient.get(getRequest ->
                getRequest.index(index).id(id),Document.class
        );
        if (response.found()) {
            return response.source();
        }else {
            return null;
        }
    }

    public ArrayList<Document> queryDocument(RequestQueryDto request) throws IOException {
        ArrayList<Document> results = new ArrayList<>();

        SearchResponse<Document> response = elasticsearchClient.search(s -> s
                        .index(request.getIndex())
                        .query(q -> q
                                .match(t -> t
                                        .field(request.getField())
                                        .query(request.getKeyword())
                                )
                        ),
                Document.class);
        TotalHits total = response.hits().total();
        boolean isExactResult = total.relation() == TotalHitsRelation.Eq;

        List<Hit<Document>> hits = response.hits().hits();
        for (Hit<Document> hit: hits) {
            results.add(hit.source());
        }

        return results;
    }



}
