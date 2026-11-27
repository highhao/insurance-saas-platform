package example.insurance.integration;
import example.insurance.api.dto.PlanDto;
import example.insurance.api.dto.QuoteDto;
import java.util.List;
import org.springframework.stereotype.Component;
/** 僅使用虛構條件與固定示範結果，不含真實商品或定價規則。 */
@Component
public class MockQuoteWorker {
    public QuoteDto quote(String category, String reference) {
        if ("car".equals(category)) {
            return new QuoteDto(category, reference, List.of(
                new PlanDto("示範公司 A", "方案 A", 1000),
                new PlanDto("示範公司 B", "方案 B", 1200)));
        }
        return new QuoteDto(category, reference, List.of(
            new PlanDto("示範公司 A", "旅程方案 A", 300),
            new PlanDto("示範公司 B", "旅程方案 B", 450)));
    }
}
