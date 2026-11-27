package example.insurance.integration;
import example.insurance.api.dto.ApplicationDio;
import java.util.Map;
import org.springframework.stereotype.Component;
@Component
public class MockApplicationWorker {
    public Map<String, Object> reprice(ApplicationDio input) {
        // 金額只為展示兩個情境，並非保險公司定價或核保規則。
        int sample = input.getScenario().equals("history") ? 1500 : 1000;
        return Map.of("category", input.getCategory(), "plan", input.getPlan(), "sampleAmount", sample, "label", "虛構示範金額");
    }
    public Map<String, Object> submit(ApplicationDio input) {
        return Map.of("status", input.getOutcome().equals("success") ? "模擬完成" : "模擬未完成",
                      "message", "示範結果；未連接保險公司、金流或資料庫");
    }
}
