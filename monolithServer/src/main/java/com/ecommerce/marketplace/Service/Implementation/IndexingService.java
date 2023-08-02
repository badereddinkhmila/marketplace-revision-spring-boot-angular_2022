package com.ecommerce.marketplace.Service.Implementation;

import lombok.RequiredArgsConstructor;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Service
public class IndexingService {
    private final EntityManager em;
    private final Logger logger = LoggerFactory.getLogger(IndexingService.class);

    @Transactional
    public void initiateIndexing() throws InterruptedException {
        logger.info("initiating indexing ...");
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        fullTextEntityManager.createIndexer().startAndWait();
        logger.info("Entities are indexed.");
    }
}
