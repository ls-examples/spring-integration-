package ru.lilitweb.springintegration.repostory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.lilitweb.springintegration.domain.StockQuote;

import java.util.Arrays;

@DataMongoTest
class StockQuoteRepositoryTest {
    @Autowired
    StockQuoteRepository repository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void findByStockCodeAndIdNot() {
        StockQuote shouldBeFirst = mongoTemplate.save(new StockQuote("amd", 5.6f));
        StockQuote shouldExcludeByStockCode = mongoTemplate.save(new StockQuote("intel", 5.6f));
        StockQuote shouldExcludeById = mongoTemplate.save(new StockQuote("amd", 6f));
        StockQuote shouldBeSecond = mongoTemplate.save(new StockQuote("amd", 5.6f));
        Page<StockQuote> page= repository.findByStockCodeAndIdNot("amd", shouldExcludeById.getId(), null);
        assertFalse(page.getContent().
                containsAll(Arrays.asList(shouldExcludeByStockCode, shouldExcludeById)));
        assertTrue(page.getContent().
                containsAll(Arrays.asList(shouldBeFirst, shouldBeSecond)));
        assertEquals(shouldBeFirst.getId(), page.get().findFirst().get().getId());
    }
}
