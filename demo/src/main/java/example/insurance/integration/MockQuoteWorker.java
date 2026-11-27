package example.insurance.integration;
import example.insurance.api.dto.QuoteDto;
import org.springframework.stereotype.Component;
@Component
public class MockQuoteWorker {
    public QuoteDto quote(String category, String reference) {
        // 虛構結果：不含實際承保、計價或保險公司整合。
        return new QuoteDto(category, "示範資料，未進行實際報價");
    }
}
