package example.insurance.integration;
import example.insurance.api.dto.ApplicationDio;
import example.insurance.domain.ApplicationStatus;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Component;
@Component
public class MockApplicationWorker {
    public Map<String, Object> reprice(ApplicationDio input) {
        // 金額只為展示兩個情境，並非保險公司定價或核保規則。
        int sample = input.getScenario().equals("history") ? 1500 : 1000;
        return Map.of("category", input.getCategory(), "plan", input.getPlan(), "sampleAmount", sample, "label", "虛構示範金額");
    }
    public Map<String, Object> submit(ApplicationDio input) {
        boolean success = input.getOutcome().equals("success");
        return Map.of(
            "status", success ? ApplicationStatus.COMPLETED : ApplicationStatus.NOT_COMPLETED,
            "message", success ? "示範投保已完成" : "示範送件未通過",
            "demoReference", "DEMO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase(),
            "category", input.getCategory(),
            "plan", input.getPlan(),
            "sampleAmount", input.getScenario().equals("history") ? 1500 : 1000
        );
    }
}
