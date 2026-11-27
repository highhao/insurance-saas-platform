package example.insurance.application;
import example.insurance.api.dto.*;
import example.insurance.integration.MockQuoteWorker;
import java.time.Instant;
import org.springframework.stereotype.Service;
@Service
public class TravelQuoteService extends BaseService<QuoteDio, QuoteDto> {
    private final MockQuoteWorker worker;
    public TravelQuoteService(MockQuoteWorker worker) { this.worker = worker; }
    protected QuoteDto processData(QuoteDio input) {
        if (!"demo-trip".equals(input.getReference())) {
            throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "不符合示範條件");
        }
        return worker.quote("travel", input.getReference());
    }
    protected ResponseDto<QuoteDto> generateResult(QuoteDto data, Instant startedAt) { return new ResponseDto<>(data, startedAt); }
}
