package example.insurance.application;
import example.insurance.api.dto.*;
import example.insurance.integration.MockQuoteWorker;
import java.time.Instant;
import org.springframework.stereotype.Service;
@Service
public class TravelQuoteService extends BaseService<QuoteDio, QuoteDto> {
    private final MockQuoteWorker worker;
    public TravelQuoteService(MockQuoteWorker worker) { this.worker = worker; }
    protected QuoteDto processData(QuoteDio input) { return worker.quote("travel", input.getReference()); }
    protected ResponseDto<QuoteDto> generateResult(QuoteDto data, Instant startedAt) { return new ResponseDto<>(data, startedAt); }
}
