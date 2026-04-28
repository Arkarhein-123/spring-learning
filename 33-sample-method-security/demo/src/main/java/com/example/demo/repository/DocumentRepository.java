package com.example.demo.repository;

import com.example.demo.model.Document;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.function.DoubleConsumer;

@Repository
public class DocumentRepository {

    private Map<String , Document> documents = Map.of(
            "abc123",new Document("Arkar"),
            "qwe123",new Document("Arkar"),
            "asd123",new Document("Hein")
    );

    public Document findDocument(String code){
        return documents.get(code);
    }
}
