package ru.lilitweb.springintegration.service.stockanalyzer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import ru.lilitweb.springintegration.domain.StockQuote;
import ru.lilitweb.springintegration.repostory.StockQuoteRepository;

import java.util.Optional;

public class StockAnalyzerServiceImpl implements StockAnalyzerService {

    private StockQuoteRepository repository;

    public StockAnalyzerServiceImpl(StockQuoteRepository repository) { ;
        this.repository = repository;
    }

    @Override
    public StockAnalyzeResult analyze(StockQuote quote) {
        Page<StockQuote> page = repository.findByStockCodeAndIdNot(
                quote.getStockCode(),
                quote.getId(),
                PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "createdAt")));
        StockAnalyzeResult res = new StockAnalyzeResult(0f, quote);

        Optional<StockQuote> oldQuote = page.get().findFirst();
        if (!oldQuote.isPresent()) {
            return res;
        }

        res.setPrevious(oldQuote.get());
        float oldPrice = res.getPrevious().getLatestPrice();
        float minDelta = oldPrice * 0.05f;
        float deltaPrice = Math.abs(oldPrice - quote.getLatestPrice());
        res.setDeltaPrice(deltaPrice);
        if (deltaPrice > minDelta) {
            res.setIsImportantDelta(true);
        }

        return res;
    }
}
