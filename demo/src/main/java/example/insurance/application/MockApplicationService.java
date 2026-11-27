package example.insurance.application;
import example.insurance.api.dto.ApplicationDio;
import example.insurance.api.dto.ResponseDto;
import example.insurance.integration.MockApplicationWorker;
import java.time.Instant;
import java.util.Map;
import org.springframework.stereotype.Service;
@Service
public class MockApplicationService extends BaseService<MockApplicationService.Input, Map<String, Object>> {
    private final MockApplicationWorker worker;
    public MockApplicationService(MockApplicationWorker worker) { this.worker = worker; }
    public static class Input {
        public final String stage;
        public final ApplicationDio data;
        public Input(String stage, ApplicationDio data) { this.stage = stage; this.data = data; }
    }
    protected Map<String, Object> processData(Input input) {
        return input.stage.equals("reprice") ? worker.reprice(input.data) : worker.submit(input.data);
    }
    protected ResponseDto<Map<String, Object>> generateResult(Map<String, Object> data, Instant startTime) {
        return new ResponseDto<>(data, startTime);
    }
}
