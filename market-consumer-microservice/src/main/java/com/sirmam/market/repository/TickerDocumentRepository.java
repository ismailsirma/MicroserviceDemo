package com.sirmam.market.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sirmam.market.document.TickerDocument;

public interface TickerDocumentRepository extends MongoRepository<TickerDocument, String> {

}
