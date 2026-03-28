package me.elpomoika.MovieHub.configuration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.elpomoika.MovieHub.domain.entity.Media;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class EntityIndexer implements ApplicationListener<ContextRefreshedEvent> {
    @PersistenceContext
    private EntityManager entityManager;

    private Logger logger = LogManager.getLogger();

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("Started Initializing Indexes");

        SearchSession searchSession = Search.session(entityManager);

        try {
            searchSession.massIndexer()
                    .idFetchSize(150)
                    .batchSizeToLoadObjects(25)
                    .threadsToLoadObjects(10)
                    .startAndWait();
        } catch (InterruptedException e) {
            logger.warn("Failed to load data from database");
            throw new RuntimeException(e);
        }

        logger.info("Completed Indexing");
    }
}