package ru.lilitweb.springintegration.repostory;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.lilitweb.springintegration.domain.StockQuote;

public interface StockQuoteRepository extends PagingAndSortingRepository<StockQuote, String> {

    @Query("{'$and' : [{'stockCode' : ?0}, {'_id': { $ne: ?1 }}]}")
    Page<StockQuote> findByStockCodeAndIdNot(String stockCode, String Id, Pageable pageable);
}
